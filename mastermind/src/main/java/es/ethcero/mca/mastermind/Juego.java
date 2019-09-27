package es.ethcero.mca.mastermind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author fran
 */
public class Juego {



    public static void main(String[] args) {

        Consola consola = new ConsolaImpl();
        new Partida(consola, new CombinacionBuilder(consola)).iniciarJuego();

    }

    private static class ConsolaImpl implements Consola {
        private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        @Override
        public void imprimirSalida(String salida) {
            System.out.println(salida);
        }

        @Override
        public String leerEntrada() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
