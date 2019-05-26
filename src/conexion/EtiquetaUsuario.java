/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Etiqueta;
import conexion.objetos.Recurso;
import conexion.objetos.RecursoCliente;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ALF
 */
public class EtiquetaUsuario {
    
     public static List<Etiqueta> obtenerEtiquetasNovedades(){
        Client client=ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/etiqueta/novedades")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>(){});
       
    }
    
    public static List<Etiqueta> obtenerEtiquetasGenerales(){
        Client client=ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/etiqueta/generales" )
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>(){});
       
    }
    public static List<RecursoCliente> obtenerRecursoSinEtiquetar(){
        Client client=ClientBuilder.newClient();
        return (List<RecursoCliente>) client.target(
             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/recurso/sinetiquetar")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<RecursoCliente>>(){});
    }

    public static List<String> obtenerEtiquetasUsuarioPublicas(int idUsuario){
        Client client=ClientBuilder.newClient();
        return (List<String>) client.target(
             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/etiqueta/publicas?idUsuario="+idUsuario)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>(){});
       
    }
    
    public static List<String> obtenerEtiquetasUsuarioPrivadas(int idUsuario){
        Client client=ClientBuilder.newClient();
        return (List<String>) client.target(
             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/etiqueta/privadas?idUsuario="+idUsuario)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>(){});
       
    }
    
}
