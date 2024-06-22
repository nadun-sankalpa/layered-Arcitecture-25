package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.ItemBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.DTO.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);

    }

    public boolean add(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(dto);

    }

    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }

    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();

    }

    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);

    }
}
