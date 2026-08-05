package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppBackButton;
import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppLogo;
import com.kaamconnect.component.AppPasswordField;
import com.kaamconnect.component.AppTextField;
import com.kaamconnect.component.ErrorLabel;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.enums.UserRole;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public final class RegisterView extends StackPane {

    private final AppBackButton backButton;

    private final AppTextField firstNameField;
    private final AppTextField lastNameField;
    private final AppTextField emailField;
    private final AppTextField phoneField;

    private final AppPasswordField passwordField;
    private final AppPasswordField confirmPasswordField;

    private final ComboBox<UserRole> roleComboBox;

    private final ErrorLabel errorLabel;
    private final PrimaryButton registerButton;
    private final Hyperlink loginLink;

    public RegisterView() {

        backButton =
                new AppBackButton();

        firstNameField =
                new AppTextField(
                        "Enter first name"
                );

        lastNameField =
                new AppTextField(
                        "Enter last name"
                );

        emailField =
                new AppTextField(
                        "Enter email address"
                );

        phoneField =
                new AppTextField(
                        "3XXXXXXXXX"
                );

        passwordField =
                new AppPasswordField(
                        "Create password"
                );

        confirmPasswordField =
                new AppPasswordField(
                        "Confirm password"
                );

        roleComboBox =
                new ComboBox<>();

        errorLabel =
                new ErrorLabel();

        registerButton =
                new PrimaryButton(
                        "Create Account"
                );

        loginLink =
                new Hyperlink("Login");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        UIStyles.stylePageBackground(this);
        UIStyles.styleLink(loginLink);

        configureFields();
        configurePhoneField();
        configureRoleComboBox();

        AppLogo appLogo =
                new AppLogo(165);

        StackPane topBar =
                new StackPane(
                        appLogo,
                        backButton
                );

        StackPane.setAlignment(
                backButton,
                Pos.CENTER_LEFT
        );

        topBar.setMaxWidth(
                AppDimensions.FORM_WIDTH
        );

        Label titleLabel =
                new Label("Create Account");

        titleLabel.setFont(
                AppFonts.pageTitle()
        );

        titleLabel.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        Label descriptionLabel =
                new Label(
                        "Register as a customer or worker"
                );

        descriptionLabel.setFont(
                AppFonts.body()
        );

        descriptionLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        VBox headerBox = new VBox(
                AppDimensions.SPACING_MEDIUM,
                topBar,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(
                Pos.CENTER
        );

        VBox firstNameGroup =
                createFieldGroup(
                        "First Name",
                        firstNameField
                );

        VBox lastNameGroup =
                createFieldGroup(
                        "Last Name",
                        lastNameField
                );

        VBox emailGroup =
                createFieldGroup(
                        "Email Address",
                        emailField
                );

        VBox phoneGroup =
                createFieldGroup(
                        "Phone Number",
                        createPhoneContainer()
                );

        Label phoneHelpLabel =
                new Label(
                        "Enter 10 digits after +92, starting with 3"
                );

        phoneHelpLabel.setFont(
                AppFonts.small()
        );

        phoneHelpLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        phoneGroup.getChildren().add(
                phoneHelpLabel
        );

        VBox passwordGroup =
                createFieldGroup(
                        "Password",
                        passwordField
                );

        Label passwordHelpLabel =
                new Label(
                        "Minimum 8 characters with uppercase, " +
                                "lowercase, number and special character"
                );

        passwordHelpLabel.setFont(
                AppFonts.small()
        );

        passwordHelpLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        passwordHelpLabel.setWrapText(true);

        passwordGroup.getChildren().add(
                passwordHelpLabel
        );

        VBox confirmPasswordGroup =
                createFieldGroup(
                        "Confirm Password",
                        confirmPasswordField
                );

        VBox roleGroup =
                createFieldGroup(
                        "Select Role",
                        roleComboBox
                );

        Label accountLabel =
                new Label(
                        "Already have an account?"
                );

        accountLabel.setFont(
                AppFonts.body()
        );

        accountLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        HBox loginRow = new HBox(
                AppDimensions.SPACING_SMALL,
                accountLabel,
                loginLink
        );

        loginRow.setAlignment(
                Pos.CENTER
        );

        AppCard registerCard =
                new AppCard();

        registerCard.getChildren().addAll(
                firstNameGroup,
                lastNameGroup,
                emailGroup,
                phoneGroup,
                passwordGroup,
                confirmPasswordGroup,
                roleGroup,
                errorLabel,
                registerButton,
                loginRow
        );

        VBox pageContent = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                registerCard
        );

        pageContent.setAlignment(
                Pos.TOP_CENTER
        );

        pageContent.setMaxWidth(
                AppDimensions.FORM_WIDTH
        );

        pageContent.setPadding(
                new Insets(
                        AppDimensions.SCREEN_PADDING
                )
        );

        ScrollPane scrollPane =
                new ScrollPane(pageContent);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setBackground(
                Background.EMPTY
        );

        scrollPane.setBorder(
                Border.EMPTY
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;"
        );

        getChildren().add(scrollPane);
    }

    private void configureFields() {

        firstNameField.setMaxWidth(
                Double.MAX_VALUE
        );

        lastNameField.setMaxWidth(
                Double.MAX_VALUE
        );

        emailField.setMaxWidth(
                Double.MAX_VALUE
        );

        passwordField.setMaxWidth(
                Double.MAX_VALUE
        );

        confirmPasswordField.setMaxWidth(
                Double.MAX_VALUE
        );
    }

    private void configurePhoneField() {

        phoneField.setMaxWidth(
                Double.MAX_VALUE
        );

        phoneField.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 0 10 0 10;" +
                        "-fx-font-size: 14px;"
        );

        TextFormatter<String> formatter =
                new TextFormatter<>(
                        change -> {

                            String newText =
                                    change
                                            .getControlNewText();

                            if (newText.matches(
                                    "\\d{0,10}"
                            )) {
                                return change;
                            }

                            return null;
                        }
                );

        phoneField.setTextFormatter(
                formatter
        );
    }

    private HBox createPhoneContainer() {

        Label prefixLabel =
                new Label("+92");

        prefixLabel.setFont(
                AppFonts.body()
        );

        prefixLabel.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        Label separatorLabel =
                new Label("|");

        separatorLabel.setTextFill(
                AppColors.BORDER
        );

        HBox.setHgrow(
                phoneField,
                Priority.ALWAYS
        );

        HBox container = new HBox(
                AppDimensions.SPACING_SMALL,
                prefixLabel,
                separatorLabel,
                phoneField
        );

        container.setAlignment(
                Pos.CENTER_LEFT
        );

        container.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        container.setMaxWidth(
                Double.MAX_VALUE
        );

        container.setPadding(
                new Insets(0, 12, 0, 14)
        );

        container.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: "
                        + AppColors.BORDER_HEX + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;"
        );

        return container;
    }

    private void configureRoleComboBox() {

        roleComboBox.getItems().setAll(
                UserRole.CUSTOMER,
                UserRole.WORKER
        );

        roleComboBox.setPromptText(
                "Select Customer or Worker"
        );

        roleComboBox.setVisibleRowCount(2);

        UIStyles.styleComboBox(
                roleComboBox
        );

        roleComboBox.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            UserRole role
                    ) {

                        if (role == null) {
                            return "";
                        }

                        return switch (role) {
                            case CUSTOMER ->
                                    "Customer";

                            case WORKER ->
                                    "Worker";

                            default ->
                                    role.name();
                        };
                    }

                    @Override
                    public UserRole fromString(
                            String value
                    ) {
                        return null;
                    }
                }
        );
    }

    private VBox createFieldGroup(
            String labelText,
            Node field
    ) {

        Label label =
                new Label(labelText);

        label.setFont(
                AppFonts.body()
        );

        label.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        VBox group = new VBox(
                AppDimensions.SPACING_SMALL,
                label,
                field
        );

        group.setMaxWidth(
                Double.MAX_VALUE
        );

        return group;
    }

    public AppBackButton getBackButton() {
        return backButton;
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

    public Hyperlink getLoginLink() {
        return loginLink;
    }
}