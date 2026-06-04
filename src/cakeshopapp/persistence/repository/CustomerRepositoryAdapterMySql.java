package cakeshopapp.persistence.repository;

import cakeshopapp.domain.Customer;
import cakeshopapp.persistence.database.DataBaseConnectionMySql;
import cakeshopapp.persistence.mapper.CustomerRowMapper;
import cakeshopapp.services.outputport.CustomerPersistencePort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryAdapterMySql implements CustomerPersistencePort {

    private final CustomerRowMapper mapper = new CustomerRowMapper();

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customer (id_customer, name, last_name, email, password, status, address, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPassword());
            ps.setBoolean(6, customer.isStatus());
            ps.setString(7, customer.getAddress());
            ps.setString(8, customer.getCity());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente en MySQL: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                customers.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes desde MySQL: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM customer WHERE id_customer = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por Email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customer SET name = ?, last_name = ?, email = ?, password = ?, address = ?, city = ? WHERE id_customer = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPassword());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getCity());
            ps.setInt(7, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM customer WHERE id_customer = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
