package com.api.pointscalculator;

import com.api.pointscalculator.facade.PointsCalculatorFacade;
import com.api.pointscalculator.model.request.Customer;
import com.api.pointscalculator.model.request.Item;
import com.api.pointscalculator.model.response.CustomerEarnedPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PointsCalculatorApplicationTests {

	@Mock
	private PointsCalculatorFacade pointsCalculatorFacade;

	@InjectMocks
	private PointsCalculatorApplication pointsCalculatorApplication;

	private final List<Customer> customerList = new ArrayList<>();
	private final List<CustomerEarnedPoint> customerEarnedPoints = new ArrayList<>();

	@BeforeEach
	void setUpCustomer(){
		Customer customer = new Customer();
		customer.setCustomerId("cust10001");
		customer.setFirstName("Smith");
		customer.setLastName("Grame");

		List<Item> items = new ArrayList<>();
		Item item = new Item();
		item.setItemId("item1001");
		item.setItemPrice("120");
		item.setDescription("ItemDesc1");
		item.setQty("3");
		item.setPurchaseDate("2004-05-01 11:20");
		items.add(item);

		item.setItemId("item1002");
		item.setItemPrice("20");
		item.setDescription("ItemDesc2");
		item.setQty("3");
		item.setPurchaseDate("2004-06-01 11:20");
		items.add(item);

		item.setItemId("item1003");
		item.setItemPrice("20");
		item.setDescription("ItemDesc3");
		item.setQty("3");
		item.setPurchaseDate("2004-07-01 11:20");
		items.add(item);

		customer.setItems(items);
		customerList.add(customer);


//---------------------------------------
		customer = new Customer();
		customer.setCustomerId("cust10012");
		customer.setFirstName("Peter");
		customer.setLastName("Kevin");

		items = new ArrayList<>();
		item = new Item();
		item.setItemId("item1011");
		item.setItemPrice("180");
		item.setDescription("ItemDesc2");
		item.setQty("3");
		item.setPurchaseDate("2004-05-01 11:20");
		items.add(item);

		item.setItemId("item1022");
		item.setItemPrice("120");
		item.setDescription("ItemDesc21");
		item.setQty("31");
		item.setPurchaseDate("2004-06-01 11:20");
		items.add(item);
		customer.setItems(items);
		customerList.add(customer);
	}

	@BeforeEach
	void setUpcustomerPoints(){

		CustomerEarnedPoint customerEarnedPoint = new CustomerEarnedPoint();
		customerEarnedPoint.setCustomerId("cust10001");
		Map<String, Integer> rewardDetails = new HashMap<>();
		rewardDetails.put("MAY2024",90);
		customerEarnedPoint.setRewardDetails(rewardDetails);
		customerEarnedPoint.setTotalRewards(90);
		customerEarnedPoints.add(customerEarnedPoint);

		customerEarnedPoint = new CustomerEarnedPoint();
		customerEarnedPoint.setCustomerId("cust10012");
		rewardDetails = new HashMap<>();
		rewardDetails.put("MAY2024",210);
		rewardDetails.put("JUNE2024",90);
		customerEarnedPoint.setRewardDetails(rewardDetails);
		customerEarnedPoint.setTotalRewards(300);

		customerEarnedPoints.add(customerEarnedPoint);
	}

	@Test
	void getPointsTest(){


		Mockito.when(pointsCalculatorFacade.calculatePoints(customerList) ).thenReturn(customerEarnedPoints);

		ResponseEntity<List<CustomerEarnedPoint>> customerEarnedPointsResponse = pointsCalculatorApplication.getPoints(customerList);

		assertThat(customerEarnedPointsResponse.getBody()).isEqualTo(customerEarnedPoints);

	}
}
