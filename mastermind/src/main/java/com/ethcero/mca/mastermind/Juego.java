package com.ethcero.mca.mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class Juego {

    private static BufferedReader br;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        new Partida().iniciarJuego();

    }

    private static class Partida {

        private static final int MAX_JUGADAS = 10;

        boolean finJuego = false;
        List<Color> combinacion;
        List<Jugada> jugadas;

        void iniciarJuego() {
            do {
                System.out.println("--- MASTERMIND ---");
                this.combinacion = new CombinacionBuilder().aleatoria();
                nuevaPartida();
                reiniciarOSalir();
            } while (!finJuego);
        }

        private void nuevaPartida() {
            boolean finPartida = false;
            this.jugadas = new ArrayList<Jugada>();
            while (!finPartida) {
                Jugada jugada = nuevaJugada();
                this.jugadas.add(jugada);
                System.out.println(String.format("%d intentos: \n ***", jugadas.size()));
                printJugadas(jugadas);
                if (jugada.esGanadora()) {
                    System.out.println("YOU WIN!!!");
                    finPartida = true;
                } else if (jugadas.size() > MAX_JUGADAS) {
                    System.out.println("YOU LOST!!!");
                    finPartida = true;
                }
            }
        }

        private Jugada nuevaJugada() {
            List<Color> combinacion;
            do {
                System.out.println("Introduce una combinación: ");
                String entrada = leerEntrada();
                combinacion = new CombinacionBuilder().desdeCadena(entrada);
            } while (combinacion == null);

            return new Jugada(this.combinacion, combinacion);
        }

        private void reiniciarOSalir() {
            do {
                System.out.println("Reiniciar? (s/n): ");
                String entrada = leerEntrada();

                if (entrada != null && (entrada.equals("s") || (entrada.equals("n")))) {
                    if (entrada.equals("n")) {
                        finJuego = true;
                    }
                    break;
                }
            } while (true);
        }

        private void printJugadas(List<Jugada> jugadas) {
            jugadas.forEach(System.out::println);
        }

        private String leerEntrada() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
