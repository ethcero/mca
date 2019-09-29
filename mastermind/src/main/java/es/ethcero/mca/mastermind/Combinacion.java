package es.ethcero.mca.mastermind;

import java.util.List;

public abstract class Combinacion {

    protected static final int LENGTH = 4;

    protected  List<Color> vector;

    @Override
    public String toString() {
        return vector.toString();
    }
}
