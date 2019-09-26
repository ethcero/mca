package com.ethcero.mca.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author fran
 */
public class CombinacionBuilder {

    public static int LENGTH = 4;

    public List<Color> aleatoria() {
        List<Color> combinacion = new ArrayList<Color>();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            Color c;
            do {
                c = Color.values()[random.nextInt(Color.values().length)];
            }
            while (existeColor(combinacion, c)) ;

            combinacion.add(c);
        }
        return combinacion;
    }

    public List<Color> desdeCadena(String cadena) {

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

    private boolean existeColor(List<Color> combinacion, Color color) {
        for (Color c : combinacion) {
            if (color.equals(c)) {
                return true;
            }
        }
        return false;
    }

}
