package com.example.layeredarchitecture.dao.custom.impl;


import com.example.layeredarchitecture.dao.SqlUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.DTO.OrderDTO;
import com.example.layeredarchitecture.entity.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    public String generateNewID() throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");*/
        ResultSet rst = SqlUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {
      /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        return stm.executeQuery().next();*/
        ResultSet rst = SqlUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return rst.next();
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean add(Order entity) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, dto.getOrderId());
        stm.setDate(2, Date.valueOf(dto.getOrderDate()));
        stm.setString(3, dto.getCustomerId());
        return stm.executeUpdate()>0;*/
        return SqlUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",entity.getOrderId(),entity.getOrderDate(),entity.getCustomerId());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }


}
