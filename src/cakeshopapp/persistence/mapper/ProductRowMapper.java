package cakeshopapp.persistence.mapper;

import cakeshopapp.domain.Product;
import cakeshopapp.domain.Cake;
import cakeshopapp.domain.Pastry;
import cakeshopapp.domain.Cupcake; // Asegúrate de importar estas clases
import cakeshopapp.domain.Cookie;  // Asegúrate de importar estas clases
import cakeshopapp.domain.enums.ProductState;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs) throws SQLException {
        String type = rs.getString("product_type");
        Product product = null;

        // 1. Instanciación basada en el tipo
        if ("Cake".equalsIgnoreCase(type)) {
            Cake cake = new Cake();
            cake.setSlices(rs.getInt("slices"));
            product = cake;
        } else if ("Pastry".equalsIgnoreCase(type)) {
            Pastry pastry = new Pastry();
            pastry.setSize(rs.getString("size"));
            product = pastry;
        } else if ("Cupcake".equalsIgnoreCase(type)) {
            Cupcake cupcake = new Cupcake();
            cupcake.setFrosting(rs.getString("frosting"));
            product = cupcake;
        } else if ("Cookie".equalsIgnoreCase(type)) {
            Cookie cookie = new Cookie();
            cookie.setIsCrunchy(rs.getBoolean("is_crunchy"));
            product = cookie;
        }

        // Manejo de error si el tipo no existe o el objeto no se pudo crear
        if (product == null) {
            throw new SQLException("Tipo de producto desconocido o no soportado: " + type);
        }

        // 2. Mapeo de atributos compartidos (de la clase abstracta Product)
        product.setIdProduct(rs.getInt("id_product"));
        product.setName(rs.getString("name"));
        product.setFlavor(rs.getString("flavor"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));

        // 3. Mapeo del Enum de estado
        String stateStr = rs.getString("state");
        if (stateStr != null) {
            try {
                product.setState(ProductState.valueOf(stateStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.err.println("Estado no válido encontrado en BD: " + stateStr);
            }
        }

        return product;
    }
}