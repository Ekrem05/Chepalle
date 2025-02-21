package org.example.service.contracts;

import org.example.model.dto.SizeViewDTO;
import org.example.model.entity.tyre.Size;

import java.util.List;

public interface SizeService {
    Size findById(int id);
    List<SizeViewDTO> getAll();
}
