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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alfredo
 */
public class ComentarioRecurso {
   
     public static List<Comentario> obtenerComentariosRecursos(){
        Client client=ClientBuilder.newClient();
        return (List<Comentario>) client.target(
             "http://"+IPSERVER+":8080/Servidor/servicios/comentario" )
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Comentario>>(){});
       
    }
}
