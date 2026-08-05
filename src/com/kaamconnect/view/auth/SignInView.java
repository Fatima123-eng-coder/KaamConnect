package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppLogo;
import com.kaamconnect.component.AppPasswordField;
import com.kaamconnect.component.AppTextField;
import com.kaamconnect.component.ErrorLabel;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class SignInView extends BorderPane {

    private final AppTextField emailField;
    private final AppPasswordField passwordField;

    private final ErrorLabel errorLabel;
    private final PrimaryButton signInButton;
    private final Hyperlink registerLink;

    public SignInView() {

        emailField =
                new AppTextField(
                        "Enter your email address"
                );

        passwordField =
                new AppPasswordField(
                        "Enter your password"
                );

        errorLabel =
                new ErrorLabel();

        signInButton =
                new PrimaryButton("Login");

        registerLink =
                new Hyperlink("Register");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        setPadding(
                new Insets(
                        AppDimensions.SCREEN_PADDING
                )
        );

        UIStyles.stylePageBackground(this);
        UIStyles.styleLink(registerLink);

        emailField.setMaxWidth(
                Double.MAX_VALUE
        );

        passwordField.setMaxWidth(
                Double.MAX_VALUE
        );

        AppLogo appLogo =
                new AppLogo(190);

        Label titleLabel =
                new Label("Login");

        titleLabel.setFont(
                AppFonts.pageTitle()
        );

        titleLabel.setTextFill(
                AppColors.TEXT_PRIMARY
        );

        Label descriptionLabel =
                new Label(
                        "Welcome back! Sign in to continue."
                );

        descriptionLabel.setFont(
                AppFonts.body()
        );

        descriptionLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        VBox headerBox = new VBox(
                AppDimensions.SPACING_MEDIUM,
                appLogo,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(
                Pos.CENTER
        );

        VBox emailGroup =
                createFieldGroup(
                        "Email Address",
                        emailField
                );

        VBox passwordGroup =
                createFieldGroup(
                        "Password",
                        passwordField
                );

        Label accountLabel =
                new Label(
                        "Don’t have an account?"
                );

        accountLabel.setFont(
                AppFonts.body()
        );

        accountLabel.setTextFill(
                AppColors.TEXT_SECONDARY
        );

        HBox registerRow = new HBox(
                AppDimensions.SPACING_SMALL,
                accountLabel,
                registerLink
        );

        registerRow.setAlignment(
                Pos.CENTER
        );

        AppCard signInCard =
                new AppCard();

        signInCard.getChildren().addAll(
                emailGroup,
                passwordGroup,
                errorLabel,
                signInButton,
                registerRow
        );

        VBox mainContent = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                signInCard
        );

        mainContent.setAlignment(
                Pos.CENTER
        );

        mainContent.setMaxWidth(
                AppDimensions.FORM_WIDTH
        );

        setCenter(mainContent);
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

    public AppTextField getEmailField() {
        return emailField;
    }

    public AppPasswordField getPasswordField() {
        return passwordField;
    }

    public ErrorLabel getErrorLabel() {
        return errorLabel;
    }

    public PrimaryButton getSignInButton() {
        return signInButton;
    }

    public Hyperlink getRegisterLink() {
        return registerLink;
    }
}