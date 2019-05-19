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
public class Visibilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    protected VisibilidadPK visibilidadPK;
    private boolean visibilidad;
    private Etiqueta etiqueta;
    private Recurso recurso;


    public Visibilidad() {
    }

    public Visibilidad(VisibilidadPK visibilidadPK) {
        this.visibilidadPK = visibilidadPK;
    }

    public Visibilidad(VisibilidadPK visibilidadPK, boolean visibilidad) {
        this.visibilidadPK = visibilidadPK;
        this.visibilidad = visibilidad;
    }

    public Visibilidad(int idUsuario, String nomEtiqueta, int idRecurso) {
        this.visibilidadPK = new VisibilidadPK(idUsuario, nomEtiqueta, idRecurso);
    }

    public VisibilidadPK getVisibilidadPK() {
        return visibilidadPK;
    }

    public void setVisibilidadPK(VisibilidadPK visibilidadPK) {
        this.visibilidadPK = visibilidadPK;
    }

    public boolean getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Etiqueta getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Etiqueta etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visibilidadPK != null ? visibilidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visibilidad)) {
            return false;
        }
        Visibilidad other = (Visibilidad) object;
        if ((this.visibilidadPK == null && other.visibilidadPK != null) || (this.visibilidadPK != null && !this.visibilidadPK.equals(other.visibilidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.Visibilidad[ visibilidadPK=" + visibilidadPK + " ]";
    }
    
}
