package org.example.service.contracts;

import org.example.model.dto.SizeViewDto;
import org.example.model.entity.tyre.Size;

import java.util.List;

public interface SizeService {
    Size findById(int id);
    List<SizeViewDto> getAll();
}
