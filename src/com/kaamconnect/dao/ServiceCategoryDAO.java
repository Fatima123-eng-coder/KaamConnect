package com.kaamconnect.dao;

import com.kaamconnect.database.DBConnection;
import com.kaamconnect.model.ServiceCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategoryDAO {

    private final Connection connection;

    public ServiceCategoryDAO() {
        connection = DBConnection.getConnection();
    }

    // Save Category
    public boolean save(ServiceCategory category) {

        String sql = """
                INSERT INTO Service_Category
                (
                    category_name,
                    description,
                    icon_path
                )
                VALUES
                (
                    ?,?,?
                )
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getIconPath());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // Get Category By ID

    public ServiceCategory getById(int categoryId) {

        String sql =
                "SELECT * FROM Service_Category WHERE category_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return mapCategory(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    // Get All Categories

    public List<ServiceCategory> getAll() {

        List<ServiceCategory> categories = new ArrayList<>();

        String sql = "SELECT * FROM Service_Category";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                categories.add(mapCategory(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return categories;
    }


    // Update Category

    public boolean update(ServiceCategory category) {

        String sql = """
                UPDATE Service_Category
                SET
                    category_name=?,
                    description=?,
                    icon_path=?
                WHERE category_id=?
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getIconPath());
            statement.setInt(4, category.getCategoryId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // Delete Category


    public boolean delete(int categoryId) {

        String sql =
                "DELETE FROM Service_Category WHERE category_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // Check Category Exists


    public boolean existsByName(String categoryName) {

        String sql =
                "SELECT 1 FROM Service_Category WHERE category_name=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, categoryName);

            ResultSet rs = statement.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // Map ResultSet


    private ServiceCategory mapCategory(ResultSet rs)
            throws SQLException {

        ServiceCategory category = new ServiceCategory();

        category.setCategoryId(rs.getInt("category_id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setDescription(rs.getString("description"));
        category.setIconPath(rs.getString("icon_path"));

        return category;
    }

}