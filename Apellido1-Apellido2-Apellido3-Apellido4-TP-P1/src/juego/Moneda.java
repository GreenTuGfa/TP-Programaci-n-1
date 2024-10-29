package juego;

import java.awt.Color;
//import java.awt.Image;
import entorno.Entorno;

public class Moneda {
    
    private int x,y;
    private int ancho,alto;
    private int velocidad;

    public Moneda(int x, int y, int ancho, int alto, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }
    public void caer(){
        
        this.y = this.y + velocidad;
    }
    public void mover(){
        this.x = this.x + this.velocidad;
    }
    public void cambiarDireccion(){
        this.velocidad = this.velocidad *(-1);
    }
    public void dibujarMoneda(Entorno entorno){
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.YELLOW);
    }
}
