package com.malbolge.bookmanager.ui.bookview.listbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookListTable extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookListTable.class.getResource("bookListView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Books list");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
