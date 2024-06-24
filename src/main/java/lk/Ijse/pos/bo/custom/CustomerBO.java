package lk.Ijse.pos.bo.custom;

import lk.Ijse.pos.bo.SuperBO;
import lk.Ijse.pos.DTO.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException ;


    public boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean exist(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewID() throws SQLException, ClassNotFoundException ;


    public boolean delete(String id) throws SQLException, ClassNotFoundException ;


    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException ;
}
