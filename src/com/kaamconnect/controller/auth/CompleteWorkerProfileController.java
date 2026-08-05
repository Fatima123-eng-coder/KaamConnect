package com.kaamconnect.controller.auth;

import com.kaamconnect.dao.UserDAO;
import com.kaamconnect.model.User;
import com.kaamconnect.model.WorkerProfile;
import com.kaamconnect.navigation.AppNavigator;
import com.kaamconnect.service.auth.WorkerVerificationService;
import com.kaamconnect.session.SessionManager;
import com.kaamconnect.util.Constants;
import com.kaamconnect.util.FileUtil;
import com.kaamconnect.util.Validator;
import com.kaamconnect.view.auth.CompleteWorkerProfileView;
import com.kaamconnect.view.auth.SignInView;
import com.kaamconnect.view.worker.WorkerDashboardView;

import java.io.File;
import java.io.IOException;

public final class CompleteWorkerProfileController {

    private final CompleteWorkerProfileView view;
    private final AppNavigator navigator;

    private final WorkerVerificationService verificationService;
    private final SessionManager sessionManager;
    private final UserDAO userDAO;

    private final WorkerProfile workerProfile;

    private File selectedProfilePicture;

    public CompleteWorkerProfileController(
            CompleteWorkerProfileView view,
            AppNavigator navigator,
            WorkerProfile workerProfile
    ) {

        this.view = view;
        this.navigator = navigator;
        this.workerProfile = workerProfile;

        verificationService =
                new WorkerVerificationService();

        sessionManager =
                SessionManager.getInstance();

        userDAO =
                new UserDAO();

        loadExistingProfile();
        connectEvents();
    }

    private void connectEvents() {

        view.getChooseProfilePictureButton().setOnAction(
                event -> chooseProfilePicture()
        );

        view.getCompleteProfileButton().setOnAction(
                event -> handleCompleteProfile()
        );

        view.getBackButton().setOnAction(
                event -> logoutAndOpenSignIn()
        );
    }

    private void loadExistingProfile() {

        if (workerProfile == null) {
            return;
        }

        if (workerProfile.getBio() != null) {

            view.getBioTextArea().setText(
                    workerProfile.getBio()
            );
        }

        if (workerProfile.getExperienceYears() > 0) {

            view.getExperienceField().setText(
                    String.valueOf(
                            workerProfile.getExperienceYears()
                    )
            );
        }

        if (workerProfile.getServiceArea() != null) {

            view.getServiceAreaField().setText(
                    workerProfile.getServiceArea()
            );
        }

        if (workerProfile.getAddress() != null) {

            view.getAddressField().setText(
                    workerProfile.getAddress()
            );
        }

        User currentUser =
                sessionManager.getCurrentUser();

        if (currentUser != null
                && currentUser.getProfileImagePath() != null
                && !currentUser
                .getProfileImagePath()
                .isBlank()) {

            File existingImage =
                    new File(
                            currentUser.getProfileImagePath()
                    );

            view.setProfilePictureFileName(
                    existingImage.getName()
            );
        }
    }

    private void chooseProfilePicture() {

        File selectedFile =
                FileUtil.chooseImage(
                        navigator.getStage()
                );

        if (selectedFile == null) {
            return;
        }

        if (!Validator.isValidImage(selectedFile)) {

            view.getErrorLabel().showError(
                    "Select a JPG, JPEG or PNG image."
            );

            return;
        }

        if (!Validator.isValidFileSize(selectedFile)) {

            view.getErrorLabel().showError(
                    "Profile picture must not exceed 5 MB."
            );

            return;
        }

        selectedProfilePicture =
                selectedFile;

        view.setProfilePictureFileName(
                selectedFile.getName()
        );

        view.getErrorLabel().clearError();
    }

