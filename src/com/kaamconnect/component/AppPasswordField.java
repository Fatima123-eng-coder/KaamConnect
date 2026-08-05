package com.kaamconnect.component;

import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.UIStyles;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public final class AppPasswordField extends HBox {

    private final PasswordField passwordField;
    private final TextField visibleTextField;
    private final Button eyeButton;

    private boolean passwordVisible;

    public AppPasswordField(
            String promptText
    ) {

        passwordField =
                new PasswordField();

        visibleTextField =
                new TextField();

        eyeButton =
                new Button("👁");

        passwordField.setPromptText(
                promptText
        );

        visibleTextField.setPromptText(
                promptText
        );

        visibleTextField.textProperty()
                .bindBidirectional(
                        passwordField.textProperty()
                );

        configureFields();
        configureEyeButton();
        configureLayout();
    }

    private void configureFields() {

        String fieldStyle =
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 0 10 0 14;" +
                        "-fx-font-size: 14px;";

        passwordField.setStyle(fieldStyle);
        visibleTextField.setStyle(fieldStyle);

        passwordField.setMaxWidth(
                Double.MAX_VALUE
        );

        visibleTextField.setMaxWidth(
                Double.MAX_VALUE
        );

        passwordField.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        visibleTextField.setPrefHeight(
                AppDimensions.INPUT_HEIGHT
        );

        visibleTextField.setVisible(false);
        visibleTextField.setManaged(false);
    }

    private void configureEyeButton() {

        eyeButton.setFocusTraversable(false);

        eyeButton.setAccessibleText(
                "Show or hide password"
        );

        eyeButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-font-size: 15px;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 0 12 0 8;"
        );

        eyeButton.setOnAction(
                event -> togglePasswordVisibility()
        );
    }

    private void configureLayout() {

        StackPane fieldContainer =
                new StackPane(
                        passwordField,
                        visibleTextField
                );

        HBox.setHgrow(
                fieldContainer,
                Priority.ALWAYS
        );

        setAlignment(Pos.CENTER);
        setMaxWidth(Double.MAX_VALUE);

        UIStyles.stylePasswordContainer(this);

        getChildren().addAll(
                fieldContainer,
                eyeButton
        );
    }

    private void togglePasswordVisibility() {

        passwordVisible =
                !passwordVisible;

        visibleTextField.setVisible(
                passwordVisible
        );

        visibleTextField.setManaged(
                passwordVisible
        );

        passwordField.setVisible(
                !passwordVisible
        );

        passwordField.setManaged(
                !passwordVisible
        );

        if (passwordVisible) {

            visibleTextField.requestFocus();

            visibleTextField.positionCaret(
                    visibleTextField
                            .getText()
                            .length()
            );

        } else {

            passwordField.requestFocus();

            passwordField.positionCaret(
                    passwordField
                            .getText()
                            .length()
            );
        }
    }

    public String getText() {
        return passwordField.getText();
    }

    public void setText(String text) {
        passwordField.setText(text);
    }

    public void clear() {
        passwordField.clear();
    }

    public void setOnAction(
            EventHandler<ActionEvent> handler
    ) {

        passwordField.setOnAction(handler);
        visibleTextField.setOnAction(handler);
    }
}