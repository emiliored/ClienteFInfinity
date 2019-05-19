/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.objetos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ALF
 */
public class Aprecio implements Serializable {

    private static final long serialVersionUID = 1L;
    protected AprecioPK aprecioPK;  
    private Date fecha;
    private Usuario usuario;
    private Recurso recurso;
    public Aprecio() {
    }

    public Aprecio(AprecioPK aprecioPK) {
        this.aprecioPK = aprecioPK;
    }

    public Aprecio(AprecioPK aprecioPK, Date fecha) {
        this.aprecioPK = aprecioPK;
        this.fecha = fecha;
    }

    public Aprecio(int idUsuario, int idRecurso) {
        this.aprecioPK = new AprecioPK(idUsuario, idRecurso);
    }

    public AprecioPK getAprecioPK() {
        return aprecioPK;
    }

    public void setAprecioPK(AprecioPK aprecioPK) {
        this.aprecioPK = aprecioPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aprecioPK != null ? aprecioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aprecio)) {
            return false;
        }
        Aprecio other = (Aprecio) object;
        if ((this.aprecioPK == null && other.aprecioPK != null) || (this.aprecioPK != null && !this.aprecioPK.equals(other.aprecioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.Aprecio[ aprecioPK=" + aprecioPK + " ]";
    }
    
}
