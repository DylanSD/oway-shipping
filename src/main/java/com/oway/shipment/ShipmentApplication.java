package com.oway.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan
@EntityScan
public class ShipmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentApplication.class, args);
	}

}
