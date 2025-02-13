package org.example.repository;

import org.example.model.entity.tyre.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Integer> {
}
