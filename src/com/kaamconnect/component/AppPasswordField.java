package com.kaamconnect.component;

import com.kaamconnect.theme.UIStyles;
import javafx.scene.control.PasswordField;

public final class AppPasswordField extends PasswordField {

    public AppPasswordField(String promptText) {
        setPromptText(promptText);
        UIStyles.styleInput(this);
    }
}