package com.brayanroncancio.gestionproyectos.exceptions;

public class UsuarioException extends RuntimeException{

  public UsuarioException(){
      super();
  }

  public UsuarioException(String mensaje){
      super(mensaje);
  }

}
