package com.kaamconnect.component;

import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppFonts;
import javafx.scene.control.Label;

public final class ErrorLabel extends Label {

    public ErrorLabel() {

        setFont(AppFonts.small());
        setTextFill(AppColors.ERROR);
        setWrapText(true);

        setVisible(false);
        setManaged(false);
    }

    public void showError(String message) {

        if (message == null || message.isBlank()) {
            clearError();
            return;
        }

        setText(message);
        setVisible(true);
        setManaged(true);
    }

    public void clearError() {

        setText("");
        setVisible(false);
        setManaged(false);
    }
}