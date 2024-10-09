package juego;

import java.awt.Image;
import entorno.Herramientas;

public class Isla {
    
    private Image imagenIsla;
    private int x,y;
    

    public Isla(int x, int y, String rutaImagen){

        this.x = x;
        this.y = y;
        this.imagenIsla = Herramientas.cargarImagen(rutaImagen);
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
    public Image getImagenIsla(){
        return this.imagenIsla;
    }
}
