package com.malbolge.bookmanager.ui.bookview.listbook;

import com.malbolge.bookmanager.database.datahandlers.BookDataHandler;
import com.malbolge.bookmanager.database.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookListTableController implements Initializable {

    final ObservableList<Book> mBookList = FXCollections.observableArrayList();

    @FXML
    private AnchorPane mRootPane;
    @FXML
    private TableView<Book> mTable;
    @FXML
    private TableColumn<Book, String> mTitleColumn;
    @FXML
    private TableColumn<Book, String> mAuthorColumn;
    @FXML
    private TableColumn<Book, String> mPublisherColumn;
    @FXML
    private TableColumn<Book, Integer> mEditionColumn;
    @FXML
    private TableColumn<Book, String> mIsbnColumn;
    @FXML
    private TableColumn<Book, Integer> mAmountColumn;

    private final BookDataHandler mBookDataHandler = new BookDataHandler();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        init();
        populate();
    }

    private void init() {
        mTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        mAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
        mPublisherColumn.setCellValueFactory(new PropertyValueFactory<>("Publisher"));
        mEditionColumn.setCellValueFactory(new PropertyValueFactory<>("Edition"));
        mIsbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        mAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void populate() {
        List<Book> bookList = mBookDataHandler.getAll();

        if (bookList != null)
            mBookList.addAll(bookList);

        mTable.getItems().setAll(mBookList);
    }
}
