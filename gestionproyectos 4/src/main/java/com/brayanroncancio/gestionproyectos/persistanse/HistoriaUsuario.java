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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id_Historia;
    String detalles;
    String criteriosAceptacion;
    String estado;

    @ManyToOne
    @JoinColumn(name = "Id_proyecto")
    Proyecto proyecto;

    @OneToMany(mappedBy = "historiaUsuario")
    private List<Tarea> tareas;

    public HistoriaUsuario(long l, String detallesDeLaHistoria, String criteriosDeAceptación, String enProgreso, Proyecto proyecto) {

    }

    public HistoriaUsuario obtenerHistoriaUsuarioPorId(Long historiaId) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'obtenerHistoriaUsuarioPorId'");
    }

    public HistoriaUsuario crearHistoriaUsuario(String detalles2, String criteriosAceptacion2, String estado2,
        Proyecto proyecto2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'crearHistoriaUsuario'");
    }

    public List<HistoriaUsuario> obtenerTodasLasHistorias() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'obtenerTodasLasHistorias'");
    }
}
