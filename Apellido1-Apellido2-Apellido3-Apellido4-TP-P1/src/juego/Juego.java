package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.Random;
import java.awt.Image;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	public Random random = new Random();

	public boolean sePresionoSaltar = false;
	public Image imagenFondo; 
	Cofre cofre;
	public BolaDeFuego boladefuego;
	public Moneda[] monedas = new Moneda[3];
	public Isla[] islas = new Isla[15];
	public Caballero caballero;
	public Esqueleto esqueleto;
	public Esqueleto[] esqueletos = new Esqueleto[3];
	boolean confirmar = false;

	int yCaballero = 0;
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		imagenFondo = Herramientas.cargarImagen("images/fondo.gif");

		
		esqueletos[0] = new Esqueleto(70,100,20,30,1);
		esqueletos[1] = new Esqueleto(300,100,20,30,1);
		esqueletos[2] = new Esqueleto(680,100,20,30,1);

		monedas[0] = new Moneda(150, 100, 12, 12, 1);
		monedas[1] = new Moneda(300, 100, 12, 12, 1);
		monedas[2] = new Moneda(500, 100, 12, 12, 1);

		generarIslas();
		cofre = new Cofre(400,65,30,30);
		caballero = new Caballero(100, 50, 5,20,30);


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
		entorno.dibujarImagen(imagenFondo, 400, 300, 0, 0.8);
		dibujarMonedas(monedas);
		dibujarEsqueletos(esqueletos);
		dibujarIslas(islas);
		cofre.dibujarCofre(entorno);
		//esqueleto.dibujarEsqueleto(entorno);
		caballero.dibujarCaballero(entorno);


		//SECCION DE CABALLERO Y ESQUELETO

		boolean estaPresionadaDerecha = entorno.estaPresionada(entorno.TECLA_DERECHA);
		boolean estaPresionadaIzquierda = entorno.estaPresionada(entorno.TECLA_IZQUIERDA);
		

		//MOVIMIENTO MONEDA
		for (int i = 0; i < monedas.length; i++) {
			Moneda moneda = monedas[i];

			if(monedas[i] != null){
				if (monedaEstaTocandoAlgunaIsla(islas, moneda) != null) {
					moneda.mover();
				}else{
					moneda.caer();
				}
				if(moneda.y >= 600){
					monedas[i] = null;
				}
			}else{
				generarMonedas(monedas);
				dibujarMonedas(monedas);
			}

		}
		//MOVIMIENTO ESQUELETO
		for (int i = 0; i < esqueletos.length; i++) {
			Esqueleto esqueleto = esqueletos[i];
			if(esqueletos[i] != null){
				if(esqueletos[i].getY() >= 600){
					esqueletos[i] = null;
				}
				if(esqueletoEstaTocandoAlgunaIsla(islas, esqueleto) != null){
					Isla isla = esqueletoEstaTocandoAlgunaIsla(islas,esqueleto); //Guarda la isla que el esqueleto toco
		
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
			}else{
				generarEsqueleto(esqueletos);
				dibujarEsqueletos(esqueletos);
			}
	
		}


		//MOVIMIENTO CABALLERO
		if(caballeroEstaTocandoAlgunaIsla(islas)!=null){
			
			if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && sePresionoSaltar == false){
				caballero.saltar();
				sePresionoSaltar = true;
				
			}
			if(entorno.seLevanto(entorno.TECLA_ESPACIO)){
				sePresionoSaltar = false;
			}
		}else{
			caballero.caer();
			
		}

		if(estaPresionadaDerecha){
			caballero.moverDerecha();
		}
		if (estaPresionadaIzquierda) {
			caballero.moverIzquierda();
		}
		
		//SECCION BOLA DE FUEGO
		if(entorno.sePresiono(entorno.TECLA_CTRL) && confirmar == false){
			confirmar = true;
			boladefuego = new BolaDeFuego(caballero.getX(), caballero.getY(),10, 10, 10);
		}

		if(confirmar == true){
			if(boladefuego.x <= entorno.ancho()){
				boladefuego.dibujarBolaDeFuego(entorno);
				boladefuego.mover();
			}else{
				confirmar = false;
			}
			for (int i = 0; i < esqueletos.length; i++) {
				Esqueleto esqueleto = esqueletos[i];
				if(boladefuego.x == esqueleto.getX() && boladefuego.y == esqueleto.getY()){
					esqueletos[i] = null;
					confirmar = false;
				}
			}
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
	public Isla esqueletoEstaTocandoAlgunaIsla(Isla[] islas, Esqueleto esqueleto){
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
	public Isla monedaEstaTocandoAlgunaIsla(Isla[] islas,Moneda moneda){
		for (int i = 0; i < islas.length; i++) {
			Isla isla = islas[i];
			if (moneda.tocaAbajo(isla)) {
				return isla;
			}
		}
		return null;
	}
	public boolean faltaAlgunaMoneda(Moneda[] monedas){
		for (int i = 0; i < monedas.length; i++) {
			if(monedas[i] != null){
				return true;
			}
		}
		return false;
	}
	public void generarMonedas(Moneda[] monedas){
		for (int i = 0; i < monedas.length; i++) {
			if(monedas[i] == null){
				monedas[i] = new Moneda(cofre.x, cofre.y, 12, 12, 1);
			}
		}
	}
	public void generarEsqueleto(Esqueleto[] esqueletos){
		//int cordenadaX = 0;
		for (int i = 0; i < esqueletos.length; i++) {
			if(esqueletos[i] == null){
				int cordenadaX = (int) (random.nextDouble()*800);
				System.out.println(cordenadaX);
				esqueletos[i] = new Esqueleto(cordenadaX, cofre.y, 20, 30, 1);
			}
		}
	}
	public void dibujarEsqueletos(Esqueleto[] esqueletos){
		for (int i = 0; i < esqueletos.length; i++) {
			if(esqueletos[i] !=  null){
				esqueletos[i].dibujarEsqueleto(entorno);
			}
		}
	}
	public void dibujarMonedas(Moneda[] monedas){
		for (int i = 0; i < monedas.length; i++) {
			if(monedas[i] != null){
				monedas[i].dibujarMoneda(entorno);
			}
		}
	}
	
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
