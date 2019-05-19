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
public class Usuario implements Serializable {

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", apodo=" + apodo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", contrasena=" + contrasena + ", usersalt=" + usersalt + '}';
    }

    private static final long serialVersionUID = 1L;
  
    private Integer idUsuario;
   
    private String apodo;
 
    private String nombre;
  
    private String apellidos;
   
    private String contrasena;
 
    private String usersalt;
  

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String apodo, String nombre, String apellidos, String contrasena, String usersalt) {
        this.idUsuario = idUsuario;
        this.apodo = apodo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.usersalt = usersalt;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsersalt() {
        return usersalt;
    }

    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "fish.payara.ejemploserverrest.Usuario[ idUsuario=" + idUsuario + " ]";
//    }
    
}