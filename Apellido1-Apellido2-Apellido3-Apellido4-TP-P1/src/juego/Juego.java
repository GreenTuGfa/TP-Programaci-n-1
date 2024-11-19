package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.Random;
import java.awt.Color;
import java.awt.Image;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	public Random random = new Random();



	boolean estaCayendo = true;
	Image imagenFondo; 
	Cofre cofre;
	BolaDeFuego boladefuego;
	Moneda[] monedas = new Moneda[3];
	Isla[] islas = new Isla[15];
	Caballero caballero;
	Esqueleto esqueleto;
	Esqueleto[] esqueletos = new Esqueleto[3];
	boolean confirmar = false;
	int monedasObtenidasPorCaballero;
	int esqueletosEliminados;
	int monedasPerdidas;
	int maxAlturaAlcanzada = 0;
	Image gameover = Herramientas.cargarImagen("images/gameover.jpg");
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Al Rescate de los Gnomos", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		imagenFondo = Herramientas.cargarImagen("images/fondo.gif");

		
		esqueletos[0] = new Esqueleto(70,100,20,30,1);
		esqueletos[1] = new Esqueleto(300,100,20,30,1);
		esqueletos[2] = new Esqueleto(680,100,20,30,1);
		//monedaAncho 12, alto 12;
		monedas[0] = new Moneda(150, 100, 20, 20, 1);
		monedas[1] = new Moneda(300, 100, 20, 20, 1);
		monedas[2] = new Moneda(500, 100, 20, 20, 1);

		generarIslas();
		cofre = new Cofre(400,65,30,30);
		caballero = new Caballero(100, 50, 3,20,30);


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
		
		//El juego se mantendra funcionando mientras el caballero no sea elminiado
		if(caballero != null){
			entorno.dibujarImagen(imagenFondo, 400, 300, 0, 0.8);
			dibujarMonedas(monedas);
			dibujarEsqueletos(esqueletos);
			dibujarIslas(islas);
			cofre.dibujarCofre(entorno);
			//esqueleto.dibujarEsqueleto(entorno);
			caballero.dibujarCaballero(entorno);

			entorno.cambiarFont("Impact", 18, Color.WHITE);
			entorno.escribirTexto("Monedas recogidas: " + monedasObtenidasPorCaballero, 50, 50);
			
			entorno.cambiarFont("Impact", 18, Color.WHITE);
			entorno.escribirTexto("Monedas perdidas: " + monedasPerdidas, 50, 75);

			entorno.cambiarFont("Impact", 18, Color.WHITE);
			entorno.escribirTexto("Esqueletos eliminados: " + esqueletosEliminados , 50, 100);

			entorno.cambiarFont("Impact", 18, Color.WHITE);
			entorno.escribirTexto("Tiempo transcurrido: " + entorno.tiempo()/1000, 550, 100);


			
			//SECCION DE CABALLERO Y ESQUELETO
			
			boolean estaPresionadaDerecha = entorno.estaPresionada(entorno.TECLA_DERECHA);
			boolean estaPresionadaIzquierda = entorno.estaPresionada(entorno.TECLA_IZQUIERDA);
			

			//MOVIMIENTO MONEDA (Gnomo)
			for (int i = 0; i < monedas.length; i++) {
				
				Moneda moneda = monedas[i];

				
				if(monedas[i] != null){
					
					//Si se esta tocando alguna isla..
					if (monedaEstaTocandoAlgunaIsla(islas, moneda) != null) {
						
						//La direccion a la que se mueve la moneda, se indica mediante numeros. (1 == izquierda) (0 == derecha)
						if(moneda.getDireccion() == 1){
							moneda.moverIzquierda();
						}else{
							moneda.moverDerecha();
						}
					/*
					 * La direccion a la que se mueve la moneda es aleatoria, y se decide mientras la misma cae.
					 * 	Esto es devido a que si la moneda decidiese la direccion a la que se deve mover mientras esta
					 * 		tocando alguna isla, la misma estaria cambiando todo le tiempo.
					 */
					}else{
						
						moneda.direccionAleatoria();
						moneda.caer();
					}
					//Si se supera el margen de la pantalla se elimina la moneda
					if(moneda.getY() >= 600){
						this.monedasPerdidas ++;
						monedas[i] = null;
					}
					/*
					 * Las monedas podran ser recogidas por el caballero, unicamente si se encuentran por la mitad inferior del entorno.
					 * 	Es decir, se podran recoger unicamente en las islas inferiores. Se evito utilizar metodos que comprueben si 
					 * 	la moneda o el caballero se encuentran tocando las islas inferiores, debido a que de hacerlo, no se podrian 
					 * 	recoger las monedas que estan cayendo en el aire.
					 */
					if(caballero.tocaMoneda(moneda) && moneda.getY() > 300){ 
						monedas[i] = null;
						monedasObtenidasPorCaballero++;
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
			
						//Comprueban si el esqueleto llego al borde derecho o izquierdo de la isla
						boolean tocaDerecha = esqueleto.getX() + esqueleto.getAncho()/2 == isla.getX() + isla.getAncho()/2;
						boolean tocaIzquierda = esqueleto.getX() - esqueleto.getAncho()/2 == isla.getX() - isla.getAncho()/2;


						if(tocaDerecha || tocaIzquierda){
							esqueleto.cambiarDireccion();
							esqueleto.mover(); //Es importante para que el esqueleto reactive su movimiento al llegar a un borde, y no se quede quieto en el mismo.
						}else{
							esqueleto.mover();
						}
					}else{
						esqueleto.caer();
					}
					//Comprueba si el esqueleto toca alguna moneda
					for (int j = 0; j < monedas.length; j++) {

						Moneda moneda = monedas[i];

						if(esqueleto.tocaMoneda(moneda)){

							this.monedasPerdidas ++;
							monedas[i] = null;
				
						}
					}
					//Comprueba si el esqueleto toca al caballero
					if(esqueleto.tocaCaballero(caballero)){
						caballero = null;
					}
				}else{
					generarEsqueleto(esqueletos);
					dibujarEsqueletos(esqueletos);
				}
		
			}


			//MOVIMIENTO CABALLERO
			
			if(caballeroEstaTocandoAlgunaIsla(islas)==null && !entorno.estaPresionada(entorno.TECLA_ESPACIO)){
				caballero.caer();
				estaCayendo = true;
			}
			if(caballeroEstaTocandoAlgunaIsla(islas)!= null){
				maxAlturaAlcanzada = 0;
				estaCayendo = false;
			}
			if(maxAlturaAlcanzada > 15){
				caballero.caer();
				estaCayendo = true;
			}
			if(estaCayendo == false){
				if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && maxAlturaAlcanzada <= 15){
					caballero.saltar();
					maxAlturaAlcanzada++;
					
				}
			}

			if(caballero.getY() > 600){
				caballero = null;
			}
			if(estaPresionadaDerecha){
				caballero.moverDerecha();

			}
			if (estaPresionadaIzquierda) {
				caballero.moverIzquierda();
			}
			
			//SECCION BOLA DE FUEGO

			if(entorno.sePresiono(entorno.TECLA_CTRL) && confirmar == false){

				//Controla cuando se crea la bola de fuego, para que no cree nuevas bolas de fuego mientras hay una en pantalla.
				confirmar = true;
				boladefuego = new BolaDeFuego(caballero.getX(), caballero.getY(),10, 10, 10);
				if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.sePresiono(entorno.TECLA_IZQUIERDA)){
					caballero.setDireccion(1);
				}
				if(entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.sePresiono(entorno.TECLA_DERECHA)){
					caballero.setDireccion(2);
				}

			}

			if(confirmar == true){

				if(caballero.getDireccion() == 1){
					if(boladefuego.x > 0){
						boladefuego.dibujarBolaDeFuego(entorno);
						boladefuego.moverIzquierda();
	
					}else{
						confirmar = false; //Cuando la bola de fuego supera el margen del entorno, se nos permite volver a generar otra bola de fuego
					}
				}else{
					if(boladefuego.x <= entorno.ancho()){
						boladefuego.dibujarBolaDeFuego(entorno);
						boladefuego.moverDerecha();
	
					}else{
						confirmar = false; //Cuando la bola de fuego supera el margen del entorno, se nos permite volver a generar otra bola de fuego
					}
				}
	
				//Seccion dedicada a comprobar si la bola de fuego impacta con algun esqueleto
				for (int i = 0; i < esqueletos.length; i++) {

					Esqueleto esqueleto = esqueletos[i];

					if(boladefuego.tocaEsqueleto(esqueleto)){

						this.esqueletosEliminados ++;
						esqueletos[i] = null;
						confirmar = false; //Al eliminar un esuqueleto, se nos permite volver a generar otra bola de fuego
					}
				}
			}
		}else{
			//Se activa la pantalla de game over
			entorno.dibujarImagen(gameover, 400, 300, 0, 1);
		}
		

		
		
	}
	public void generarIslas(){

		//Islas ordenadas de islas inferiores a islas superiores
		
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

	//Hecho con chatGPT
	public static int generarNumeroAleatorio(Random random) {
        // Generamos un número aleatorio en el rango [0, 700] (350 números en 0-349 y 351 en 450-800)
        int indice = random.nextInt(701); // 701 porque queremos un número entre 0 y 700 inclusive

        // Mapeamos el índice al rango adecuado
        if (indice < 350) {
            return indice; // Corresponde al rango 0-349
        } else {
            return 450 + (indice - 350); // Corresponde al rango 450-800
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
	public Isla caballeroChocaConAlgunaIsla(Isla[] islas){
		for (int i = 0; i < islas.length; i++) {
			Isla isla = islas[i];
			if(caballero.tocaArriba(isla)){
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
				monedas[i] = new Moneda(cofre.x, cofre.y, 20, 20, 1);
			}
		}
	}
	public void generarEsqueleto(Esqueleto[] esqueletos){
		//int cordenadaX = 0;
		for (int i = 0; i < esqueletos.length; i++) {
			if(esqueletos[i] == null){
				int cordenadaX = generarNumeroAleatorio(random);
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
