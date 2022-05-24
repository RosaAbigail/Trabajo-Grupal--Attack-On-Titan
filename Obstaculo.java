package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.*;
import java.util.Random;

public class Obstaculo {
	// Variables
	private double x;
	private double y;
	private double radio;
	Image o0;
	Image o1;
	Image o2;
	Image o3;
	Image o4;
	Image o5;
	
	// Constructores
	public Obstaculo (double x, double y) {
		this.x = x;
		this.y = y;
		this.radio = 100;
		this.o0 = Herramientas.cargarImagen("o0.png"); 
		this.o1 = Herramientas.cargarImagen("o1.png");
		this.o2 = Herramientas.cargarImagen("o2.png");
		this.o3 = Herramientas.cargarImagen("o3.png");
		this.o4 = Herramientas.cargarImagen("o4.png");
		this.o5 = Herramientas.cargarImagen("o5.png"); 
	}
	
	// Metodos
	public void dibujar(Entorno e) { // Modificar
//		Image[] edificios = new Image[]{this.o1, this.o2, this.o3, this.o4, this.o5, this.o6};
//		int i = (int)(Math.random() * (edificios.length - 1));
//		e.dibujarImagen(edificios[i], this.x, this.y, this.radio);
		e.dibujarImagen(this.o0, this.x, this.y, 0);
	}
	public static void crearObstaculos(Entorno e, Obstaculo[] o) { // Modificar 
		double x = 0;
		double y = 0;
		for (int i = 0; i < o.length; i++) {
			if (i == 0) {
				x = 600;
				y = 600;
				o[i] = new Obstaculo(x, y);
			}
			if (i == 1) {
				x = 200;
				y = 500;
				o[i] = new Obstaculo(x, y);
			}
			if (i == 2) {
				x = 300;
				y = 250;
				o[i] = new Obstaculo(x, y);
			}
			if (i == 3) {
				x = 800;
				y = 100;
				o[i] = new Obstaculo(x, y);
			}
			if (i == 4) {
				x = 1000;
				y = 400;
				o[i] = new Obstaculo(x, y);
			}
			if (i == 5) {
				x = 1100;
				y = 550;
				o[i] = new Obstaculo(x, y);
			}
		}
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
