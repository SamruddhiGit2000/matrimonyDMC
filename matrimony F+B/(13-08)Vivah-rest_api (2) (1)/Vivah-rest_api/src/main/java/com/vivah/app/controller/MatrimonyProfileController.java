package com.vivah.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vivah.app.assembler.Assembler;
import com.vivah.app.dtos.ProfileDto;
import com.vivah.app.dtos.UserInterestsDTO;
import com.vivah.app.model.UserInterests;
import com.vivah.app.repo.MatrimonyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.vivah.app.model.MatrimonyProfile;
import com.vivah.app.model.MatrimonyProfilesResponse;
import com.vivah.app.service.MatrimonyProfileService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class MatrimonyProfileController {

    @Autowired
    MatrimonyProfileRepository matrimonyProfileRepository;

    @Autowired
    private MatrimonyProfileService matrimonyProfileService;

    @Autowired
    private Assembler assembler;

    // Create a new Matrimony Profile
    @PostMapping
    public ResponseEntity<MatrimonyProfile> createMatrimonyProfile(@RequestBody MatrimonyProfile matrimonyProfile) {
        MatrimonyProfile createdProfile = matrimonyProfileService.createMatrimonyProfile(matrimonyProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    // Retrieve all Matrimony Profiles
    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileDto>> getAllMatrimonyProfiles() {
//        List<MatrimonyProfile> profiles = matrimonyProfileService.getAllMatrimonyProfiles();
        MatrimonyProfilesResponse response = matrimonyProfileService.getAllMatrimonyProfiles();
        List<ProfileDto> profilesDto = new ArrayList<ProfileDto>();
        List<MatrimonyProfile> profiles = response.getProfiles();
        for(MatrimonyProfile profile: profiles){
            ProfileDto profileDto = assembler.convertToProfileDto(profile);
            profilesDto.add(profileDto);
        }
//        response.setProfiles(profiles);
        return new ResponseEntity<>(profilesDto, HttpStatus.OK);
    }

    @GetMapping("/profilesSearch")
    public ResponseEntity<List<ProfileDto>> getMatrimonyProfilesByName(@RequestParam String firstName) {
//        List<MatrimonyProfile> profiles = matrimonyProfileService.getAllMatrimonyProfiles();
        MatrimonyProfilesResponse response = matrimonyProfileService.getAllMatrimonyProfiles();
        List<ProfileDto> profilesDto = new ArrayList<ProfileDto>();
        List<MatrimonyProfile> profiles = response.getProfiles();
        for(MatrimonyProfile profile: profiles){
            if(profile.getFirstName().equals(firstName)) {
                ProfileDto profileDto = assembler.convertToProfileDto(profile);
                profilesDto.add(profileDto);
            }
        }
//        response.setProfiles(profiles);
        return new ResponseEntity<>(profilesDto, HttpStatus.OK);
    }

    @GetMapping("/getFilteredProfile/{id}")
    public ResponseEntity<List<ProfileDto>> getFilteredProfile(@PathVariable Long id) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getProfilesExcluding(id);

        List<ProfileDto> profileDtos = new ArrayList<>();
        for(MatrimonyProfile profile: profiles){
            profileDtos.add(assembler.convertToProfileDto(profile));
        }
        return ResponseEntity.ok(profileDtos);
    }

    // Retrieve a specific Matrimony Profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<MatrimonyProfile> getMatrimonyProfileById(@PathVariable Long id) {
        Optional<MatrimonyProfile> matrimonyProfile = matrimonyProfileService.getMatrimonyProfileById(id);
        return matrimonyProfile.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a Matrimony Profile
    @PutMapping("/{id}")
    public ResponseEntity<MatrimonyProfile> updateMatrimonyProfile(@PathVariable Long id, @RequestBody MatrimonyProfile updatedProfile) {
        Optional<MatrimonyProfile> existingProfile = matrimonyProfileService.getMatrimonyProfileById(id);
        if (existingProfile.isPresent()) {
            MatrimonyProfile updated = matrimonyProfileService.updateMatrimonyProfile(id, updatedProfile);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Matrimony Profile
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMatrimonyProfile(@PathVariable Long id) {
        if (matrimonyProfileService.deleteMatrimonyProfile(id)) {
            String message = "Deleted Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping("/byname/{firstName}")
//    public ResponseEntity<List<ProfileDto>> getMatrimonyProfilesByName(@PathVariable String firstName) {
//        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByName(firstName);
//            List<ProfileDto> profileDtos = new ArrayList<>();
//            for(MatrimonyProfile profile: profiles){
//                profileDtos.add(assembler.convertToProfileDto(profile));
//            }
//        return new ResponseEntity<>(profileDtos, HttpStatus.OK);
//    }
    // Find Matrimony Profiles by Occupation
    @GetMapping("/by-occupation/{occupation}")
    public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByOccupation(@PathVariable String occupation) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByOccupation(occupation);
        MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
        response.setProfiles(profiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Find Matrimony Profiles by Income
    @GetMapping("/by-income/{income}")
    public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByIncome(@PathVariable String income) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByIncome(income);
        MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
        response.setProfiles(profiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Find Matrimony Profiles by Education
    @GetMapping("/by-education/{education}")
    public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByEducation(@PathVariable String education) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByEducation(education);
        MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
        response.setProfiles(profiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Filter with one field and value
    @GetMapping("/filter/{field}/{value}")
    public ResponseEntity<MatrimonyProfilesResponse> filterByField(@PathVariable String field, @PathVariable String value) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.filterByField(field, value);
        MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
        response.setProfiles(profiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Filter with multiple field and values
    @GetMapping("/filter")
    public ResponseEntity<MatrimonyProfilesResponse> filterByFields(@RequestParam Map<String, String> filterParams) {
        List<MatrimonyProfile> filteredProfiles = matrimonyProfileService.filterByFields(filterParams);
        MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
        response.setProfiles(filteredProfiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
}
