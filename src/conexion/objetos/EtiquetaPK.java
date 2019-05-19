/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.objetos;

import java.io.Serializable;

/**
 *
 * @author ALF
 */

    
public class EtiquetaPK implements Serializable {

    
    private int idUsuario;    
    private String nombre;

    public EtiquetaPK() {
    }

    public EtiquetaPK(int idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtiquetaPK)) {
            return false;
        }
        EtiquetaPK other = (EtiquetaPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.EtiquetaPK[ idUsuario=" + idUsuario + ", nombre=" + nombre + " ]";
    }
    
}


