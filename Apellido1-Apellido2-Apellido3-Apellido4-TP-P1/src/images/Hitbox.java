

package juego;

import java.awt.Color;

import entorno.Entorno;

public class Hitbox {
    
    int x,y;
    int ancho,alto;

    public Hitbox(int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujarHitbox(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.CYAN);
    }

    //Metodos Setter y Getter
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }

}
