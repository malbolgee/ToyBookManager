package com.malbolge.bookmanager.database.datahandlers;

import com.malbolge.bookmanager.database.models.Member;
import com.malbolge.bookmanager.utils.logger.Log;

import javax.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Handles the {@link Member} database related business. */
public class MemberDataHandler extends DatabaseHandler<Member> {

    private static final String QUERY = "SELECT * FROM member";
    private static final String DELETE = "DELETE FROM member WHERE _id=?;";
    private static final String INSERT = "INSERT INTO member(firstName, lastName, cpf, phone, email, sex) " +
            "VALUES(?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE member SET firstName=?,lastName=?,cpf=?,phone=?,email=?,sex=? WHERE _id=?;";

    private static final String LOG_TAG = MemberDataHandler.class.getSimpleName();

    public MemberDataHandler() {}

    @Override
    public void add(@Nonnull final Member member) throws SQLException {
        Log.d(LOG_TAG, "add");
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getCpf());
            stmt.setString(4, member.getPhone());
            stmt.setString(5, member.getEmail());
            stmt.setString(6, member.getSex());
            stmt.execute();
        }
    }

    @Override
    public void remove(@Nonnull final Member member) throws SQLException {
        Log.d(LOG_TAG, "remove");
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, member.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(@Nonnull final Member member) throws SQLException {
        Log.d(LOG_TAG, "update");
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getCpf());
            stmt.setString(4, member.getPhone());
            stmt.setString(5, member.getEmail());
            stmt.setString(6, member.getSex());
            stmt.setInt(7, member.getId());
        }
    }

    @Override
    public List<Member> getAll() {
        Log.d(LOG_TAG, "getAll");
        List<Member> memberList = new ArrayList<>();

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(QUERY);

            while (result.next()) {
                memberList.add(new Member(result.getInt("_id"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("cpf"), result.getString("phone"),
                        result.getString("email"), result.getString("sex")));
            }
            return memberList;
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error on connecting to the database: " + e.getMessage());
        }

        return memberList;
    }
}
