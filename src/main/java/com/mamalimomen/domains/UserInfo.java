package com.mamalimomen.domains;

public class UserInfo {

    public UserInfo(Long id, String firstName, String lastName, Role role, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.city = city;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private Role role;

    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void printCompleteInformation() {
        System.out.printf("%nFirstName: %s%nLastName: %s%nCity: %s%nRole: %s%n",
                getFirstName(), getLastName(), getCity(), getRole());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getFirstName(), getRole());
    }
}
