package cakeshopapp.persistence.mapper;

import cakeshopapp.domain.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt("id_admin"));
        admin.setName(rs.getString("name"));
        admin.setLastName(rs.getString("last_name"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));
        admin.setStatus(rs.getBoolean("status"));
        admin.setRole(rs.getString("role"));
        admin.setPermission(rs.getString("permissions"));
        return admin;
    }
}