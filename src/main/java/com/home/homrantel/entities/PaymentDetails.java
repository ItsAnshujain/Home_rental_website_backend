package com.home.homrantel.entities;

public class PaymentDetails {

    private String status;
    private String paymentId;

    public PaymentDetails() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentDetails(String status, String paymentId) {
        this.status = status;
        this.paymentId = paymentId;
    }

}
