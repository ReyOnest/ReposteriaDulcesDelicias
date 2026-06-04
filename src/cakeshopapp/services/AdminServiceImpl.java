package cakeshopapp.services;

import cakeshopapp.domain.Admin;
import cakeshopapp.services.input.AdminService;
import cakeshopapp.services.outputport.AdminPersistencePort;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final AdminPersistencePort adminPersistencePort;

    public AdminServiceImpl(AdminPersistencePort adminPersistencePort) {
        this.adminPersistencePort = adminPersistencePort;
    }

    @Override
    public void registerAdmin(Admin admin) {
        adminPersistencePort.save(admin);
    }

    @Override
    public Admin getAdminById(int id) {
        return adminPersistencePort.findById(id);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminPersistencePort.findByEmail(email);
    }

    @Override
    public void updateAdminData(Admin admin) {
        adminPersistencePort.update(admin);
    }

    @Override
    public void deleteAdminSystem(int id) {
        adminPersistencePort.deleteById(id);
    }

    @Override
    public List<Admin> listAllAdmins() {
        return adminPersistencePort.findAll();
    }
}