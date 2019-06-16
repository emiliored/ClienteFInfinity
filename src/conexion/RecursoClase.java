/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Recurso;
import controladores.BaseController;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.flume.thrift.Status;

/**
 *
 * @author ALF
 */
public class RecursoClase {

    public static List<Recurso> obtenerRecursos() {
        Client client = ClientBuilder.newClient();
        return client.target(
                "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/recurso?idUsuario=" + BaseController.usuarioInicio.getIdUsuario())
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>() {
        });

    }

    public static Recurso obtenerRecursosPorId(int idRecurso) {
        Client client = ClientBuilder.newClient();
        return client.target(
                "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/recurso/" + idRecurso)
                .request(MediaType.APPLICATION_JSON).get(Recurso.class);
    }

    public static List<Recurso> obtenerRecursosPorEtiqueta(String nombre) {
        Client client = ClientBuilder.newClient();
        return client.target(
                "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/recurso/etiqueta?nombre=" + nombre)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>() {
        });
    }

    public static List<Recurso> obtenerRecursosBuscarPorEtiqueta(String nombre) {
        Client client = ClientBuilder.newClient();
        return client.target(
                "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/recurso/buscar?nombre=" + nombre)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>() {
        });
    }

    public static boolean eliminarRecurso(int idRecurso) {
        boolean boo = false;
        Client client = ClientBuilder.newClient();
        Response.StatusType estado = client.target(
                "https://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/recurso/borrar?idRecurso=" + idRecurso)
                .request().delete().getStatusInfo();
        if (Response.Status.OK.getStatusCode() == estado.getStatusCode()) {
            boo = true;
        }
        return boo;
    }
}
