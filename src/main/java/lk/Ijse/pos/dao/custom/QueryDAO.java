package lk.Ijse.pos.dao.custom;

import lk.Ijse.pos.dao.SuperDAO;

public interface QueryDAO  extends SuperDAO {
    void getOrdersByCustomerName();
}
