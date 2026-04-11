package com.donaton.msnecesidades;


import com.donaton.msnecesidades.model.Necesidad;
import com.donaton.msnecesidades.repository.NecesidadRepository;
import com.donaton.msnecesidades.service.NecesidadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NecesidadControllerTest {

    @Autowired
    private NecesidadService service;

    @Autowired
    private NecesidadRepository repository;

    @BeforeEach
    void limpiar() {
        repository.deleteAll();
    }

    @Test
    void crearNecesidad_debeGuardarYRetornarConId() {
        Necesidad n = service.crear("ropa", 10, "Sector Norte", "RM", "Juan");
        assertNotNull(n.getId());
        assertEquals("ropa", n.getRecurso());
    }

    @Test
    void listarNecesidades_debeRetornarListaNoNula() {
        service.crear("alimento", 5, "Calle 1", "Valpo", "María");
        assertFalse(service.listar().isEmpty());
    }
}