package com.malbolge.bookmanager.database.datahandlers;

import com.malbolge.bookmanager.utils.logger.Log;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Base class to Database core business. All classes that wish to make database manipulation must implement it. */
public abstract class DatabaseHandler<T> {

    private static final String LOG_TAG = DatabaseHandler.class.getSimpleName();

    private static final String DB_URL = "jdbc:sqlite:data/books.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Log.i(LOG_TAG, "Connection done successfully!");
        return conn;
    }

    @Nonnull
    public static List<String> getTables() {
        try {
            final Connection conn = connect();
            final ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
            final List<String> tableNames = new ArrayList<>();
            while (rs.next())
                tableNames.add(rs.getString("TABLE_NAME"));

            conn.close();
            return tableNames;
        } catch (SQLException e) {
            Log.e(LOG_TAG, "There was an error in the database connection: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public abstract void add(@Nonnull final T object) throws SQLException;

    public abstract void remove(@Nonnull final T object) throws SQLException;

    public abstract void update(@Nonnull final T object) throws SQLException;

    public abstract List<T> getAll();
}
