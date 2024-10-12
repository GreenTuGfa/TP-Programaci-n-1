package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Caballero {

    BolaDeFuego bolaDeFuego;
    private Image imagenCaballero;
    public int alto,ancho;
    private int x,y;
    private int velocidad;

    public Caballero(int x, int y, int velocidad,String rutaImagen){
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.imagenCaballero = Herramientas.cargarImagen(rutaImagen);
        this.ancho = imagenCaballero.getWidth(null);
        this.alto = imagenCaballero.getHeight(null);
        
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
    public void lanzarBolaDeFuego(Entorno entorno){
        
    }

    public void dibujarCaballero(Entorno entorno){
        entorno.dibujarImagen(this.imagenCaballero, this.x, this.y, 0,0.5);
    }

    //Setter y Getter
    //Getters
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getVelocidad(){
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
