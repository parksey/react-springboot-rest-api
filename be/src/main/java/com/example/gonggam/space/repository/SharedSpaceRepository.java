package com.example.gonggam.space.repository;

import com.example.gonggam.space.domain.SharedSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SharedSpaceRepository extends JpaRepository<SharedSpace, Long> {

    @Query("SELECT a " +
            "FROM SharedSpace as a " +
            "JOIN Owner as b " +
              "ON a.ownerId = b.ownerId " +
           "WHERE b.ownerNo = :ownerNo")
    List<SharedSpace> findAllByOwnerNo(@Param(value = "ownerNo") String ownerNo);
}
