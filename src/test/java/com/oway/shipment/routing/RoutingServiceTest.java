package com.oway.shipment.routing;

import com.oway.shipment.model.Shipment;
import com.oway.shipment.parsing.ZipCsvReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class RoutingServiceTest {

    private ZipLocationCache zipLocationCache;
    private RoutingService routingService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        zipLocationCache = new ZipLocationCache(new ZipCsvReader());
        routingService = new RoutingService(zipLocationCache);
    }

    @org.junit.jupiter.api.Test
    void route() {
        List<String> pickupZips = new ArrayList<>();
        pickupZips.add("10001");
        pickupZips.add("90001");
        pickupZips.add("60601");
        List<String> deliverZips = new ArrayList<>();
        deliverZips.add("11201");
        deliverZips.add("92614");
        deliverZips.add("53202");
        List<Shipment> shipments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Shipment s = new Shipment();
            s.setId(UUID.randomUUID());
            s.setOriginZip(pickupZips.get(i));
            s.setDestinationZip(deliverZips.get(i));
            s.setPalletCount(1 + (i % 3));         // 1–3 pallets
            s.setWeightLbs(500 + i * 100);         // 500–1400 lbs
            s.setStatus(Shipment.Status.PENDING);
            shipments.add(s);
        }
        long st = System.currentTimeMillis();
        RoutingService.DisplaySolution solution = routingService.route("90058", shipments);
        long ed = System.currentTimeMillis();
        System.out.println("Time taken: " + (ed - st));
        System.out.println(solution);
    }
}