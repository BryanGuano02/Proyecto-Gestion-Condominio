package ModuloMuebles;

import Administracion.Administrador;
import Administracion.Residente;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Grupo3
 */
public class AdministracionAlquiler implements Serializable {

    ArrayList<Alquiler> listaAlquileres;
            Disponibilidad disponibilidad;

    public AdministracionAlquiler() {
        listaAlquileres = new <Alquiler>ArrayList();
    }

    public AdministracionAlquiler(ArrayList<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
    }


    public ArrayList<Alquiler> getListaAlquileres() {
        return listaAlquileres;
    }

    public void setListaAlquileres(ArrayList<Alquiler> listaAlquileres) {
        this.listaAlquileres = listaAlquileres;
    }

    public boolean estaVacia() {
        return listaAlquileres.isEmpty();
    }
    
//metodos de alquiler y devolucion/finalizacion
    public boolean alquilar(Administrador administrador, String tipoMueble, int cantidad, String correo, String dias) {
        Residente residente = administrador.obtenerResidentePorCorreo(correo);
        Alquiler alquiler = new Alquiler(residente, tipoMueble, cantidad, dias);
        if (listaAlquileres != null && !estaVacia()&& disponibilidad.verificarDisponibilidad(dias,listaAlquileres, tipoMueble, cantidad)) {
            double precioTotal = calcularPrecioTotal(cantidad);
            alquiler.setPrecioTotal(precioTotal);
            listaAlquileres.add(alquiler);
            disponibilidad.actualizarDisponibilidad(listaAlquileres,tipoMueble, cantidad, false);
            return true;
        }
        return false;  
    }

    public double calcularPrecioTotal(int cantidad) {
        double precioMueble = 0.0;
        if (listaAlquileres != null && !estaVacia()) {
            precioMueble = disponibilidad.getMueble().getPrecio();
        }
        // Calcula el precio total multiplicando la cantidad por el precio de cada mueble
        return cantidad * precioMueble;
    }
    
    
    public Alquiler finalizarAlquiler(int id, Fecha fDevolucion) {
        Alquiler alquilerDevuelto = null;
        boolean flag = false;
        for (Alquiler alquiler : listaAlquileres) {
            if (id == alquiler.getIdAlquiler()) {
                //comparar fechas
                if (alquiler.getFechaFin().getAnio() <= fDevolucion.getAnio()) {
                    flag = true;
                }
            } else {
                return null;
            }
            if (flag) {
                alquiler.getInventario().actualizarDisponibilidad(alquiler.getTipoMueble(), id, true);
            }
        }
        return alquilerDevuelto;
    }

    @Override
    public String toString() {
        String salida = "Lista de Muebles Alquilados: ";
        int i = 1;
        if (estaVacia()) {
            return "Lista vacía";
        } else {
            for (Alquiler aux : listaAlquileres) {
                salida += "\nAlquiler " + i;
                salida += aux.toString();
                i++;
            }
            return salida;
        }
    }

}
