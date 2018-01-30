package com.MyApp.model;


import com.MyApp.annotations.ColumnName;
import com.MyApp.annotations.Id;
import com.MyApp.annotations.Immutable;
import com.MyApp.annotations.TableName;

 @TableName("users")
    public class User {
        @Id("id")
        private long id;
        @ColumnName("name")
        private String name;
        @ColumnName("password")
        private String password;
        @Immutable("immutable")
        @ColumnName("email")
        private String email;

     private String token;

     public User() {
     }

     public User(String name, String password, String email) {
         this.name = name;
         this.password = password;
         this.email = email;
     }

     public User(Long id, String name, String email, String password) {
         this.id = id;
         this.name = name;
         this.email = email;
         this.password = password;
     }

     public void setToken(String token) {
         this.token = token;
     }

     public String getToken() {
         return token;
     }

     public Long getId() {
         return id;
     }

     public String getName() {
         return name;
     }

     public String getPassword() {
         return password;
     }

     public String getEmail() {
         return email;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public void setName(String name) {
         this.name = name;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     @Override
     public String toString() {
         return "User{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 ", password='" + password + '\'' +
                 ", email='" + email + '\'' +
                 '}';
     }

 }
