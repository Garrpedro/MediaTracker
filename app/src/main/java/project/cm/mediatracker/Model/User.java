package project.cm.mediatracker.Model;

import java.util.Date;
import java.util.List;

/**
 * Created by pedrog on 26-12-2017.
 */

public class User {

    private String email;
    private String username;
    private String password;
    private Boolean active;
    private List<Lista> listas;

    public User(String email, String username, String password, Boolean active, List<Lista> listas) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = active;
        this.listas = listas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Lista> getListas() {
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
