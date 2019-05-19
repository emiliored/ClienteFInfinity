/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author usuario
 */
public class CompresionZip {
    
     //Descomprimir ficheros zip.
    public static boolean descomprimirFicheros(File ficheroZip,String rutaDestino){
        boolean boo=true;
        try(ZipInputStream zis=new ZipInputStream(new FileInputStream(ficheroZip))){
            for(ZipEntry ze=zis.getNextEntry();Objects.nonNull(ze);ze=zis.getNextEntry()){
                //Preparamos el nuevo fichero.
                File nuevoFichero=new File(rutaDestino,ze.getName());
                //Leemos las entradas Zip.
                if(!ze.isDirectory()){  //Fichero
                    FileOutputStream fos=new FileOutputStream(nuevoFichero);
                    //Leemos la entrada del zip, y lo guardamos en un fichero.
                    byte[]buffer=new byte[1024];
                    int longitud;
                    while((longitud=zis.read(buffer))>0)
                        fos.write(buffer, 0, longitud);
                    fos.close();
                }
                else{    //Directorio
                    nuevoFichero.mkdir();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CompresionZip.class.getName()).log(Level.SEVERE, null, ex);
            boo=false;
        }
        return boo;
    }   //Bien  
   
    
      //Comprimir fichero en formato zip.
    public static File comprimirFicheroTemp(File fichero) throws IOException{
        File fichTemp=File.createTempFile(fichero.getName().substring(0,fichero.getName().lastIndexOf(".")),".zip");
        try(ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(
                    fichTemp));
                FileInputStream fis=new FileInputStream(fichero)){
            //Establecemos la compresión.
            zos.setLevel(9);
            zos.putNextEntry(new ZipEntry(fichero.getName()));
            byte [] buffer=new byte[1024];
            int longitud;
            while((longitud=fis.read(buffer))>=0)
                zos.write(buffer, 0, longitud);
        } catch (IOException ex) {
            Logger.getLogger(CompresionZip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fichTemp;
    }
    
     //Comprimir fichero en formato zip.
    public static boolean comprimirFichero(File fichero,String ruta){
        boolean boo=true;
        try(ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(
                    ruta+fichero.getName().substring(0,fichero.getName().lastIndexOf("."))+".zip"));
                FileInputStream fis=new FileInputStream(fichero)){
            //Establecemos la compresión.
            zos.setLevel(9);
            zos.putNextEntry(new ZipEntry(fichero.getName()));
            byte [] buffer=new byte[1024];
            int longitud;
            while((longitud=fis.read(buffer))>=0)
                zos.write(buffer, 0, longitud);
        } catch (IOException ex) {
            Logger.getLogger(CompresionZip.class.getName()).log(Level.SEVERE, null, ex);
            boo=false;
        }
        return boo;
    }   //Bien
    
}

