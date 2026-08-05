package com.kaamconnect.dao;

import com.kaamconnect.database.DBConnection;
import com.kaamconnect.enums.UserRole;
import com.kaamconnect.enums.UserStatus;
import com.kaamconnect.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;

    public UserDAO() {
        connection = DBConnection.getConnection();
    }
    // Add User
    public boolean save(User user) {

        String sql = """
                INSERT INTO Users
                (
                    first_name,
                    last_name,
                    email,
                    password,
                    phone,
                    profile_image_path,
                    role,
                    status
                )
                VALUES
                (
                    ?, ?, ?, ?, ?, ?, ?, ?
                )
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getProfileImagePath());
            statement.setString(7, user.getRole().name());
            statement.setString(8, user.getStatus().name());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
    // Get User By ID
    public User getById(int userId) {

        String sql = "SELECT * FROM Users WHERE user_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapUser(rs);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // Get User By Email

    public User getByEmail(String email) {

        String sql = "SELECT * FROM Users WHERE email = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapUser(rs);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // Get All Users

    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                users.add(mapUser(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return users;
    }


    // Map ResultSet to User Object
    private User mapUser(ResultSet rs)
            throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setProfileImagePath(rs.getString("profile_image_path"));

        user.setRole(
                UserRole.valueOf(
                        rs.getString("role")
                )
        );

        user.setStatus(
                UserStatus.valueOf(
                        rs.getString("status")
                )
        );

        Timestamp timestamp =
                rs.getTimestamp("created_at");

        if (timestamp != null) {

            user.setCreatedAt(
                    timestamp.toLocalDateTime()
            );

        }

        return user;
    }


// Update User


    public boolean update(User user) {

        String sql = """
            
                UPDATE Users
            SET
                first_name = ?,
                last_name = ?,
                email = ?,
                phone = ?,
                role = ?,
                status = ?
            WHERE user_id = ?
            """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getRole().name());
            statement.setString(6, user.getStatus().name());
            statement.setInt(7, user.getUserId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


// Update Profile Image


    public boolean updateProfileImage(int userId,
                                      String imagePath) {

        String sql =
                "UPDATE Users SET profile_image_path=? WHERE user_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, imagePath);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


// Update Password


    public boolean updatePassword(int userId,
                                  String password) {

        String sql =
                "UPDATE Users SET password=? WHERE user_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, password);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


// Update Status


    public boolean updateStatus(int userId,
                                UserStatus status) {

        String sql =
                "UPDATE Users SET status=? WHERE user_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, status.name());
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

// Delete User


    public boolean delete(int userId) {

        String sql =
                "DELETE FROM Users WHERE user_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


// Check Email Exists


    public boolean emailExists(String email) {

        String sql =
                "SELECT 1 FROM Users WHERE email=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return
false;
    }


}