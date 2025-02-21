package org.example.service.contracts;

import com.stripe.exception.StripeException;
import jakarta.transaction.InvalidTransactionException;
import org.example.model.dto.TyreCreationDTO;
import org.example.model.dto.TyreViewDTO;
import org.example.model.entity.tyre.Tyre;

import java.util.List;

public interface TyreService {
    Tyre findById(int id);
    List<TyreViewDTO> getAll();
    TyreViewDTO addNew(TyreCreationDTO dto) throws InvalidTransactionException, StripeException;
}
