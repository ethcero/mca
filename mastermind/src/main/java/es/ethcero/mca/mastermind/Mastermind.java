package es.ethcero.mca.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author fran
 */
public class Mastermind {

    private static final int MAX_JUGADAS = 10;

    private CombinacionSecreta combinacionSecreta;
    private List<CombinacionPropuesta> historicoPropuestas;

    void iniciarJuego() {
        do {
            System.out.println("--- MASTERMIND ---");
            preparaPartida();
            comienzaPartida();
        } while (quiereReiniciar());
    }

    private void comienzaPartida() {
        CombinacionPropuesta propuesta;
        do {
            propuesta = new CombinacionPropuesta();
            this.historicoPropuestas.add(propuesta);
            propuesta.introducirPropuesta();
            combinacionSecreta.evaluaResultado(propuesta);
            this.imprimeIntentos();
            combinacionSecreta.imprimeSecreto();
            this.imprimePropuestas();
        } while(!hayGanador(propuesta));
    }

    private boolean hayGanador(CombinacionPropuesta propuesta){
        if (propuesta.esGanadora()) {
            System.out.println("YOU WIN!!!");
            return true;
        } else if (historicoPropuestas.size() > MAX_JUGADAS) {
            System.out.println("YOU LOST!!!");
            return true;
        }
        return false;
    }

    private void preparaPartida(){
        this.combinacionSecreta = new CombinacionSecreta();
        this.historicoPropuestas = new ArrayList<>();
    }

    private boolean quiereReiniciar() {
        do {
            System.out.println("Reiniciar? (s/n): ");
            Scanner scanner = new Scanner(System.in);
            String entrada = scanner.nextLine();
            if (entrada != null && (entrada.equals("s") || (entrada.equals("n")))) {
                return entrada.equals("s");
            }
        } while (true);
    }

    private void imprimeIntentos(){
        System.out.println(String.format("%s intentos", this.historicoPropuestas.size()));
    }
    private void imprimePropuestas() {
        this.historicoPropuestas.forEach(p -> System.out.println(p.toString()));
    }

}
