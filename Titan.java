package juego;

import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

public class Titan {
	// Variables
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double h;
	private double orientacion;
	private double velocidad;
	private double radio = 60;
	
	// Constructores
	public Titan(double x, double y, double orientacion) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.h = h;
		this.orientacion = orientacion;
		this.velocidad = 0.5;
	}
	
	// Metodos
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.WHITE);
	}
		
	public void desplazamiento(Entorno e, Mikasa m) {
		// Vectores directores
		this.dx = m.getX() - this.x;
		this.dy = m.getY() - this.y;
		// Normalizacion
		this.h = Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
		this.dx = this.dx/this.h;
		this.dy = this.dy/this.h;
		// Cambiar posicion
		this.x += this.dx * this.velocidad;
		this.y += this.dy * this.velocidad;
	}
  
	public void noAvanzar(Entorno e) {
		if (this.x < this.radio/2) {
			this.x += this.velocidad;
        	}
        	if (this.y < this.radio/2) {
        		this.y += this.velocidad;
        	}
        	if (this.x > e.ancho() - this.radio/2) {
        		this.x -= this.velocidad;
       		}
        	if (this.y > e.alto() - this.radio/2) {
        		this.y -= this.velocidad;
        	}
	}
	
	public boolean colisionEntreTitanes(Entorno e, Titan t) {
		if (t == null) {
            		return false;
        	}
		return this.x > t.getX() - this.radio/2 && this.x < t.getX() + this.radio/2 && this.y > t.getY() - this.radio/2 && this.y < t.getY() + this.radio/2;
	}
	
//	public boolean colisionConObstaculos(Entorno e, Obstaculo o) {
//		
//	}
	
	public boolean colisionConEntorno(Entorno e) {
		return this.x - 40 < this.radio || this.x + 20 > e.ancho() - this.radio || this.y - 40 < this.radio || this.y + 20 > e.alto() - this.radio;		
	}

	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public double getOrientacion() {
		return this.orientacion;
	}
	
	public double getRadio() {
		return this.radio;
	}
	
}
