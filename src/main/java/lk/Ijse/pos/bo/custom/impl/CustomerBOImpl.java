package lk.Ijse.pos.bo.custom.impl;

import lk.Ijse.pos.bo.custom.CustomerBO;
import lk.Ijse.pos.dao.DAOFactory;
import lk.Ijse.pos.dao.custom.CustomerDAO;
import lk.Ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.Ijse.pos.DTO.CustomerDTO;
import lk.Ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer>customers = customerDAO.getAll();

        ArrayList<CustomerDTO>customerDTOS = new ArrayList<>();
        for (Customer c: customers) {
            CustomerDTO customerDTO = new CustomerDTO(c.getId(),c.getName(),c.getAddress());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }


    public boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.add(new Customer(dto.getId(),dto.getName(),dto.getAddress()));

    }

    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));

    }

    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.exist(id);
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {

        return customerDAO.generateNewID();
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return customerDAO.delete(id);

    }


    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        CustomerDTO customerDTO = new CustomerDTO(c.getId(),c.getName(),c.getAddress());

        return customerDTO;
    }
}
