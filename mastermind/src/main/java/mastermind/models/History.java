package mastermind.models;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<GameSnapshot> snapshots;
    private int currentItemIndex;

    public History() {
        snapshots = new ArrayList<>();
        currentItemIndex = -1;
    }

    public void add(GameSnapshot snapshot) {
        this.snapshots.add(snapshot);
        currentItemIndex = this.snapshots.size()-1;
    }

    public void undo() {
        if (isUndoable()) {
            snapshots.get(--currentItemIndex).restore();
        }
    }

    public void redo() {
        if (isRedoable()) {
            snapshots.get(++currentItemIndex).restore();
        }
    }

    public void reset(){
        this.snapshots.clear();
        currentItemIndex = -1;
    }

    public boolean isUndoable() {
        return currentItemIndex >= 1;
    }

    public boolean isRedoable() {
        return snapshots.size() > 0 && currentItemIndex < snapshots.size()-1;
    }
}
