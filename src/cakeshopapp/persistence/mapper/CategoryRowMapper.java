package cakeshopapp.persistence.mapper;

import cakeshopapp.domain.Category;
import cakeshopapp.domain.enums.CategoryStateEnum; // Importa tu enum
import java.sql.ResultSet;
import java.sql.SQLException;

// Implementación con el tipo genérico
public class CategoryRowMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs) throws SQLException {
        Category category = new Category();

        category.setIdCategory(rs.getInt("id_category"));
        category.setNameCategory(rs.getString("name_category"));
        category.setDescription(rs.getString("description"));
        String stateStr = rs.getString("state");
        category.setState(CategoryStateEnum.valueOf(stateStr));

        return category;
    }
}