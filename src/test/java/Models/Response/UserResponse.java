package Models.Response;

import Models.Request.UserRequest_Address;

import java.util.Arrays;

public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String status;
    String[] roles;
    String createdAt;
    UserRequest_Address[] addresses;

    public UserResponse(String id, String firstName, String lastName, String email, String phone, String status, String[] roles, String createdAt, UserRequest_Address[] addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.roles = roles;
        this.createdAt = createdAt;
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", createdAt='" + createdAt + '\'' +
                ", addresses=" + Arrays.toString(addresses) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public UserRequest_Address[] getAddresses() {
        return addresses;
    }
    public void setAddresses(UserRequest_Address[] addresses) {
        this.addresses = addresses;
    }

}
