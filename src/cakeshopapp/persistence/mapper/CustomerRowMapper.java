package cakeshopapp.persistence.mapper;

import cakeshopapp.domain.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        // Se extraen los datos de las columnas de la tabla de MySQL
        customer.setId(rs.getInt("id_customer"));
        customer.setName(rs.getString("name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setStatus(rs.getBoolean("status"));
        customer.setAddress(rs.getString("address"));
        customer.setCity(rs.getString("city"));

        return customer;
    }
}