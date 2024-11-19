package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class BolaDeFuego {

    Image imagenboladefuego = Herramientas.cargarImagen("images/boladefuego4.gif");
    int x, y;
    int ancho, alto;
    int velocidad;
    int direccion;  // 1 para derecha, -1 para izquierda

    public BolaDeFuego(int x, int y, int ancho, int alto, int velocidad, int direccion) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.direccion = direccion; // Dirección determinada por el caballero
    }

    public void dibujarBolaDeFuego(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.ORANGE);
        entorno.dibujarImagen(imagenboladefuego, x, y, 0, 0.2);
    }

    public void mover() {
        this.x = this.x + (velocidad * direccion);  // La dirección afecta el movimiento
    }

    public void comprobarReposicion(int pantallaAncho) {
        // Si la bola de fuego se mueve a la izquierda y sale de la pantalla
        if (this.direccion == -1 && this.x < 0) {
            this.x = pantallaAncho;  // Reposicionarla al lado derecho de la pantalla
            this.y = 200; // Ajustar la posición vertical si es necesario
        }
        // Si la bola de fuego se mueve a la derecha y sale de la pantalla
        else if (this.direccion == 1 && this.x > pantallaAncho) {
            this.x = 0;  // Reposicionarla al lado izquierdo de la pantalla
            this.y = 200; // Ajustar la posición vertical si es necesario
        }
    }

    public boolean tocaEsqueleto(Esqueleto esqueleto) {
        boolean tocaX = this.x >= esqueleto.getX() - esqueleto.getAncho() / 2 && this.x <= esqueleto.getX() + esqueleto.getAncho() / 2;
        boolean tocaY = this.y >= esqueleto.getY() - esqueleto.getAlto() / 2 && this.y <= esqueleto.getY() + esqueleto.getAlto() / 2;
        return tocaX && tocaY;
    }
}