package mastermind.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Combination {
	
	private static final int WIDTH = 4;

	protected List<Color> colors;
	
	protected Combination (){
		this.colors = new ArrayList<Color>();
	}

    public List<Color> getColors() {
        return colors;
    }

    public static int getWidth() {
		return Combination.WIDTH;
	}

    @Override
    public String toString() {
        return colors.toString();
    }
}
