package com.vivah.app.repo;


import com.vivah.app.model.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterests, Long> {
    List<UserInterests> findByUserId(Long userId);
    List<UserInterests> findByInterestedInUserId(Long interestedInUserId);

//    UserInterests findByUserIdAndInterestedInUserId(Long userId, Long interestedInUserId);
//
//    List<UserInterests> findByUserIdAndStatus(Long userId, String status);

    // Delete interest by userId and interestedInUserId
    void deleteByUserIdAndInterestedInUserId(Long userId, Long interestedInUserId);

    // Check if interest exists
    boolean existsByUserIdAndInterestedInUserId(Long userId, Long interestedInUserId);


}
