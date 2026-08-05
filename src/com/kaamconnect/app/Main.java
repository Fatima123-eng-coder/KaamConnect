package com.kaamconnect.app;

import com.kaamconnect.controller.auth.SignInController;
import com.kaamconnect.navigation.AppNavigator;
import com.kaamconnect.view.auth.SignInView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public final class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        InputStream iconStream = Main.class.getResourceAsStream(
                "/com/kaamconnect/assets/kaamconnect_app_icon.png"
        );

        if (iconStream != null) {
            primaryStage.getIcons().add(new Image(iconStream));
        }

        AppNavigator navigator =
                new AppNavigator(primaryStage);

        SignInView signInView =
                new SignInView();

        new SignInController(
                signInView,
                navigator
        );

        navigator.show(signInView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}