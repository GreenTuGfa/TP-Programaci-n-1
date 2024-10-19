package juego;

import java.awt.Color;
//import java.awt.Image;
import entorno.Entorno;

public class Cofre {
    private int x,y;
    private int ancho,alto;

    public Cofre(int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujarCofre(Entorno entorno){
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
    }
}
