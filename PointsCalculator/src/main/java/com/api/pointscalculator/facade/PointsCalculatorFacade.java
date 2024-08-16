package com.api.pointscalculator.facade;

import com.api.pointscalculator.intface.IPointsCalculator;
import com.api.pointscalculator.model.request.Customer;
import com.api.pointscalculator.model.response.CustomerEarnedPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointsCalculatorFacade {
    IPointsCalculator iPointsCalculator;

    public PointsCalculatorFacade(IPointsCalculator iPointsCalculator) {
        this.iPointsCalculator = iPointsCalculator;
    }

    public List<CustomerEarnedPoint> calculatePoints(List<Customer> customerList){
        return iPointsCalculator.calculatePointsForCustomers(customerList);
    }
}
