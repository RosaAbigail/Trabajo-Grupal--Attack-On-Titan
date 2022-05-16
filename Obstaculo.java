package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.*;

public class Obstaculo {
	// Variables
	private double x;
	private double y;
	private double radio = 70;
	
	// Constructores
	public Obstaculo (double x, double y) {
		this.x = x;
		this.y = y;
		this.radio = radio;
	}
	
	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, radio, Color.YELLOW);
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
