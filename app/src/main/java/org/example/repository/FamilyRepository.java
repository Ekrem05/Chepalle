package org.example.repository;

import org.example.model.entity.tyre.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family,Integer> {
}
