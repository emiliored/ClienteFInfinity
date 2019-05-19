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
public class VisibilidadPK implements Serializable {

    private int idUsuario;
    private String nomEtiqueta;
    private int idRecurso;

    public VisibilidadPK() {
    }

    public VisibilidadPK(int idUsuario, String nomEtiqueta, int idRecurso) {
        this.idUsuario = idUsuario;
        this.nomEtiqueta = nomEtiqueta;
        this.idRecurso = idRecurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomEtiqueta() {
        return nomEtiqueta;
    }

    public void setNomEtiqueta(String nomEtiqueta) {
        this.nomEtiqueta = nomEtiqueta;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (nomEtiqueta != null ? nomEtiqueta.hashCode() : 0);
        hash += (int) idRecurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisibilidadPK)) {
            return false;
        }
        VisibilidadPK other = (VisibilidadPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if ((this.nomEtiqueta == null && other.nomEtiqueta != null) || (this.nomEtiqueta != null && !this.nomEtiqueta.equals(other.nomEtiqueta))) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.VisibilidadPK[ idUsuario=" + idUsuario + ", nomEtiqueta=" + nomEtiqueta + ", idRecurso=" + idRecurso + " ]";
    }
    
}

