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
public class AprecioPK implements Serializable {

    private int idUsuario;
    private int idRecurso;

    public AprecioPK() {
    }

    public AprecioPK(int idUsuario, int idRecurso) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (int) idRecurso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AprecioPK)) {
            return false;
        }
        AprecioPK other = (AprecioPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idRecurso != other.idRecurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.AprecioPK[ idUsuario=" + idUsuario + ", idRecurso=" + idRecurso + " ]";
    }
    
}
