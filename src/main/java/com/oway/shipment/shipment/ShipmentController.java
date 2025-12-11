package com.oway.shipment.shipment;

import com.oway.shipment.model.Shipment;
import com.oway.shipment.utils.PageableUtils;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.oway.shipment.routing.RoutingController.PAGE;
import static com.oway.shipment.routing.RoutingController.SIZE;

@Controller
public class ShipmentController {

    private static final Logger log = LoggerFactory.getLogger(ShipmentController.class);

    public static final String PENDING = "/pending";
    public static final String COMPLETED = "/completed";

    private final ShipmentService shipmentService;
    private final TemplateEngine templateEngine;

    public ShipmentController(ShipmentService shipmentService,
                              TemplateEngine templateEngine) {
        this.shipmentService = shipmentService;
        this.templateEngine = templateEngine;
    }

    // ----------------------------------------
    // GET: Pending Shipments
    // ----------------------------------------
    @GetMapping("/pending")
    public String pending(HttpServletRequest request, Model model) {
        var pageable = PageableUtils.getPageable(request, PAGE, SIZE);
        log.info("Fetching pending shipments page={} size={}", pageable.getPageNumber(), pageable.getPageSize());

        List<Shipment> shipments = shipmentService.listPending(pageable);
        log.debug("Fetched {} pending shipments", shipments.size());

        return setShipmentDetails(PENDING, "Pending Shipments", shipments, model);
    }

    // ----------------------------------------
    // GET: Completed Shipments
    // ----------------------------------------
    @GetMapping("/completed")
    public String completed(HttpServletRequest request, Model model) {
        var pageable = PageableUtils.getPageable(request, PAGE, SIZE);
        log.info("Fetching completed shipments page={} size={}", pageable.getPageNumber(), pageable.getPageSize());

        List<Shipment> shipments = shipmentService.listCompleted(pageable);
        log.debug("Fetched {} completed shipments", shipments.size());

        return setShipmentDetails(COMPLETED, "Completed Shipments", shipments, model);
    }

    // ----------------------------------------
    // GET: Create Shipment Page
    // ----------------------------------------
    @GetMapping("/create-shipment")
    public String createShipment(Model model) {
        log.info("Rendering create shipment page");
        model.addAttribute("currentPath", "/create-shipment");
        return "pages/app";
    }

    // ----------------------------------------
    // POST: Create Shipment
    // ----------------------------------------
    @PostMapping("/shipments")
    public String createShipment(@ModelAttribute Shipment shipment,
                                 HttpServletRequest request,
                                 Model model) {

        log.info("Creating new shipment originZip={} destinationZip={} weight={} pallets={}",
                shipment.getOriginZip(), shipment.getDestinationZip(),
                shipment.getWeightLbs(), shipment.getPalletCount());

        shipmentService.createShipments(List.of(shipment));

        return pending(request, model);
    }

    // ----------------------------------------
    // POST: Complete Shipment (HTMX)
    // ----------------------------------------
    @PostMapping("/shipments/{id}/complete")
    @ResponseBody
    public String complete(@PathVariable UUID id) {

        log.info("Completing shipment id={}", id);

        try {
            Shipment updated = shipmentService.markCompleted(id);
            log.info("Shipment completed id={} status={}", updated.getId(), updated.getStatus());

            StringOutput out = new StringOutput();
            templateEngine.render(
                    "components/shipments/shipment.jte",
                    Map.of("shipment", updated),
                    out
            );

            return out.toString();

        } catch (Exception ex) {
            log.error("Failed to complete shipment id={} error={}", id, ex.getMessage(), ex);
            throw ex;
        }
    }

    // ----------------------------------------
    // Helper for page rendering
    // ----------------------------------------
    public static String setShipmentDetails(String endpoint,
                                            String title,
                                            List<Shipment> shipments,
                                            Model model) {

        log.debug("Rendering shipment page endpoint={} title={} count={}", endpoint, title, shipments.size());

        model.addAttribute("currentPath", endpoint);
        model.addAttribute("shipments", shipments);
        model.addAttribute("shipmentTitle", title);

        return "pages/app";
    }
}
