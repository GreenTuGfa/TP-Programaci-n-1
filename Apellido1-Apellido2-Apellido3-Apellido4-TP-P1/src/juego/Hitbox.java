/**
* Estoy viendo todabia si dejar o no esta clase. Basicamente la idea de la clase es que a partir de la posicion, alto y ancho de la imagen
* se cree una hitbox invisible, que no es mas que un rectangulo dibujado sin contorno ni color.
* Realmente no es completamente necesario esta clase, ya que se puede usar el mismo ancho y alto de la imagen de la isla como limite de colicion.
* 
*/

package juego;

import java.awt.Color;

import entorno.Entorno;

public class Hitbox {
    
    int x,y;
    int ancho,alto;

    public Hitbox(int x, int y, int ancho, int alto){
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujarHitbox(Entorno entorno){
        entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.CYAN);
    }

    //Metodos Setter y Getter
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getAncho(){
        return this.ancho;
    }
    public int getAlto(){
        return this.alto;
    }

}
