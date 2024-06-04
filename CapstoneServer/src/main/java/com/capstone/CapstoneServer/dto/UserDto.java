package com.capstone.CapstoneServer.dto;

public class UserDto {
    private String userName;
    private String email;
    private String password;

    // Default constructor
    public UserDto() {
    }

    // Constructor with parameters
    public UserDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    // Builder pattern
    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public static class UserDtoBuilder {
        private String userName;
        private String email;
        private String password;

        public UserDtoBuilder() {
        }

        public UserDtoBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDto build() {
            return new UserDto(userName, email, password);
        }
    }

    // Getters and setters
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
}
