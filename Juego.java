package  juego;

import java.util.Random;
import java.awt.*;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends Interface {
  // Variables
  private Entorno entorno;
  private Mikasa mikasa;
  private Proyectil proyectil;
  private Suero suero;
  private Titan[] titanes;
  private Obstaculo[] obstaculos;
  private int cantidadDeProyectiles;
  private int titanesAsesinados;
  private int puntaje;
  private int vidas;
  private boolean tomarSuero;
  private boolean finDelJuego;
  
  public Juego() { // Principal
    // Inicializar el entorno
    this.entorno = new Entorno(this, "Attack on Titan: Wings of Freedom", 800, 600);
    mikasa = new Mikasa(entorno.ancho()/2, entorno.alto()/2, -Math.PI/2);
    titanes = new Titan[6];
    obstaculos = new Obstaculo[6];
    
    // Contadores mostrados en pantalla
    cantidadDeProyectiles = 60;
    titanesAsesinados = 0;
    puntaje = 0;
    vidas = 3;
    
    // Otros
    tomarSuero = false;
    finDelJuego = false;
    
    // Creacion de los obstaculos y los titanes
    Random r = new Random();
    double maxX = entorno.ancho()-50;
    double minX = entorno.ancho()/100;
    double maxY = entorno.alto()-50;
    double minY = entorno.alto()/100;
    double x = minX + (maxX - minX) * r.nextDouble();
    double y = minY + (maxY - minY) * r.nextDouble();
    double orientacion = Math.PI/2;
    int i = 0;
    
    for (int j = 0; j < obstaculos.length; j++) {
      obstaculos[i++] = new Obstaculos(x, y);
      x = minX + (maxX - minX) * r.nextDouble();
      y = minY + (maxY - minY) * r.nextDouble();
    }
    
    for (int j = 0; j < titanes.length; j++) {
      titanes[i++] = new Titan(x, y, orientacion);
      x = minX + (maxX - minX) * r.nextDouble();
      y = minY + (maxY - minY) * r.nextDouble();
      orientacion = orientacion * (-1);
    }
    
    // Inicia el juego
    this.entorno.iniciar();
  }
  
  public void tick() { // Control de pantalla
    // Dibujar en pantalla
    for (int i = 0; i < obstaculos.length; i++) {
			if (obstaculos[i] != null) {
				obstaculos[i].dibujar(entorno);
			}
		}
    
    for (int i = 0; i < titanes.length; i++) {
			if (titanes[i] != null) {
				titanes[i].dibujar(entorno);
				titanes[i].desplazamiento(entorno, mikasa);
				if (mikasa.colisionConTitan(entorno, titanes[i])) {
					finDelJuego = true;
				}
      }
    }
    
    mikasa.dibujar(entorno);
    
    // Control de movimiento
    for (int i = 0; i < obstaculos.length; i++) {
			if (mikasa.colisionConObstaculo(entorno, obstaculos[i])) {
        mikasa.noColision(obstaculos[i]);
			}
		}

    if (mikasa.colisionConEntorno(entorno)) {
      mikasa.noAvanzar(entorno);
    }
    if (entorno.estaPresionada('w')) {
      mikasa.desplazamiento(-Math.PI/2);
    }
    if (entorno.estaPresionada('s')) {
      mikasa.desplazamiento(Math.PI/2);
    }
    if (entorno.estaPresionada('a')) {
      mikasa.desplazamiento(0);
    }
    if (entorno.estaPresionada(entorno.TECLA_SHIFT)) {
      mikasa.correr(entorno);
    }
    else {
      mikasa.caminar(entorno);
    }
    
    // Proyectiles
    if (proyectil == null && cantidadDeProyectiles > 0) { // Creacion de proyectil
      if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
        proyectil = mikasa.lanzarProyectil(entorno);
        cantidadDeProyectiles -= 1;
    }
    
    if (proyectil != null) { // Dibujar y mover proyectiles
      proyectil.dibujar(entorno);
      proyectil.desplazamiento();
      
      // Colision con titanes
      for (int i = 0; i < titanes.length; i++) {
        if (proyectil != null && titanes[i] != null && proyectil.colisionConTitan(titanes[i], proyectil)) {
          titanes[i] = null;
          proyectil = null;
          titanesAsesinados++;
          puntaje += 15;
        }
      }
      
      // Colision con obstaculo o entorno
      for (int i = 0; i < obstaculos.length; i++) {
				if (proyectil != null && obstaculos[i] != null & proyectil.colisionConObstaculo(obstaculos[i])) {
					proyectil = null;
				}
			}
      
      if (proyectil != null && proyectil.colisionConEntorno(entorno)) { // Agregar colision con obstaculo
        proyectil = null;
      }
    }
    
    // Texto en pantalla
    entorno.cambiarFont("sans", entorno.alto()/40, Color.RED); // Modificar coodenadas
	  entorno.escribirTexto("Proyectiles restantes: " + cantidadDeProyectiles, entorno.ancho()/80, entorno.alto()/50);
	  entorno.escribirTexto("Titanes eliminados: " + titanesAsesinados, entorno.ancho()/80, entorno.alto()/30);
	  entorno.escribirTexto("Puntaje: " + puntaje, entorno.ancho()/80, entorno.alto()/20);
	  entorno.escribirTexto("Vidas: " + vidas, entorno.ancho()/80, entorno.alto()/16);
      
    // Pantalla de fin del juego
    if (finDelJuego == true) {
      entorno.escribirTexto("Puntaje final: " + puntaje, entorno.ancho()/2, entorno.alto()/2);
    }
  }
    
  // Inicar el juego
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Juego juego = new Juego();
  }
