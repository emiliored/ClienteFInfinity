/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Aprecio;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

/**
 *
 * @author ALF
 */
public class AprecioConectar {
    
    //Obtener likes.
    public static Like obtenerLikesRecurso(int idUsuario,int idRecurso) {
        Client client = ClientBuilder.newClient();
        return client.target(
                "https://" + IPSERVER + ":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/aprecio/likes?idUsuario="+idUsuario+"&idRecurso="+idRecurso)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<Like>(){});
    }
    
    //Añadir likes.
    public static boolean anadirAprecio(Aprecio a){
        boolean boo=false;
        Client client=ClientBuilder.newClient();
        StatusType respuesta=client.target(
             "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/aprecio" )
                .request().put(Entity.entity(a, MediaType.APPLICATION_JSON)).getStatusInfo();
        if(respuesta.equals(Status.CREATED))
            boo=true;
        return boo;
    }
    //Borrar likes.
    public static boolean borrarAprecio(int idUsuario,int idRecurso){
        boolean boo=false;
        Client client=ClientBuilder.newClient();
        StatusType respuesta=client.target(
             "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/aprecio?idUsuario="+idUsuario+"&idRecurso="+idRecurso)
                .request().delete().getStatusInfo();
        if(respuesta.equals(Status.OK))
            boo=true;
        return boo;
    }
    
    
}
