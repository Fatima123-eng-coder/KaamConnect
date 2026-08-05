package com.kaamconnect.component;

import com.kaamconnect.theme.UIStyles;
import javafx.scene.control.Button;

public final class PrimaryButton extends Button {

    public PrimaryButton(String text) {
        super(text);
        UIStyles.stylePrimaryButton(this);
    }
}
