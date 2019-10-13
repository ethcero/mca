package mastermind.models.dao;

import mastermind.models.Game;

public abstract class GameDAO implements DAO{

    Game game;

    public GameDAO(Game game) {
        this.game = game;
    }

    public abstract void save();
    public abstract void load();
}
