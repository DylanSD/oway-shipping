package com.oway.shipment.routing;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.job.Shipment;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.graphhopper.jsprit.core.problem.solution.route.activity.DeliverShipment;
import com.graphhopper.jsprit.core.problem.solution.route.activity.PickupShipment;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TimeWindow;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Solutions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Service
public class RoutingService {

    private final Logger logger = LoggerFactory.getLogger(RoutingService.class);
    private final ZipLocationCache zipLocationCache;

    public RoutingService(ZipLocationCache zipLocationCache) {
        this.zipLocationCache = zipLocationCache;
    }

    public DisplaySolution route(String startZip,
                                          List<com.oway.shipment.model.Shipment> shipments) {

        VehicleRoutingProblem.Builder vrpBuilder =  VehicleRoutingProblem.Builder.newInstance();

        // specify service - which involves one stop
        Service service =  Service.Builder.newInstance("serviceId")
                .setName("myService")
                .setLocation(Location.newInstance(5,7))
                .addSizeDimension(0,5).addSizeDimension(1,20)
                //.addRequiredSkill("electric drill")
                .setTimeWindow(TimeWindow.newInstance(20,35))
                .build();
// specify shipment - which involves two stops
        for (com.oway.shipment.model.Shipment shipment : shipments) {
            Shipment newShipment = Shipment.Builder.newInstance(shipment.getId().toString())
                    .setName(shipment.getId().toString())
                    .setPickupLocation(zipLocationCache.getLocation(shipment.getOriginZip()))
                    .setDeliveryLocation(zipLocationCache.getLocation(shipment.getDestinationZip()))
                    //.addSizeDimension(0,9).addSizeDimension(1,50)
                    //.addRequiredSkill("loading bridge").addRequiredSkill("electric drill")
                    .build();
            vrpBuilder.addJob(newShipment);
        }

        // specify type of both vehicles
        VehicleTypeImpl vehicleType = VehicleTypeImpl.Builder.newInstance("vehicleType")
                //.addCapacityDimension(0,30).addCapacityDimension(1,100)
                .build();

// specify vehicle1 with different start and end locations
        VehicleImpl vehicle1 = VehicleImpl.Builder.newInstance("vehicle1Id")
                .setType(vehicleType)
                .setStartLocation(zipLocationCache.getLocation(startZip))
                .setEndLocation(zipLocationCache.getLocation(startZip))
                //.addSkill("loading bridge").addSkill("electric drill")
                .build();

        //vrpBuilder.addJob(service).addJob(shipment).addVehicle(vehicle1).addVehicle(vehicle2);
        vrpBuilder.addVehicle(vehicle1);

        vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);
        //vrpBuilder.setRoutingCost()
        VehicleRoutingProblem problem =  vrpBuilder.build();

        // define an algorithm out of the box - this creates a large neighborhood search algorithm
        VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);

        // search solutions
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
// get best
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

        List<DisplayRoute> displayRoutes = new ArrayList<>();
        //System.out.println("Best solution: " + bestSolution);
        VehicleRoute vehroute = bestSolution.getRoutes().stream().findFirst().get();
        for (TourActivity activity : vehroute.getTourActivities().getActivities()) {
            if (activity instanceof PickupShipment pickupShipment) {
                System.out.println("Pickup: " + pickupShipment);
                double arrtime = pickupShipment.getArrTime();
                if (pickupShipment.getJob() instanceof Shipment ps) {
                    displayRoutes.add(new DisplayRoute("Pickup", ps.getId(), arrtime));
                }
            }
            if (activity instanceof DeliverShipment deliverShipment) {
                System.out.println("Deliver: " + deliverShipment);
                double arrtime = deliverShipment.getArrTime();
                if (deliverShipment.getJob() instanceof Shipment ds) {
                    displayRoutes.add(new DisplayRoute("Deliver", ds.getId(), arrtime));
                }
            }
        }
        return new DisplaySolution(displayRoutes, bestSolution.getCost());
    }

    public record DisplayRoute(String activity, String id, double arrTime) {

    }

    public record DisplaySolution(List<DisplayRoute> displayRoute, double cost) {

    }
}
