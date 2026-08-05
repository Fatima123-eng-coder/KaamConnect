package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppPasswordField;
import com.kaamconnect.component.AppTextField;
import com.kaamconnect.component.ErrorLabel;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.component.SecondaryButton;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public final class SignInView extends BorderPane {

    private final AppTextField emailField;
    private final AppPasswordField passwordField;

    private final ErrorLabel errorLabel;

    private final PrimaryButton signInButton;
    private final SecondaryButton registerButton;

    public SignInView() {

        emailField = new AppTextField("Enter your email");
        passwordField = new AppPasswordField("Enter your password");

        errorLabel = new ErrorLabel();

        signInButton = new PrimaryButton("Sign In");
        registerButton = new SecondaryButton("Create Account");

        configureView();
    }

    private void configureView() {

        setPrefSize(
                AppDimensions.WINDOW_WIDTH,
                AppDimensions.WINDOW_HEIGHT
        );

        setPadding(
                new Insets(AppDimensions.SCREEN_PADDING)
        );

        UIStyles.stylePageBackground(this);

        Label appNameLabel = new Label("KaamConnect");
        appNameLabel.setFont(AppFonts.heading());
        appNameLabel.setTextFill(AppColors.PRIMARY);

        Label titleLabel = new Label("Welcome Back");
        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label descriptionLabel =
                new Label("Sign in to continue to your account");

        descriptionLabel.setFont(AppFonts.body());
        descriptionLabel.setTextFill(AppColors.TEXT_SECONDARY);

        VBox headerBox = new VBox(
                AppDimensions.SPACING_SMALL,
                appNameLabel,
                titleLabel,
                descriptionLabel
        );

        headerBox.setAlignment(Pos.CENTER);

        emailField.setMaxWidth(Double.MAX_VALUE);
        passwordField.setMaxWidth(Double.MAX_VALUE);

        AppCard signInCard = new AppCard();

        signInCard.getChildren().addAll(
                emailField,
                passwordField,
                errorLabel,
                signInButton,
                registerButton
        );

        VBox mainContent = new VBox(
                AppDimensions.SPACING_LARGE,
                headerBox,
                signInCard
        );

        mainContent.setAlignment(Pos.CENTER);
        mainContent.setMaxWidth(AppDimensions.FORM_WIDTH);

        setCenter(mainContent);
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

    public SecondaryButton getRegisterButton() {
        return registerButton;
    }
}