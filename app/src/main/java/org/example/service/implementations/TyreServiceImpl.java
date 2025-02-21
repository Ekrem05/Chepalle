package org.example.service.implementations;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import jakarta.transaction.InvalidTransactionException;
import jakarta.transaction.Transactional;
import org.example.model.dto.TyreCreationDTO;
import org.example.model.dto.TyreViewDTO;
import org.example.model.entity.tyre.*;
import org.example.repository.*;
import org.example.service.contracts.StripeService;
import org.example.service.contracts.TyreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TyreServiceImpl implements TyreService {

    private final TyreRepository tyreRepository;
    private final FamilyRepository familyRepository;
    private final SeasonRepository seasonRepository;
    private final TechnologyRepository technologyRepository;
    private final SizeRepository tyreSizeRepository;
    private final TypeRepository tyreTypeRepository;
    private final StripeService stripeService;

    public TyreServiceImpl(TyreRepository tyreRepository, FamilyRepository familyRepository,
                       SeasonRepository seasonRepository, TechnologyRepository technologyRepository,
                       SizeRepository tyreSizeRepository, TypeRepository tyreTypeRepository,
                           StripeService stripeService) {
        this.tyreRepository = tyreRepository;
        this.familyRepository = familyRepository;
        this.seasonRepository = seasonRepository;
        this.technologyRepository = technologyRepository;
        this.tyreSizeRepository = tyreSizeRepository;
        this.tyreTypeRepository = tyreTypeRepository;
        this.stripeService=stripeService;
    }


    @Override
    public Tyre findById(int id) {
        return null;
    }

    @Override
    public List<TyreViewDTO> getAll() {
        List<Tyre> tyres = tyreRepository.findAll();

        List<TyreViewDTO> models = tyres.stream().map(tyre-> new TyreViewDTO(String.valueOf(tyre.getId()),tyre.getModel(),
                tyre.getDescription(),
                tyre.getFamily().getName(),
                tyre.getSeason().getName(),
                tyre.getTechnology().getName(),
                tyre.getSizes().stream().map(size->String.format("%d/%d R%d",size.getWidth(),size.getHeight(),size.getDiameter())).collect(Collectors.toSet()),
                tyre.getTypes().stream().map(type -> type.getName()).collect(Collectors.toSet()))).collect(Collectors.toList());

        return models;
    }

    @Transactional
    @Override
    public TyreViewDTO addNew(TyreCreationDTO dto) throws InvalidTransactionException,StripeException {

        Family family = familyRepository.findById(dto.familyId())
                .orElseThrow(()->new InvalidTransactionException());
        Season season  = seasonRepository.findById(dto.seasonId())
                .orElseThrow(()->new InvalidTransactionException());
        Technology technology = technologyRepository.findById(dto.technologyId())
                .orElseThrow(()->new InvalidTransactionException());

        Set<Size> sizes = tyreSizeRepository.findAllById(dto.sizeIds()).stream().collect(Collectors.toSet());
        Set<Type> types = tyreTypeRepository.findAllById(dto.typeIds()).stream().collect(Collectors.toSet());

            Product product=stripeService.createProduct(dto.model(), dto.description());
            Price price = stripeService.createPrice(dto.price()*100,"bgn",product.getId());



        Tyre newTyre = tyreRepository.save(new Tyre(dto.model(),
                dto.description(),
                dto.price(),
                product.getId(),
                price.getId(),
                family, season,
                technology, sizes,
                types));

        return new TyreViewDTO(String.valueOf(newTyre.getId()),
                newTyre.getModel(),
                newTyre.getDescription(),
                newTyre.getFamily().getName(),
                newTyre.getSeason().getName(),
                newTyre.getTechnology().getName(),
                newTyre.getSizes().stream().map(size->String.
                        format("%d/%d R%d",size.getWidth(),size.getHeight(),size.getDiameter()))
                        .collect(Collectors.toSet())
                ,newTyre.getTypes().stream()
                        .map(type -> type.getName())
                        .collect(Collectors.toSet()));
    }
}
