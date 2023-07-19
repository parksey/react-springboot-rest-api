package com.example.gonggam.owner.repository;

import com.example.gonggam.owner.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    boolean existsByOwnerNo(String ownerNo);
}
