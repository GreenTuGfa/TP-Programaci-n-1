package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	public Isla isla;
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		isla = new Isla(10, 10, "images/bloque.jpg");

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		// Procesamiento de un instante de tiempo
		entorno.dibujarImagen(isla.getImagenIsla(), isla.getX(), isla.getY(), 0,0.2); //El ultimo parametro es para la escala. Entre 0 y 1, se achica la imagen
		entorno.dibujarImagen(caballero.getImagenCaballero(), caballero.getXcaballero(), caballero.getYcaballero(), 0, 0.1);
		
		boolean estaPresionadaDerecha = entorno.estaPresionada(entorno.TECLA_DERECHA);
		boolean estaPresionadaIzquierda = entorno.estaPresionada(entorno.TECLA_IZQUIERDA);

		if(estaPresionadaDerecha){
			caballero.moverDerecha();
		}
		if (estaPresionadaIzquierda) {
			caballero.moverIzquierda();
		}
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
			
			do {
				caballero.caer();
			} while (caballero.getYcaballero() >= 500);
		}
	}
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
