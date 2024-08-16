package com.api.pointscalculator;

import com.api.pointscalculator.facade.PointsCalculatorFacade;
import com.api.pointscalculator.model.request.Customer;
import com.api.pointscalculator.model.response.CustomerEarnedPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
@EnableAsync
@RequestMapping("/api")
public class PointsCalculatorApplication {

	@Autowired
	PointsCalculatorFacade pointsCalculatorFacade;

	public PointsCalculatorApplication(PointsCalculatorFacade pointsCalculatorFacade) {
		this.pointsCalculatorFacade = pointsCalculatorFacade;
	}

	public static void main(String[] args) {
		SpringApplication.run(PointsCalculatorApplication.class, args);
	}

	@GetMapping("/calculatePoints")
	public ResponseEntity<List<CustomerEarnedPoint>> getPoints(@RequestBody List<Customer> customers){
		return ResponseEntity.ok(pointsCalculatorFacade.calculatePoints(customers));
	}
}
