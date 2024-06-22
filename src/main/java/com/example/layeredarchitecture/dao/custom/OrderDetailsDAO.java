package com.example.layeredarchitecture.dao.custom;


import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.DTO.OrderDetailDTO;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetail> {

    boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
