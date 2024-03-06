/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administracion;

import java.util.ArrayList;
import Administracion.Contrato;
import BD.BaseDeDatos;
import java.io.Serializable;

/**
 *
 * @author jeanp
 */
public class Aprobacion implements Serializable{

    private final Directiva directiva;

    public Aprobacion(Directiva directiva) {
        this.directiva = directiva;
        
    }

    
    
    public void notificarAprobacion(String descripcionContrato) {
        directiva.actualizarAprobacion(descripcionContrato);  
    }

}
