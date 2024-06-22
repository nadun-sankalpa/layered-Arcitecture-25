package com.example.layeredarchitecture.dao.custom.impl;


import com.example.layeredarchitecture.dao.SqlUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");*/

       ResultSet rst = SqlUtil.execute("SELECT * FROM Customer");

        while (rst.next()) {
            Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }


    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",entity.getId(),entity.getName(),entity.getAddress());

    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate() > 0;*/
       return SqlUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/
        ResultSet rst = SqlUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");*/

        ResultSet rst =SqlUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/
        return SqlUtil.execute("DELETE FROM Customer WHERE id=?",id);

    }


    public Customer search(String id) throws SQLException, ClassNotFoundException {
       /* Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, id + "");
        ResultSet rst = pstm.executeQuery();*/
        ResultSet rst = SqlUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("address"));
    }

}
