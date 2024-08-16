package com.vivah.app.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vivah.app.model.MatrimonyProfile;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MatrimonyProfileRepository extends JpaRepository<MatrimonyProfile, Long> {
	@Query("SELECT p FROM MatrimonyProfile p WHERE p.id <> :id")
	List<MatrimonyProfile> findAllExcept(@Param("id") Long id);

	List<MatrimonyProfile> findAllByUserIdNot(Long userId);

	@Modifying
	@Transactional
	@Query("DELETE FROM MatrimonyProfile p WHERE p.userId = :userId")
	void deleteByUserId(@Param("userId") Long userId);

	MatrimonyProfile findByUserId(Long userId);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.occupation = :occupation")
	List<MatrimonyProfile> findByOccupation(@Param("occupation") String occupation);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.firstName = :firstName")
	List<MatrimonyProfile> findByName(@Param("firstName") String firstName);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.income = :income")
	List<MatrimonyProfile> findByIncome(@Param("income") String income);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.education = :education")
	List<MatrimonyProfile> findByEducation(@Param("education") String education);

	@Query("SELECT p FROM MatrimonyProfile p WHERE p.gender = :gender")
	List<MatrimonyProfile> findByGender(@Param("gender") String gender);

	List<MatrimonyProfile> filterByField(@Param("field") String field, @Param("value") String value);

	List<MatrimonyProfile> filterByFields(Map<String, String> filterParams);

}
