package cakeshopapp.persistence.mapper;

import cakeshopapp.domain.Order;
import cakeshopapp.domain.Customer;
import cakeshopapp.domain.enums.OrderState;
import cakeshopapp.domain.enums.PaidMethodEnum; // Asegúrate de importar tu Enum
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRowMapper {
    public Order mapRow(ResultSet rs) throws SQLException {
        // 1. Mapear el cliente asociado
        Customer customer = new Customer();
        customer.setId(rs.getInt("id_customer"));
        customer.setName(rs.getString("name"));

        // 2. Crear la orden usando el constructor
        Order order = new Order();
        order.setOrderId(rs.getInt("id_order"));
        order.setCustomer(customer);
        order.setItems(new ArrayList<>()); // Por ahora vacía, luego se llena
        order.setTotalPrice(rs.getDouble("total_price"));
        order.setStatus(OrderState.valueOf(rs.getString("state")));
        order.setOrderDate(rs.getDate("order_date").toLocalDate());

        // 3. Mapeo seguro del método de pago (Enum)
        String metodoStr = rs.getString("paid_method");
        if (metodoStr != null) {
            order.setPaidMethod(PaidMethodEnum.valueOf(metodoStr));
        }

        return order;
    }
}