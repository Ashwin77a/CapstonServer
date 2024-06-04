package com.capstone.CapstoneServer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
//@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotBlank(message = "Please provide a username")
    private String userName;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Please provide an email")
    private String email;

    @NotBlank(message = "Please provide a password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreation;

    // Default constructor
    public User() {
        this.dateOfCreation = new Date();
    }

    // Constructor with parameters
    public User(int userId, String userName, String email, String password, Date dateOfCreation) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateOfCreation = dateOfCreation;
    }

    // Builder pattern
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int userId;
        private String userName;
        private String email;
        private String password;
        private Date dateOfCreation;

        public UserBuilder() {
        }

        public UserBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder dateOfCreation(Date dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
            return this;
        }

        public User build() {
            return new User(userId, userName, email, password, dateOfCreation);
        }
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
