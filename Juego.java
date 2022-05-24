package juego;

import java.awt.*;
import java.time.*;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// Variables
	private Entorno entorno;
    private Proyectil p;
	private Mikasa m;
	private Suero s;
    private Kyojin[] k;
    private Obstaculo[] o;
    private int proyectilesRestantes, kyojinesAsesinados, puntaje, vidas;
    private boolean tomarSuero, finDelJuego;
	Image fondo_juego, radar;

	public Juego() {
		this.entorno = new Entorno(this, "Attack on Titan: Wings of Freedom", 1280, 700);
		m = new Mikasa(entorno.ancho()/2, entorno.alto()/2);
		k = new Kyojin[6];
		o = new Obstaculo[6];
		proyectilesRestantes = 100;
		kyojinesAsesinados = 0;
        puntaje = 0;
        vidas = 3;
        tomarSuero = false;
		finDelJuego = false;
		fondo_juego = Herramientas.cargarImagen("fondo_juego.jpg");
		radar = Herramientas.cargarImagen("radar.png");
	    Obstaculo.crearObstaculos(entorno, o);
//	    Kyojin.crearKyojines(entorno, k, m);
		this.entorno.iniciar();
	}

	public void tick() {
		// Dibujar obstaculos y titanes
		entorno.dibujarImagen(fondo_juego, entorno.ancho()/2, entorno.alto()/2, 0);

		for (int i = 0; i < o.length; i++) {
			if (o[i] != null) {
				entorno.dibujarCirculo(o[i].getX(), o[i].getY(), o[i].getRadio(), Color.BLACK);
				o[i].dibujar(entorno);
			}
		}
		
//		for (int i = 0; i < k.length; i++) {
//			if (k[i] != null) {
//				entorno.dibujarCirculo(k[i].getX(), k[i].getY(), k[i].getRadio(), Color.BLACK);
//				k[i].dibujar(entorno);
//				k[i].avanzar(entorno, m);
//			}
//			if (k[i] == null) {
//				k[i] = new Kyojin(entorno.ancho()/2, 0, m.getOrientacion());
//			}
//		}
		
		// Dibujar + Control de moviento de Mikasa
		entorno.dibujarCirculo(m.getX(), m.getY(), m.getRadio(), Color.BLACK);
		m.dibujar(entorno);
		entorno.dibujarTriangulo(m.getX(), m.getY(), 15, 15, m.getOrientacion(), Color.BLUE);
		entorno.dibujarImagen(radar, m.getX(), m.getY(), m.getOrientacion());
		
		if (entorno.estaPresionada('w')) {
			m.avanzar(entorno);
		}
		if (entorno.estaPresionada('a')) {
			m.girar((-1/360.0)*(2*Math.PI));
		}
		if (entorno.estaPresionada('d')) {
			m.girar((1/360.0)*(2*Math.PI));
		}
		
		for (int i = 0; i < o.length; i++) {
			if (m.colisionConEntorno(entorno) || m.colisionConObstaculo(entorno, o[i])) {
				m.noAvanzar(entorno, o[i]);
			}
		}
		
		// Suero
//		if (tomarSuero == false) {
//			s = new Suero(700, 100);
//			s.dibujar(entorno);
//			if (m.colisionConSuero(entorno, s)) {
//				entorno.dibujarCirculo(m.getX(), m.getY(), m.getRadio(), Color.BLACK);
//				m.dibujar(entorno);
//				tomarSuero = true;
//				s = null;
//			}
//		}
		
		// Proyectiles
		if (p == null && proyectilesRestantes > 0 && tomarSuero == false) { // Crear proyectil
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				p = new Proyectil(entorno, m.getX(), m.getY(), m.getOrientacion());
				proyectilesRestantes -= 1;
			}
		}
		
		if (p != null) { // Dibujar y mover proyectiles + Interaccion
			p.dibujar(entorno);
			p.desplazamiento();
			for (int i = 0; i < k.length; i++) { // Colision con titanes
				if (p != null && k[i] != null && p.colisionConKyojin(k[i])) {
					k[i] = null;
					p = null;
					kyojinesAsesinados += 1;
					puntaje += 15;
				}
			}
			for (int i = 0; i < o.length; i++) { // Colision con obstaculos
				if (p != null && o[i] != null && (p.colisionConObstaculo(o[i]) || p.colisionConEntorno(entorno))) {
					p = null;
				}
			}
		}
				
		// Texto en pantalla
		entorno.cambiarFont("sans", entorno.alto()/40, Color.RED); // Modificar coodenadas
	    entorno.escribirTexto("Proyectiles restantes: " + proyectilesRestantes, entorno.ancho()/80, entorno.alto()/50);
	    entorno.escribirTexto("Titanes eliminados: " + kyojinesAsesinados, entorno.ancho()/80, entorno.alto()/30);
	    entorno.escribirTexto("Puntaje: " + puntaje, entorno.ancho()/80, entorno.alto()/20);
	    entorno.escribirTexto("Vidas: " + vidas, entorno.ancho()/80, entorno.alto()/16);

	    // Pantalla de fin del juego
	    // ...	    
	}
	
	// Inicia el juego
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
