package com.brayanroncancio.gestionproyectos.persistanse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

import javax.persistence.Entity;
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
@ToString
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_proyecto;
    String nombre;
    String descripcion;
    String fechaInicio;

    @ManyToOne
    @JoinColumn(name = "Id_gerente")
    private Usuario gerente;

    @ManyToMany
    @JoinTable(
            name = "proyecto_desarrolladores",
            joinColumns = @JoinColumn(name = "Id_proyecto"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id"))
    private List<Usuario> desarrolladores;

}
