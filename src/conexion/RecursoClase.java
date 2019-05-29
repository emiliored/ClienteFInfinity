/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Etiqueta;
import conexion.objetos.Recurso;
import controladores.IdentificarController;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ALF
 */
public class RecursoClase {

    public static List<Recurso> obtenerRecursos() {
        Client client = ClientBuilder.newClient();
        return client.target(
                "http://" + IPSERVER + ":8080/ServidorFInfinity/servicios/recurso?idUsuario="+IdentificarController.usuarioActual.getIdUsuario())
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>() {
        });

    }

    public static Recurso obtenerRecursosPorId(int idRecurso) {
        Client client = ClientBuilder.newClient();
        return client.target(
                "http://" + IPSERVER + ":8080/ServidorFInfinity/servicios/recurso/" + idRecurso)
                .request(MediaType.APPLICATION_JSON).get(Recurso.class);
    }

    public static List<Recurso> obtenerRecursosPorEtiqueta(String nombre) {
        Client client = ClientBuilder.newClient();
        return client.target(
                "http://" + IPSERVER + ":8080/ServidorFInfinity/servicios/recurso/etiqueta?nombre="+nombre)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Recurso>>(){});
    }
    
}
