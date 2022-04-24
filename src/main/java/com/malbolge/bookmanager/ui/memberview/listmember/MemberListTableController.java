package com.malbolge.bookmanager.ui.memberview.listmember;

import com.malbolge.bookmanager.database.datahandlers.MemberDataHandler;
import com.malbolge.bookmanager.database.models.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MemberListTableController implements Initializable {

    private final MemberDataHandler mMemberDataHandler = new MemberDataHandler();
    final ObservableList<Member> mMemberList = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> mTable;
    @FXML
    private TableColumn<Member, String> mName;
    @FXML
    private TableColumn<Member, String> mCpf;
    @FXML
    private TableColumn<Member, String> mPhone;
    @FXML
    private TableColumn<Member, String> mEmail;
    @FXML
    private TableColumn<Member, String> mSex;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        init();
        populate();
    }

    private void init() {
        mName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        mPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        mSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
    }

    private void populate() {
        List<Member> memberList = mMemberDataHandler.getAll();

        if (memberList != null)
            mMemberList.addAll(memberList);

        mTable.getItems().setAll(mMemberList);
    }
}
