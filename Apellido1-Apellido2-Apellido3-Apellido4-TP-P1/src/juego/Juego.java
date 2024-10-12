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
	public Caballero caballero;
	public Hitbox hitboxIsla;
	public Hitbox hitboxCaballero;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego

		isla = new Isla(200, 550, "images/isla1.jpg");
		caballero = new Caballero(100, 50, 5,"images/caballero.png");
		hitboxIsla = new Hitbox(isla.getX(), isla.getY(),isla.getAnchoIsla(),isla.getAltoIsla());
		hitboxCaballero = new Hitbox(caballero.getX(), caballero.getY(), caballero.ancho, caballero.alto);
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

		//Se multiplica el alto y ancho de la hitbox por 0.5, para achicarlo a la misma altura de la imagen de la isla. La imagen de la isla tambien se multiplica por 0.5 para ahicarla
		//isla.dibujarIsla(entorno);
		caballero.dibujarCaballero(entorno);
		hitboxIsla.dibujarHitbox(entorno);
		
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
		if(hitboxCaballero.tocaAbajo(hitboxIsla) == false){
			caballero.caer();
		}
	}

	//Este metodo no deben darle demasiada bola por que todavia no esta del todo bien hecho
	// public boolean colicionoConIsla(){
	// 	boolean tocaX = caballero.getX() > hitboxIsla.getX() - hitboxIsla.getAncho()/2 && caballero.getX() < hitboxIsla.getX() + hitboxIsla.getAncho()/2;
	// 	boolean tocaY = caballero.getY() - caballero.alto/2 > hitboxIsla.getY() - hitboxIsla.getAlto()/2;
	// 	return tocaY;
	// }
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
