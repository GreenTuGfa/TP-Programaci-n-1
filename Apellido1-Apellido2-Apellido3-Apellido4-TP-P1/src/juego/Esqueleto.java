package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Esqueleto {
    
    private int x,y;
    private int ancho,alto;
    private int velocidad;
    boolean estaVivo;  // Atributo que indica si el esqueleto está vivo

    Image imagenEsqueleto;
    public Esqueleto(int x, int y, int ancho, int alto, int velocidad){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.imagenEsqueleto = Herramientas.cargarImagen("images/esqueleto.png");
    }
    public Esqueleto() {
        estaVivo = true;  // Inicialmente el esqueleto está vivo
    }

    public void morir() {
        estaVivo = false;  // Cambia el estado a muerto cuando el esqueleto es destruido
    }

    public void saltar(){
        this.y = this.y - velocidad;
    }
    public void caer(){
        
        this.y = this.y + velocidad;
    }
    public void mover(){
        this.x = this.x + this.velocidad;
    }
    public void cambiarDireccion(){
        this.imagenEsqueleto = Herramientas.cargarImagen("images/esqueleto1.png");
        this.velocidad = this.velocidad *(-1);
    }
    public boolean tocaAbajo(Isla isla){
        boolean tocaX = this.x >= isla.getX() - isla.getAncho()/2 && this.x <= isla.getX() + isla.getAncho()/2;
        boolean tocaY = this.y + this.alto/2 == isla.getY() - isla.getAlto()/2;
        return tocaY && tocaX;
    }
    public boolean tocaMoneda(Moneda moneda){
        boolean tocaX = this.x >= moneda.x - moneda.ancho/2 && this.x <= moneda.x + moneda.ancho/2;
        boolean tocaY = this.y >= moneda.y - moneda.alto/2 && this.y <= moneda.y + moneda.alto/2;
        return tocaX && tocaY;
    }
    public boolean tocaCaballero(Caballero caballero){
        boolean tocaX = this.x >= caballero.getX() - caballero.getAncho()/2 && this.x <= caballero.getX() + caballero.getAncho()/2;
        boolean tocaY = this.y >= caballero.getY() - caballero.getAlto()/2 && this.y <= caballero.getY() + caballero.getAlto()/2;
        return tocaX && tocaY;
    }

    public void dibujarEsqueleto(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.WHITE);
        entorno.dibujarImagen(imagenEsqueleto, this.x, this.y, 0, 0.1);
    }

    //Setter y Getters
    //Getters
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
}



