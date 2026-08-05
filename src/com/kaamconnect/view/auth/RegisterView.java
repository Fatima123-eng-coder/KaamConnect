package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppPasswordField;
import com.kaamconnect.component.AppTextField;
import com.kaamconnect.component.ErrorLabel;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.component.SecondaryButton;
import com.kaamconnect.enums.UserRole;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public final class RegisterView extends BorderPane {

    private final AppTextField firstNameField;
    private final AppTextField lastNameField;
    private final AppTextField emailField;
    private final AppTextField phoneField;

    private final AppPasswordField passwordField;
    private final AppPasswordField confirmPasswordField;

    private final ComboBox<UserRole> roleComboBox;

    private final ErrorLabel errorLabel;

    private final PrimaryButton registerButton;
    private final SecondaryButton backButton;

    public RegisterView() {

        firstNameField =
                new AppTextField("Enter your first name");

        lastNameField =
                new AppTextField("Enter your last name");

        emailField =
                new AppTextField("Enter your email");

        phoneField =
                new AppTextField("Enter your phone number");

        passwordField =
                new AppPasswordField("Create a password");

        confirmPasswordField =
                new AppPasswordField("Confirm your password");

        roleComboBox = new ComboBox<>();

        errorLabel = new ErrorLabel();

        registerButton =
                new PrimaryButton("Create Account");

        backButton =
                new SecondaryButton("Back to Sign In");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        UIStyles.stylePageBackground(this);

        configureRoleComboBox();
        configureInputWidths();

        Label appNameLabel = new Label("KaamConnect");
        appNameLabel.setFont(AppFonts.heading());
        appNameLabel.setTextFill(AppColors.PRIMARY);

        Label titleLabel = new Label("Create Account");
        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label descriptionLabel =
                new Label("Register as a customer or worker");

        descriptionLabel.setFont(AppFonts.body());
        descriptionLabel.setTextFill(AppColors.TEXT_SECONDARY);

        VBox headerBox = new VBox(
                AppDimensions.SPACING_SMALL,
                appNameLabel,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(Pos.CENTER);

        AppCard registerCard = new AppCard();

        registerCard.getChildren().addAll(
                firstNameField,
                lastNameField,
                emailField,
                phoneField,
                passwordField,
                confirmPasswordField,
                roleComboBox,
                errorLabel,
                registerButton,
                backButton
        );

        VBox pageContent = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                registerCard
        );

        pageContent.setAlignment(Pos.TOP_CENTER);

        pageContent.setPadding(
                new Insets(AppDimensions.SCREEN_PADDING)
        );

        ScrollPane scrollPane = new ScrollPane(pageContent);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setBorder(null);

        setCenter(scrollPane);
    }

    private void configureRoleComboBox() {

        roleComboBox.getItems().addAll(
                UserRole.CUSTOMER,
                UserRole.WORKER
        );

        roleComboBox.setPromptText("Select account type");
        roleComboBox.setPrefHeight(AppDimensions.INPUT_HEIGHT);
        roleComboBox.setMaxWidth(Double.MAX_VALUE);
        roleComboBox.setVisibleRowCount(2);
    }

    private void configureInputWidths() {

        firstNameField.setMaxWidth(Double.MAX_VALUE);
        lastNameField.setMaxWidth(Double.MAX_VALUE);
        emailField.setMaxWidth(Double.MAX_VALUE);
        phoneField.setMaxWidth(Double.MAX_VALUE);
        passwordField.setMaxWidth(Double.MAX_VALUE);
        confirmPasswordField.setMaxWidth(Double.MAX_VALUE);
    }

    public AppTextField getFirstNameField() {
        return firstNameField;
    }

    public AppTextField getLastNameField() {
        return lastNameField;
    }

    public AppTextField getEmailField() {
        return emailField;
    }

    public AppTextField getPhoneField() {
        return phoneField;
    }

    public AppPasswordField getPasswordField() {
        return passwordField;
    }

    public AppPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public ComboBox<UserRole> getRoleComboBox() {
        return roleComboBox;
    }

    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }

    public PrimaryButton getRegisterButton() {
        return registerButton;
    }

    public SecondaryButton getBackButton() {
        return backButton;
    }
}