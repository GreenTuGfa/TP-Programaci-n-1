package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import entorno.Entorno;
import entorno.Herramientas;

public class Caballero {

    public BolaDeFuego boladefuego;
    private Image imagenCaballero;
    private Image imagenCaballeroReflejada;
    public int alto, ancho;
    private int x, y;
    private int velocidad;
    private int direccion;  // 1 para derecha, -1 para izquierda
    private int velocidadSalto;  // Velocidad del salto (cuántas unidades sube por ciclo)

    private int alturaMaxima;    // Altura máxima del salto

    public boolean saltando;    // Indica si el caballero está saltando
    private int yInicial; // Posición inicial en el eje y
    
    
    public Caballero(int x, int y, int velocidad, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        this.imagenCaballero = Herramientas.cargarImagen("images/caballero.png");
        this.direccion = 1; // Por defecto, el caballero está mirando a la derecha
        this.imagenCaballeroReflejada = crearImagenReflejada(imagenCaballero);
        this.velocidadSalto = 3;     // Ajusta la velocidad del salto
        this.alturaMaxima = 140;     // Altura máxima del salto
        this.saltando = false;
        
    }

    // Método para reflejar la imagen horizontalmente
    private Image crearImagenReflejada(Image original) {
        int width = original.getWidth(null);
        int height = original.getHeight(null);

        // Crear una nueva imagen en memoria con el mismo tamaño
        BufferedImage imagenReflejada = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Obtener el objeto Graphics2D para dibujar sobre la imagen
        Graphics2D g2d
        = imagenReflejada.createGraphics();

        // Reflejar la imagen horizontalmente
        g2d.drawImage(original, width, 0, -width, height, null);

        // Finalizar el dibujo
        g2d.dispose();

        return imagenReflejada;
    }

    public void moverDerecha() {
        this.x = this.x + velocidad;
        this.direccion = 1;  // Actualizar dirección a la derecha
    }

    public void moverIzquierda() {
        this.x = this.x - velocidad;
        this.direccion = -1;  // Actualizar dirección a la izquierda
    }
    public void saltar() {
        // Solo permite saltar si no está en medio de un salto
        if (!saltando) {
            saltando = true;
            yInicial = y; // Guarda la posición inicial al comenzar el salto
        }
    }

    public void actualizarSalto() {
        if (saltando) {
            // Si aún no ha alcanzado la altura máxima, sigue subiendo
            if (y > yInicial - alturaMaxima) {
                y -= velocidadSalto;
            } else {
                // Una vez alcanzada la altura máxima, comienza a caer
                saltando = false;
            }
        }
    }
    public void caer() {
        if (!saltando && this.y < 600) {
            this.y += 3;  // Puedes ajustar la velocidad de caída si es necesario
        }
    }


    public boolean tocaAbajo(Isla isla) {
        // Ajusta un margen de error para la comprobación de colisión en X y Y
        boolean tocaX = this.x + this.ancho / 2 > isla.getX() - isla.getAncho() / 2 && this.x - this.ancho / 2 < isla.getX() + isla.getAncho() / 2;
        boolean tocaY = this.y + this.alto / 2 >= isla.getY() - isla.getAlto() / 2 && this.y + this.alto / 2 <= isla.getY() + isla.getAlto() / 2;
        return tocaY && tocaX;
    }

    public boolean tocaArriba(Isla isla) {
        boolean tocaX = this.x >= isla.getX() - isla.getAncho() / 2 && this.x <= isla.getX() + isla.getAncho() / 2;
        boolean tocaY = this.y + this.alto / 2 == isla.getY() + isla.getAlto() / 2;
        return tocaY && tocaX;
    }

    public boolean tocaMoneda(Moneda moneda) {
        boolean tocaX = this.x >= moneda.x - moneda.ancho / 2 && this.x <= moneda.x + moneda.ancho / 2;
        boolean tocaY = this.y >= moneda.y - moneda.alto / 2 && this.y <= moneda.y + moneda.alto / 2;
        return tocaX && tocaY;
    }

    public void dibujarCaballero(Entorno entorno) {
        if (this.direccion == -1) {  // Si la dirección es hacia la izquierda
            entorno.dibujarImagen(this.imagenCaballeroReflejada, this.x, this.y, 0, 0.07);  // Usar la imagen reflejada
        } else {
            entorno.dibujarImagen(this.imagenCaballero, this.x, this.y, 0, 0.07);  // Imagen normal
        }
    }

    //////// Getter y Setter /////////

    // Getter
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    public Image getImagenCaballero() {
        return this.imagenCaballero;
    }

    public int getDireccion() {
        return this.direccion; // 1 para derecha, -1 para izquierda
    }

    // Setter
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
 // Getter para consultar el estado desde otras clases
    public boolean isSaltando() {
        return this.saltando;
    }
}
