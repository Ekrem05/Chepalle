package org.example.repository;

import org.example.model.entity.tyre.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Integer> {
}
