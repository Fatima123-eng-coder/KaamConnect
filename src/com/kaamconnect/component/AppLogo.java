package com.kaamconnect.component;

import com.kaamconnect.theme.AppColors;
import com.kaamconnect.theme.AppFonts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;

public final class AppLogo extends VBox {

    public AppLogo(double width) {

        setAlignment(Pos.CENTER);

        URL logoUrl = AppLogo.class.getResource(
                "/com/kaamconnect/assets/" +
                        "kaamconnect_logo_wordmark.png"
        );

        if (logoUrl != null) {

            Image image =
                    new Image(
                            logoUrl.toExternalForm()
                    );

            ImageView imageView =
                    new ImageView(image);

            imageView.setFitWidth(width);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            getChildren().add(imageView);

        } else {

            Label fallbackLabel =
                    new Label("KaamConnect");

            fallbackLabel.setFont(
                    AppFonts.heading()
            );

            fallbackLabel.setTextFill(
                    AppColors.PRIMARY
            );

            getChildren().add(fallbackLabel);
        }
    }
}