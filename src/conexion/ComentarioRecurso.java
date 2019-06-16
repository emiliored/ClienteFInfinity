/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Comentario;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

/**
 *
 * @author Alfredo
 */
public class ComentarioRecurso {
   
//     public static List<Comentario> obtenerComentariosRecursos(){
//        Client client=ClientBuilder.newClient();
//        return (List<Comentario>) client.target(
//             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/comentario" )
//                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Comentario>>(){});
//    }
    
    public static List<Comentario> obtenerComentariosPorRecurso(int idRecurso){
        Client client=ClientBuilder.newClient();
        return (List<Comentario>) client.target(
             "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/comentario/recurso?idRecurso="+idRecurso)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Comentario>>(){});
    }
    
    public static boolean subirComentario(Comentario c){
        boolean boo=false;
        Client client=ClientBuilder.newClient();
        StatusType respuesta=client.target(
             "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/comentario" )
                .request().put(Entity.entity(c, MediaType.APPLICATION_JSON)).getStatusInfo();
        if(respuesta.equals(Status.CREATED))
            boo=true;
        return boo;
    }
     
}
