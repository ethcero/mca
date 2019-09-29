package es.ethcero.mca.mastermind;

public class Resultado {

    private int tocados;
    private int hundidos;
    private boolean ganador;

    public Resultado(int tocados, int hundidos, boolean ganador){
        this.tocados = tocados;
        this.hundidos = hundidos;
        this.ganador = ganador;
    }

    @Override
    public String toString(){
        return String.format("%d tocados y %d hundidos",tocados,hundidos);
    }

    public void imprime(){
        System.out.println(this);
    }

    public boolean isGanador(){
        return ganador;
    }
}
