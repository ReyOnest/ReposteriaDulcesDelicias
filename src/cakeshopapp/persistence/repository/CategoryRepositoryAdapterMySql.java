package cakeshopapp.persistence.repository;

import cakeshopapp.domain.Category;
import cakeshopapp.persistence.database.DataBaseConnectionMySql;
import cakeshopapp.persistence.mapper.CategoryRowMapper;
import cakeshopapp.services.outputport.CategoryPersistencePort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryAdapterMySql implements CategoryPersistencePort {

    private final CategoryRowMapper mapper = new CategoryRowMapper();

    @Override
    public Category saveCategory(Category category) {
        String sql = "INSERT INTO category (name_category, state) VALUES (?, ?)";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, category.getNameCategory());
            ps.setString(2, category.getState().name());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar categoría en MySQL: " + e.getMessage(), e);
        }
        return category;
    }

    @Override
    public Optional<Category> findCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id_category = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar categoría por ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categories.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar categorías desde MySQL: " + e.getMessage());
        }
        return categories;
    }

    @Override
    public Category updateCategory(Category category) {
        String sql = "UPDATE category SET name_category = ?, state = ? WHERE id_category = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.getNameCategory());
            ps.setString(2, category.getState().name());
            ps.setInt(3, category.getIdCategory());
            ps.executeUpdate();
            return category;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar categoría: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCategory(int id) {
        String sql = "DELETE FROM category WHERE id_category = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la categoría: " + e.getMessage(), e);
        }
    }
}