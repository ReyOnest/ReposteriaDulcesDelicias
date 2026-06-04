package cakeshopapp.persistence.repository;

import cakeshopapp.domain.Admin;
import cakeshopapp.persistence.database.DataBaseConnectionMySql;
import cakeshopapp.persistence.mapper.AdminRowMapper; // Importamos el nuevo Mapper
import cakeshopapp.services.outputport.AdminPersistencePort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryAdapterMySql implements AdminPersistencePort {

    // 1. Declaramos e instanciamos el mapper
    private final AdminRowMapper mapper = new AdminRowMapper();

    @Override
    public void save(Admin admin) {
        String sql = "INSERT INTO admin (id_admin, name, last_name, email, password, status, role, permissions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, admin.getId());
            ps.setString(2, admin.getName());
            ps.setString(3, admin.getLastName());
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getPassword());
            ps.setBoolean(6, admin.isStatus());
            ps.setString(7, admin.getRole());
            ps.setString(8, admin.getPermission());

            ps.executeUpdate();
            System.out.println("Administrador guardado en la BD con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al guardar administrador en MySQL: " + e.getMessage());
        }
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                admins.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar administradores desde MySQL: " + e.getMessage());
        }
        return admins;
    }

    @Override
    public Admin findById(int id) {
        String sql = "SELECT * FROM admin WHERE id_admin = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar administrador por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Admin findByEmail(String email) {
        String sql = "SELECT * FROM admin WHERE email = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar administrador por Email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Admin admin) {
        // CORREGIDO: Query SQL ajustado a las columnas reales de la tabla admin
        String sql = "UPDATE admin SET name = ?, last_name = ?, email = ?, password = ?, role = ?, permissions = ?, status = ? WHERE id_admin = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getLastName());
            ps.setString(3, admin.getEmail());
            ps.setString(4, admin.getPassword());
            ps.setString(5, admin.getRole());
            ps.setString(6, admin.getPermission());
            ps.setBoolean(7, admin.isStatus());
            ps.setInt(8, admin.getId());

            ps.executeUpdate();
            System.out.println("Administrador actualizado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar administrador: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM admin WHERE id_admin = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar administrador: " + e.getMessage());
        }
    }
}