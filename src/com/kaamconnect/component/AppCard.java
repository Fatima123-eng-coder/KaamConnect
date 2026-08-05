package com.kaamconnect.component;

import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public final class AppCard extends VBox {

    public AppCard() {

        setSpacing(AppDimensions.SPACING_MEDIUM);

        setPadding(
                new Insets(AppDimensions.PADDING_LARGE)
        );

        setMaxWidth(AppDimensions.FORM_WIDTH);

        UIStyles.styleCard(this);
    }
}