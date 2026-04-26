package cakeshopapp.domain;

public class Admin extends Person {

    // Atributos de Admin
    private String role;
    private String permission;

    // Constructores
    public Admin(int id, String name, String lastName, String email, String password, boolean status, String role, String permission) {
        super(id, name, lastName, email, password, status);
        this.role = role;
        this.permission = permission;
    }
    // Constructor vacío
    public Admin() {
        super();
    }

    public Admin(String email) {
        super(email);
    }

    // Implementación heredada de la clase abstracta
    @Override
    public void showInformation() {
        System.out.println("Administrador: " + getName() + " | Rol: " + role);
    }

    // Getters y Setters
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPermission() { return permission; }
    public void setPermission(String permission) { this.permission = permission; }

    // Método toString
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() + // Usamos Getters
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                ", status=" + isStatus() +
                '}';
    }
}