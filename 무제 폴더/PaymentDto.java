package com.multi.miniproject.member.model.dto;

public class PaymentDto {
    private String PAYMENT_NUM;
    private String ORDER_NUM;
    private double PAYMENT_AMOUNT;
    private String PAYMENT_METHOD;

    // Getters and setters
    public String getPAYMENT_NUM() {
        return PAYMENT_NUM;
    }

    public void setPAYMENT_NUM(String PAYMENT_NUM) {
        this.PAYMENT_NUM = PAYMENT_NUM;
    }

    public String getORDER_NUM() {
        return ORDER_NUM;
    }

    public void setORDER_NUM(String ORDER_NUM) {
        this.ORDER_NUM = ORDER_NUM;
    }

    public double getPAYMENT_AMOUNT() {
        return PAYMENT_AMOUNT;
    }

    public void setPAYMENT_AMOUNT(double PAYMENT_AMOUNT) {
        this.PAYMENT_AMOUNT = PAYMENT_AMOUNT;
    }

    public String getPAYMENT_METHOD() {
        return PAYMENT_METHOD;
    }

    public void setPAYMENT_METHOD(String PAYMENT_METHOD) {
        this.PAYMENT_METHOD = PAYMENT_METHOD;
    }
}
