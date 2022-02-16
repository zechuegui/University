package spring.josesantos.model;

public class Utilizador {

    private String username;
    private String password;
    private String email;
    private String role;

    public Utilizador() {
    }

    public Utilizador(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilizador:[" + username + ", " + email + ", " + role + "]";
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
