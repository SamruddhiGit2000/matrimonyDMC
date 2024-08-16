package com.vivah.app.dtos;

import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.UserInterests;

import java.util.List;

public class UserProfilesResponse {

    private List<ProfileDto> profiles;
    private List<UserInterestsDTO> interests;

    public UserProfilesResponse(List<ProfileDto> profiles, List<UserInterestsDTO> interests) {
        this.profiles = profiles;
        this.interests = interests;
    }

    public List<ProfileDto> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileDto> profiles) {
        this.profiles = profiles;
    }

    public List<UserInterestsDTO> getInterests() {
        return interests;
    }

    public void setInterests(List<UserInterestsDTO> interests) {
        this.interests = interests;
    }
}

