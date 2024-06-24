package lk.Ijse.pos.dao.custom;


import lk.Ijse.pos.dao.CrudDAO;
import lk.Ijse.pos.DTO.OrderDetailDTO;
import lk.Ijse.pos.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetail> {

    boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
