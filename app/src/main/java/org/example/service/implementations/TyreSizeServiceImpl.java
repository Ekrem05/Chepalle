package org.example.service.implementations;

import org.example.model.entity.tyre.TyreSize;
import org.example.repository.TyreSizeRepository;
import org.example.service.contracts.TyreSizeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TyreSizeServiceImpl implements TyreSizeService {

    private TyreSizeRepository repository;

    public TyreSizeServiceImpl(TyreSizeRepository repository) {
        this.repository=repository;
    }

    @Override
    public List<TyreSize> getAll() {
        return repository.findAll();
    }
}
