package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class BolaDeFuego {

    Image imagenboladefuego = Herramientas.cargarImagen("images/boladefuego4.gif");
    int x,y;
    int ancho,alto;
    int velocidad;
    int direccion = 0;

    public BolaDeFuego(int x, int y, int ancho, int alto, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }
    public void dibujarBolaDeFuego(Entorno entorno){
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.ORANGE);
        entorno.dibujarImagen(imagenboladefuego, x, y, 0, 0.2);
    }
    public void mover(){
        this.x = this.x + velocidad;

    }
    public void moverDerecha(){
        this.x = this.x + velocidad;
    }
    public void moverIzquierda(){
        this.x = this.x - velocidad;
    }
    public boolean tocaEsqueleto(Esqueleto esqueleto){
        boolean tocaX = this.x >= esqueleto.getX() - esqueleto.getAncho()/2 && this.x <= esqueleto.getX() + esqueleto.getAncho()/2;
        boolean tocaY = this.y >= esqueleto.getY() - esqueleto.getAlto()/2 && this.y <= esqueleto.getY() + esqueleto.getAlto()/2;
        return tocaX && tocaY;
    }
}
