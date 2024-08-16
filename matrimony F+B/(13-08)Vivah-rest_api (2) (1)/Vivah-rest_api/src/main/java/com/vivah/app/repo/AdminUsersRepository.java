package com.vivah.app.repo;


import com.vivah.app.model.AdminUsers;
import com.vivah.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUsersRepository extends JpaRepository<AdminUsers, Long> {

    @Query("SELECT p FROM AdminUsers p WHERE p.username = :username")
    List<AdminUsers> findByUsername(@Param("username") String username);

}

