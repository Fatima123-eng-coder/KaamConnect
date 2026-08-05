package com.kaamconnect.service.auth;

import com.kaamconnect.dao.UserDAO;
import com.kaamconnect.enums.UserRole;
import com.kaamconnect.enums.UserStatus;
import com.kaamconnect.model.User;
import com.kaamconnect.session.SessionManager;
import com.kaamconnect.util.PasswordHasher;
import com.kaamconnect.util.Validator;
import com.kaamconnect.dao.WorkerProfileDAO;
import com.kaamconnect.model.WorkerProfile;

public class AuthenticationService {

    private final UserDAO userDAO;
    private final SessionManager sessionManager;
    private final WorkerProfileDAO workerProfileDAO;

    public AuthenticationService() {

        userDAO = new UserDAO();
        workerProfileDAO = new WorkerProfileDAO();
        sessionManager = SessionManager.getInstance();

    }

    // Register Customer

    public boolean registerCustomer(User user) {

        if (!validateRegistration(user)) {
            return false;
        }

        if (userDAO.emailExists(user.getEmail())) {

            System.out.println("Email already exists.");
            return false;
        }

        user.setRole(UserRole.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        String hashedPassword =
                PasswordHasher.hashPassword(user.getPassword());

        user.setPassword(hashedPassword);

        return userDAO.save(user);

    }


    // Register Worker


    public boolean registerWorker(User user) {

        if (!validateRegistration(user)) {
            return false;
        }

        if (userDAO.emailExists(user.getEmail())) {

            System.out.println("Email already exists.");
            return false;
        }

        user.setRole(UserRole.WORKER);
        user.setStatus(UserStatus.ACTIVE);

        String hashedPassword =
                PasswordHasher.hashPassword(user.getPassword());

        user.setPassword(hashedPassword);

        return userDAO.save(user);

    }

    // Validate Registration

    private boolean validateRegistration(User user) {

        if (user == null) {
            return false;
        }

        if (!Validator.isValidName(user.getFirstName())) {

            System.out.println("Invalid First Name");
            return false;

        }

        if (!Validator.isValidName(user.getLastName())) {

            System.out.println("Invalid Last Name");
            return false;

        }

        if (!Validator.isValidEmail(user.getEmail())) {

            System.out.println("Invalid Email");
            return false;

        }

        if (!Validator.isValidPassword(user.getPassword())) {

            System.out.println("Weak Password");
            return false;

        }

        return true;

    }


    // Check Email Availability


    public boolean isEmailAvailable(String email) {

        return !userDAO.emailExists(email);

    }

// Login


    public User login(String email, String password) {

        if (!validateLogin(email, password)) {
            return null;
        }

        User user = userDAO.getByEmail(email);

        if (user == null) {
            System.out.println("Invalid Email.");
            return null;
        }

        if (!PasswordHasher.verifyPassword(password, user.getPassword())) {
            System.out.println("Invalid Password.");
            return null;
        }

        if (user.getStatus() != UserStatus.ACTIVE) {
            System.out.println("Your account is suspended.");
            return null;
        }

        sessionManager.login(user);
        if (user.getRole() == UserRole.WORKER) {

            WorkerProfile profile =
                    workerProfileDAO.getByUserId(user.getUserId());

            sessionManager.setCurrentWorkerProfile(profile);
        }

        return user;
    }


// Logout


    public void logout() {

        sessionManager.logout();

    }


// Change Password


    public boolean changePassword(int userId,
                                  String oldPassword,
                                  String newPassword) {

        User user = userDAO.getById(userId);

        if (user == null) {
            return false;
        }

        if (!PasswordHasher.verifyPassword(
                oldPassword,
                user.getPassword())) {

            System.out.println("Old password is incorrect.");
            return false;
        }

        if (!Validator.isValidPassword(newPassword)) {

            System.out.println("Weak password.");
            return false;
        }

        String hashedPassword =
                PasswordHasher.hashPassword(newPassword);

        return userDAO.updatePassword(userId, hashedPassword);

    }


// Validate Login


    private boolean validateLogin(String email,
                                  String password) {

        if (!Validator.isValidEmail(email)) {

            System.out.println("Invalid Email.");
            return false;

        }

        if (password == null || password.isBlank()) {

            System.out.println("Password Required.");
            return false;

        }

        return true;

    }

}