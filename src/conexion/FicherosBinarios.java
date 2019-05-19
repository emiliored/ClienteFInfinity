/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import static conexion.Conectar.IPSERVER;
import conexion.objetos.Recurso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class FicherosBinarios {

    public static final String CARPETACLIENTE="C:\\Users\\ALF\\InfinityClienteFicheros"; 
    
    public static boolean subir(File fichero,Recurso r) { //Pasar fichero que el cliente quiere subir al servidor(idRecurso,filehash,ruta,nombre)-->Arrastrar el idUsuario y poner check visib,y falta descripcion con metodo cambiar espacio por +
        boolean boo=true;
        try {
            Client cliente = ClientBuilder.newClient();
            //Subir comprimido.
            File ficheroComprimido = CompresionZip.comprimirFicheroTemp(fichero);
            InputStream is = new FileInputStream(ficheroComprimido);
            //Subir
            int estado = cliente.target("http://"+IPSERVER+":8080/Servidor/servicios/recurso/subida?idUsuario="+r.getIdUsuario()+"&nombre=" + ficheroComprimido.getName() + "&descripcion="+r.getDescripcion()+"&visibilidad="+r.getVisibilidad())
                    .request()
                    .post(Entity.entity(is, MediaType.APPLICATION_OCTET_STREAM)).getStatus();
        } catch(Exception e){
            boo=false;
        }
        return boo;
    }

    public static boolean descargar(int idRecurso) {
        boolean boo=true;
        try {
            Client cliente = ClientBuilder.newClient();
            //Descargar comprimido.
            Response respuesta = cliente.target("http://"+IPSERVER+":8080/Servidor/servicios/recurso/descargar?idRecurso="+idRecurso) 
                    .request(MediaType.APPLICATION_OCTET_STREAM)
                    .get();
            File fichero = File.createTempFile("temp", ".zip");
            int estado = respuesta.getStatus();
            if (estado == Status.OK.getStatusCode()) {
            InputStream is = respuesta.readEntity(InputStream.class);
            OutputStream os = new FileOutputStream(fichero) ;
            int longitud;
            byte[] buffer = new byte[1024];
            while ((longitud = is.read(buffer)) >= 0) {
                os.write(buffer, 0, longitud);
            }
            os.flush();
            os.close();
            is.close();
            }
            if(!CompresionZip.descomprimirFicheros(fichero, CARPETACLIENTE))
                throw new Exception("Compresión fallida.");
        } catch (Exception ex) {
            Logger.getLogger(FicherosBinarios.class.getName()).log(Level.SEVERE, null, ex);
            boo=false;
        }
        return boo;
    }

}

