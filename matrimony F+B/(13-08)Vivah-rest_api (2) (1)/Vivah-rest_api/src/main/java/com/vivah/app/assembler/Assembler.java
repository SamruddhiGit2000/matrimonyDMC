package com.vivah.app.assembler;

import com.vivah.app.dtos.ProfileDto;
import com.vivah.app.dtos.UserInterestsDTO;
import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.UserInterests;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Assembler {

    public ProfileDto convertToProfileDto(MatrimonyProfile profile) {
        ProfileDto dto = new ProfileDto();
        dto.setFirstName(profile.getFirstName());
        dto.setLastName(profile.getLastName());
        dto.setGender(profile.getGender());
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setMaritalStatus(profile.getMaritalStatus());
        dto.setAddress(profile.getAddress());
        dto.setReligion(profile.getReligion());
        dto.setEmail(profile.getEmail());
        dto.setMobileNumber(profile.getMobileNumber());
        dto.setEducation(profile.getEducation());
        dto.setOccupation(profile.getOccupation());
        dto.setIncome(profile.getIncome());
        dto.setAboutMe(profile.getAboutMe());
        dto.setId(profile.getId());
        dto.setAge(profile.getAge());
        dto.setUserId(profile.getUserId());
        dto.setReligionPref(profile.getReligionPref());

//        if (dto.getProfilePhoto() != null && !dto.getProfilePhoto().isEmpty()) {
//            try {
//                profile.setProfilePhoto(dto.getProfilePhoto().getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return dto;
    }

    public UserInterests convertToUserInterestsEntity(UserInterestsDTO dto) {
        UserInterests entity = new UserInterests();

        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setInterestedInUserId(dto.getInterestedInUserId());
        entity.setInterestDate(dto.getInterestDate());

        return entity;
    }

    public UserInterestsDTO convertToUserInterestsDTO(UserInterests userInterests) {
        UserInterestsDTO dto = new UserInterestsDTO();

        dto.setId(userInterests.getId());
        dto.setUserId(userInterests.getUserId());
        dto.setInterestedInUserId(userInterests.getInterestedInUserId());
        dto.setInterestDate(userInterests.getInterestDate());

        return dto;
    }

}
