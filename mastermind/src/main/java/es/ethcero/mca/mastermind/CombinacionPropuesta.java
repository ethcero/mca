package es.ethcero.mca.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CombinacionPropuesta extends Combinacion {

    private Resultado resultado;

    public void introducirPropuesta(){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Introduce una combinación: ");
            String entrada = scanner.nextLine();
            this.vector = convertirEntrada(entrada);
        } while (this.vector == null);
    }

    private List<Color> convertirEntrada(String cadena) {

        if (cadena == null || cadena.isEmpty()) {
            return null;
        }

        if (esLongitudValida(cadena)) {
            List<Color> combinacion = new ArrayList<Color>();

            for (int i = 0; i < cadena.length(); i++) {
                if (esColorValido(Character.toString(cadena.charAt(i)))) {
                    combinacion.add(Color.valueOf(Character.toString(cadena.charAt(i)).toUpperCase()));
                } else {
                    System.out.println(String.format("Color %s inválido. Permitidos: %s", cadena.charAt(i),
                            Arrays.stream(Color.values()).map(Color::getValor).collect(Collectors.joining(","))));
                    return null;
                }
            }
            return combinacion;
        } else {
            System.out.println(String.format("Longitud inválida. Máximo %d colores", LENGTH));
        }
        return null;
    }

    public void setResultado(Resultado resultado){
        this.resultado = resultado;
    }

    public boolean contiene(Color color){
        for (Color c : vector) {
            if(color.equals(c)){
                return true;
            }
        }
        return false;
    }

    public boolean contiene(Color color, int posicion){
        return vector.get(posicion).equals(color);
    }

    public boolean esGanadora() {
        return resultado.isGanador();
    }

    private boolean esLongitudValida(String cad) {
        return cad.length() == LENGTH;
    }

    private boolean esColorValido(String color) {
        try {
            Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s --> %s",super.toString(), this.resultado);
    }
}
