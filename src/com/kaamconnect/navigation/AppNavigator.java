package com.kaamconnect.navigation;

import com.kaamconnect.theme.AppDimensions;
import com.kaamconnect.view.auth.RegisterView;
import com.kaamconnect.view.auth.SignInView;
import com.kaamconnect.view.auth.WorkerVerificationView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class AppNavigator {

    private final Stage stage;

    public AppNavigator(Stage stage) {
        this.stage = stage;
    }

    public void showSignIn() {
        SignInView signInView = new SignInView();
        setRoot(signInView);
    }

    public void showRegister() {
        RegisterView registerView = new RegisterView();
        setRoot(registerView);
    }

    public void showWorkerVerification() {
        WorkerVerificationView verificationView =
                new WorkerVerificationView();

        setRoot(verificationView);
    }

    private void setRoot(Parent root) {

        Scene scene = stage.getScene();

        if (scene == null) {

            scene = new Scene(
                    root,
                    AppDimensions.WINDOW_WIDTH,
                    AppDimensions.WINDOW_HEIGHT
            );

            stage.setScene(scene);

        } else {

            scene.setRoot(root);
        }

        stage.setTitle("KaamConnect");
        stage.setResizable(true);
        stage.show();
    }
}