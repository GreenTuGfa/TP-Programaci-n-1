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
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego

		isla = new Isla(100, 550, "images/isla1.jpg");
		caballero = new Caballero(100, 100, 5,"images/caballero.png");
		hitboxIsla = new Hitbox(isla.getX(), isla.getY(),300,50);
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
		entorno.dibujarRectangulo(hitboxIsla.getX(), hitboxIsla.getY(), hitboxIsla.getAncho()*0.5, hitboxIsla.getAlto()*0.5, 0, Color.BLUE); 
		entorno.dibujarImagen(isla.getImagenIsla(), isla.getX(), isla.getY(), 0,0.5); //0.5 es el valor por el que se multiplica el alto y ancho de la imagen para que se achique
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
			
			caballero.saltar();
		}
		if(colicionoConIsla() != true){
			caballero.caer();
		}
	}

	//Este metodo no deben darle demasiada bola por que todavia no esta del todo bien hecho
	public boolean colicionoConIsla(){
		if(caballero.getYcaballero() == hitboxIsla.getY()-hitboxIsla.getAlto()){
			return true;
		}else{
			return false;
		}
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
