package com.donation;

public abstract class Donation {
    protected String donorName;
    protected double amount;

    public Donation(String donorName, double amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    public abstract void recordDonation();

    public String getDonorName() { return donorName; }
    public double getAmount() { return amount; }
}

