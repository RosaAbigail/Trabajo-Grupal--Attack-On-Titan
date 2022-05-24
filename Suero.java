package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.*;
import java.util.Random;

public class Suero {
	// Variables
	private double x;
	private double y;
	private double radio;
	
	// Constructores
	public Suero(double x, double y) {
		this.x = x;
		this.y = y;
		this.radio = 40;
	}
	
	// Metodos
	public void dibujar(Entorno e) { // Modificar
//	    Random r = new Random();
//	    double maxX = e.ancho()-50;
//	    double minX = e.ancho()/100;
//	    double maxY = e.alto()-50;
//	    double minY = e.alto()/100;
//	    this.x = minX + (maxX - minX) * r.nextDouble();
//	    this.y = minY + (maxY - minY) * r.nextDouble();
		e.dibujarCirculo(this.x, this.y, this.radio, Color.GREEN);
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
