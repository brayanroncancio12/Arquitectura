package com.brayanroncancio.gestionproyectos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "tareas")
public class HistoriaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_Historia;

    String detalles;
    String criteriosAceptacion;
    String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_proyecto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Proyecto proyecto;

    @JsonIgnore
    @OneToMany(mappedBy = "historiaUsuario", fetch = FetchType.LAZY)
    private Set<Tarea> tareas = new HashSet<>();
}