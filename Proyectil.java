package juego;

import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {

	// Variables
	private double x;
	private double y;
	private double orientacion;
	private double velocidad;
	private double radio;
	
	// Constructores
	public Proyectil(Entorno e, double x, double y, double orientacion) {
		this.x = x;
	    	this.y = y;
	    	this.orientacion = orientacion;
	    	this.velocidad = 5;
	    	this.radio = 15;
	}

	// Metodos
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.CYAN);
	}

	public void desplazamiento() {
    		this.x += Math.cos(this.orientacion) * this.velocidad;
    		this.y += Math.sin(this.orientacion) * this.velocidad;
	}
	
	public boolean colisionConTitan(Titan t) {
//		double distancia = Math.sqrt ((this.x-t.getX())*(this.x-t.getX()) + (this.y-t.getY())*(this.y-t.getY()));
//		if (distancia < this.radio + t.getRadio()) {
//			return true;
//		}
//		return false;
		if (this.x > t.getX() - this.radio/2 && this.x < t.getX() + this.radio/2 && this.y > t.getY() - this.radio/2 && this.y < t.getY() + this.radio/2) {
			return true;
		}
		return false;
	}
	
	public boolean colisionConObstaculo (Obstaculo o) {
		double distancia = Math.sqrt ((this.x-o.getX())*(this.x-o.getX()) + (this.y-o.getY())*(this.y-o.getY()));
		if (distancia < this.radio + o.getRadio()) {
			return true;
		}
		return false;
	}
	
	public boolean colisionConEntorno(Entorno e) {
		return this.x < this.radio/2 || this.x > e.ancho() - this.radio/2 || this.y < this.radio/2 || this.y > e.alto() - this.radio/2;
	}

}
