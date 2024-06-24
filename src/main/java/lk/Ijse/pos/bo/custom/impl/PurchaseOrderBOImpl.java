package lk.Ijse.pos.bo.custom.impl;

import lk.Ijse.pos.bo.custom.PurchaseOrderBO;
import lk.Ijse.pos.dao.DAOFactory;
import lk.Ijse.pos.dao.custom.CustomerDAO;
import lk.Ijse.pos.dao.custom.ItemDAO;
import lk.Ijse.pos.dao.custom.OrderDAO;
import lk.Ijse.pos.dao.custom.OrderDetailsDAO;
import lk.Ijse.pos.db.DBConnection;
import lk.Ijse.pos.DTO.CustomerDTO;
import lk.Ijse.pos.DTO.ItemDTO;
import lk.Ijse.pos.DTO.OrderDetailDTO;
import lk.Ijse.pos.entity.Customer;
import lk.Ijse.pos.entity.Item;
import lk.Ijse.pos.entity.Order;
import lk.Ijse.pos.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {

        Customer c =  customerDAO.search(id);
        CustomerDTO customerDTO = new CustomerDTO(c.getId(),c.getName(),c.getAddress());
        return customerDTO;
    }


    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {

        Item i = itemDAO.search(code);
        ItemDTO itemDTO = new ItemDTO(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand());
        return itemDTO;
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {

        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {

        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();

        ArrayList<CustomerDTO>customerDTOS = new ArrayList<>();
        for (Customer c: customers) {
            CustomerDTO customerDTO = new CustomerDTO(c.getId(),c.getName(),c.getAddress());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item>items = itemDAO.getAll();

        ArrayList<ItemDTO>itemDTOS = new ArrayList<>();
        for (Item i: items) {
            ItemDTO itemDTO = new ItemDTO(i.getCode(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }


    @Override
    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails){
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            //Check order id already exist or not

            boolean b1 = orderDAO.exist(orderId);
            /*if order id already exist*/
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);
            //Save the Order to the order table
            boolean b2 = orderDAO.add(new Order(orderId, orderDate, customerId));

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            // add data to the Order Details table

            for (OrderDetailDTO detail : orderDetails) {
                boolean b3 = orderDetailsDAO.add(new OrderDetail(detail.getItemCode(), detail.getItemCode(), detail.getQty(), detail.getUnitPrice()));
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //update item

                boolean b = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemDTO findItem(String code) {
        try {
            PurchaseOrderBOImpl purchaseOrderBO = new PurchaseOrderBOImpl();
            return purchaseOrderBO.searchItem(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
