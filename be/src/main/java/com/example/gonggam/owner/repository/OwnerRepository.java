package com.example.gonggam.owner.repository;

import com.example.gonggam.owner.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    boolean existsByOwnerNo(String ownerNo);

    Optional<Owner> findByOwnerNo(String ownerNo);
}
