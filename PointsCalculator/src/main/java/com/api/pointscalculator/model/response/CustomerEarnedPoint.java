package com.api.pointscalculator.model.response;

import java.util.Map;

public class CustomerEarnedPoint
{
    private String customerId;
    private Map<String, Integer> rewardDetails;
    private long totalRewards;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getRewardDetails() {
        return rewardDetails;
    }

    public void setRewardDetails(Map<String, Integer> rewardDetails) {
        this.rewardDetails = rewardDetails;
    }

    public long getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }
}
