/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Usuario;
import controladores.BaseController;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author ALF
 */ 
public class UsuarioLogin { 
      
   public static boolean loginUsuario(String apodoUser, String contrasenaUser){
        boolean respuesta=false;
        Client client=ClientBuilder.newClient();
        Response respuestaServidor=client.target(
             "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/usuario/login?apodo="+apodoUser+"&contrasena="+contrasenaUser).request()
                .get();
        if(Status.ACCEPTED.getStatusCode()==respuestaServidor.getStatus()){
            BaseController.usuarioInicio=respuestaServidor.readEntity(UsuarioCliente.class);
            respuesta=true;
        }    
        return respuesta;
    }
   
    public static boolean registroUsuario(String nombreUser, String apellidosUser,String apodoUser, String contrasenaUser){
        boolean respuesta=false;
        Usuario u=new Usuario(null,apodoUser,nombreUser,apellidosUser,contrasenaUser,null);
        Client client=ClientBuilder.newClient();
        int estadoServidor=client.target(
             "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/usuario/registro").request()
                .put(Entity.entity(u,MediaType.APPLICATION_JSON)).getStatus();
        if(Status.CREATED.getStatusCode()==estadoServidor)
            respuesta=true;
        System.out.println("Estado:"+Status.CREATED.getStatusCode()+"Retorno:"+estadoServidor);
        System.out.println(u.toString());
        return respuesta;
    }
    
    public static String obtenerApodoUsuario(int idUsuario){
         Client client=ClientBuilder.newClient();
        return client.target(
             "http://"+IPSERVER+":"+Conectar.PORTSERVER+"/ServidorFInfinity/servicios/usuario/apodo?idUsuario="+idUsuario)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<String>(){});
    }
}
    

