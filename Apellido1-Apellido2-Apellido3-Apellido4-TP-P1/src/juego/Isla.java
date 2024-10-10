package juego;

import java.awt.Image;
import entorno.Herramientas;

public class Isla {
    
    
    private Image imagenIsla;
    private int x,y;
    private int anchoIsla, altoIsla;

    public Isla(int x, int y, String rutaImagen){

        this.x = x;
        this.y = y;
        this.imagenIsla = Herramientas.cargarImagen(rutaImagen);
        this.anchoIsla = imagenIsla.getWidth(null); //Se obtiene el alto y el ancho de la imagen ORIGINAL(Si se achica la imagen, se debe achicar tambien el alto y ancho de la hitbox)
        this.altoIsla = imagenIsla.getWidth(null);
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
