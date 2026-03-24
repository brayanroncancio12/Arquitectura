package com.brayanroncancio.gestionproyectos.model;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "desarrolladores")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_proyecto;

    String nombre;
    String descripcion;
    String fechaInicio;

    // Carga el gerente completo (con todos sus datos) al traer el proyecto
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Id_gerente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario gerente;

    // Carga la lista de desarrolladores completa
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "proyecto_desarrolladores",
            joinColumns = @JoinColumn(name = "Id_proyecto"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id"))
    private Set<Usuario> desarrolladores = new HashSet<>();
}