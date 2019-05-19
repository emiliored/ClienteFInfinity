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
//    private Collection<Visibilidad> visibilidadCollection;
//    private Collection<Comentario> comentarioCollection;
//    private Collection<Aprecio> aprecioCollection;
    private Usuario idUsuario;

    public Recurso() {
    }

    public Recurso(Integer idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Recurso(Integer idRecurso, String nombre, String descripcion, String filehash, String ruta, boolean visibilidad) {
        this.idRecurso = idRecurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.filehash = filehash;
        this.ruta = ruta;
        this.visibilidad = visibilidad;
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

//    @JsonbTransient
//    public Collection<Visibilidad> getVisibilidadCollection() {
//        return visibilidadCollection;
//    }

//    public void setVisibilidadCollection(Collection<Visibilidad> visibilidadCollection) {
//        this.visibilidadCollection = visibilidadCollection;
//    }

//    @JsonbTransient
//    public Collection<Comentario> getComentarioCollection() {
//        return comentarioCollection;
//    }

//    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
//        this.comentarioCollection = comentarioCollection;
//    }

//    @JsonbTransient
//    public Collection<Aprecio> getAprecioCollection() {
//        return aprecioCollection;
//    }

//    public void setAprecioCollection(Collection<Aprecio> aprecioCollection) {
//        this.aprecioCollection = aprecioCollection;
//    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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

    @Override
    public String toString() {
        return "fish.payara.ejemploserverrest.Recurso[ idRecurso=" + idRecurso + " ]";
    }
    
}

