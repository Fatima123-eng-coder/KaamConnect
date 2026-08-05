package com.kaamconnect.dao;

import com.kaamconnect.database.DBConnection;
import com.kaamconnect.enums.ApprovalStatus;
import com.kaamconnect.model.WorkerProfile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerProfileDAO {

    private final Connection connection;

    public WorkerProfileDAO() {
        connection = DBConnection.getConnection();
    }


    // Save Worker Profile


    public boolean save(WorkerProfile profile) {

        String sql = """
               
                INSERT INTO Worker_Profile
               (
               user_id,
               cnic_number,
               cnic_front_path,
               cnic_back_path,
               bio,
               experience_years,
               category_id,
               city,
               service_area,
               address,
               approval_status,
               rejection_reason,
               profile_completed
               )
               VALUES
               (
               ?,?,?,?,?,?,?,?,?,?,?,?,?
               )
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, profile.getUserId());
            statement.setString(2, profile.getCnicNumber());
            statement.setString(3, profile.getCnicFrontPath());
            statement.setString(4, profile.getCnicBackPath());
            statement.setString(5, profile.getBio());
            statement.setInt(6, profile.getExperienceYears());
            statement.setInt(7, profile.getCategoryId());
            statement.setString(8, profile.getCity());
            statement.setString(9, profile.getServiceArea());
            statement.setString(10, profile.getAddress());
            statement.setString(11, profile.getApprovalStatus().name());
            statement.setString(12, profile.getRejectionReason());
            statement.setBoolean(13, profile.isProfileCompleted());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // Get Worker By User Id


    public WorkerProfile getByUserId(int userId) {

        String sql =
                "SELECT * FROM Worker_Profile WHERE user_id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return mapWorkerProfile(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // Update Worker Profile


    public boolean updateProfile(WorkerProfile profile) {

        String sql = """
                UPDATE Worker_Profile
                SET
                    bio=?,
                    experience_years=?,
                    city=?,
                    service_area=?,
                    address=?
                WHERE user_id=?
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, profile.getBio());
            statement.setInt(2, profile.getExperienceYears());
            statement.setString(3, profile.getCity());
            statement.setString(4, profile.getServiceArea());
            statement.setString(5, profile.getAddress());
            statement.setInt(6, profile.getUserId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // Update Verification Details


    public boolean updateVerification(WorkerProfile profile) {

        String sql = """
            UPDATE Worker_Profile
            SET
                cnic_number = ?,
                cnic_front_path = ?,
                cnic_back_path = ?,
                city = ?,
                category_id = ?,
                approval_status = ?,
                rejection_reason = ?
            WHERE user_id = ?
            """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, profile.getCnicNumber());
            statement.setString(2, profile.getCnicFrontPath());
            statement.setString(3, profile.getCnicBackPath());
            statement.setString(4, profile.getCity());
            statement.setInt(5, profile.getCategoryId());
            statement.setString(6, profile.getApprovalStatus().name());
            statement.setString(7, profile.getRejectionReason());
            statement.setInt(8, profile.getUserId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

// Update Approval Status


    public boolean updateApprovalStatus(int userId, ApprovalStatus status, String rejectionReason) {

    String sql =
                        """
            UPDATE Worker_Profile
            SET
            approval_status=?,
            rejection_reason=?
            WHERE user_id=?
           """;

    try (PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(1, status.name());
        statement.setString(2,rejectionReason);
        statement.setInt(3, userId);

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return false;
}

// Update Profile Completed


public boolean updateProfileCompleted(int userId,
                                      boolean completed) {

    String sql =
            "UPDATE Worker_Profile SET profile_completed=? WHERE user_id=?";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setBoolean(1, completed);
        statement.setInt(2, userId);

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return false;
}


// Get Pending Workers


public List<WorkerProfile> getPendingWorkers() {

    List<WorkerProfile> workers = new ArrayList<>();

    String sql =
            "SELECT * FROM Worker_Profile WHERE approval_status='PENDING'";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql);

         ResultSet rs = statement.executeQuery()) {

        while (rs.next()) {

            workers.add(mapWorkerProfile(rs));

        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return workers;
}


// Get Approved Workers


public List<WorkerProfile> getApprovedWorkers() {

    List<WorkerProfile> workers = new ArrayList<>();

    String sql =
            "SELECT * FROM Worker_Profile WHERE approval_status='APPROVED'";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql);

         ResultSet rs = statement.executeQuery()) {

        while (rs.next()) {

            workers.add(mapWorkerProfile(rs));

        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return workers;
}

// Get Rejected Workers


public List<WorkerProfile> getRejectedWorkers() {

    List<WorkerProfile> workers = new ArrayList<>();

    String sql =
            "SELECT * FROM Worker_Profile WHERE approval_status='REJECTED'";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql);

         ResultSet rs = statement.executeQuery()) {

        while (rs.next()) {

            workers.add(mapWorkerProfile(rs));

        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return workers;
}


// Delete Worker Profile


public boolean delete(int userId) {

    String sql =
            "DELETE FROM Worker_Profile WHERE user_id=?";

    try (PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setInt(1, userId);

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return false;
}


// Map ResultSet to WorkerProfile


private WorkerProfile mapWorkerProfile(ResultSet rs)
        throws SQLException {

    WorkerProfile profile = new WorkerProfile();

    profile.setUserId(rs.getInt("user_id"));
    profile.setCnicNumber(rs.getString("cnic_number"));
    profile.setCnicFrontPath(rs.getString("cnic_front_path"));
    profile.setCnicBackPath(rs.getString("cnic_back_path"));
    profile.setBio(rs.getString("bio"));
    profile.setExperienceYears(rs.getInt("experience_years"));
    profile.setCategoryId(rs.getInt("category_id"));
    profile.setCity(rs.getString("city"));
    profile.setServiceArea(rs.getString("service_area"));
    profile.setAddress(rs.getString("address"));

    profile.setApprovalStatus(
            ApprovalStatus.valueOf(
                    rs.getString("approval_status")
            )
    );
    profile.setRejectionReason(
            rs.getString("rejection_reason")
    );

    profile.setProfileCompleted(
            rs.getBoolean("profile_completed")
    );

    return profile;
}

}