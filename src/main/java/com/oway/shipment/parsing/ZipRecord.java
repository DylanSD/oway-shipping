package com.oway.shipment.parsing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ZipRecord(
     String zip,
     @JsonProperty("zip_code_type")
     String zipCodeType,
     boolean active,
     String city,
     @JsonProperty("acceptable_cities")
     List<String> acceptableCities,
     @JsonProperty("unacceptable_cities")
     List<String> unacceptableCities,
     String state,
     String county,
     String timezone,
     @JsonProperty("area_codes")
     List<String> areaCodes,
     @JsonProperty("world_region")
     String worldRegion,
     String country,
     double lat,
     double lng
) {
}
