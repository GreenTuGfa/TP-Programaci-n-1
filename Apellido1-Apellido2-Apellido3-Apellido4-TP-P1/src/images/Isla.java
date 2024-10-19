package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Isla {
    
    private Image imagenIsla;
    private int x,y;
    private int ancho, alto;

    public Isla(int x, int y, int ancho, int alto){

        this.x = x;
        this.y = y;
        this.ancho = ancho; 
        this.alto = alto;
    }
    
    public void dibujarIsla(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.CYAN);
    }
    

    //Metodos getter

    public int getX(){
        int x = this.x;
        return x;
    }
    public int getY(){
        int y = this.y;
        return y;
    }
    public Image getImagen(){
        return this.imagenIsla;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }
}
