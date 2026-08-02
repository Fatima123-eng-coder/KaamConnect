package com.kaamconnect.model;

import com.kaamconnect.enums.ApprovalStatus;

public class WorkerProfile {
    private int workerProfileId;
    private int userId;
    private String cnicNumber;
    private String cnicFrontPath;
    private String cnicBackPath;
    private String city;
    private String address;
    private String bio;
    private int experienceYears;
    private String serviceArea;
    private ApprovalStatus approvalStatus;
    private boolean profileCompleted;

    public WorkerProfile(int workerProfileId, boolean profileCompleted, ApprovalStatus approvalStatus, String serviceArea, int experienceYears, String bio, String address, String city, String cnicBackPath, String cnicFrontPath, String cnicNumber, int userId) {
        this.workerProfileId = workerProfileId;
        this.profileCompleted = profileCompleted;
        this.approvalStatus = approvalStatus;
        this.serviceArea = serviceArea;
        this.experienceYears = experienceYears;
        this.bio = bio;
        this.address = address;
        this.city = city;
        this.cnicBackPath = cnicBackPath;
        this.cnicFrontPath = cnicFrontPath;
        this.cnicNumber = cnicNumber;
        this.userId = userId;
    }

    public int getWorkerProfileId() {
        return workerProfileId;
    }

    public void setWorkerProfileId(int workerProfileId) {
        this.workerProfileId = workerProfileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.cnicNumber = cnicNumber;
    }

    public String getCnicFrontPath() {
        return cnicFrontPath;
    }

    public void setCnicFrontPath(String cnicFrontPath) {
        this.cnicFrontPath = cnicFrontPath;
    }

    public String getCnicBackPath() {
        return cnicBackPath;
    }

    public void setCnicBackPath(String cnicBackPath) {
        this.cnicBackPath = cnicBackPath;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }
}
