/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Finanzas;

/**
 *
 * @author alejo
 */
public abstract class MetodoRecarga {
    private  double abono;
    protected String descripcion;

    public MetodoRecarga(double abono) {
        this.abono = abono;
    }

     abstract void realizarTransaccion();

    @Override
    public String toString() {
        return descripcion + ". Se añadió a la cuenta el abono de: " + abono;
    }
}
