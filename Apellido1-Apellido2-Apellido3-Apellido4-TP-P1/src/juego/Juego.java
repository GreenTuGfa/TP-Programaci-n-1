package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	Cofre cofre;
	public BolaDeFuego boladefuego;
	public Isla[] islas = new Isla[15];
	public Caballero caballero;
	public Esqueleto esqueleto;
	boolean confirmar = false;

	int yCaballero = 0;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego

		generarIslas();
		cofre = new Cofre(400,50,30,30);
		caballero = new Caballero(100, 50, 5,20,30);
		esqueleto = new Esqueleto(150, 100, 20, 30, 5);

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
		dibujarIslas(islas);
		cofre.dibujarCofre(entorno);
		esqueleto.dibujarEsqueleto(entorno);
		caballero.dibujarCaballero(entorno);


		//SECCION DE CABALLERO Y ESQUELETO

		boolean estaPresionadaDerecha = entorno.estaPresionada(entorno.TECLA_DERECHA);
		boolean estaPresionadaIzquierda = entorno.estaPresionada(entorno.TECLA_IZQUIERDA);

		if(esqueletoEstaTocandoAlgunaIsla(islas) != null){
			Isla isla = esqueletoEstaTocandoAlgunaIsla(islas); //Guarda la isla que el esqueleto toco

			boolean tocaDerecha = esqueleto.getX() + esqueleto.getAncho()/2 == isla.getX() + isla.getAncho()/2;
			boolean tocaIzquierda = esqueleto.getX() - esqueleto.getAncho()/2 == isla.getX() - isla.getAncho()/2;
			if(tocaDerecha || tocaIzquierda){
				esqueleto.cambiarDireccion();
				esqueleto.mover();
			}else{
				esqueleto.mover();
			}
		}else{
			esqueleto.caer();
		}

		if(caballeroEstaTocandoAlgunaIsla(islas)==null){
			caballero.caer();
			if(entorno.estaPresionada(entorno.TECLA_ESPACIO)){
				caballero.saltar();
			}
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
		
		//SECCION DE COFRE Y MONEDAS
		
		if(faltaAlgunaMoneda(cofre)){
			añadirMonedas(cofre);
			dibujarMonedas(cofre);
		}
		
	}
	public void generarIslas(){
		islas[0] = new Isla(100, 500, 100, 30);
		islas[1] = new Isla(250, 500, 100, 30);
		islas[2] = new Isla(400, 500, 100, 30);
		islas[3] = new Isla(550, 500, 100, 30);
		islas[4] = new Isla(700, 500, 100, 30);

		islas[5] = new Isla(175, 400, 100, 30);
		islas[6] = new Isla(325, 400, 100, 30);
		islas[7] = new Isla(475, 400, 100, 30);
		islas[8] = new Isla(625, 400, 100, 30);

		islas[9] = new Isla(250, 300, 100, 30);
		islas[10] = new Isla(400, 300, 100, 30);
		islas[11] = new Isla(550, 300, 100, 30);


		islas[12] = new Isla(325, 200, 100, 30);
		islas[13] = new Isla(475, 200, 100, 30);

		islas[14] = new Isla(400, 100, 100, 30);
	}
	public void dibujarIslas(Isla[] isla){
		for (int i = 0; i < isla.length; i++) {
			islas[i].dibujarIsla(entorno);
		}
	}
	public Isla esqueletoEstaTocandoAlgunaIsla(Isla[] islas){
		for (int i = 0; i < islas.length; i++) {
			Isla isla = islas[i];
			if(esqueleto.tocaAbajo(isla)){
				return isla;
			}
		}
		return null;
	}
	public Isla caballeroEstaTocandoAlgunaIsla(Isla[] islas){
		for (int i = 0; i < islas.length; i++) {
			Isla isla = islas[i];
			if(caballero.tocaAbajo(isla)){
				return isla;
			}
		}
		return null;
	}
	public boolean faltaAlgunaMoneda(Cofre cofre){
		for (int i = 0; i < cofre.monedas.length; i++) {
			if(cofre.monedas[i] == null){
				return true;
			}
		}
		return false;
	}
	public void añadirMonedas(Cofre cofre){
		for (int i = 0; i < cofre.monedas.length; i++) {
			if(cofre.monedas[i] == null){
				cofre.monedas[i] = new Moneda(400, 50, 20,20,3);
			}
		}
	}
	public void dibujarMonedas(Cofre cofre){
		for (int i = 0; i < cofre.monedas.length; i++) {
			cofre.monedas[i].dibujarMoneda(entorno);
		}
	}
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
