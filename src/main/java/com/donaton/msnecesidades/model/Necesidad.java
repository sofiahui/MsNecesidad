package com.donaton.msnecesidades.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Necesidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recurso;        // ropa, alimento, medicamento
    private Integer cantidad;
    private String ubicacion;      // dirección o sector
    private String region;
    private String estado;         // pendiente, atendida, en_proceso
    private LocalDate fechaReporte;
    private String reportadoPor;   // nombre del reportante
}