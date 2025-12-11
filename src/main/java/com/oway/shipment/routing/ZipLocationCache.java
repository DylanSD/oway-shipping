package com.oway.shipment.routing;

import org.springframework.stereotype.Component;

import com.oway.shipment.parsing.ZipCsvReader;
import com.oway.shipment.parsing.ZipRecord;
import com.graphhopper.jsprit.core.problem.Location;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class ZipLocationCache {

    private final ZipCsvReader zipCsvReader;

    // Immutable view exposed publicly
    private Map<String, Location> zipToLocation;

    public ZipLocationCache(ZipCsvReader zipCsvReader) throws Exception {
        this.zipCsvReader = zipCsvReader;
        loadZipIfNeeded();
    }

    public void loadZipIfNeeded() throws Exception {
        Map<String, Location> tmp = new HashMap<>();
        List<ZipRecord> zips = zipCsvReader.readZipData(
                Paths.get("/users/Dylan/Downloads/zips.csv").toFile()
        );

        for (ZipRecord zip : zips) {
            tmp.put(zip.zip(), GeoConverter.fromLatLon(zip.lat(), zip.lng()));
        }

        // Create an immutable map for safety
        this.zipToLocation = Collections.unmodifiableMap(tmp);

        System.out.println("Loaded ZIP → Location cache: " + zipToLocation.size() + " entries");
    }

    public Location getLocation(String zip) {
        return zipToLocation.get(zip);
    }

    public Map<String, Location> getAll() {
        return zipToLocation;
    }

    public Location getRandomLocation() {
        Random random = new Random();
        if (zipToLocation == null || zipToLocation.isEmpty()) {
            throw new IllegalStateException("ZIP location map is empty or not initialized.");
        }

        int index = random.nextInt(zipToLocation.size());
        return zipToLocation.values().stream()
                .skip(index)
                .findFirst()
                .orElseThrow();
    }
}
