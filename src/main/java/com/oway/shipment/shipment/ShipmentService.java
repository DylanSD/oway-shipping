package com.oway.shipment.shipment;

import com.oway.shipment.model.Shipment;
import com.oway.shipment.routing.RoutingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ShipmentService {

    private final Logger logger = LoggerFactory.getLogger(ShipmentService.class);
    @Autowired
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> listPending(Pageable pageable) {
        return shipmentRepository.findByStatus(Shipment.Status.PENDING, pageable).stream().toList();
    }

    public List<Shipment> listCompleted(Pageable pageable) {
        return shipmentRepository.findByStatus(Shipment.Status.COMPLETED, pageable).stream().toList();
    }

    public List<Shipment> listAll(Pageable pageable) {
        return shipmentRepository.findAll(pageable).stream().toList();
    }

    public List<Shipment> listShipments(Page<Shipment> page,
                                        Comparator<Shipment> comparator) {
        return page.stream().sorted(comparator).toList();
    }

    public List<Shipment> createShipments(List<Shipment> s) {
        return shipmentRepository.saveAll(s);
    }

    public Shipment markCompleted(UUID id) {
        Shipment s = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        s.setStatus(Shipment.Status.COMPLETED);
        return shipmentRepository.save(s);
    }

}
