/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Etiqueta;
import conexion.objetos.EtiquetaPK;
import conexion.objetos.Recurso;
import conexion.objetos.Visibilidad;
import controladores.BaseController;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

/**
 *
 * @author ALF
 */
public class EtiquetaUsuario {

    public static List<Etiqueta> obtenerEtiquetasPopulares() {
        Client client = ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/populares")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>() {
        });
    }

    public static List<Etiqueta> obtenerEtiquetasValoradas() {
        Client client = ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/valoradas")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>() {
        });
    }
    
    public static List<Etiqueta> obtenerEtiquetasNovedades() {
        Client client = ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/novedades")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>() {
        });

    }

    public static List<Etiqueta> obtenerEtiquetasGenerales() {
        Client client = ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/generales")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>() {
        });

    }

    public static List<Recurso> obtenerRecursoSinEtiquetar() {
        Client client = ClientBuilder.newClient();
        return (List<Recurso>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/recurso/sinetiquetar")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>() {
        });
    }

    public static List<String> obtenerEtiquetasUsuarioPublicas(int idUsuario) {
        Client client = ClientBuilder.newClient();
        return (List<String>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/publicas?idUsuario=" + idUsuario)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {
        });

    }

    public static List<String> obtenerEtiquetasUsuarioPrivadas(int idUsuario) {
        Client client = ClientBuilder.newClient();
        return (List<String>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/privadas?idUsuario=" + idUsuario)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<String>>() {
        });

    }

    //Obtener las etiquetas de un recurso.
    public static List<Etiqueta> obtenerEtiquetasRecurso(int idRecurso) {
        Client client = ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
                "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta/recurso?idRecurso=" + idRecurso+"&idUsuario="+BaseController.usuarioInicio.getIdUsuario())
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>() {
        });
    }

    //Crear una etiqueta.
    public static boolean crearEtiqueta(Visibilidad v) {
        boolean boo = false;
        Client client = ClientBuilder.newClient();
        StatusType respuesta
                = client.target(
                        "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta")
                        .request().put(Entity.entity(v, MediaType.APPLICATION_JSON)).getStatusInfo();
        System.out.println(respuesta);
        if (respuesta.equals(Response.Status.CREATED)) {
            boo = true;
        }
        return boo;
    }

    //Borrar una etiqueta.
    public static boolean borrarEtiqueta(EtiquetaPK tag) {
        boolean boo = false;
        System.out.println("La etiqueta es: " + tag.toString());
        Client client = ClientBuilder.newClient();
        StatusType respuesta
                = client.target(
                        "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/etiqueta")
                        .queryParam("idUsuario", tag.getIdUsuario())
                        .queryParam("nombre", tag.getNombre())
                        .request().delete().getStatusInfo();
        System.out.println(respuesta);
        if (respuesta.equals(Response.Status.OK)) {
            boo = true;
        }
        return boo;
    }

}
