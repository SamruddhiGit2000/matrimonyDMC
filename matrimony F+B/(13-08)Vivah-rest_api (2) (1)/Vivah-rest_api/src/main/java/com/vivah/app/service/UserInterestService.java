package com.vivah.app.service;

import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.UserInterests;
import com.vivah.app.repo.MatrimonyProfileRepository;
import com.vivah.app.repo.UserInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInterestService {

    @Autowired
    private UserInterestRepository userInterestRepository;

    @Autowired
    private MatrimonyProfileRepository matrimonyProfileRepository;

    @Transactional
    public void removeInterest(Long userId, Long interestedInUserId) {
        if (userInterestRepository.existsByUserIdAndInterestedInUserId(userId, interestedInUserId)) {
            userInterestRepository.deleteByUserIdAndInterestedInUserId(userId, interestedInUserId);
        } else {
            throw new IllegalArgumentException("Interest record not found.");
        }
    }

    public List<MatrimonyProfile> getInterestedProfiles(Long profileId) {
        // Assuming there's a method in your repository to find profiles interested in a given profileId
        List<UserInterests> userInterests = userInterestRepository.findByInterestedInUserId(profileId);
        List<MatrimonyProfile> profiles = new ArrayList<MatrimonyProfile>();

        for(UserInterests user: userInterests){
            MatrimonyProfile prof = matrimonyProfileRepository.findByUserId(user.getUserId());
            if(prof != null){
                profiles.add(prof);
            }
        }
        return profiles;
    }

//    @Transactional
//    public boolean updateInterestStatus(Long profileId, Long actionProfileId, String action) {
//        UserInterests interest = userInterestRepository.findByUserIdAndInterestedInUserId(profileId, actionProfileId);
//
//        if (interest != null) {
//            switch (action.toUpperCase()) {
//                case "ACCEPT":
//                    interest.setStatus(UserInterests.Status.ACCEPTED);
//                    break;
//                case "REJECT":
//                    interest.setStatus(UserInterests.Status.REJECTED);
//                    break;
//                default:
//                    return false; // Invalid action
//            }
//            userInterestRepository.save(interest);
//            return true;
//        }
//        return false; // Interest not found
//    }

//    public List<UserInterests> getAcceptedProfiles(Long profileId) {
//        return userInterestRepository.findByUserIdAndStatus(profileId, "ACCEPTED")
//                .stream()
//                .map(UserInterests::getInterestedInUserId)
//                .map(userRepository::findById)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }
//
//    public List<User> getRejectedProfiles(Long profileId) {
//        return userInterestRepository.findByUserIdAndStatus(profileId, "REJECTED")
//                .stream()
//                .map(UserInterests::getInterestedInUserId)
//                .map(userRepository::findById)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public List<UserInterests> getInterestsByUserId(Long userId) {
        return userInterestRepository.findByUserId(userId);
    }
}
