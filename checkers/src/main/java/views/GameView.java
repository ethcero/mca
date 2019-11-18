package views;

import controllers.Controller;
import models.Color;
import models.Coordinate;

class GameView extends SubView {

    private static final String[] COLORS = new String[]{"b", "n", " "};

	void write(Controller controller) {
        assert controller != null;
        final int DIMENSION = controller.getDimension();
        this.writeNumbersLine(DIMENSION);
        for(int i=0; i<DIMENSION; i++){
            this.console.write((i+1)+"");
            for(int j=0; j<DIMENSION; j++){
                Color color = controller.getColor(new Coordinate(i,j));
                if (color == null){
                    this.console.write(GameView.COLORS[2]);
                } else {
                    if(controller.isDraught(new Coordinate(i,j)))
                    {
                        this.console.write(GameView.COLORS[color.ordinal()].toUpperCase());
                    }else {
                        this.console.write(GameView.COLORS[color.ordinal()]);
                    }
                }
            }
            this.console.writeln((i+1)+"");
        }
        this.writeNumbersLine(DIMENSION);
	}


    private void writeNumbersLine(final int DIMENSION) {
        assert DIMENSION > 0;
        this.console.write(" ");
        for(int i=0; i<DIMENSION; i++){
            this.console.write((i+1)+"");
        }
        this.console.writeln();
    }

}
