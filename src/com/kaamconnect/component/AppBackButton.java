package com.kaamconnect.component;

import com.kaamconnect.theme.UIStyles;
import javafx.scene.control.Button;

public final class AppBackButton extends Button {

    public AppBackButton() {

        super("←");

        setAccessibleText("Go back");

        UIStyles.styleBackButton(this);
    }
}