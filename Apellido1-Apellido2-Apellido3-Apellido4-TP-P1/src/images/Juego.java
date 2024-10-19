package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	public BolaDeFuego boladefuego;
	public Isla isla;
	public Isla isla2;
	public Caballero caballero;
	public Esqueleto esqueleto;
	boolean confirmar = false;

	int yCaballero = 0;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego

		
		isla = new Isla(200, 550,200,50);
		isla2 = new Isla(400,200,200,50);
		caballero = new Caballero(100, 50, 5,30,50);
		esqueleto = new Esqueleto(200, 100, 30, 50, 5);

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
		isla2.dibujarIsla(entorno);
		esqueleto.dibujarEsqueleto(entorno);
		

		boolean estaPresionadaDerecha = entorno.estaPresionada(entorno.TECLA_DERECHA);
		boolean estaPresionadaIzquierda = entorno.estaPresionada(entorno.TECLA_IZQUIERDA);

		boolean tocaDerecha = esqueleto.getX() + esqueleto.getAncho()/2 == isla.getX() + isla.getAncho()/2;
		boolean tocaIzquierda = esqueleto.getX() - esqueleto.getAncho()/2 == isla.getX() - isla.getAncho()/2;

		if(esqueleto.tocaAbajo(isla)){
			
			if(tocaDerecha || tocaIzquierda){
				esqueleto.cambiarDireccion();
				esqueleto.mover();
			}else{
				esqueleto.mover();
			}
		}else{
			esqueleto.caer();
		}

		if(estaPresionadaDerecha){
			caballero.moverDerecha();
		}
		if (estaPresionadaIzquierda) {
			caballero.moverIzquierda();
		}
		if (entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
			
			caballero.saltar();
		}
		
		if(entorno.sePresiono(entorno.TECLA_CTRL)){
			confirmar = true;
			boladefuego = new BolaDeFuego(caballero.getX(), caballero.getY(),10, 10, 10);
		}
		if(confirmar == true){
			if(boladefuego.x <= entorno.ancho()){
				boladefuego.dibujarBolaDeFuego(entorno);
				boladefuego.mover();
			}
			if(boladefuego.x == esqueleto.getX() && boladefuego.y == esqueleto.getY()){
				esqueleto = null;
			}
		}
		
		/**
		 * Siempre que el caballero no este tocando el suelo, caera automaticamente al vacio.
		 *  Osea que el caballero siempre esta "cayendo". Es su movimiento por defecto, hasta que toca toca el suelo.
		 */
		if(caballero.tocaAbajo(isla) == false){
			caballero.caer();
			if(entorno.estaPresionada(entorno.TECLA_ESPACIO)){
				caballero.saltar();
			}
		}
		
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
