package juego;

import java.awt.Image;

import entorno.Herramientas;

public class Caballero {
    private Image imagenCaballero;
    
    private int x,y;
    private int velocidad;

    public Caballero(int x, int y, int velocidad,String rutaImagen){
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.imagenCaballero = Herramientas.cargarImagen(rutaImagen);
    }

    public void moverDerecha(){
        this.x = this.x + velocidad;
    }
    public void moverIzquierda(){
        this.x = this.x - velocidad;
    }
    public void saltar(){
        this.y = this.y - velocidad;
    }
    public void caer(){
        
        this.y = this.y + velocidad;
    }

    //Setter y Getter
    //Getters
    public int getXcaballero(){
        return this.x;
    }
    public int getYcaballero(){
        return this.y;
    }
    public int getVelocidadCaballero(){
        return this.velocidad;
    }
    public Image getImagenCaballero(){
        return this.imagenCaballero;
    }

    //Setters
    public void setXcaballero(int x){
        this.x = x;
    }
    public void setycaballero(int y){
        this.y = y;
    }
    public void setVelocidadCaballero(int velocidad){
        this.velocidad = velocidad;
    }
}
