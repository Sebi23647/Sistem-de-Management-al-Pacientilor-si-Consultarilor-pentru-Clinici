package ro.digitalnation.authentication;

public class LoginDTO {
    private String email;
    private String password;

    // Getters și setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
