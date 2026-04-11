package com.donaton.msnecesidades;

import com.donaton.msnecesidades.model.Necesidad;
import com.donaton.msnecesidades.repository.NecesidadRepository;
import com.donaton.msnecesidades.service.NecesidadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NecesidadServiceTest {

    @Autowired
    private NecesidadService service;

    @Autowired
    private NecesidadRepository repository;

    @BeforeEach
    void limpiar() {
        repository.deleteAll();
    }

    @Test
    void crear_debeGuardarNecesidadCorrectamente() {
        Necesidad n = service.crear("ropa", 10, "Sector Norte", "RM", "Juan");
        assertNotNull(n.getId());
        assertEquals("ropa", n.getRecurso());
        assertEquals("pendiente", n.getEstado());
    }

    @Test
    void listar_debeRetornarNecesidades() {
        service.crear("alimento", 5, "Calle 1", "Valpo", "María");
        List<Necesidad> lista = service.listar();
        assertFalse(lista.isEmpty());
    }

    @Test
    void crear_debeTenerFechaDeHoy() {
        Necesidad n = service.crear("medicamento", 20, "Av. Sur", "RM", "Pedro");
        assertEquals(java.time.LocalDate.now(), n.getFechaReporte());
    }
}