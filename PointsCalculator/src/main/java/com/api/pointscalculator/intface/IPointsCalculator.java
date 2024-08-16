package com.api.pointscalculator.intface;

import com.api.pointscalculator.model.request.Customer;
import com.api.pointscalculator.model.response.CustomerEarnedPoint;

import java.math.BigDecimal;
import java.util.List;

public interface IPointsCalculator {
    List<CustomerEarnedPoint> calculatePointsForCustomers(List<Customer> customerList);

    final static BigDecimal FIFTY = new BigDecimal(50.00);
    final static BigDecimal HUNDRED_DOLLAR = new BigDecimal(100.00);
    final static BigDecimal TWO_POINTS = new BigDecimal(2);

}
