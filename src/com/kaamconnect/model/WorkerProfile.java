package com.kaamconnect.model;

import com.kaamconnect.enums.ApprovalStatus;

public class WorkerProfile {
    //private int workerProfileId;
    private int userId;
    private String cnicNumber;
    private String cnicFrontPath;
    private String cnicBackPath;
    private String city;
    private String address;
    private String bio;
    private int experienceYears;
    private int categoryId;
    private String serviceArea;
    private ApprovalStatus approvalStatus;
    private String rejectionReason;
    private boolean profileCompleted;


    public WorkerProfile() {

    }

    public boolean isProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnicBackPath() {
        return cnicBackPath;
    }

    public void setCnicBackPath(String cnicBackPath) {
        this.cnicBackPath = cnicBackPath;
    }

    public String getCnicFrontPath() {
        return cnicFrontPath;
    }

    public void setCnicFrontPath(String cnicFrontPath) {
        this.cnicFrontPath = cnicFrontPath;
    }

    public String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.cnicNumber = cnicNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}