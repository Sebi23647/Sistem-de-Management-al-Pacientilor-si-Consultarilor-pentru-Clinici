package ro.digitalnation.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User {
    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin = true;

    // Getters și Setters
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
