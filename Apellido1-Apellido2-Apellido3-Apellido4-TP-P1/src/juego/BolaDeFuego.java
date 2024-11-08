package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class BolaDeFuego {

    Image imagenboladefuego = Herramientas.cargarImagen("images/boladefuego1.gif");
    int x,y;
    int ancho,alto;
    int velocidad;

    public BolaDeFuego(int x, int y, int ancho, int alto, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        //this.imagenboladefuego = entorno.Herramientas.cargarImagen("src/images/boladefuego.gif");
    }
    public void dibujarBolaDeFuego(Entorno entorno){
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.ORANGE);
        entorno.dibujarImagen(imagenboladefuego, x, y, 0, 0.2);
    }
    public void mover(){
        this.x = this.x + velocidad;

    }
}
