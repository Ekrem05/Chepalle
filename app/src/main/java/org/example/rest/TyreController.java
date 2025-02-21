package org.example.rest;

import com.stripe.exception.StripeException;
import jakarta.transaction.InvalidTransactionException;
import org.example.model.dto.OrderRquestDTO;
import org.example.model.dto.SizeViewDTO;
import org.example.model.dto.TyreCreationDTO;
import org.example.model.dto.TyreViewDTO;
import org.example.service.contracts.SizeService;
import org.example.service.contracts.TyreService;
import org.springframework.web.bind.annotation.*;

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
    public List<SizeViewDTO> GetSizes(){
            List<SizeViewDTO> tyreSizes = sizeService.getAll();
            return tyreSizes;
    }

    @PostMapping("/tyre")
    public TyreViewDTO AddTyre(@RequestBody TyreCreationDTO dto) throws InvalidTransactionException, StripeException {
        TyreViewDTO tyre = tyreService.addNew(dto);
        return tyre;
    }

    @GetMapping("/tyres")
    public List<TyreViewDTO> GetTyres(){
        List<TyreViewDTO> tyres = tyreService.getAll();
        return tyres;
    }

    @PostMapping("order")
    public void Order(@RequestParam OrderRquestDTO order){

    }
}
