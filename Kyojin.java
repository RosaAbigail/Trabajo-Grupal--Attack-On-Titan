package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.*;

public class Kyojin {
	// Variables
	private double x;
	private double y;
	private double orientacion;
	private double velocidad;
	private double radio;
	Image k_d;
	Image k_i;
//	Image j1_d;
//	Image j1_i;
//	Image j2_d;
//	Image j2_i;
	
	// Constructores
	public Kyojin(double x, double y, double orientacion) {
		this.x = x;
		this.y = y;
		this.orientacion = orientacion;
		this.velocidad = 1;
		this.radio = 115;
		this.k_d = Herramientas.cargarImagen("k_d.png");
		this.k_i = Herramientas.cargarImagen("k_i.png");
//		this.j1_d = Herramientas.cargarImagen("j1_d.png");
//		this.j1_i = Herramientas.cargarImagen("j1_i.png");
//		this.j2_d = Herramientas.cargarImagen("j2_d.png");
//		this.j2_i = Herramientas.cargarImagen("j2_i.png");
	}
	
	// Metodos
	public void dibujar(Entorno e) { // Modificar 
		if (this.orientacion > -Math.PI/2 || this.orientacion < Math.PI/2) {
			e.dibujarImagen(k_d, this.x, this.y, 0);
		}
		if (this.orientacion > Math.PI/2 || this.orientacion < -Math.PI/2) {
			e.dibujarImagen(k_i, this.x, this.y, 0);
		}
	}	
	public static void crearKyojines(Entorno e, Kyojin[] k, Mikasa m) { // Modificar 
		double x = 0;
		double y = 0;
		for (int i = 0; i < k.length; i++) {
			if (i == 0) {
				x = 800;
				y = 600;
				k[i] = new Kyojin(x, y, m.getOrientacion());
			}
			if (i == 1) {
				x = 50;
				y = 550;
				k[i] = new Kyojin(x, y, m.getOrientacion());
			}
			if (i == 2) {
				x = 700;
				y = 100;
				k[i] = new Kyojin(x, y, m.getOrientacion());
			}
			if (i == 3) {
				x = 650;
				y = 500;
				k[i] = new Kyojin(x, y, m.getOrientacion());
			}
			if (i == 4) {
				x = 350;
				y = 50;
				k[i] = new Kyojin(x, y, m.getOrientacion());
			}
			if (i == 5) {
				x = 350;
				y = 450;
				k[i] = new Kyojin(x, y, m.getOrientacion());
			}
		}
	}
	public void avanzar(Entorno e, Mikasa m) {
		double dx = m.getX() - this.x; // Distancia entre el x de Mikasa y de los kyojines
		double dy = m.getY() - this.y; // Distancia entre el y de Mikasa y de los kyojines
		double h = Math.sqrt((dx * dx) + (dy * dy)); // Normalizacion de los vectores directores
		this.x += (dx / h) * this.velocidad; // Cambiar posicion de x
		this.y += (dy / h) * this.velocidad; // Cambiar posicion de y
	}	
	public void noAvanzar(Entorno e, Obstaculo o) { // Encontrar la lógica o modificar la colision con el entorno
		if (this.x <= this.radio/2 || this.y <= this.radio/2 || this.x >= e.ancho() - this.radio/2 || this.y >= e.alto() - this.radio/2) { 	// Colision con entorno
			this.orientacion += Math.PI/2;
        }
		if ((this.x - o.getX()) * (this.x - o.getX()) + (this.y - o.getY()) * (this.y - o.getY()) <= this.radio * o.getRadio()) { // Colision con obstaculos
			this.orientacion += Math.PI/2;
		}
	}	
	public boolean colisionEntreKyojines(Entorno e, Kyojin k) {
		return (this.x - k.getX()) * (this.x - k.getX()) + (this.y - k.getY()) * (this.y - k.getY()) <= this.radio * k.getRadio();
	}
	public boolean colisionConObstaculos(Entorno e, Obstaculo o) {
		return (this.x - o.getX()) * (this.x - o.getX()) + (this.y - o.getY()) * (this.y - o.getY()) <= this.radio * o.getRadio();
	}
	public boolean colisionConEntorno(Entorno e) {
		return this.x <= this.radio || this.y <= this.radio || this.x >= e.ancho() - this.radio || this.y >= e.alto() - this.radio;
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
