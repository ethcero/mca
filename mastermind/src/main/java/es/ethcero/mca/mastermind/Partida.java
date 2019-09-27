/**
 * Copyright (C) 2019 Deveryware S.A. All Rights Reserved.
 */
package es.ethcero.mca.mastermind;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class Partida {

    private static final int MAX_JUGADAS = 10;

    private boolean finJuego = false;
    private List<Color> combinacion;
    private CombinacionBuilder combinacionBuilder;
    private Consola consola;

    public Partida(Consola consola, CombinacionBuilder builder) {
        this.combinacionBuilder = builder;
        this.consola = consola;
    }

    void iniciarJuego() {
        do {
            this.consola.imprimirSalida("--- MASTERMIND ---");
            this.combinacion = this.combinacionBuilder.aleatoria();
            nuevaPartida();
            reiniciarOSalir();
        } while (!finJuego);
    }

    private void nuevaPartida() {
        boolean finPartida = false;
        List<Jugada> jugadas = new ArrayList<Jugada>();
        while (!finPartida) {
            Jugada jugada = nuevaJugada();
            jugadas.add(jugada);
            this.consola.imprimirSalida(String.format("%d intentos: \n ***", jugadas.size()));
            printJugadas(jugadas);
            if (jugada.esGanadora()) {
                this.consola.imprimirSalida("YOU WIN!!!");
                finPartida = true;
            } else if (jugadas.size() > MAX_JUGADAS) {
                this.consola.imprimirSalida("YOU LOST!!!");
                finPartida = true;
            }
        }
    }

    private Jugada nuevaJugada() {
        List<Color> combinacion;
        do {
            this.consola.imprimirSalida("Introduce una combinación: ");
            String entrada = this.consola.leerEntrada();
            combinacion = this.combinacionBuilder.desdeCadena(entrada);
        } while (combinacion == null);

        return new Jugada(this.combinacion, combinacion);
    }

    private void reiniciarOSalir() {
        do {
            this.consola.imprimirSalida("Reiniciar? (s/n): ");
            String entrada = this.consola.leerEntrada();

            if (entrada != null && (entrada.equals("s") || (entrada.equals("n")))) {
                if (entrada.equals("n")) {
                    finJuego = true;
                }
                break;
            }
        } while (true);
    }

    private void printJugadas(List<Jugada> jugadas) {
        jugadas.forEach(j -> this.consola.imprimirSalida(j.toString()));
    }

}
