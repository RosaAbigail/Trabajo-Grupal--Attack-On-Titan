package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.*;

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
        this.radio = 25;
	}
	
	// Metodos
	public void dibujar(Entorno e) {
		e.dibujarCirculo(this.x, this.y, this.radio, Color.CYAN);
	}
	public void desplazamiento() {
        this.x += Math.cos(this.orientacion) * this.velocidad;
        this.y += Math.sin(this.orientacion) * this.velocidad;
	}			
	public boolean colisionConKyojin(Kyojin k) {
		return (this.x - k.getX()) * (this.x - k.getX()) + (this.y - k.getY()) * (this.y - k.getY()) <= this.radio * k.getRadio();
	}
	public boolean colisionConObstaculo (Obstaculo o) {
		return (this.x - o.getX()) * (this.x - o.getX()) + (this.y - o.getY()) * (this.y - o.getY()) <= this.radio * o.getRadio();
	}
	public boolean colisionConEntorno(Entorno e) {
		return this.x <= this.radio || this.y <= this.radio || this.x >= e.ancho() - this.radio || this.y >= e.alto() - this.radio;
	}
}
