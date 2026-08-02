package com.kaamconnect.model;


import com.kaamconnect.enums.UserRole;
import com.kaamconnect.enums.UserStatus;

import java.time.LocalDateTime;

public class User {
   private int userId;
   private String firstName;
   private String lastName;
   private String email;
   private String password;
   private String phone;
   private String profileImagePath;
   private UserRole role;
   private UserStatus status;
   private LocalDateTime createdAt;

   public User(int userId, String firstName, String lastName, String email, String password, String phone, String profileImagePath, UserRole role, UserStatus status, LocalDateTime createdAt) {
      this.userId = userId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.phone = phone;
      this.profileImagePath = profileImagePath;
      this.role = role;
      this.status = status;
      this.createdAt = createdAt;
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getProfileImagePath() {
      return profileImagePath;
   }

   public void setProfileImagePath(String profileImagePath) {
      this.profileImagePath = profileImagePath;
   }

   public UserRole getRole() {
      return role;
   }

   public void setRole(UserRole role) {
      this.role = role;
   }

   public UserStatus getStatus() {
      return status;
   }

   public void setStatus(UserStatus status) {
      this.status = status;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }
}
