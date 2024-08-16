package com.vivah.app.dtos;

public class InterestUpdateRequest {

    private Long profileId;
    private Long actionProfileId;
    private String action;

    // Default constructor
    public InterestUpdateRequest() {
    }

    // Parameterized constructor
    public InterestUpdateRequest(Long profileId, Long actionProfileId, String action) {
        this.profileId = profileId;
        this.actionProfileId = actionProfileId;
        this.action = action;
    }

    // Getters and Setters
    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getActionProfileId() {
        return actionProfileId;
    }

    public void setActionProfileId(Long actionProfileId) {
        this.actionProfileId = actionProfileId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

