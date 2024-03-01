package com.brayanroncancio.gestionproyectos.persistanse;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_tarea;
    String descripcion;
    String estado;

    @ManyToOne
    @JoinColumn(name = "Id_historia")
    HistoriaUsuario historiaUsuario;
}