    private void handleCompleteProfile() {

        view.getErrorLabel().clearError();

        if (workerProfile == null) {

            view.getErrorLabel().showError(
                    "Worker profile could not be loaded."
            );

            return;
        }

        String bio =
                view.getBioTextArea()
                        .getText()
                        .trim();

        String experienceText =
                view.getExperienceField()
                        .getText()
                        .trim();

        String serviceArea =
                view.getServiceAreaField()
                        .getText()
                        .trim();

        String address =
                view.getAddressField()
                        .getText()
                        .trim();

        if (bio.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter a short bio."
            );

            return;
        }

        if (bio.length() > 250) {

            view.getErrorLabel().showError(
                    "Bio must not exceed 250 characters."
            );

            return;
        }

        if (experienceText.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter your experience in years."
            );

            return;
        }

        int experienceYears;

        try {

            experienceYears =
                    Integer.parseInt(
                            experienceText
                    );

        } catch (NumberFormatException exception) {

            view.getErrorLabel().showError(
                    "Experience must be a number."
            );

            return;
        }

        if (experienceYears < 0) {

            view.getErrorLabel().showError(
                    "Experience cannot be negative."
            );

            return;
        }

        if (experienceYears > 70) {

            view.getErrorLabel().showError(
                    "Enter valid experience years."
            );

            return;
        }

        if (serviceArea.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter your service area."
            );

            return;
        }

        if (address.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter your complete address."
            );

            return;
        }

        int userId =
                sessionManager.getUserId();

        if (userId <= 0) {

            view.getErrorLabel().showError(
                    "Your session has expired. Please log in again."
            );

            return;
        }

        try {

            String profileImagePath =
                    saveProfilePicture(userId);

            if (profileImagePath == null) {
                return;
            }

            workerProfile.setUserId(userId);
            workerProfile.setBio(bio);

            workerProfile.setExperienceYears(
                    experienceYears
            );

            workerProfile.setServiceArea(
                    serviceArea
            );

            workerProfile.setAddress(
                    address
            );

            boolean completed =
                    verificationService.completeProfile(
                            workerProfile
                    );

            if (!completed) {

                view.getErrorLabel().showError(
                        "Unable to complete your profile."
                );

                return;
            }

            workerProfile.setProfileCompleted(
                    true
            );

            sessionManager.setCurrentWorkerProfile(
                    workerProfile
            );

            openWorkerDashboard();

        } catch (IOException exception) {

            view.getErrorLabel().showError(
                    "Unable to save the profile picture."
            );

            exception.printStackTrace();

        } catch (Exception exception) {

            view.getErrorLabel().showError(
                    "Unable to complete profile. " +
                            "Check the database connection."
            );

            exception.printStackTrace();
        }
    }

    private String saveProfilePicture(
            int userId
    ) throws IOException {

        User currentUser =
                sessionManager.getCurrentUser();

        /*
         * Keep the existing picture when the worker
         * already has one and does not choose a new one.
         */
        if (selectedProfilePicture == null) {

            if (currentUser != null
                    && currentUser.getProfileImagePath() != null
                    && !currentUser
                    .getProfileImagePath()
                    .isBlank()) {

                return currentUser.getProfileImagePath();
            }

            view.getErrorLabel().showError(
                    "Select a profile picture."
            );

            return null;
        }

        String savedPath =
                FileUtil.copyFile(
                        selectedProfilePicture,
                        Constants.PROFILE_IMAGE_FOLDER
                );

        boolean imageUpdated =
                userDAO.updateProfileImage(
                        userId,
                        savedPath
                );

        if (!imageUpdated) {

            view.getErrorLabel().showError(
                    "Unable to update the profile picture."
            );

            return null;
        }

        if (currentUser != null) {

            currentUser.setProfileImagePath(
                    savedPath
            );
        }

        return savedPath;
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
                        event ->
                                logoutAndOpenSignIn()
                );

        navigator.show(dashboardView);
    }

    private void logoutAndOpenSignIn() {

        sessionManager.logout();

        SignInView signInView =
                new SignInView();

        new SignInController(
                signInView,
                navigator
        );

        navigator.show(signInView);
    }
}