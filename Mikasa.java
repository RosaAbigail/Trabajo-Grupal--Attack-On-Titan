package juego;

import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

public class Mikasa {	
	// Variables	
	private double x;
	private double y;
	private double orientacion;
	private double velocidad;
	private double radio = 40;
		
	// Constructores
	public Mikasa(double x, double y, double orientacion) {
		this.x = x;
		this.y = y;
		this.orientacion = orientacion;
	}
	
	// Metodos
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.RED);
	}
		
	public void caminar(Entorno e) {
		this.velocidad = 3;
	}
	
	public void correr(Entorno e) {
		this.velocidad = 6;
	}
	
	public void desplazamiento(double direccion) {
		if (direccion == -Math.PI/2) { // Arriba
			this.orientacion = -Math.PI/2;
			this.x += Math.cos(this.orientacion) * this.velocidad;
			this.y += Math.sin(this.orientacion) * this.velocidad;
		}
		if (direccion == Math.PI/2) { // Abajo
      this.orientacion = Math.PI/2;
      this.x += Math.cos(this.orientacion) * this.velocidad;
			this.y += Math.sin(this.orientacion) * this.velocidad;
    }
		if (direccion == Math.PI) { // Izquierda
      this.orientacion = Math.PI;
			this.x -= this.velocidad;
    }
    if (direccion == 0) { // Derecha
			this.orientacion = 0;
			this.x += this.velocidad;
		}
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
	
	public boolean colisionConEntorno(Entorno e) {
		return this.x - 40 < this.radio || this.x + 20 > e.ancho() - this.radio || this.y - 40 < this.radio || this.y + 20 > e.alto() - this.radio;		
	}

	public Proyectil lanzarProyectil(Entorno e) {
		Proyectil proyectil = new Proyectil(e, x, y, orientacion);
		return proyectil;
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
	
}
