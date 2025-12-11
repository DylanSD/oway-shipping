package com.oway.shipment.shipment;

import com.oway.shipment.model.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

    Page<Shipment> findByStatus(Shipment.Status status, Pageable pageable);

}
