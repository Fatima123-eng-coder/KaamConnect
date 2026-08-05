package com.kaamconnect.component;

import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.theme.UIStyles;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public final class AppCard extends VBox {

    public AppCard() {

        setSpacing(AppDimensions.SPACING_MEDIUM);

        setPadding(
                new Insets(AppDimensions.CARD_PADDING)
        );

        setMaxWidth(Double.MAX_VALUE);

        UIStyles.styleCard(this);
    }
}