package com.kaamconnect.session;

import com.kaamconnect.enums.UserRole;
import com.kaamconnect.model.User;
import com.kaamconnect.model.WorkerProfile;

public class SessionManager {

    private static SessionManager instance;

    private User currentUser;
    private WorkerProfile currentWorkerProfile;

    // Private constructor
    private SessionManager() {
    }

    // Singleton Instance
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Login
    public void login(User user) {
        this.currentUser = user;
    }

    // Logout
    public void logout() {
        currentUser = null;
        currentWorkerProfile = null;
    }

    // Current User
    public User getCurrentUser() {
        return currentUser;
    }

    // Worker Profile
    public WorkerProfile getCurrentWorkerProfile() {
        return currentWorkerProfile;
    }

    public void setCurrentWorkerProfile(WorkerProfile profile) {
        this.currentWorkerProfile = profile;
    }

    // Session Check
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Role Checks
    public boolean isAdmin() {
        return isLoggedIn() && currentUser.getRole() == UserRole.ADMIN;
    }

    public boolean isCustomer() {
        return isLoggedIn() && currentUser.getRole() == UserRole.CUSTOMER;
    }

    public boolean isWorker() {
        return isLoggedIn() && currentUser.getRole() == UserRole.WORKER;
    }

    // Helper Methods
    public int getUserId() {
        return isLoggedIn() ? currentUser.getUserId() : -1;
    }

    public String getFirstName() {
        return isLoggedIn() ? currentUser.getFirstName() : null;
    }

    public String getLastName() {
        return isLoggedIn() ? currentUser.getLastName() : null;
    }

    public String getFullName() {
        return isLoggedIn()
                ? currentUser.getFirstName() + " " + currentUser.getLastName()
                : null;
    }

    public String getEmail() {
        return isLoggedIn() ? currentUser.getEmail() : null;
    }

    public UserRole getRole() {
        return isLoggedIn() ? currentUser.getRole() : null;
    }

    // Clear Session
    public void clearSession() {
        logout();
    }
}