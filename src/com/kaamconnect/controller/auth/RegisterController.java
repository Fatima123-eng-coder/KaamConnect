package com.kaamconnect.controller.auth;

import com.kaamconnect.enums.UserRole;
import com.kaamconnect.model.User;
import com.kaamconnect.navigation.AppNavigator;
import com.kaamconnect.service.auth.AuthenticationService;
import com.kaamconnect.util.Validator;
import com.kaamconnect.view.auth.RegisterView;
import com.kaamconnect.view.auth.SignInView;
import com.kaamconnect.view.auth.WorkerVerificationView;

import java.util.Locale;

public final class RegisterController {

    private final RegisterView view;
    private final AppNavigator navigator;

    private final AuthenticationService authenticationService;

    public RegisterController(
            RegisterView view,
            AppNavigator navigator
    ) {

        this.view = view;
        this.navigator = navigator;

        authenticationService =
                new AuthenticationService();

        connectEvents();
    }

    private void connectEvents() {

        view.getRegisterButton().setOnAction(
                event -> handleRegistration()
        );

        view.getLoginLink().setOnAction(
                event -> openSignIn()
        );

        view.getBackButton().setOnAction(
                event -> openSignIn()
        );

        view.getConfirmPasswordField().setOnAction(
                event -> handleRegistration()
        );
    }

    private void handleRegistration() {

        view.getErrorLabel().clearError();

        String firstName =
                view.getFirstNameField()
                        .getText()
                        .trim();

        String lastName =
                view.getLastNameField()
                        .getText()
                        .trim();

        String email =
                view.getEmailField()
                        .getText()
                        .trim()
                        .toLowerCase(Locale.ROOT);

        /*
         * The user types only the ten digits after +92.
         * Example: 3001234567
         */
        String phoneDigits =
                view.getPhoneField()
                        .getText()
                        .trim();

        String password =
                view.getPasswordField()
                        .getText();

        String confirmPassword =
                view.getConfirmPasswordField()
                        .getText();

        UserRole selectedRole =
                view.getRoleComboBox()
                        .getValue();

        if (!Validator.isValidName(firstName)) {

            view.getErrorLabel().showError(
                    "First name must contain only letters and spaces."
            );

            return;
        }

        if (!Validator.isValidName(lastName)) {

            view.getErrorLabel().showError(
                    "Last name must contain only letters and spaces."
            );

            return;
        }

        if (!Validator.isValidEmail(email)) {

            view.getErrorLabel().showError(
                    "Enter a valid email address."
            );

            return;
        }

        /*
         * Valid examples:
         * 3001234567
         * 3211234567
         *
         * The final stored number becomes:
         * +923001234567
         */
        if (!phoneDigits.matches("3\\d{9}")) {

            view.getErrorLabel().showError(
                    "Enter 10 digits after +92, starting with 3."
            );

            return;
        }

        if (!Validator.isValidPassword(password)) {

            view.getErrorLabel().showError(
                    "Password must have at least 8 characters, " +
                            "including uppercase, lowercase, number " +
                            "and special character."
            );

            return;
        }

        if (!password.equals(confirmPassword)) {

            view.getErrorLabel().showError(
                    "Passwords do not match."
            );

            return;
        }

        if (selectedRole == null) {

            view.getErrorLabel().showError(
                    "Select Customer or Worker."
            );

            return;
        }

        String completePhoneNumber =
                "+92" + phoneDigits;

        User user =
                new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(completePhoneNumber);
        user.setPassword(password);
        user.setRole(selectedRole);

        try {

            boolean registered;

            if (selectedRole == UserRole.CUSTOMER) {

                registered =
                        authenticationService
                                .registerCustomer(user);

            } else {

                registered =
                        authenticationService
                                .registerWorker(user);
            }

            if (!registered) {

                view.getErrorLabel().showError(
                        "Registration failed. The email or phone number " +
                                "may already be registered."
                );

                return;
            }

            if (selectedRole == UserRole.CUSTOMER) {

                openSignIn();

            } else {

                loginWorkerAndOpenVerification(
                        email,
                        password
                );
            }

        } catch (Exception exception) {

            view.getErrorLabel().showError(
                    "Unable to register. Check the database connection."
            );

            exception.printStackTrace();
        }
    }

    private void loginWorkerAndOpenVerification(
            String email,
            String plainPassword
    ) {

        try {

            User worker =
                    authenticationService.login(
                            email,
                            plainPassword
                    );

            if (worker == null) {

                view.getErrorLabel().showError(
                        "The account was created, but automatic login failed. " +
                                "Return to Login and sign in."
                );

                return;
            }

            WorkerVerificationView verificationView =
                    new WorkerVerificationView();

            new WorkerVerificationController(
                    verificationView,
                    navigator,
                    null
            );

            navigator.show(verificationView);

        } catch (Exception exception) {

            view.getErrorLabel().showError(
                    "The account was created, but verification " +
                            "could not be opened."
            );

            exception.printStackTrace();
        }
    }

    private void openSignIn() {

        SignInView signInView =
                new SignInView();

        new SignInController(
                signInView,
                navigator
        );

        navigator.show(signInView);
    }
}