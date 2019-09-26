package com.ethcero.mca.mastermind;

import java.util.List;

/**
 * @author fran
 */
public class Jugada {

    private List<Color> combinacionInicial;
    private List<Color> combinacion;
    private Resultado resultado;

    public Jugada(List<Color> combinacionInicial, List<Color> combinacion) {
        this.combinacionInicial = combinacionInicial;
        this.combinacion = combinacion;
        evaluaResultado();
    }

    public Resultado getResultado() {
        return resultado;
    }

    public boolean esGanadora() {
        return resultado.getNegros() == CombinacionBuilder.LENGTH;
    }

    private void evaluaResultado() {
        resultado = new Resultado(compruebaColor(), compruebaPosicion());
    }

    private int compruebaPosicion() {
        int aciertos = 0;
        for (int i = 0; i < combinacion.size(); i++) {
            if (combinacion.get(i).equals(combinacionInicial.get(i))) {
                aciertos++;
            }
        }
        return aciertos;
    }

    private int compruebaColor() {
        int aciertos = 0;
        for (int i = 0; i < combinacion.size(); i++) {
            for (int j = 0; j < combinacionInicial.size(); j++) {
                if (combinacion.get(i).equals(combinacionInicial.get(j)) &&
                        !combinacion.get(i).equals(combinacionInicial.get(i))) {
                    aciertos++;
                }
            }
        }
        return aciertos;
    }

    @Override
    public String toString() {
        return String.format("%s -- > %s --> %d negros y %d blancos",this.combinacionInicial.toString(),this.combinacion.toString(), this.resultado.getNegros(), this.resultado.getBlancos());
    }

    private class Resultado {

        private int blancos;
        private int negros;

        public Resultado(int blancos, int negros) {
            this.blancos = blancos;
            this.negros = negros;
        }

        public int getBlancos() {
            return blancos;
        }

        public int getNegros() {
            return negros;
        }

    }

}
