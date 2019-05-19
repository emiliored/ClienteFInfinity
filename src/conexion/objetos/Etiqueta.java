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
public class Etiqueta implements Serializable{

    private static final long serialVersionUID = 1L;

    protected EtiquetaPK etiquetaPK;


    private Usuario usuario;

    public Etiqueta() {
    }

    public Etiqueta(EtiquetaPK etiquetaPK) {
        this.etiquetaPK = etiquetaPK;
    }

//    public Etiqueta(int idUsuario, String nombre) {
//        this.etiquetaPK = new EtiquetaPK(idUsuario, nombre);
//    }

    public EtiquetaPK getEtiquetaPK() {
        return etiquetaPK;
    }

    public void setEtiquetaPK(EtiquetaPK etiquetaPK) {
        this.etiquetaPK = etiquetaPK;
    }

//    @JsonbTransient
//    public Collection<Visibilidad> getVisibilidadCollection() {
//        return visibilidadCollection;
//    }

//    public void setVisibilidadCollection(Collection<Visibilidad> visibilidadCollection) {
//        this.visibilidadCollection = visibilidadCollection;
//    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etiquetaPK != null ? etiquetaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etiqueta)) {
            return false;
        }
        Etiqueta other = (Etiqueta) object;
        if ((this.etiquetaPK == null && other.etiquetaPK != null) || (this.etiquetaPK != null && !this.etiquetaPK.equals(other.etiquetaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.Etiqueta[ etiquetaPK=" + etiquetaPK + " ]";
    }
    
}
