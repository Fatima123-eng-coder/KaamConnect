package com.kaamconnect.view.auth;

import com.kaamconnect.component.AppBackButton;
import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.AppLogo;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public final class VerificationRejectedView extends BorderPane {

    private final AppBackButton backButton;
    private final Label rejectionReasonLabel;
    private final PrimaryButton editApplicationButton;
    private final SecondaryButton backToLoginButton;

    public VerificationRejectedView() {

        backButton =
                new AppBackButton();

        rejectionReasonLabel =
                new Label("No rejection reason provided.");

        editApplicationButton =
                new PrimaryButton("Edit Application");

        backToLoginButton =
                new SecondaryButton("Back to Login");

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
                new Label("REJECTED");

        statusLabel.setFont(AppFonts.body());
        statusLabel.setTextFill(AppColors.ERROR);

        Label titleLabel =
                new Label("Application Rejected");

        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label messageLabel =
                new Label("Your verification application was rejected.");

        messageLabel.setFont(AppFonts.body());
        messageLabel.setTextFill(AppColors.TEXT_SECONDARY);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        Label reasonTitleLabel =
                new Label("Rejection Reason");

        reasonTitleLabel.setFont(AppFonts.body());
        reasonTitleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        rejectionReasonLabel.setFont(AppFonts.body());
        rejectionReasonLabel.setTextFill(AppColors.ERROR);
        rejectionReasonLabel.setWrapText(true);
        rejectionReasonLabel.setTextAlignment(TextAlignment.CENTER);

        AppCard card =
                new AppCard();

        card.setAlignment(Pos.CENTER);

        card.getChildren().addAll(
                statusLabel,
                titleLabel,
                messageLabel,
                reasonTitleLabel,
                rejectionReasonLabel,
                editApplicationButton,
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

    public void setRejectionReason(String reason) {

        rejectionReasonLabel.setText(
                reason == null || reason.isBlank()
                        ? "No rejection reason provided."
                        : reason
        );
    }

    public AppBackButton getBackButton() {
        return backButton;
    }

    public Label getRejectionReasonLabel() {
        return rejectionReasonLabel;
    }

    public PrimaryButton getEditApplicationButton() {
        return editApplicationButton;
    }

    public SecondaryButton getBackToLoginButton() {
        return backToLoginButton;
    }
}