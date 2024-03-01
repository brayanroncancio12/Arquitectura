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
public class AsignacionUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_asignacion;

    @ManyToOne
    @JoinColumn(name = "Id_proyecto")
    Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "Id_usuario")
    Usuario usuario;
    String rol;

}
