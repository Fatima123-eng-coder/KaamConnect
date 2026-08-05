package com.kaamconnect.theme;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Region;

public final class UIStyles {

    private UIStyles() {
    }

    public static void stylePageBackground(
            Region region
    ) {

        region.setStyle(
                "-fx-background-color: "
                        + AppColors.BACKGROUND_HEX
                        + ";"
        );
    }

    public static void styleCard(
            Region region
    ) {

        region.setStyle(
                "-fx-background-color: "
                        + AppColors.SURFACE_HEX + ";" +
                        "-fx-background-radius: "
                        + AppDimensions.BORDER_RADIUS + ";" +
                        "-fx-border-color: "
                        + AppColors.BORDER_HEX + ";" +
                        "-fx-border-radius: "
                        + AppDimensions.BORDER_RADIUS + ";" +
                        "-fx-border-width: 1;"
        );
    }

    public static void styleInput(
            Control control
    ) {

        control.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        control.setStyle(
                "-fx-background-color: "
                        + AppColors.SURFACE_HEX + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: "
                        + AppColors.BORDER_HEX + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;" +
                        "-fx-padding: 0 14 0 14;" +
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: "
                        + AppColors.PRIMARY_HEX + ";" +
                        "-fx-prompt-text-fill: "
                        + AppColors.TEXT_SECONDARY_HEX + ";"
        );
    }

    public static void stylePasswordContainer(
            Region region
    ) {

        region.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        region.setStyle(
                "-fx-background-color: "
                        + AppColors.SURFACE_HEX + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: "
                        + AppColors.BORDER_HEX + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;"
        );
    }

    public static void stylePrimaryButton(
            ButtonBase button
    ) {

        button.setPrefHeight(
                AppDimensions.BUTTON_HEIGHT
        );

        button.setMaxWidth(Double.MAX_VALUE);
        button.setCursor(Cursor.HAND);

        button.setStyle(
                "-fx-background-color: "
                        + AppColors.PRIMARY_HEX + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 18 10 18;"
        );
    }

    public static void styleSecondaryButton(
            ButtonBase button
    ) {

        button.setPrefHeight(
                AppDimensions.BUTTON_HEIGHT
        );

        button.setMaxWidth(Double.MAX_VALUE);
        button.setCursor(Cursor.HAND);

        button.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: "
                        + AppColors.PRIMARY_HEX + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-text-fill: "
                        + AppColors.PRIMARY_HEX + ";" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 10 18 10 18;"
        );
    }

    public static void styleLink(
            Hyperlink hyperlink
    ) {

        hyperlink.setCursor(Cursor.HAND);

        hyperlink.setStyle(
                "-fx-border-color: transparent;" +
                        "-fx-padding: 0;" +
                        "-fx-text-fill: "
                        + AppColors.PRIMARY_HEX + ";" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );
    }

    public static void styleBackButton(
            Button button
    ) {

        button.setCursor(Cursor.HAND);
        button.setFocusTraversable(false);

        button.setPrefSize(38, 38);

        button.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-text-fill: "
                        + AppColors.PRIMARY_HEX + ";" +
                        "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 0;"
        );
    }

    public static void styleComboBox(
            ComboBox<?> comboBox
    ) {

        comboBox.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        comboBox.setMaxWidth(Double.MAX_VALUE);

        comboBox.setStyle(
                "-fx-background-color: "
                        + AppColors.SURFACE_HEX + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: "
                        + AppColors.BORDER_HEX + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;" +
                        "-fx-font-size: 14px;"
        );
    }
}