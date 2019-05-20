/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Etiqueta;
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
    
    public static List<Etiqueta> obtenerEtiquetasUsuarios(){
        Client client=ClientBuilder.newClient();
        return (List<Etiqueta>) client.target(
             "http://"+IPSERVER+":8080/ServidorFInfinity/servicios/etiqueta" )
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Etiqueta>>(){});
       
    }
}
