package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Caballero {
    
    public BolaDeFuego boladefuego;
    private Image imagenCaballero;
    public int alto,ancho;
    private int x,y;
    private int velocidad;

    public Caballero(int x, int y, int velocidad,int ancho, int alto){
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        this.imagenCaballero = Herramientas.cargarImagen("images/caballero.png");
        
    }

    public void moverDerecha(){
        this.x = this.x + velocidad;
    }
    public void moverIzquierda(){
        this.x = this.x - velocidad;
    }
    public void saltar(){
        this.y = this.y - velocidad*2;
    }
    public void caer(){
        
        this.y = this.y + velocidad*2;
    }
    public boolean tocaAbajo(Isla isla){
        boolean tocaX = this.x >= isla.getX() - isla.getAncho()/2 && this.x <= isla.getX() + isla.getAncho()/2;
        boolean tocaY = this.y + this.alto/2 == isla.getY() - isla.getAlto()/2;
        return tocaY && tocaX;
    }

    public void dibujarCaballero(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
        //entorno.dibujarImagen(this.imagenCaballero, this.x, this.y, 0, 0.1);
    }

    ////////Setter y Getter///////
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
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
}
