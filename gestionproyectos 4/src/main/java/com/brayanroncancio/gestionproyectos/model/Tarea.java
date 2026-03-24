package com.brayanroncancio.gestionproyectos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    // Carga la historia completa al traer la tarea
    // exclude "tareas" para evitar bucle infinito: Tarea→Historia→Tareas→Tarea...
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_historia")
    @JsonIgnoreProperties({"tareas", "hibernateLazyInitializer", "handler"})
    HistoriaUsuario historiaUsuario;
}