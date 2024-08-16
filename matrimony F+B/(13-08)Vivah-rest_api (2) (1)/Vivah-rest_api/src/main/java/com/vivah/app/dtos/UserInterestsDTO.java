package com.vivah.app.dtos;

import java.sql.Timestamp;

public class UserInterestsDTO {

    private Long id;
    private Long userId;
    private Long interestedInUserId;
    private Timestamp interestDate;

    // Default constructor
    public UserInterestsDTO() {}

    // Constructor with all fields
    public UserInterestsDTO(Long id, Long userId, Long interestedInUserId, Timestamp interestDate) {
        this.id = id;
        this.userId = userId;
        this.interestedInUserId = interestedInUserId;
        this.interestDate = interestDate;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getInterestedInUserId() {
        return interestedInUserId;
    }

    public Timestamp getInterestDate() {
        return interestDate;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setInterestedInUserId(Long interestedInUserId) {
        this.interestedInUserId = interestedInUserId;
    }

    public void setInterestDate(Timestamp interestDate) {
        this.interestDate = interestDate;
    }

    // You can add additional methods here if needed, such as toString()
    @Override
    public String toString() {
        return "UserInterestsDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", interestedInUserId=" + interestedInUserId +
                ", interestDate=" + interestDate +
                '}';
    }
}
