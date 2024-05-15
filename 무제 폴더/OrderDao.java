//OrderDao

package com.multi.miniproject.member.model.dao;

import com.multi.miniproject.common.DBConnectionMgr;
import com.multi.miniproject.member.model.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao {

    Connection con = null;
    DBConnectionMgr dbcp;

    public OrderDao() {
        try {
            dbcp = DBConnectionMgr.getInstance();
            con = dbcp.getConnection();
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException("OrderDao connection error");
        }
    } //OrderDao()

    public ProductDto getProductDto(OrderDto orderDto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDto rsDto = null;

        try{
            String sql = "SELECT * FROM PRODUCTS WHERE PRODUCT_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, orderDto.getProductNum());
            rs = ps.executeQuery();
            if(rs.next()){// Order(N):Product(1)
                rsDto = new ProductDto();
                rsDto.setProductNum(rs.getString("PRODUCT_NUM"));
                rsDto.setCarNum(rs.getString("CAR_NUM"));
                rsDto.setProductStatus(rs.getString("PRODUCT_STATUS"));
                rsDto.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                rsDto.setProductAvailable(rs.getInt("PRODUCT_AVAILABLE"));
            }
        } catch(SQLException e) {
            System.out.println("getProductDto시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;
    } //getProductDto() : OrderDto를 파라미터로 넣으면 이것에 대응되는 ProductDto를 리턴해주는 함수

    public MemberDto getMemberDto(OrderDto orderDto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        MemberDto rsDto = null;

        try{
            String sql = "SELECT * FROM MEMBERS WHERE MEMBER_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, orderDto.getMemberNum());
            rsDto = new MemberDto();
            rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
            rs = ps.executeQuery();
            if(rs.next()){// Order(N):Member(1)
                rsDto.setId(rs.getString("ID"));
                rsDto.setPw(rs.getString("PW"));
                rsDto.setName(rs.getString("NAME"));
                rsDto.setEmailID(rs.getString("EMAIL_ID"));
                rsDto.setAdmin(rs.getInt("ADMIN"));
            }
        } catch(SQLException e) {
            System.out.println("getMemberDto시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;
    } //getMemberDto() : OrderDto를 파라미터로 넣으면 이것에 대응되는 MemberDto를 리턴해주는 함수

    public int delete(String orderNum) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM ORDERS WHERE ORDER_NUM = ? AND ORDER_REFUND_REQUEST = 1";
            ps = con.prepareStatement(sql);
            ps.setString(1, orderNum);

            result = ps.executeUpdate();
            if(result > 0) con.commit();
            else con.rollback();

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("delete(Order)시 에러발생");

        } finally {
            dbcp.freeConnection(con, ps);
        }
        return result;
    } // delete(Order)

    public OrderDto selectOne(String orderNum) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDto rsDto = null;

        try {
            String sql = "SELECT * FROM ORDERS WHERE ORDER_NUM = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, orderNum);
            rs = ps.executeQuery();

            if(rs.next()){
                rsDto = new OrderDto();
                rsDto.setOrderNum(rs.getString("ORDER_NUM"));
                rsDto.setMemberNum(rs.getString("MEMBER_NUM"));
                rsDto.setProductNum(rs.getString("PRODUCT_NUM"));
                rsDto.setOrderStatus(rs.getInt("ORDER_STATUS"));
                rsDto.setOrderRefundRequest(rs.getInt("ORDER_REFUND_REQUEST"));
                rsDto.setOrderRefundRequest(rs.getInt("ORDER_REFUND_COMPLETE"));
            }
        } catch(SQLException e) {
            System.out.println("selectOne(orderNum)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }

        return rsDto;

    } //selectOne(Review)


    public ArrayList<OrderDto> selectList(int criteria, String keyword) {
        ArrayList<OrderDto> rsDtoList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "";
            switch(criteria){ //검색조건
                case 0:
                    sql += "SELECT * FROM ORDERS WHERE ORDER_NUM = ?";
                    break;
                case 1:
                    sql += "SELECT * FROM ORDERS WHERE MEMBER_NUM = ?";
                    break;
                case 2:
                    sql += "SELECT * FROM ORDERS WHERE PRODUCT_NUM = ?";
                    break;
                case 3:
                    sql += "SELECT * FROM ORDERS WHERE ORDER_STATUS = ?";
                    break;
                case 4:
                    sql += "SELECT * FROM ORDERS WHERE ORDER_REFUND_REQUEST = ?";
                    break;
                case 5:
                    sql += "SELECT * FROM ORDERS WHERE ORDER_REFUND_COMPLETE = ?";
                    break;
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, keyword); //검색어
            rs = ps.executeQuery();

            while(rs.next()) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderNum(rs.getString("ORDER_NUM"));
                orderDto.setMemberNum(rs.getString("MEMBER_NUM"));
                orderDto.setProductNum(rs.getString("PRODUCT_NUM"));
                orderDto.setOrderStatus(rs.getInt("ORDER_STATUS"));
                orderDto.setOrderRefundRequest(rs.getInt("ORDER_REFUND_REQUEST"));
                orderDto.setOrderRefundComplete(rs.getInt("ORDER_REFUND_COMPLETE"));
                System.out.println(orderDto);

                rsDtoList.add(orderDto);
            }
        } catch(SQLException e) {
            System.out.println("selectList(Order)시 에러발생");
        } finally {
            dbcp.freeConnection(con, ps, rs);
        }
        return rsDtoList;
    } //selectList(Order)
}

