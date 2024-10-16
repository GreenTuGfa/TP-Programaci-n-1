package juego;


//import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	public Isla isla;
	public Caballero caballero;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego

		isla = new Isla(200, 550,200,50);
		caballero = new Caballero(100, 50, 5,30,50);
		//hitboxIsla = new Hitbox(200, 550,200,50);
		//hitboxCaballero = new Hitbox(100, 50, 30, 30);
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

		caballero.dibujarCaballero(entorno);
		isla.dibujarIsla(entorno);
		
		boolean estaPresionadaDerecha = entorno.estaPresionada(entorno.TECLA_DERECHA);
		boolean estaPresionadaIzquierda = entorno.estaPresionada(entorno.TECLA_IZQUIERDA);

		if(estaPresionadaDerecha){
			caballero.moverDerecha();
		}
		if (estaPresionadaIzquierda) {
			caballero.moverIzquierda();
		}
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
			
			caballero.saltar();
		}

		/**
		 * Siempre que el caballero no este tocando el suelo, caera automaticamente al vacio.
		 *  Osea que el caballero siempre esta "cayendo". Es su movimiento por defecto, hasta que toca toca el suelo.
		 */
		if(caballero.tocaAbajo(isla) == false){
			caballero.caer();
		}
		//En proceso...
		if(caballero.tocaCostado(isla) && (estaPresionadaDerecha || estaPresionadaIzquierda)){
			
		}
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
