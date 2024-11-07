package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Esqueleto {
    
    private int x,y;
    private int ancho,alto;
    private int velocidad;
    Image imagenEsqueleto;
    public Esqueleto(int x, int y, int ancho, int alto, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.imagenEsqueleto = Herramientas.cargarImagen("images/esqueleto.png");
    }

    public void saltar(){
        this.y = this.y - velocidad;
    }
    public void caer(){
        
        this.y = this.y + velocidad;
    }
    public void mover(){
        this.x = this.x + this.velocidad;
    }
    public void cambiarDireccion(){
        this.imagenEsqueleto = Herramientas.cargarImagen("images/esqueleto1.jpg");
        this.velocidad = this.velocidad *(-1);
    }
    public boolean tocaAbajo(Isla isla){
        boolean tocaX = this.x >= isla.getX() - isla.getAncho()/2 && this.x <= isla.getX() + isla.getAncho()/2;
        boolean tocaY = this.y + this.alto/2 == isla.getY() - isla.getAlto()/2;
        return tocaY && tocaX;
    }

    public void dibujarEsqueleto(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
    }

    //Setter y Getters
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
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
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
