package com.example.gonggam.space.repository;

import com.example.gonggam.space.domain.SharedSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedSpaceRepository extends JpaRepository<SharedSpace, Long> {

    List<SharedSpace> findAllByOwnerNo(String ownerNo);
}
