package org.example.repository;

import org.example.model.entity.tyre.Tyre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TyreRepository extends JpaRepository<Tyre,Integer> {
}
