package juego;

//import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Cofre {

    
    public int x,y;
    public int ancho,alto;
    public Image imagen = Herramientas.cargarImagen("images/cofre.png");

    public Cofre(int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujarCofre(Entorno entorno){
        //entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
        entorno.dibujarImagen(imagen, x, y, 0, 0.15);
    }
}
