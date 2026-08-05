package com.kaamconnect.navigation;

import com.kaamconnect.theme.AppDimensions;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class AppNavigator {

    private final Stage stage;

    public AppNavigator(Stage stage) {
        this.stage = stage;
    }

    public void show(Parent view) {

        if (view == null) {
            return;
        }

        Scene scene = stage.getScene();

        if (scene == null) {

            scene = new Scene(
                    view,
                    AppDimensions.WINDOW_WIDTH,
                    AppDimensions.WINDOW_HEIGHT
            );

            stage.setScene(scene);

        } else {

            scene.setRoot(view);
        }

        stage.setTitle("KaamConnect");

        /*
         * Keep the application at a fixed
         * mobile-style size.
         */
        stage.setResizable(false);

        stage.setWidth(
                AppDimensions.WINDOW_WIDTH
        );

        stage.setHeight(
                AppDimensions.WINDOW_HEIGHT
        );

        stage.centerOnScreen();

        if (!stage.isShowing()) {
            stage.show();
        }
    }

    public Stage getStage() {
        return stage;
    }
}