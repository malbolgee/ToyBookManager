package com.malbolge.bookmanager.ui.memberview.addmember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMember extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddMember.class.getResource("addMemberView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add a member");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
