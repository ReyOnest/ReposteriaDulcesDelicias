package cakeshopapp.services.outputport;

import cakeshopapp.domain.Admin;
import java.util.List;

public interface AdminPersistencePort {
    void save(Admin admin);
    Admin findById(int id);
    Admin findByEmail(String email);
    void update(Admin admin);
    void deleteById(int id);
    List<Admin> findAll();
}