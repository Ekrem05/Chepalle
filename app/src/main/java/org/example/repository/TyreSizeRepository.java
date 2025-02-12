package org.example.repository;

import org.example.model.entity.tyre.TyreSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TyreSizeRepository extends JpaRepository<TyreSize,Integer> {
}
