package com.donaton.msnecesidades;


import com.donaton.msnecesidades.factory.NecesidadFactory;
import com.donaton.msnecesidades.model.Necesidad;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class NecesidadFactoryTest {

    private NecesidadFactory factory = new NecesidadFactory();

    @Test
    void crear_debeAsignarEstadoPendientePorDefecto() {
        Necesidad n = factory.crear("ropa", 10, "Av. Principal", "RM", "Juan Pérez");
        assertEquals("pendiente", n.getEstado());
    }

    @Test
    void crear_debeAsignarFechaDeHoy() {
        Necesidad n = factory.crear("alimento", 5, "Calle 5", "Valpo", "María López");
        assertEquals(LocalDate.now(), n.getFechaReporte());
    }

    @Test
    void crear_debeAsignarCamposCorrectamente() {
        Necesidad n = factory.crear("medicamento", 20, "Sector Norte", "RM", "Pedro Soto");
        assertEquals("medicamento", n.getRecurso());
        assertEquals(20, n.getCantidad());
        assertEquals("Sector Norte", n.getUbicacion());
        assertEquals("RM", n.getRegion());
        assertEquals("Pedro Soto", n.getReportadoPor());
    }
}