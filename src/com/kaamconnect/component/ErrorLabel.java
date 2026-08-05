package com.kaamconnect.component;

import com.kaamconnect.theme.UIStyles;
import javafx.scene.control.Label;

public final class ErrorLabel extends Label {

    public ErrorLabel() {
        UIStyles.styleErrorLabel(this);
        setVisible(false);
        setManaged(false);
    }

    public void showError(String message) {
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