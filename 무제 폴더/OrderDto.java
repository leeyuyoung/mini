package com.multi.miniproject.member.model.dto;

public class OrderDto {
//    CREATE TABLE ORDERS(
//    ORDER_NUM VARCHAR2(100) PRIMARY KEY,
//    MEMBER_NUM VARCHAR2(100) REFERENCES MEMBERS(MEMBER_NUM),
//    PRODUCT_NUM VARCHAR2(100) REFERENCES PRODUCTS(PRODUCT_NUM),
//    ORDER_STATUS NUMBER NOT NULL,
//    ORDER_REFUND_REQUEST NUMBER NOT NULL,
//    ORDER_REFUND_COMPLETE NUMBER NOT NULL
//	);

    private String orderNum;
    private String memberNum;
    private String productNum;
    private int orderStatus;
    private int orderRefundRequest;
    private int orderRefundComplete;

    public OrderDto() {
    }

    public OrderDto(String orderNum, String memberNum, String productNum, int orderStatus, int orderRefundRequest, int orderRefundComplete) {
        this.orderNum = orderNum;
        this.memberNum = memberNum;
        this.productNum = productNum;
        this.orderStatus = orderStatus;
        this.orderRefundRequest = orderRefundRequest;
        this.orderRefundComplete = orderRefundComplete;
    }



    // 생성자에 orderNum 추가
    public OrderDto(String orderNum) {
        this.orderNum = orderNum;
    }

    // setter 메서드
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }


    public String getOrderNum() {
        return orderNum;
    }


    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderRefundRequest() {
        return orderRefundRequest;
    }

    public void setOrderRefundRequest(int orderRefundRequest) {
        this.orderRefundRequest = orderRefundRequest;
    }

    public int getOrderRefundComplete() {
        return orderRefundComplete;
    }

    public void setOrderRefundComplete(int orderRefundComplete) {
        this.orderRefundComplete = orderRefundComplete;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderNum='" + orderNum + '\'' +
                ", memberNum='" + memberNum + '\'' +
                ", productNum='" + productNum + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderRefundRequest=" + orderRefundRequest +
                ", orderRefundComplete=" + orderRefundComplete +
                '}';
    }
}

