package com.malbolge.bookmanager.database.datahandlers;

import com.malbolge.bookmanager.database.models.Book;
import com.malbolge.bookmanager.utils.logger.Log;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Handles the {@link Book} database related business. */
public class BookDataHandler extends DatabaseHandler<Book> {

    private static final String QUERY = "SELECT * FROM book";
    private static final String DELETE = "DELETE FROM book WHERE _id=?;";
    private static final String INSERT = "INSERT INTO book(title, author, publisher, edition, isbn, amount) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE book SET title=?,author=?,publisher=?,edition=?,isbn=?,amount=? WHERE _id=?;";

    private static final String LOG_TAG = BookDataHandler.class.getSimpleName();

    public BookDataHandler() {}

    public void add(@Nonnull final Book book) throws SQLException {
        Log.d(LOG_TAG, "Book to add: " + book);
        Book inBook = getBook(book.getIsbn());
        if (inBook != null) {
            Log.i(LOG_TAG, "The book: " + book.getTitle() + " is already registered, trying to raise its amount" +
                    " stead");
            inBook.setAmount(inBook.getAmount() + 1);
            update(inBook);
            return;
        }

        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getEdition());
            stmt.setString(5, book.getIsbn());
            stmt.setInt(6, 1);
            stmt.execute();
        }
    }

    @Override
    public void remove(@Nonnull final Book book) throws SQLException {
        Log.d(LOG_TAG, "Book to remove: " + book);
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, book.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(@Nonnull final Book book)  throws SQLException {
        Log.d(LOG_TAG, "Book to be updated: " + book);
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getEdition());
            stmt.setString(5, book.getIsbn());
            stmt.setInt(6, book.getAmount());
            stmt.setInt(7, book.getId());
            stmt.executeUpdate();
        }
    }

    public List<Book> getAll() {
        final List<Book> bookList = new ArrayList<>();

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(QUERY);

            while (result.next()) {
                bookList.add(new Book(result.getInt("_id"), result.getInt("amount"),
                        result.getString("title"), result.getString("author"), result.getString("publisher"),
                        result.getInt("edition"), result.getString("isbn")));
            }
            return bookList;
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error on connecting to the database: " + e.getMessage());
        }
        return bookList;
    }

    @Nullable
    public Book getBook(@Nonnull final String isbn) {
        final String sql = "SELECT * FROM book WHERE isbn=?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, isbn);

            ResultSet rs = stmt.executeQuery();

            Book book = null;
            while (rs.next()) {
                book = new Book(rs.getInt("_id"), rs.getInt("amount"), rs.getString("title"),
                        rs.getString("author"), rs.getString("publisher"), rs.getInt("edition"), rs.getString("isbn"));
            }
            return book;
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Could not retrieve book: " + e.getMessage());
            return null;
        }
    }
}
