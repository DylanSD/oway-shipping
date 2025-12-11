package com.oway.shipment.routing;

import com.oway.shipment.model.Shipment;
import com.oway.shipment.shipment.ShipmentController;
import com.oway.shipment.shipment.ShipmentService;
import com.oway.shipment.utils.PageableUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.oway.shipment.shipment.ShipmentController.PENDING;

@Controller
public class RoutingController {

    private static final Logger log = LoggerFactory.getLogger(RoutingController.class);

    public static final String PAGE = "page";
    public static final String SIZE = "size";

    private final ShipmentService shipmentService;
    private final RoutingService routingService;

    public RoutingController(ShipmentService shipmentService,
                             RoutingService routingService) {
        this.shipmentService = shipmentService;
        this.routingService = routingService;
    }

    @GetMapping("/route")
    public String route(HttpServletRequest request, Model model) {

        var pageable = PageableUtils.getPageable(request, PAGE, SIZE);
        log.info("Starting route generation page={} size={}",
                pageable.getPageNumber(),
                pageable.getPageSize());

        List<Shipment> shipments = shipmentService.listPending(pageable);

        log.debug("Routing {} pending shipments", shipments.size());

        long start = System.currentTimeMillis();
        try {
            var solution = routingService.route("90058", shipments);
            long duration = System.currentTimeMillis() - start;

            log.info("Route generation completed shipments={} cost={} durationMs={}",
                    shipments.size(),
                    solution.cost(),
                    duration);

            model.addAttribute("displaySolution", solution);
            return ShipmentController.setShipmentDetails(
                    PENDING,
                    "Pending Shipments",
                    shipments,
                    model
            );

        } catch (Exception ex) {
            long duration = System.currentTimeMillis() - start;
            log.error("Route generation failed shipments={} durationMs={} error={}",
                    shipments.size(),
                    duration,
                    ex.getMessage(),
                    ex);
            throw ex;
        }
    }
}
