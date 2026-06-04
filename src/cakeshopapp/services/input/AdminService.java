package cakeshopapp.services.input;

import cakeshopapp.domain.Admin;
import java.util.List;

public interface AdminService {
    void registerAdmin(Admin admin);
    Admin getAdminById(int id);
    Admin getAdminByEmail(String email);
    void updateAdminData(Admin admin);
    void deleteAdminSystem(int id);
    List<Admin> listAllAdmins();
}