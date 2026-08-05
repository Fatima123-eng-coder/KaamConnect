package com.kaamconnect.component;

import com.kaamconnect.theme.UIStyles;
import javafx.scene.control.Button;

public final class SecondaryButton extends Button {

    public SecondaryButton(String text) {

        super(text);
        UIStyles.styleSecondaryButton(this);
    }
}