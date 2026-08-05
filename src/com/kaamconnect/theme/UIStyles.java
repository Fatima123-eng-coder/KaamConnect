package com.kaamconnect.theme;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public final class UIStyles {

    // Applies the main indigo style to primary buttons
    public static void stylePrimaryButton(ButtonBase button) {

        button.setFont(AppFonts.button());
        button.setTextFill(AppColors.TEXT_ON_PRIMARY);

        button.setPrefHeight(AppDimensions.BUTTON_HEIGHT);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setCursor(Cursor.HAND);

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                AppColors.PRIMARY,
                                new CornerRadii(
                                        AppDimensions.RADIUS_MEDIUM
                                ),
                                Insets.EMPTY
                        )
                )
        );
    }


    // Applies the white background and indigo border to secondary buttons
    public static void styleSecondaryButton(ButtonBase button) {

        button.setFont(AppFonts.button());
        button.setTextFill(AppColors.PRIMARY);

        button.setPrefHeight(AppDimensions.BUTTON_HEIGHT);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setCursor(Cursor.HAND);

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                AppColors.SURFACE,
                                new CornerRadii(
                                        AppDimensions.RADIUS_MEDIUM
                                ),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                createBorder(
                        AppColors.PRIMARY,
                        AppDimensions.RADIUS_MEDIUM
                )
        );
    }


    // Applies the standard design to text fields and password fields
    public static void styleInput(TextInputControl input) {

        input.setFont(AppFonts.body());
        input.setPrefHeight(AppDimensions.INPUT_HEIGHT);

        input.setBackground(
                new Background(
                        new BackgroundFill(
                                AppColors.SURFACE,
                                new CornerRadii(
                                        AppDimensions.RADIUS_SMALL
                                ),
                                Insets.EMPTY
                        )
                )
        );

        input.setBorder(
                createBorder(
                        AppColors.BORDER,
                        AppDimensions.RADIUS_SMALL
                )
        );

        // Changes the border colour when the input is selected
        input.focusedProperty().addListener(
                (observable, oldValue, isFocused) -> {

                    Color borderColor;

                    if (isFocused) {
                        borderColor = AppColors.FOCUS_BORDER;
                    } else {
                        borderColor = AppColors.BORDER;
                    }

                    input.setBorder(
                            createBorder(
                                    borderColor,
                                    AppDimensions.RADIUS_SMALL
                            )
                    );
                }
        );
    }


    // Applies a white card design with rounded corners
    public static void styleCard(Region card) {

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                AppColors.SURFACE,
                                new CornerRadii(
                                        AppDimensions.RADIUS_LARGE
                                ),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                createBorder(
                        AppColors.BORDER,
                        AppDimensions.RADIUS_LARGE
                )
        );
    }


    // Applies the main application background
    public static void stylePageBackground(Region page) {

        page.setBackground(
                new Background(
                        new BackgroundFill(
                                AppColors.BACKGROUND,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
    }


    // Applies error styling to validation messages
    public static void styleErrorLabel(Label label) {

        label.setFont(AppFonts.small());
        label.setTextFill(AppColors.ERROR);
    }


    // Creates a reusable border
    private static Border createBorder(
            Color color,
            double radius
    ) {

        return new Border(
                new BorderStroke(
                        color,
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(radius),
                        new BorderWidths(1)
                )
        );
    }


    // Prevents creation of UIStyles objects
    private UIStyles() {
    }
}