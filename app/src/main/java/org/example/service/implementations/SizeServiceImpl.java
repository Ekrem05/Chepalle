package org.example.service.implementations;

import org.example.model.dto.SizeViewDto;
import org.example.model.entity.tyre.Size;
import org.example.repository.SizeRepository;
import org.example.service.contracts.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {

    private SizeRepository repository;

    public SizeServiceImpl(SizeRepository repository) {
        this.repository=repository;
    }

    @Override
    public Size findById(int id) {
        return null;
    }

    @Override
    public List<SizeViewDto> getAll() {
        return repository.findAll().stream().map(size->
                new SizeViewDto(size.getWidth(),size.getHeight(),size.getDiameter())).collect(Collectors.toList());
    }
}
