package com.oway.shipment.parsing;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class ZipCsvReader {

    public List<ZipRecord> readZipData(File csvFile) throws Exception {

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        // Read each row as a List<String>
        MappingIterator<ZipRecord> it = mapper
                .readerFor(ZipRecord.class)
                .with(schema)
                .readValues(csvFile);

        List<ZipRecord> result = new ArrayList<>();

        while (it.hasNext()) {
            ZipRecord row = it.next();
            result.add(row);
        }

        return result;
    }

    private ZipRecord parseRow(List<String> row) {
        return new ZipRecord(
                row.get(0),                         // zip
                row.get(1),                         // zipCodeType
                Boolean.parseBoolean(row.get(2)),   // active
                row.get(3),                         // city
                parseList(row.get(4)),              // acceptableCities
                parseList(row.get(5)),              // unacceptableCities
                row.get(6),                         // state
                row.get(7),                         // county
                row.get(8),                         // timezone
                parseList(row.get(9)),              // areaCodes
                row.get(10),                        // worldRegion
                row.get(11),                        // country
                parseDouble(row.get(12)),           // lat
                parseDouble(row.get(13))            // lng
        );
    }

    /**
     * Converts:
     *   []                         → []
     *   ['631']                    → ["631"]
     *   ['A','B']                  → ["A","B"]
     *   ['Internal Revenue Service'] → ["Internal Revenue Service"]
     */
    private List<String> parseList(String raw) {
        if (raw == null || raw.trim().equals("[]")) {
            return List.of();
        }

        // Remove brackets
        String cleaned = raw.trim();
        if (cleaned.startsWith("["))
            cleaned = cleaned.substring(1);
        if (cleaned.endsWith("]"))
            cleaned = cleaned.substring(0, cleaned.length() - 1);

        // Remove single quotes
        cleaned = cleaned.replace("'", "").trim();

        if (cleaned.isBlank()) {
            return List.of();
        }

        return Arrays.stream(cleaned.split(","))
                .map(String::trim)
                .toList();
    }

    private double parseDouble(String v) {
        try { return Double.parseDouble(v.trim()); }
        catch (Exception e) { return 0.0; }
    }
}