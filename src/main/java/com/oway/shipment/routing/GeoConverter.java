package com.oway.shipment.routing;

import com.graphhopper.jsprit.core.problem.Location;

public final class GeoConverter {

    private static final double EARTH_RADIUS = 6371000; // meters

    // Normalization reference latitude – pick center of USA (~39)
    private static final double LAT0 = 39.0;

    public static double toX(double lon) {
        return Math.toRadians(lon) * EARTH_RADIUS * Math.cos(Math.toRadians(LAT0));
    }

    public static double toY(double lat) {
        return Math.toRadians(lat) * EARTH_RADIUS;
    }

    public static Location fromLatLon(double lat, double lon) {
        return Location.newInstance(toX(lon), toY(lat));
    }
}
