package entity;

import org.springframework.stereotype.Component;

@Component
public class Member {
    private int id;
    private String account;
    private String username;
    private String password;
    private String location;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", admin=" + admin +
                ", email='" + email + '\'' +
                '}';
    }

    private int admin;
    private String email;

    public Member() {
    }

    public Member(int id, String account, String username, String password, String location, int admin, String email) {
        this.id = id;
        this.account = account;
        this.username = username;
        this.password = password;
        this.location = location;
        this.admin = admin;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
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
}
