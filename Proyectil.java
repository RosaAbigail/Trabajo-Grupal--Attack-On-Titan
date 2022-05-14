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
    this.radio = 20;
	}

	// Metodos
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.CYAN);
	}

	public void desplazamiento() {
    this.x += Math.cos(this.orientacion) * this.velocidad;
    this.y += Math.sin(this.orientacion) * this.velocidad;
	}
		
	public boolean colisionConEntorno(Entorno e) {
		return this.x < this.radio/2 || this.x > e.ancho() - this.radio/2 || this.y < this.radio/2 || this.y > e.alto() - this.radio/2;
	}
	
	public boolean colisionConObstaculo (Obstaculo o, Proyectil p) {
		if (Math.sqrt ((x-o.getX()) * (x-o.getX()) + (y-o.getY()) *(y-o.getY())) < radio + o.getRadio()) {
			return true;
    }
		return false;
	}
	
	public boolean colisionConTitan(Titan t, Proyectil p) {
		if (Math.sqrt ((x-t.getX()) * (x-t.getX()) + (y-t.getY()) *(y-t.getY())) < radio + t.getRadio()) {
			return true;
		}
		return false;
	}
	
}
