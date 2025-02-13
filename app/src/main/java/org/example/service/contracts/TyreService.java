package org.example.service.contracts;

import jakarta.transaction.InvalidTransactionException;
import org.example.model.dto.TyreCreationDTO;
import org.example.model.dto.TyreViewDTO;
import org.example.model.entity.tyre.Tyre;

public interface TyreService {
    Tyre findById(int id);

    TyreViewDTO addNew(TyreCreationDTO dto) throws InvalidTransactionException;
}
