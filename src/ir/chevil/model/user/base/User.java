package ir.chevil.model.user.base;

import ir.chevil.model.base.BaseModel;

import java.util.Objects;
import java.util.UUID;

public class User extends BaseModel<UUID> {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(String fistName, String lastName, String username, String password) {
        setId(UUID.randomUUID());
        setFirstName(fistName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public int hashCode() {return Objects.hashCode(this.getId());}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(user.getId(), this.getId());
    }
}
