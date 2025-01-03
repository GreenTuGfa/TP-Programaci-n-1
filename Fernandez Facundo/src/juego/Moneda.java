package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;

public class Moneda {
    
    private int x,y;
    private int ancho,alto;
    private int velocidad;
    private Image imagen;
    private Random random = new Random();
    private int direccion = 0;

    public Moneda(int x, int y, int ancho, int alto, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.imagen = Herramientas.cargarImagen("images/moneda4.gif");
    }
    public void caer(){
        
        this.y = this.y + velocidad;
    }
    public void mover(){
        this.x = this.x + this.velocidad;
    }
    public void moverDerecha(){
        this.x = this.x + this.velocidad;
    }
    public void moverIzquierda(){
        this.x = this.x - this.velocidad;
    }
    public int direccionAleatoria(){
        direccion = random.nextInt(2);
        return direccion;
    }
    public void cambiarDireccion(){
        this.velocidad = this.velocidad *(-1);
    }
    public boolean tocaAbajo(Isla isla){
        boolean tocaX = this.x >= isla.getX() - isla.getAncho()/2 && this.x <= isla.getX() + isla.getAncho()/2;
        boolean tocaY = this.y + this.alto/2 == isla.getY() - isla.getAlto()/2;
        return tocaY && tocaX;
    }
    public void dibujarMoneda(Entorno entorno){
        //entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.YELLOW);
        entorno.dibujarImagen(imagen, x, y, 0, 0.15);
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getVelocidad(){
        return this.velocidad;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }
    public int getDireccion(){
        return this.direccion;
    }
}
