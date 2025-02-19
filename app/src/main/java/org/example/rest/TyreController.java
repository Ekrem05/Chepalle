package org.example.rest;

import jakarta.transaction.InvalidTransactionException;
import org.example.model.dto.SizeViewDto;
import org.example.model.dto.TyreCreationDTO;
import org.example.model.dto.TyreViewDTO;
import org.example.service.contracts.SizeService;
import org.example.service.contracts.TyreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TyreController {

    private final SizeService sizeService;
    private final TyreService tyreService;

    public TyreController(SizeService sizeService,TyreService tyreService) {
        this.sizeService = sizeService;
        this.tyreService=tyreService;
    }

    @GetMapping("/size")
    public List<SizeViewDto> GetSizes(){
            List<SizeViewDto> tyres = sizeService.getAll();
            return tyres;
    }

    @PostMapping("/tyre")
    public TyreViewDTO AddTyre(TyreCreationDTO dto) throws InvalidTransactionException {
        TyreViewDTO tyre = tyreService.addNew(dto);
        return tyre;
    }
}
