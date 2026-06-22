package com.donaton.msnecesidades.repository;

import com.donaton.msnecesidades.model.Necesidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface NecesidadRepository extends JpaRepository<Necesidad, Long> {
    List<Necesidad> findByRegion(String region);
    List<Necesidad> findByEstado(String estado);
    List<Necesidad> findByRecurso(String recurso);
}