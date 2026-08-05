package com.kaamconnect.component;

import com.kaamconnect.theme.UIStyles;
import javafx.scene.control.TextField;

public final class AppTextField extends TextField {

    public AppTextField(String promptText) {
        setPromptText(promptText);
        UIStyles.styleInput(this);
    }
}