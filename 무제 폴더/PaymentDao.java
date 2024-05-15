package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.member.model.dto.PaymentDto;
import com.multi.miniproject.common.DBConnectionMgr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao {

    private Connection connect() throws Exception {
        // Oracle DB 연결
        DBConnectionMgr dbcp = DBConnectionMgr.getInstance();
        return dbcp.getConnection();
    }

    public boolean insertPayment(PaymentDto payment) {
        String sql = "INSERT INTO payments (PAYMENT_NUM, ORDER_NUM, PAYMENT_AMOUNT, PAYMENT_METHOD) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, payment.getPAYMENT_NUM());
            pstmt.setString(2, payment.getORDER_NUM());
            pstmt.setDouble(3, payment.getPAYMENT_AMOUNT());
            pstmt.setString(4, payment.getPAYMENT_METHOD());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Insert payment failed: " + e.getMessage());
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PaymentDto getPayment(String PAYMENT_NUM) {
        String sql = "SELECT * FROM payments WHERE PAYMENT_NUM = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, PAYMENT_NUM);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PaymentDto payment = new PaymentDto();
                payment.setPAYMENT_NUM(rs.getString("PAYMENT_NUM"));
                payment.setORDER_NUM(rs.getString("ORDER_NUM"));
                payment.setPAYMENT_AMOUNT(rs.getDouble("PAYMENT_AMOUNT"));
                payment.setPAYMENT_METHOD(rs.getString("PAYMENT_METHOD"));
                return payment;
            }
        } catch (SQLException e) {
            System.out.println("Get payment failed: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean cancelPayment(String PAYMENT_NUM) {
        String sql = "DELETE FROM payments WHERE PAYMENT_NUM = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, PAYMENT_NUM);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Cancel payment failed: " + e.getMessage());
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
