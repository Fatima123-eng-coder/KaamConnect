package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppBackButton;
import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppLogo;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public final class VerificationPendingView extends BorderPane {

    private final AppBackButton backButton;
    private final PrimaryButton backToLoginButton;

    public VerificationPendingView() {

        backButton =
                new AppBackButton();

        backToLoginButton =
                new PrimaryButton("Back to Login");

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

        AppLogo logo =
                new AppLogo(165);

        StackPane topBar =
                new StackPane(logo, backButton);

        StackPane.setAlignment(
                backButton,
                Pos.CENTER_LEFT
        );

        Label statusLabel =
                new Label("UNDER REVIEW");

        statusLabel.setFont(AppFonts.body());
        statusLabel.setTextFill(AppColors.SUCCESS);

        Label titleLabel =
                new Label("Application Pending");

        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label messageLabel = new Label(
                "Your worker application is still under review."
        );

        messageLabel.setFont(AppFonts.body());
        messageLabel.setTextFill(AppColors.TEXT_SECONDARY);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        Label informationLabel = new Label(
                "You will be able to continue after admin approval."
        );

        informationLabel.setFont(AppFonts.small());
        informationLabel.setTextFill(AppColors.TEXT_SECONDARY);
        informationLabel.setWrapText(true);
        informationLabel.setTextAlignment(TextAlignment.CENTER);

        AppCard card =
                new AppCard();

        card.setAlignment(Pos.CENTER);

        card.getChildren().addAll(
                statusLabel,
                titleLabel,
                messageLabel,
                informationLabel,
                backToLoginButton
        );

        VBox content = new VBox(
                AppDimensions.SPACING_LARGE,
                topBar,
                card
        );

        content.setAlignment(Pos.CENTER);
        content.setMaxWidth(AppDimensions.FORM_WIDTH);

        setCenter(content);
    }

    public AppBackButton getBackButton() {
        return backButton;
    }

    public PrimaryButton getBackToLoginButton() {
        return backToLoginButton;
    }
}