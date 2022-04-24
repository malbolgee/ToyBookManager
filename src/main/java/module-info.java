module com.bookmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires jsr305;
    requires javafx.base;

    opens com.malbolge.bookmanager.ui.main to javafx.fxml;
    opens com.malbolge.bookmanager.ui.bookview.addbook to javafx.fxml;
    opens com.malbolge.bookmanager.ui.bookview.listbook to javafx.fxml;
    opens com.malbolge.bookmanager.ui.memberview.addmember to javafx.fxml;
    opens com.malbolge.bookmanager.ui.memberview.listmember to javafx.fxml;

    exports com.malbolge.bookmanager.ui.main;
    exports com.malbolge.bookmanager.ui.bookview.addbook;
    exports com.malbolge.bookmanager.ui.bookview.listbook;
    exports com.malbolge.bookmanager.ui.memberview.addmember;
    exports com.malbolge.bookmanager.ui.memberview.listmember;
    exports com.malbolge.bookmanager.database.models;
    exports com.malbolge.bookmanager.utils;
}