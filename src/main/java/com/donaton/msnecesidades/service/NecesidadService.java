package com.donaton.msnecesidades.service;


import com.donaton.msnecesidades.model.Necesidad;
import com.donaton.msnecesidades.factory.NecesidadFactory;
import com.donaton.msnecesidades.repository.NecesidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.lang.Exception;

@Service
@RequiredArgsConstructor
public class NecesidadService {

    private final NecesidadRepository repository;
    private final NecesidadFactory factory;

    public List<Necesidad> listar() {
        return repository.findAll();
    }

    public Necesidad crear(String recurso, int cantidad,
                           String ubicacion, String region,
                           String reportadoPor) {
        Necesidad n = factory.crear(recurso, cantidad, ubicacion, region, reportadoPor);
        return repository.save(n);
    }

    @CircuitBreaker(name = "necesidadService", fallbackMethod = "fallbackListar")
    public List<Necesidad> listarConCircuitBreaker() {
        return repository.findAll();
    }

    public List<Necesidad> fallbackListar(Exception e) {
        return new ArrayList<>();
    }
}