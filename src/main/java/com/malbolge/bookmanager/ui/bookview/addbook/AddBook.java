package com.malbolge.bookmanager.ui.bookview.addbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBook extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddBook.class.getResource("addBookView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Add a book");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
