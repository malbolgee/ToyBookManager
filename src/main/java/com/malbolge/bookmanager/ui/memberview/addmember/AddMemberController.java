package com.malbolge.bookmanager.ui.memberview.addmember;

import com.malbolge.bookmanager.database.datahandlers.MemberDataHandler;
import com.malbolge.bookmanager.database.models.Member;
import com.malbolge.bookmanager.utils.logger.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

import static com.malbolge.bookmanager.ui.viewutils.ViewUtils.showFailureAlert;
import static com.malbolge.bookmanager.ui.viewutils.ViewUtils.showSuccessAlert;
import static com.malbolge.bookmanager.utils.TextUtils.isEmpty;

public class AddMemberController {

    private static final String PHONE_REGEX = "^\\d+$";
    private static final String CPF_REGEX = "^\\d{11}$";
    private static final String LOG_TAG = AddMemberController.class.getSimpleName();

    @FXML
    private TextField mFirstName;
    @FXML
    private TextField mLastName;
    @FXML
    private TextField mCpf;
    @FXML
    private TextField mPhone;
    @FXML
    private TextField mEmail;
    @FXML
    private CheckBox mMaleCheckBox;
    @FXML
    private CheckBox mFemaleCheckBox;

    @FXML
    private Button mSaveButton;
    @FXML
    private Button mCancelButton;

    private final MemberDataHandler mMemberDataHandler = new MemberDataHandler();

    @FXML
    private void onKeyTyped(KeyEvent event) {
        if (isAllFieldsFilled()) {
            mSaveButton.setDisable(!(isValidCpf() && isValidPhone()));
        } else {
            mSaveButton.setDisable(true);
        }
    }

    private boolean isValidCpf() {
        return mCpf.getText().matches(CPF_REGEX);
    }

    private boolean isValidPhone() {
       return mPhone.getText().matches(PHONE_REGEX);
    }

    @FXML
    private void onSave(ActionEvent event) {
        Log.d(LOG_TAG, "onSave");
        Member member = new Member(mFirstName.getText(),
                mLastName.getText(), mCpf.getText(), mPhone.getText(), mEmail.getText(), getSexString());

        try {
            mMemberDataHandler.add(member);
            showSuccessAlert("The member has been successfully registered.");
            mSaveButton.setDisable(true);
            clearFields();
            Log.i(LOG_TAG, "Member " + member.getFirstName() + " successfully inserted.");
        } catch (SQLException e) {
            showFailureAlert(e.getMessage());
            Log.e(LOG_TAG, e.getMessage());
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Log.d(LOG_TAG, "onCancel");
    }

    @FXML
    private void onSelectMale(ActionEvent event) {
        Log.d(LOG_TAG, "onSelectMale");
        if (!mMaleCheckBox.isSelected())
            mMaleCheckBox.setSelected(true);

        mFemaleCheckBox.setSelected(false);
    }

    @FXML
    private void onSelectFemale(ActionEvent event) {
        Log.d(LOG_TAG, "onSelectFemale");
        if (!mFemaleCheckBox.isSelected())
            mFemaleCheckBox.setSelected(true);

        mMaleCheckBox.setSelected(false);
    }

    private boolean isAllFieldsFilled() {
        return !isEmpty(mFirstName.getText()) &&
                !isEmpty(mLastName.getText()) &&
                !isEmpty(mCpf.getText()) &&
                !isEmpty(mEmail.getText()) &&
                !isEmpty(mPhone.getText());
    }

    private String getSexString() {
        return mMaleCheckBox.isSelected() ? "M" : "F";
    }

    private void clearFields() {
        mFirstName.setText("");
        mLastName.setText("");
        mCpf.setText("");
        mPhone.setText("");
        mEmail.setText("");
        mMaleCheckBox.setSelected(true);
        mFemaleCheckBox.setSelected(false);
    }
}
