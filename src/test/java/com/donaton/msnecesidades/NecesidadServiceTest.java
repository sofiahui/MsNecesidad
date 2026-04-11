package com.donaton.msnecesidades;

import com.donaton.msnecesidades.model.Necesidad;
import com.donaton.msnecesidades.repository.NecesidadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NecesidadRepositoryTest {

    @Autowired
    private NecesidadRepository repository;

    @BeforeEach
    void limpiar() {
        repository.deleteAll();
    }

    @Test
    void findByRegion_debeRetornarNecesidadesDeLaRegion() {
        Necesidad n = new Necesidad();
        n.setRecurso("ropa");
        n.setCantidad(10);
        n.setUbicacion("Sector Norte");
        n.setRegion("RM");
        n.setEstado("pendiente");
        n.setFechaReporte(LocalDate.now());
        n.setReportadoPor("Juan");
        repository.save(n);

        List<Necesidad> resultado = repository.findByRegion("RM");
        assertFalse(resultado.isEmpty());
        assertEquals("RM", resultado.get(0).getRegion());
    }

    @Test
    void findByEstado_debeRetornarNecesidadesPendientes() {
        Necesidad n = new Necesidad();
        n.setRecurso("alimento");
        n.setCantidad(5);
        n.setUbicacion("Calle 1");
        n.setRegion("Valpo");
        n.setEstado("pendiente");
        n.setFechaReporte(LocalDate.now());
        n.setReportadoPor("María");
        repository.save(n);

        List<Necesidad> resultado = repository.findByEstado("pendiente");
        assertFalse(resultado.isEmpty());
    }

    @Test
    void findByRecurso_debeRetornarNecesidadesPorRecurso() {
        Necesidad n = new Necesidad();
        n.setRecurso("medicamento");
        n.setCantidad(15);
        n.setUbicacion("Av. Sur");
        n.setRegion("RM");
        n.setEstado("pendiente");
        n.setFechaReporte(LocalDate.now());
        n.setReportadoPor("Pedro");
        repository.save(n);

        List<Necesidad> resultado = repository.findByRecurso("medicamento");
        assertFalse(resultado.isEmpty());
    }
}