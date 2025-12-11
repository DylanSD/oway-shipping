package com.oway.shipment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oway.shipment.parsing.ZipRecord;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "shipments")
public class Shipment {

    public Shipment(ZipRecord zip) {
        this.originZip = zip.zip();                  // The ZIP city of origin
        this.destinationZip = zip.zip();             // Same until overridden
        this.palletCount = 1;                        // Default for new shipments
        this.weightLbs = 0.0;                        // Default weight
        this.status = Status.PENDING;                // Default enum value
    }

    public enum Status {
        PENDING,
        COMPLETED
    }

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID")
    private UUID id;//Decision

    @Column(name = "origin_zip", nullable = false, length = 20)
    private String originZip;//Decision separate obj

    @Column(name = "destination_zip", nullable = false, length = 20)
    private String destinationZip;//Decision use above

    @Column(name = "pallet_count", nullable = false)
    private int palletCount;//Constraint

    @Column(name = "weight_lbs", nullable = false)
    private double weightLbs;//Constraint

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PENDING;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Shipment() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOriginZip() {
        return originZip;
    }

    public void setOriginZip(String originZip) {
        this.originZip = originZip;
    }

    public String getDestinationZip() {
        return destinationZip;
    }

    public void setDestinationZip(String destinationZip) {
        this.destinationZip = destinationZip;
    }

    public int getPalletCount() {
        return palletCount;
    }

    public void setPalletCount(int palletCount) {
        this.palletCount = palletCount;
    }

    public double getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(double weightLbs) {
        this.weightLbs = weightLbs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shipment shipment = (Shipment) o;
        return palletCount == shipment.palletCount && Double.compare(weightLbs, shipment.weightLbs) == 0 && Objects.equals(id, shipment.id) && Objects.equals(originZip, shipment.originZip) && Objects.equals(destinationZip, shipment.destinationZip) && status == shipment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originZip, destinationZip, palletCount, weightLbs, status);
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", originZip='" + originZip + '\'' +
                ", destinationZip='" + destinationZip + '\'' +
                ", palletCount=" + palletCount +
                ", weightLbs=" + weightLbs +
                ", status=" + status +
                '}';
    }
}
