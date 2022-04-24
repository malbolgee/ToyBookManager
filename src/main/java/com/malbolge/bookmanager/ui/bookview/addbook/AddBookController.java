package com.malbolge.bookmanager.ui.bookview.addbook;

import com.malbolge.bookmanager.database.datahandlers.BookDataHandler;
import com.malbolge.bookmanager.database.models.Book;
import com.malbolge.bookmanager.utils.logger.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

import static com.malbolge.bookmanager.ui.viewutils.ViewUtils.showFailureAlert;
import static com.malbolge.bookmanager.ui.viewutils.ViewUtils.showSuccessAlert;
import static com.malbolge.bookmanager.utils.TextUtils.isEmpty;

public class AddBookController {

    static final String LOG_TAG = AddBookController.class.getSimpleName();

    @FXML
    private TextField mTitle;
    @FXML
    private TextField mAuthor;
    @FXML
    private TextField mPublisher;
    @FXML
    private TextField mEdition;
    @FXML
    private TextField mIsbn;

    @FXML
    private Button mSaveButton;
    @FXML
    private Button mCancelButton;

    private final BookDataHandler mBookDataHandler = new BookDataHandler();

    @FXML
    private void onSave(ActionEvent event) {
        var book = new Book(mTitle.getText(),
                mAuthor.getText(), mPublisher.getText(), Integer.parseInt(mEdition.getText()), mIsbn.getText());

        try {
            mBookDataHandler.add(book);
            showSuccessAlert("The book has been successfully registered.");
            clearFields();
            Log.i(LOG_TAG, "Book " + book.getTitle() + " successfully added.");
        } catch (SQLException e) {
            showFailureAlert(e.getMessage());
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        System.out.println("Cancel!");
    }

    @FXML
    private void onVerifyFields(KeyEvent event) {
        // TODO: make a proper verification in the edition field.
        mSaveButton.setDisable(!isAllFieldsFilled());
    }

    private boolean isAllFieldsFilled() {
        return !isEmpty(mTitle.getText()) &&
                !isEmpty(mAuthor.getText()) &&
                !isEmpty(mPublisher.getText()) &&
                !isEmpty(mEdition.getText()) &&
                !isEmpty(mIsbn.getText());
    }

    private void clearFields() {
        mTitle.setText("");
        mAuthor.setText("");
        mPublisher.setText("");
        mEdition.setText("");
        mIsbn.setText("");
        mSaveButton.setDisable(true);
    }
}
