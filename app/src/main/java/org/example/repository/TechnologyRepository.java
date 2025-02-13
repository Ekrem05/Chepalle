package org.example.repository;

import org.example.model.entity.tyre.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology,Integer> {
}
