package com.kaamconnect.controller.auth;

import com.kaamconnect.enums.ApprovalStatus;
import com.kaamconnect.enums.UserRole;
import com.kaamconnect.model.User;
import com.kaamconnect.model.WorkerProfile;
import com.kaamconnect.navigation.AppNavigator;
import com.kaamconnect.service.auth.AuthenticationService;
import com.kaamconnect.session.SessionManager;
import com.kaamconnect.util.Validator;
import com.kaamconnect.view.auth.CompleteWorkerProfileView;
import com.kaamconnect.view.auth.RegisterView;
import com.kaamconnect.view.auth.SignInView;
import com.kaamconnect.view.auth.VerificationPendingView;
import com.kaamconnect.view.auth.VerificationRejectedView;
import com.kaamconnect.view.auth.WorkerVerificationView;
import com.kaamconnect.view.customer.CustomerDashboardView;
import com.kaamconnect.view.worker.WorkerDashboardView;

import java.util.Locale;

public final class SignInController {

    private final SignInView view;
    private final AppNavigator navigator;

    private final AuthenticationService authenticationService;
    private final SessionManager sessionManager;

    public SignInController(
            SignInView view,
            AppNavigator navigator
    ) {

        this.view = view;
        this.navigator = navigator;

        authenticationService =
                new AuthenticationService();

        sessionManager =
                SessionManager.getInstance();

        connectEvents();
    }

    private void connectEvents() {

        view.getSignInButton().setOnAction(
                event -> handleSignIn()
        );

        view.getRegisterLink().setOnAction(
                event -> openRegister()
        );

        view.getPasswordField().setOnAction(
                event -> handleSignIn()
        );
    }

    private void handleSignIn() {

        view.getErrorLabel().clearError();

        String email =
                view.getEmailField()
                        .getText()
                        .trim()
                        .toLowerCase(Locale.ROOT);

        String password =
                view.getPasswordField()
                        .getText();

        if (email.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter your email address."
            );

            return;
        }

        if (!Validator.isValidEmail(email)) {

            view.getErrorLabel().showError(
                    "Enter a valid email address."
            );

            return;
        }

        if (password == null ||
                password.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter your password."
            );

            return;
        }

        try {

            User user =
                    authenticationService.login(
                            email,
                            password
                    );

            if (user == null) {

                view.getErrorLabel().showError(
                        "Invalid email or password."
                );

                return;
            }

            UserRole role =
                    user.getRole();

            if (role == UserRole.CUSTOMER) {

                openCustomerDashboard();
                return;
            }

            if (role == UserRole.WORKER) {

                handleWorkerLogin();
                return;
            }

            authenticationService.logout();

            view.getErrorLabel().showError(
                    "Admin login is not available on this screen."
            );

        } catch (Exception exception) {

            view.getErrorLabel().showError(
                    "Unable to sign in. Check the database connection."
            );

            exception.printStackTrace();
        }
    }

    private void handleWorkerLogin() {

        WorkerProfile workerProfile =
                sessionManager
                        .getCurrentWorkerProfile();

        /*
         * The worker registered but has not yet
         * submitted a verification application.
         */
        if (workerProfile == null) {

            openWorkerVerification(null);
            return;
        }

        ApprovalStatus approvalStatus =
                workerProfile.getApprovalStatus();

        if (approvalStatus == null) {

            view.getErrorLabel().showError(
                    "Worker verification status could not be loaded."
            );

            return;
        }

        switch (approvalStatus) {

            case PENDING ->
                    openPendingView();

            case REJECTED ->
                    openRejectedView(workerProfile);

            case APPROVED -> {

                if (workerProfile.isProfileCompleted()) {

                    openWorkerDashboard();

                } else {

                    openCompleteWorkerProfile(
                            workerProfile
                    );
                }
            }
        }
    }

    private void openRegister() {

        RegisterView registerView =
                new RegisterView();

        new RegisterController(
                registerView,
                navigator
        );

        navigator.show(registerView);
    }

    private void openWorkerVerification(
            WorkerProfile existingProfile
    ) {

        WorkerVerificationView verificationView =
                new WorkerVerificationView();

        new WorkerVerificationController(
                verificationView,
                navigator,
                existingProfile
        );

        navigator.show(verificationView);
    }

    private void openPendingView() {

        VerificationPendingView pendingView =
                new VerificationPendingView();

        pendingView
                .getBackButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        pendingView
                .getBackToLoginButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        navigator.show(pendingView);
    }

    private void openRejectedView(
            WorkerProfile workerProfile
    ) {

        VerificationRejectedView rejectedView =
                new VerificationRejectedView();

        rejectedView.setRejectionReason(
                workerProfile.getRejectionReason()
        );

        /*
         * Keep the worker logged in so the existing
         * verification application can be edited.
         */
        rejectedView
                .getEditApplicationButton()
                .setOnAction(
                        event ->
                                openWorkerVerification(
                                        workerProfile
                                )
                );

        rejectedView
                .getBackButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        rejectedView
                .getBackToLoginButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        navigator.show(rejectedView);
    }

    private void openCompleteWorkerProfile(
            WorkerProfile workerProfile
    ) {

        CompleteWorkerProfileView profileView =
                new CompleteWorkerProfileView();

        new CompleteWorkerProfileController(
                profileView,
                navigator,
                workerProfile
        );

        navigator.show(profileView);
    }

    private void openCustomerDashboard() {

        CustomerDashboardView dashboardView =
                new CustomerDashboardView();

        dashboardView.setCustomerName(
                sessionManager.getFullName()
        );

        dashboardView
                .getLogoutButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        navigator.show(dashboardView);
    }

    private void openWorkerDashboard() {

        WorkerDashboardView dashboardView =
                new WorkerDashboardView();

        dashboardView.setWorkerName(
                sessionManager.getFullName()
        );

        dashboardView
                .getLogoutButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        navigator.show(dashboardView);
    }

    private void logoutAndOpenSignIn() {

        authenticationService.logout();

        SignInView signInView =
                new SignInView();

        new SignInController(
                signInView,
                navigator
        );

        navigator.show(signInView);
    }
}