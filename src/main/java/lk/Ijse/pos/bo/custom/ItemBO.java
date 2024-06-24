package lk.Ijse.pos.bo.custom;

import lk.Ijse.pos.bo.SuperBO;
import lk.Ijse.pos.DTO.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean delete(String code) throws SQLException, ClassNotFoundException ;

    public boolean add(ItemDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean exist(String code) throws SQLException, ClassNotFoundException ;

    public String generateNewID() throws SQLException, ClassNotFoundException ;

    public ItemDTO search(String code) throws SQLException, ClassNotFoundException ;
}
