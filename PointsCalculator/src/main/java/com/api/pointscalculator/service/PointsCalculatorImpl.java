package com.api.pointscalculator.service;

import com.api.pointscalculator.intface.IPointsCalculator;
import com.api.pointscalculator.model.request.Customer;
import com.api.pointscalculator.model.request.Item;
import com.api.pointscalculator.model.response.CustomerEarnedPoint;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//2 points for every dollar spent over $100
//plus 1 point for every dollar spent between $50 and $100 in each transaction
//
//$120 purchase = 2x$20 + 1x$50 = 90 points

@EnableAsync
@Service
public class PointsCalculatorImpl implements IPointsCalculator {

    public List<CustomerEarnedPoint> calculatePointsForCustomers(List<Customer> customerList) {

        List<CustomerEarnedPoint> customerEarnedPoints = new ArrayList<>();

        for( Customer customer : customerList ){
            CustomerEarnedPoint customerEarnedPoint = new CustomerEarnedPoint();

            List<Item> purchasedItems = customer.getItems();
            Map<String, Integer> rewardDetails = new HashMap<>();
            long totalRewards = 0;
            int earnedPoint = 0;
            for( Item item: purchasedItems) {

                earnedPoint = calculatePoints (item.getItemPrice() );
                String monthYear = extractMonthYear ( item.getPurchaseDate() );
                if( rewardDetails.containsKey(monthYear)) {
                    int prevPoints =  rewardDetails.get(monthYear);
                    rewardDetails.put(monthYear, prevPoints+earnedPoint);
                } else {
                    rewardDetails.put(monthYear, earnedPoint);
                }

                totalRewards += earnedPoint;
            }
            customerEarnedPoint.setCustomerId(customer.getCustomerId());
            customerEarnedPoint.setRewardDetails(rewardDetails);
            customerEarnedPoint.setTotalRewards(totalRewards);
            customerEarnedPoints.add(customerEarnedPoint);
        }


        return customerEarnedPoints;
    }

    private String extractMonthYear(String strPurchasedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime purchasedDate = LocalDateTime.parse(strPurchasedDate, formatter);
        return purchasedDate.getMonth()+""+purchasedDate.getYear();
    }

    private int calculatePoints(String strAmountSpent) {
        if(strAmountSpent != null )
        {
            BigDecimal amountSpent = new BigDecimal(strAmountSpent);
            amountSpent = amountSpent.setScale(1, RoundingMode.CEILING);
            if( amountSpent.compareTo(FIFTY) <= 0 ){
                return 0;
            }
            if( amountSpent.compareTo(FIFTY) > 0 && amountSpent.compareTo(HUNDRED_DOLLAR) <= 0 )
            {
                BigDecimal afterFifty = amountSpent.subtract(FIFTY);
                return afterFifty.intValue();
            } else {
                BigDecimal afterFirstHundred = amountSpent.subtract(HUNDRED_DOLLAR);
                afterFirstHundred = (afterFirstHundred.multiply(TWO_POINTS)).add(FIFTY);
                return afterFirstHundred.intValue();
            }
        }

        return 0;
    }


}
