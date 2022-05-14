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
  private boolean finDelJuego;
  
  public Juego() { // Principal
    // Inicializar el entorno
    this.entorno = new Entorno(this, "Attack on Titan: Wings of Freedom", 1360, 670);
    mikasa = new Mikasa(entorno.ancho()/2, entorno.alto()/2, -Math.PI/2);
    titanes = new Titan[6];
    
    // Contadores mostrados en pantalla
    cantidadDeProyectiles = 60;
    titanesAsesinados = 0;
    puntaje = 0;
    vidas = 3;
    finDelJuego = false;
    
    // Creacion de los titanes
    Random r = new Random();
    double x = (entorno.ancho()/30) + (((entorno.ancho()-60) - (entorno.ancho()/30)) * r.nextDouble());
    double y = (entorno.alto()/15) + (((entorno.alto()-70) - (entorno.ancho()/15)) * r.nextDouble());
    int i = 0;
    double orientacion = Math.PI/2;
    
    for (int j = 0; j < titanes.length; j++) {
      titanes[i++] = new Titan(x, y, orientacion);
      x = (entorno.ancho()/30) + (((entorno.ancho()-60) - (entorno.ancho()/30)) * r.nextDouble());
      y = (entorno.alto()/15) + (((entorno.alto()-70) - (entorno.ancho()/15)) * r.nextDouble());
      orientacion = orientacion * (-1);
      
    // Creacion de los obstaculos
    
    
    // Inicia el juego
    this.entorno.iniciar();
  }
  
  public void tick() { // Control de pantalla
    // Dibujar en pantalla
    for (Titan t: titanes) {
      if (t != null) {
        t.dibujar(entorno);
        t.desplazamiento(entorno, mikasa);
      } 
    }
    
    mikasa.dibujar(entorno);
    
    // Control de movimiento
    if (mikasa.colisionConEntorno(entorno)) { // Agregar colisionConObstaculo
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
      if (proyectil != null && proyectil.colisionConEntorno(entorno)) { // Agregar colision con obstaculo
        proyectil = null;
      }
    }
    
    // Texto en pantalla
    
      
    // Pantalla de fin del juego
    
      
  }
    
  // Inicar el juego
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Juego juego = new Juego();
  }
