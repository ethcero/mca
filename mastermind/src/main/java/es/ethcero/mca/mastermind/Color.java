package es.ethcero.mca.mastermind;

/**
 * @author fran
 */
public enum Color {
    R("r"),
    G("g"),
    B("b"),
    Y("y"),
    O("o"),
    P("p");

    private final String color;

    private Color(String color){
        this.color = color;
    }

    public String getValor() {
        return color;
    }
}
