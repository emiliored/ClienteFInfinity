/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion.objetos;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author ALF
 */
public class Recurso implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idRecurso;
    private String nombre;
    private String descripcion;
    private String filehash;
    private String ruta;
    private boolean visibilidad;
    private int idUsuario;
    private Usuario usuario;

    public Recurso() {
    }

    public Recurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Recurso(Integer idRecurso, String nombre, String descripcion, String filehash, String ruta, boolean visibilidad, int idUsuario, Usuario usuario) {
        this.idRecurso = idRecurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.filehash = filehash;
        this.ruta = ruta;
        this.visibilidad = visibilidad;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFilehash() {
        return filehash;
    }

    public void setFilehash(String filehash) {
        this.filehash = filehash;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecurso != null ? idRecurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.idRecurso == null && other.idRecurso != null) || (this.idRecurso != null && !this.idRecurso.equals(other.idRecurso))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Recurso{" + "idRecurso=" + idRecurso + ", nombre=" + nombre + ", descripcion=" + descripcion + ", filehash=" + filehash + ", ruta=" + ruta + ", visibilidad=" + visibilidad + ", idUsuario=" + idUsuario + ", usuario=" + usuario + '}';
//    }

    @Override
    public String toString() {
        return idRecurso + "  " + nombre ;
    }

    

   
}

