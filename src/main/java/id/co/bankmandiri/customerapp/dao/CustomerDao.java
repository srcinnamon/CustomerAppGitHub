package id.co.bankmandiri.customerapp.dao;

import id.co.bankmandiri.customerapp.domain.Customer;
import id.co.bankmandiri.customerapp.domain.CustomerException;
import id.co.bankmandiri.customerapp.service.CustomerService;
import id.co.bankmandiri.customerapp.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerService {
    private Connection connection;
   // private final String dburl = "jdbc:mysql://localhost:3307/belajar_java";
   // private final String username = "root";
  //  private final String password = "";
    private final String queryDisplayAllCustomer = "Select * from customers";
    private final String queryFindCustomerById = "Select * from customers where customerid = ?";
    private final String queryEditCustomer =
            "update customers" + " set firstname = ?, lastname = ?, dateofbirth = ?" + " where customerid = ?";
    private final String queryDeleteCustomer = "delete from customers " + " where customerid = ?";
    private final String queryInsertCustomer = "insert into customers(firstname,lastname,dateofbirth) values(?,?,?)";
    public CustomerDao(){
        connection = DbUtil.getConnection();
    }
    public CustomerDao(Connection connection){
        this.connection = connection;
    }
    @Override
    public List<Customer> displayAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try(
        //        Connection connection = DriverManager.getConnection(dburl,username,password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(queryDisplayAllCustomer);
                ) {
            while (resultSet.next()){
                customers.add(new Customer(
                        resultSet.getInt("customerid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("dateofbirth").toLocalDate())
                );
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return customers;
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(queryInsertCustomer);
                ){
                ps.setString(1,customer.getFirstName());
                ps.setString(2,customer.getLastName());
                ps.setDate(3,Date.valueOf(customer.getDateOfBirth()));
                ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new CustomerException("add customer fail");
        }
    }

    @Override
    public void editCustomer(Customer customer) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(queryEditCustomer);
        ){
            ps.setString(1,customer.getFirstName());
            ps.setString(2,customer.getLastName());
            ps.setDate(3,Date.valueOf(customer.getDateOfBirth()));
            ps.setInt(4,customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new CustomerException("edit customer fail");
        }
    }

    @Override
    public Customer findCustomerById(int id) throws CustomerException {
        Customer customer=null;
        try (
                PreparedStatement ps = connection.prepareStatement(queryFindCustomerById);
                ) {
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                        customer = new Customer(
                        resultSet.getInt("customerid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("dateofbirth").toLocalDate());
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
            throw new CustomerException("find customer by id fail");
        }
        if(customer != null){
            return customer;
        } else {
            throw new CustomerException("customer tidak ditemukan");
        }
    }

    @Override
    public void deleteCustomer(int id) throws CustomerException {
        try (
                PreparedStatement ps = connection.prepareStatement(queryDeleteCustomer);
        ){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new CustomerException("delete customer fail");
        }
    }
}
