package org.example.rest;

import org.example.model.entity.tyre.TyreSize;
import org.example.service.contracts.TyreSizeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TyreController {

    private TyreSizeService tyreSizeService;

    public TyreController(TyreSizeService tyreSizeService) {
        this.tyreSizeService = tyreSizeService;
    }

    @GetMapping("index")
    public String Hello(){
        return "Hello";
    }

    @GetMapping("size")
    public List<TyreSize> GetSizes(){
        return tyreSizeService.getAll();
    }

    @PostMapping("size")
    public List<TyreSize> AddTyreSize(){
        //Post logic
        return tyreSizeService.getAll();
    }
}
