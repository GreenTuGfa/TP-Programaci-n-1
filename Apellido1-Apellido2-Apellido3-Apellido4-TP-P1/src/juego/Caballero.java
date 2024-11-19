package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Caballero {
    
    public BolaDeFuego boladefuego;
    public int alto,ancho;
    private int x,y;
    private int velocidad;
    private int direccion = 0;
    public Image imagenCaballero = Herramientas.cargarImagen("images/caballero.png");

    public Caballero(int x, int y, int velocidad,int ancho, int alto){
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        
    }

    public void moverDerecha(){
        this.x = this.x + velocidad;
        
    }
    public void moverIzquierda(){
        this.x = this.x - velocidad;
           
    }
    public void saltar(){
        this.y = this.y - velocidad*3;
    }
    public void caer(){
        
        this.y = this.y + velocidad;
    }
    public boolean tocaAbajo(Isla isla){
        boolean tocaX = this.x >= isla.getX() - isla.getAncho()/2 && this.x <= isla.getX() + isla.getAncho()/2;
        /*
         * Este margen de +1. Es que, dado a que el numero y, se incrementa o decrementa en base a la velocidad, no siempre
         *  esta dara justo en el valor y de de la isla, por lo que es necesario ponerle un pequeño margen
        */
        boolean tocaY = this.y + this.alto/2 >= (isla.getY() - isla.getAlto()/2) -1 && this.y + this.alto/2 <= (isla.getY() - isla.getAlto()/2) +1;
        return tocaY && tocaX;
    }
    public boolean tocaArriba(Isla isla){
        boolean tocaX = this.x >= isla.getX() - isla.getAncho()/2 && this.x <= isla.getX() + isla.getAncho()/2;
        boolean tocaY = this.y - this.alto/2 >= (isla.getY() + isla.getAlto()/2) -2 && this.y - this.alto/2 <= (isla.getY() + isla.getAlto()/2) +2;
        return tocaY && tocaX;
    }

    public boolean tocaMoneda(Moneda moneda){
        boolean tocaX = this.x >= moneda.getX() - moneda.getAncho()/2 && this.x <= moneda.getX() + moneda.getAncho()/2;
        boolean tocaY = this.y >= moneda.getY() - moneda.getAlto()/2 && this.y <= moneda.getY() + moneda.getAlto()/2;
        return tocaX && tocaY;
    }
    public void dibujarCaballero(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.RED);
        //entorno.dibujarImagen(this.imagenCaballero, this.x, this.y, 0, 0.08);
    }

    ////////Setter y Getter///////
    //Getters
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
    public int getVelocidad(){
        return this.velocidad;
    }
    public Image getImagenCaballero(){
        return this.imagenCaballero;
    }
    public int getDireccion(){
        return this.direccion;
    }

    //Setters
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    public void setDireccion(int direccion){
        this.direccion = direccion;
    }
}
