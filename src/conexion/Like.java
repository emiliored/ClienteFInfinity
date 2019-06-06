/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;


import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Like implements Serializable {

    private long contador;
    private boolean propio;

    public Like() {
    }

    public Like(long contador, boolean propio) {
        this.contador = contador;
        this.propio = propio;
    }
    
    public Like(long contador, long propio) {
        this.contador = contador;
        this.propio = (propio>0);
    }

    public long getContador() {
        return contador;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

    public boolean isPropio() {
        return propio;
    }

    public void setPropio(boolean propio) {
        this.propio = propio;
    }
    
    public long incrementar(){
        return ++this.contador;
    }
    
    public long decrementar(){
        return --this.contador;
    }
    
    public boolean invertir(){
        return this.propio!=this.propio;
    }
    
    @Override
    public String toString() {
        return "Like{" + "contador=" + contador + ", propio=" + propio + '}';
    }
}