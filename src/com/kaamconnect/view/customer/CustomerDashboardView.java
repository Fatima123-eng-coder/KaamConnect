package com.kaamconnect.view.customer;

import com.kaamconnect.component.AppCard;
import com.kaamconnect.component.PrimaryButton;
import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.AppFonts;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public final class CustomerDashboardView extends BorderPane {

    private final Label welcomeLabel;
    private final PrimaryButton logoutButton;

    public CustomerDashboardView() {

        welcomeLabel =
                new Label("Welcome, Customer");

        logoutButton =
                new PrimaryButton("Logout");

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

        Label appNameLabel =
                new Label("KaamConnect");

        appNameLabel.setFont(AppFonts.heading());
        appNameLabel.setTextFill(AppColors.PRIMARY);

        Label titleLabel =
                new Label("Customer Dashboard");

        titleLabel.setFont(AppFonts.pageTitle());
        titleLabel.setTextFill(AppColors.TEXT_PRIMARY);

        welcomeLabel.setFont(AppFonts.body());
        welcomeLabel.setTextFill(AppColors.TEXT_PRIMARY);

        Label messageLabel = new Label(
                "You have logged in successfully.\n" +
                        "Customer dashboard features will be added later."
        );

        messageLabel.setFont(AppFonts.body());
        messageLabel.setTextFill(AppColors.TEXT_SECONDARY);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        messageLabel.setMaxWidth(350);

        AppCard dashboardCard =
                new AppCard();

        dashboardCard.setAlignment(Pos.CENTER);

        dashboardCard.getChildren().addAll(
                titleLabel,
                welcomeLabel,
                messageLabel,
                logoutButton
        );

        VBox pageContent = new VBox(
                AppDimensions.SPACING_LARGE,
                appNameLabel,
                dashboardCard
        );

        pageContent.setAlignment(Pos.CENTER);
        pageContent.setMaxWidth(AppDimensions.FORM_WIDTH);

        setCenter(pageContent);
    }

    public void setCustomerName(String customerName) {

        if (customerName == null || customerName.isBlank()) {
            welcomeLabel.setText("Welcome, Customer");
        } else {
            welcomeLabel.setText("Welcome, " + customerName);
        }
    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }

    public PrimaryButton getLogoutButton() {
        return logoutButton;
    }
}