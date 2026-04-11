package com.donaton.msnecesidades.factory;


import com.donaton.msnecesidades.model.Necesidad;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
@Component
public class NecesidadFactory {
    public Necesidad crear(String recurso, int cantidad,
                           String ubicacion, String region,
                           String reportadoPor) {
        Necesidad n = new Necesidad();
        n.setRecurso(recurso);
        n.setCantidad(cantidad);
        n.setUbicacion(ubicacion);
        n.setRegion(region);
        n.setEstado("pendiente");
        n.setFechaReporte(LocalDate.now());
        n.setReportadoPor(reportadoPor);
        return n;
    }
}
