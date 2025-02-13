package org.example.repository;

import org.example.model.entity.tyre.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season,Integer> {
}
