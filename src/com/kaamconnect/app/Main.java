package com.kaamconnect.app;

import com.kaamconnect.dao.UserDAO;
import com.kaamconnect.enums.UserRole;
import com.kaamconnect.enums.UserStatus;
import com.kaamconnect.model.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        Scene scene = new Scene(root, 1200, 700);

        primaryStage.setTitle("KaamConnect");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

        public static void main(String[] args) {



            UserDAO dao = new UserDAO();

            User user = new User();

            user.setFirstName("Fatima");
            user.setLastName("Saleem");
            user.setEmail("fatima@gmail.com");

            user.setPassword("123456");

            user.setPhone("03123456789");

            user.setRole(UserRole.CUSTOMER);

            user.setStatus(UserStatus.ACTIVE);

            boolean result = dao.save(user);

            System.out.println(result);

        }

    }
