package com.malbolge.bookmanager.ui.memberview.listmember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberListTable extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MemberListTable.class.getResource("memberListView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Member List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
