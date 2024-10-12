

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
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.CYAN);
    }
    public boolean tocaAbajo(Hitbox hitbox){
        boolean tocaX = this.x >= hitbox.x - hitbox.ancho/2 && this.x <= hitbox.x + hitbox.ancho/2;
        boolean tocaY = this.y + this.alto > hitbox.y - hitbox.alto/2;
        return tocaY && tocaX;
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
