package es.ethcero.mca.mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombinacionSecreta extends Combinacion{

    public CombinacionSecreta(){
        this.vector = nuevaAleatoria();
    }

    public void evaluaResultado(CombinacionPropuesta propuesta) {
        int tocados = compruebaColor(propuesta);
        int hundidos = compruebaPosicion(propuesta);
        boolean ganador = hundidos == LENGTH;
        propuesta.setResultado(new Resultado(tocados,hundidos,ganador));
    }

    private int compruebaPosicion(CombinacionPropuesta propuesta) {
        int aciertos = 0;
        for (int i = 0; i < vector.size(); i++) {
            if (propuesta.contiene(vector.get(i),i)) {
                aciertos++;
            }
        }
        return aciertos;
    }

    private int compruebaColor(CombinacionPropuesta propuesta) {
        int aciertos = 0;
        for (int i = 0; i < vector.size(); i++) {
            if(propuesta.contiene(vector.get(i)) && !propuesta.contiene(vector.get(i),i)) {
                aciertos++;
            }
        }
        return aciertos;
    }


    private List<Color> nuevaAleatoria(){
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

    private boolean existeColor(List<Color> combinacion, Color color) {
        for (Color c : combinacion) {
            if (color.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public void imprimeSecreto() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < this.vector.size(); i++) {
            builder.append("*");
        }
        return builder.toString();
    }
}
