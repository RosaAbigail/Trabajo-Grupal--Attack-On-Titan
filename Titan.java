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
	private double radio = 80;
	
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
  
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public double getRadio() {
		return this.radio;
	}
	
}
