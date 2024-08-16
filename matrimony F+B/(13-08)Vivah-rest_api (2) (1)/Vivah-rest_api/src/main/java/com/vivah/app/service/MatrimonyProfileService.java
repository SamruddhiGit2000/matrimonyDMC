package com.vivah.app.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vivah.app.Exception.MatrimonyProfileNotFoundException;
import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.MatrimonyProfilesResponse;
import com.vivah.app.repo.MatrimonyProfileRepository;
import com.vivah.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatrimonyProfileService {

    @Autowired
    private MatrimonyProfileRepository matrimonyProfileRepository;

    @Autowired
    private UserRepository userRepository;
    
    MatrimonyProfilesResponse response = null;

    // Create a new Matrimony Profile
    public MatrimonyProfile createMatrimonyProfile(MatrimonyProfile matrimonyProfile) {
        return matrimonyProfileRepository.save(matrimonyProfile);
    }

    // Retrieve all Matrimony Profiles
    public MatrimonyProfilesResponse getAllMatrimonyProfiles() {
    	response = new MatrimonyProfilesResponse();
    	response.setProfiles(matrimonyProfileRepository.findAll());
        return response;
    }

    public List<MatrimonyProfile> getAllProfilesExceptUser(Long userId) {
        return matrimonyProfileRepository.findAllByUserIdNot(userId);
    }

    public List<MatrimonyProfile> getProfilesExcluding(Long profileId) {
        return matrimonyProfileRepository.findAllExcept(profileId);
    }

    // Retrieve a specific Matrimony Profile by ID
    public Optional<MatrimonyProfile> getMatrimonyProfileById(Long id) {
        return matrimonyProfileRepository.findById(id);
    }

    public List<MatrimonyProfile> getMatrimonyProfilesByName(String name) {
        return matrimonyProfileRepository.findByName(name);
    }

    // Update a Matrimony Profile
    public MatrimonyProfile updateMatrimonyProfile(Long id, MatrimonyProfile updatedProfile) {
        Optional<MatrimonyProfile> existingProfile = matrimonyProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            updatedProfile.setId(id);
            return matrimonyProfileRepository.save(updatedProfile);
        } else {
            // Handle not found scenario
            throw new MatrimonyProfileNotFoundException("Matrimony Profile not found with id: " + id);
        }
    }

    // Delete a Matrimony Profile
    @Transactional
    public boolean deleteMatrimonyProfile(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
//            matrimonyProfileRepository.deleteByUserId(id);
//            MatrimonyProfile matProf = matrimonyProfileRepository.findByUserId(id);

            return true;
        } else {
            return false; // Matrimony Profile not found
        }
    }
    public List<MatrimonyProfile> getMatrimonyProfilesByOccupation(String occupation) {
        // Implement logic to retrieve profiles by occupation from the repository
        return matrimonyProfileRepository.findByOccupation(occupation);
    }

    public List<MatrimonyProfile> getMatrimonyProfilesByIncome(String income) {
        // Implement logic to retrieve profiles by income from the repository
        return matrimonyProfileRepository.findByIncome(income);
    }

    public List<MatrimonyProfile> getMatrimonyProfilesByEducation(String education) {
        // Implement logic to retrieve profiles by education from the repository
        return matrimonyProfileRepository.findByEducation(education);
    }
    
    public List<MatrimonyProfile> filterByField(String field, String value) {
        return matrimonyProfileRepository.filterByField(field, value);
    }

    public List<MatrimonyProfile> filterByFields(Map<String, String> filterParams) {
        return matrimonyProfileRepository.filterByFields(filterParams);
    }


}
