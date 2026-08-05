package com.kaamconnect.controller.auth;

import com.kaamconnect.dao.ServiceCategoryDAO;
import com.kaamconnect.model.ServiceCategory;
import com.kaamconnect.model.WorkerProfile;
import com.kaamconnect.navigation.AppNavigator;
import com.kaamconnect.service.auth.WorkerVerificationService;
import com.kaamconnect.session.SessionManager;
import com.kaamconnect.util.Constants;
import com.kaamconnect.util.FileUtil;
import com.kaamconnect.util.Validator;
import com.kaamconnect.view.auth.SignInView;
import com.kaamconnect.view.auth.VerificationSubmittedView;
import com.kaamconnect.view.auth.WorkerVerificationView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class WorkerVerificationController {

    private final WorkerVerificationView view;
    private final AppNavigator navigator;

    private final WorkerVerificationService verificationService;
    private final ServiceCategoryDAO categoryDAO;
    private final SessionManager sessionManager;

    private final WorkerProfile existingProfile;

    private File selectedCnicFrontFile;
    private File selectedCnicBackFile;

    public WorkerVerificationController(
            WorkerVerificationView view,
            AppNavigator navigator,
            WorkerProfile existingProfile
    ) {

        this.view = view;
        this.navigator = navigator;
        this.existingProfile = existingProfile;

        verificationService =
                new WorkerVerificationService();

        categoryDAO =
                new ServiceCategoryDAO();

        sessionManager =
                SessionManager.getInstance();

        connectEvents();
        loadCategories();
        loadExistingProfile();
    }

    private void connectEvents() {

        view.getChooseCnicFrontButton().setOnAction(
                event -> chooseCnicFrontImage()
        );

        view.getChooseCnicBackButton().setOnAction(
                event -> chooseCnicBackImage()
        );

        view.getSubmitButton().setOnAction(
                event -> handleSubmit()
        );

        view.getBackButton().setOnAction(
                event -> logoutAndOpenSignIn()
        );
    }

    private void loadCategories() {

        try {

            List<ServiceCategory> categories =
                    categoryDAO.getAll();

            view.setCategories(categories);

            if (categories == null ||
                    categories.isEmpty()) {

                view.getErrorLabel().showError(
                        "No service categories are available."
                );
            }

        } catch (Exception exception) {

            view.getErrorLabel().showError(
                    "Unable to load service categories. " +
                            "The database is not connected."
            );

            exception.printStackTrace();
        }
    }

    private void loadExistingProfile() {

        if (existingProfile == null) {
            return;
        }

        if (existingProfile.getCnicNumber() != null) {

            view.getCnicNumberField().setText(
                    existingProfile.getCnicNumber()
            );
        }

        if (existingProfile.getCity() != null) {

            view.getCityField().setText(
                    existingProfile.getCity()
            );
        }

        showExistingFileNames();
        selectExistingCategory();
    }

    private void showExistingFileNames() {

        String frontPath =
                existingProfile.getCnicFrontPath();

        String backPath =
                existingProfile.getCnicBackPath();

        if (frontPath != null &&
                !frontPath.isBlank()) {

            view.setCnicFrontFileName(
                    new File(frontPath).getName()
            );
        }

        if (backPath != null &&
                !backPath.isBlank()) {

            view.setCnicBackFileName(
                    new File(backPath).getName()
            );
        }
    }

    private void selectExistingCategory() {

        int existingCategoryId =
                existingProfile.getCategoryId();

        for (ServiceCategory category :
                view.getCategoryComboBox().getItems()) {

            if (category.getCategoryId()
                    == existingCategoryId) {

                view.getCategoryComboBox()
                        .setValue(category);

                return;
            }
        }
    }

    private void chooseCnicFrontImage() {

        File selectedFile =
                FileUtil.chooseImage(
                        navigator.getStage()
                );

        if (selectedFile == null) {
            return;
        }

        if (!validateImage(selectedFile)) {
            return;
        }

        selectedCnicFrontFile =
                selectedFile;

        view.setCnicFrontFileName(
                selectedFile.getName()
        );

        view.getErrorLabel().clearError();
    }

    private void chooseCnicBackImage() {

        File selectedFile =
                FileUtil.chooseImage(
                        navigator.getStage()
                );

        if (selectedFile == null) {
            return;
        }

        if (!validateImage(selectedFile)) {
            return;
        }

        selectedCnicBackFile =
                selectedFile;

        view.setCnicBackFileName(
                selectedFile.getName()
        );

        view.getErrorLabel().clearError();
    }

    private boolean validateImage(
            File file
    ) {

        if (!Validator.isValidImage(file)) {

            view.getErrorLabel().showError(
                    "Select a JPG, JPEG or PNG image."
            );

            return false;
        }

        if (!Validator.isValidFileSize(file)) {

            view.getErrorLabel().showError(
                    "Image size must not exceed 5 MB."
            );

            return false;
        }

        return true;
    }

    private void handleSubmit() {

        view.getErrorLabel().clearError();

        String cnicNumber =
                view.getCnicNumberField()
                        .getText()
                        .trim();

        String city =
                view.getCityField()
                        .getText()
                        .trim();

        ServiceCategory selectedCategory =
                view.getCategoryComboBox()
                        .getValue();

        if (!Validator.isValidCNIC(cnicNumber)) {

            view.getErrorLabel().showError(
                    "Enter CNIC in this format: 35202-1234567-1"
            );

            return;
        }

        if (city.isBlank()) {

            view.getErrorLabel().showError(
                    "Enter your city."
            );

            return;
        }

        if (selectedCategory == null) {

            view.getErrorLabel().showError(
                    "Select one service category."
            );

            return;
        }

        if (!hasCnicFrontImage()) {

            view.getErrorLabel().showError(
                    "Select the front image of your CNIC."
            );

            return;
        }

        if (!hasCnicBackImage()) {

            view.getErrorLabel().showError(
                    "Select the back image of your CNIC."
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

            String frontImagePath =
                    getFrontImagePath();

            String backImagePath =
                    getBackImagePath();

            WorkerProfile profile;

            if (existingProfile == null) {

                profile =
                        new WorkerProfile();

            } else {

                profile =
                        existingProfile;
            }

            profile.setUserId(userId);
            profile.setCnicNumber(cnicNumber);
            profile.setCnicFrontPath(frontImagePath);
            profile.setCnicBackPath(backImagePath);
            profile.setCity(city);

            profile.setCategoryId(
                    selectedCategory.getCategoryId()
            );

            boolean submitted;

            if (existingProfile == null) {

                submitted =
                        verificationService
                                .submitApplication(profile);

            } else {

                submitted =
                        verificationService
                                .updateVerification(profile);
            }

            if (!submitted) {

                view.getErrorLabel().showError(
                        "Unable to submit the application."
                );

                return;
            }

            sessionManager.setCurrentWorkerProfile(
                    profile
            );

            openSubmittedView();

        } catch (IOException exception) {

            view.getErrorLabel().showError(
                    "Unable to save the selected CNIC images."
            );

            exception.printStackTrace();

        } catch (Exception exception) {

            view.getErrorLabel().showError(
                    "Unable to submit the application. " +
                            "Check the database connection."
            );

            exception.printStackTrace();
        }
    }

    private boolean hasCnicFrontImage() {

        if (selectedCnicFrontFile != null) {
            return true;
        }

        return existingProfile != null
                && existingProfile.getCnicFrontPath() != null
                && !existingProfile
                .getCnicFrontPath()
                .isBlank();
    }

    private boolean hasCnicBackImage() {

        if (selectedCnicBackFile != null) {
            return true;
        }

        return existingProfile != null
                && existingProfile.getCnicBackPath() != null
                && !existingProfile
                .getCnicBackPath()
                .isBlank();
    }

    private String getFrontImagePath()
            throws IOException {

        if (selectedCnicFrontFile != null) {

            return FileUtil.copyFile(
                    selectedCnicFrontFile,
                    Constants.CNIC_IMAGE_FOLDER
            );
        }

        return existingProfile.getCnicFrontPath();
    }

    private String getBackImagePath()
            throws IOException {

        if (selectedCnicBackFile != null) {

            return FileUtil.copyFile(
                    selectedCnicBackFile,
                    Constants.CNIC_IMAGE_FOLDER
            );
        }

        return existingProfile.getCnicBackPath();
    }

    private void openSubmittedView() {

        VerificationSubmittedView submittedView =
                new VerificationSubmittedView();

        submittedView
                .getBackToLoginButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        submittedView
                .getBackButton()
                .setOnAction(
                        event -> logoutAndOpenSignIn()
                );

        navigator.show(submittedView);
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