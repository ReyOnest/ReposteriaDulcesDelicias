package cakeshopapp.persistence.repository;

import cakeshopapp.domain.Order;
import cakeshopapp.domain.Product;
import cakeshopapp.persistence.database.DataBaseConnectionMySql;
import cakeshopapp.persistence.mapper.OrderRowMapper;
import cakeshopapp.services.outputport.OrderPersistencePort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryAdapterMySql implements OrderPersistencePort {

    @Override
    public void save(Order order) {
        // SQL para insertar la orden principal
        String sqlOrder = "INSERT INTO `order` (id_customer, order_date, total_price, paid_method, state) VALUES (?, ?, ?, ?, ?)";
        // SQL para insertar la relación en la tabla intermedia
        String sqlItem = "INSERT INTO order_item (id_order, id_product, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection()) {
            // Desactivamos el auto-commit para manejar la transacción manualmente
            conn.setAutoCommit(false);

            try (PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {
                // Seteamos los valores de la orden
                psOrder.setInt(1, order.getCustomer().getId());
                psOrder.setDate(2, Date.valueOf(order.getOrderDate()));
                psOrder.setDouble(3, order.getTotalPrice());
                psOrder.setString(4, order.getPaidMethod().name());
                psOrder.setString(5, order.getStatus().name());
                psOrder.executeUpdate();

                // Obtenemos el ID que se acaba de generar para la orden
                try (ResultSet generatedKeys = psOrder.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);

                        // Insertamos cada producto relacionado en order_item
                        try (PreparedStatement psItem = conn.prepareStatement(sqlItem)) {
                            for (Product product : order.getItems()) {
                                psItem.setInt(1, orderId);
                                psItem.setInt(2, product.getIdProduct());
                                psItem.setInt(3, 1); // Asumimos cantidad 1 por defecto
                                psItem.addBatch(); // Preparamos el lote
                            }
                            psItem.executeBatch(); // Ejecutamos la inserción masiva
                        }
                    }
                }
                // Si todo salió bien, confirmamos los cambios
                conn.commit();
                System.out.println("¡Pedido y productos guardados exitosamente!");
            } catch (SQLException e) {
                // Si algo falla, revertimos todos los cambios realizados
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error crítico en persistencia: " + e.getMessage());
        }
    }

    @Override
    public void update(Order order) {
        // Actualizamos los datos básicos de la orden
        String sql = "UPDATE `order` SET total_price = ?, paid_method = ?, state = ? WHERE id_order = ?";
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, order.getTotalPrice());
            ps.setString(2, order.getPaidMethod().name());
            ps.setString(3, order.getStatus().name());
            ps.setInt(4, order.getOrderId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar orden: " + e.getMessage());
        }
    }

    @Override
    public List<Order> findAll() {
        // Consulta con JOIN para traer información completa
        String sql = "SELECT o.*, c.name, c.last_name " +
                "FROM `order` o " +
                "JOIN customer c ON o.id_customer = c.id_customer";

        List<Order> orders = new ArrayList<>();
        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            OrderRowMapper mapper = new OrderRowMapper();
            while (rs.next()) {
                orders.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al recuperar órdenes: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT o.*, c.name, c.last_name " +
                "FROM `order` o " +
                "JOIN customer c ON o.id_customer = c.id_customer " +
                "WHERE o.id_order = ?";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderRowMapper().mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar orden por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        // Primero borramos los ítems relacionados (debido a la llave foránea)
        String sqlItems = "DELETE FROM order_item WHERE id_order = ?";
        String sqlOrder = "DELETE FROM `order` WHERE id_order = ?";

        try (Connection conn = DataBaseConnectionMySql.getInstance().getConnection()) {
            conn.setAutoCommit(false); // Iniciamos transacción

            try (PreparedStatement psItems = conn.prepareStatement(sqlItems);
                 PreparedStatement psOrder = conn.prepareStatement(sqlOrder)) {

                // 1. Borrar items
                psItems.setInt(1, id);
                psItems.executeUpdate();

                // 2. Borrar orden
                psOrder.setInt(1, id);
                psOrder.executeUpdate();

                conn.commit(); // Confirmamos ambos borrados
                System.out.println("Orden " + id + " eliminada correctamente.");
            } catch (SQLException e) {
                conn.rollback(); // Si falla algo, volvemos atrás
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar orden: " + e.getMessage());
        }
    }
}