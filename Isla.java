package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Isla {
    
    private Image imagenIsla;
    private int x,y;
    private int anchoIsla, altoIsla;

    public Isla(int x, int y, String rutaImagen){

        this.x = x;
        this.y = y;
        this.imagenIsla = Herramientas.cargarImagen(rutaImagen);
        this.anchoIsla = imagenIsla.getWidth(null); 
        this.altoIsla = imagenIsla.getHeight(null);
    }
    
    public void dibujarIsla(Entorno entorno){
        entorno.dibujarImagen(this.imagenIsla, this.x, this.y, 0);
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
    public int getAnchoIsla(){
        return this.anchoIsla;
    }
    public int getAltoIsla(){
        return this.altoIsla;
    }
    
}
