package com.donaton.msnecesidades;


import com.donaton.msnecesidades.controller.NecesidadController;
import com.donaton.msnecesidades.model.Necesidad;
import com.donaton.msnecesidades.repository.NecesidadRepository;
import com.donaton.msnecesidades.service.NecesidadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;

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


    @Test
        void listarController_debeRetornarLista() {

            NecesidadController controller =
                    new NecesidadController(service);

            service.crear(
                    "ropa",
                    10,
                    "Sector Norte",
                    "RM",
                    "Juan"
            );

            List<Necesidad> lista = controller.listar();

            assertFalse(lista.isEmpty());

        }

    @Test
        void crearController_debeGuardarNecesidad() {

            NecesidadController controller =
                    new NecesidadController(service);

            Necesidad n = new Necesidad();

            n.setRecurso("agua");
            n.setCantidad(5);
            n.setUbicacion("Centro");
            n.setRegion("RM");
            n.setReportadoPor("Pedro");

            Necesidad creada = controller.crear(n);

            assertNotNull(creada.getId());

            assertEquals(
                    "agua",
                    creada.getRecurso()
            );

        }

    @Test
        void obtenerController_debeRetornarNecesidad() {

            NecesidadController controller =
                    new NecesidadController(service);

            Necesidad creada = service.crear(
                    "alimento",
                    20,
                    "Norte",
                    "RM",
                    "Maria"
            );

            ResponseEntity<Necesidad> response =
                    controller.obtener(creada.getId());

            assertEquals(
                    200,
                    response.getStatusCode().value()
            );

        }
    @Test
    void obtener_debeRetornar404() {

        NecesidadController controller = new NecesidadController(service);

        ResponseEntity<Necesidad> response =
                controller.obtener(999L);

        assertEquals(404,response.getStatusCode().value());

    }
}