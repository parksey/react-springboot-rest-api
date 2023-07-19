package com.example.gonggam.owner.repository;

import com.example.gonggam.owner.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    boolean existsByOwnerNo(String ownerNo);
}
