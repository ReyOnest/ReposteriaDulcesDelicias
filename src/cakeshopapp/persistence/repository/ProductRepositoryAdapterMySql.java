package cakeshopapp.persistence.repository;

import cakeshopapp.domain.*;
import cakeshopapp.persistence.database.DataBaseConnectionMySql;
import cakeshopapp.persistence.mapper.ProductRowMapper;
import cakeshopapp.services.outputport.ProductPersistencePort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryAdapterMySql implements ProductPersistencePort {

    private final ProductRowMapper mapper = new ProductRowMapper();

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (name, flavor, price, stock, state, product_type, slices, size, frosting, is_crunchy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getFlavor());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getState().name());

            if (product instanceof Cake) {
                ps.setString(6, "CAKE");
                ps.setInt(7, ((Cake) product).getSlices());
                ps.setNull(8, Types.VARCHAR); // size
                ps.setNull(9, Types.VARCHAR); // frosting
                ps.setNull(10, Types.BOOLEAN);// crunchy
            } else if (product instanceof Pastry) {
                ps.setString(6, "PASTRY");
                ps.setNull(7, Types.INTEGER); // slices
                ps.setString(8, ((Pastry) product).getSize());
                ps.setNull(9, Types.VARCHAR); // frosting
                ps.setNull(10, Types.BOOLEAN);// crunchy
            } else if (product instanceof Cupcake) {
                ps.setString(6, "CUPCAKE");
                ps.setNull(7, Types.INTEGER); // slices
                ps.setNull(8, Types.VARCHAR); // size
                ps.setString(9, ((Cupcake) product).getFrosting());
                ps.setNull(10, Types.BOOLEAN);// crunchy
            } else if (product instanceof Cookie) {
                ps.setString(6, "COOKIE");
                ps.setNull(7, Types.INTEGER); // slices
                ps.setNull(8, Types.VARCHAR); // size
                ps.setNull(9, Types.VARCHAR); // frosting
                ps.setBoolean(10, ((Cookie) product).isCrunchy());
            }
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setIdProduct(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar producto en MySQL: " + e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>(); // Siempre inicializada, nunca null
        String sql = "SELECT * FROM product";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos desde MySQL: " + e.getMessage());
        }
        return products;
    }

    @Override
    public Optional<Product> findById(int id) {
        String sql = "SELECT * FROM product WHERE id_product = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Retornamos el producto encontrado envuelto en un Optional
                    return Optional.of(mapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto por ID: " + e.getMessage());
        }
        // Si no se encuentra nada o hay un error, retornamos un Optional vacío
        return Optional.empty();
    }

    @Override
    public Product findByName(String name) {
        String sql = "SELECT * FROM product WHERE name = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto por Nombre: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM product WHERE id_product = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}