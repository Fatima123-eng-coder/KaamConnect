package com.kaamconnect.service.auth;

import com.kaamconnect.dao.WorkerProfileDAO;
import com.kaamconnect.enums.ApprovalStatus;
import com.kaamconnect.model.WorkerProfile;

public class WorkerVerificationService {

    private final WorkerProfileDAO workerProfileDAO;

    public WorkerVerificationService() {

        workerProfileDAO = new WorkerProfileDAO();

    }


    // Submit Verification Application


    public boolean submitApplication(WorkerProfile profile) {

        if (profile == null) {
            return false;
        }

        if (profile.getUserId() <= 0) {
            return false;
        }

        if (profile.getCnicNumber() == null ||
                profile.getCnicNumber().isBlank()) {
            return false;
        }

        if (profile.getCnicFrontPath() == null ||
                profile.getCnicFrontPath().isBlank()) {
            return false;
        }

        if (profile.getCnicBackPath() == null ||
                profile.getCnicBackPath().isBlank()) {
            return false;
        }

        if (profile.getCity() == null ||
                profile.getCity().isBlank()) {
            return false;
        }

        if (profile.getCategoryId() <= 0) {
            return false;
        }

        profile.setApprovalStatus(ApprovalStatus.PENDING);
        profile.setProfileCompleted(false);
        profile.setRejectionReason(null);

        return workerProfileDAO.save(profile);
    }


    // Update Verification


    public boolean updateVerification(WorkerProfile profile) {

        if (profile == null) {
            return false;
        }

        profile.setApprovalStatus(ApprovalStatus.PENDING);
        profile.setRejectionReason(null);

        return workerProfileDAO.updateVerification(profile);
    }

    // Get Worker Profile


    public WorkerProfile getWorkerProfile(int userId) {

        return workerProfileDAO.getByUserId(userId);

    }


    // Get Approval Status


    public ApprovalStatus getApprovalStatus(int userId) {

        WorkerProfile profile =
                workerProfileDAO.getByUserId(userId);

        if (profile == null) {
            return null;
        }

        return profile.getApprovalStatus();

    }

    // Check Verification Submitted


    public boolean isVerificationSubmitted(int userId) {

        return workerProfileDAO.getByUserId(userId) != null;

    }


    // Check Approved


    public boolean isApproved(int userId) {

        WorkerProfile profile =
                workerProfileDAO.getByUserId(userId);

        return profile != null &&
                profile.getApprovalStatus() == ApprovalStatus.APPROVED;

    }

    // Check Pending

    public boolean isPending(int userId) {

        WorkerProfile profile =
                workerProfileDAO.getByUserId(userId);

        return profile != null &&
                profile.getApprovalStatus() == ApprovalStatus.PENDING;

    }


    // Check Rejected


    public boolean isRejected(int userId) {

        WorkerProfile profile =
                workerProfileDAO.getByUserId(userId);

        return profile != null &&
                profile.getApprovalStatus() == ApprovalStatus.REJECTED;

    }


    // Complete Worker Profile


    public boolean completeProfile(WorkerProfile profile) {

        if (profile == null) {
            return false;
        }

        boolean updated =
                workerProfileDAO.updateProfile(profile);

        if (!updated) {
            return false;
        }

        return workerProfileDAO.updateProfileCompleted(
                profile.getUserId(),
                true
        );

    }


    // Approve Worker (Admin)


    public boolean approveWorker(int userId) {

        return workerProfileDAO.updateApprovalStatus(
                userId,
                ApprovalStatus.APPROVED,
                null
        );

    }


    // Reject Worker (Admin)


    public boolean rejectWorker(int userId,
                                String reason) {

        return workerProfileDAO.updateApprovalStatus(
                userId,
                ApprovalStatus.REJECTED,
                reason
        );

    }

}
