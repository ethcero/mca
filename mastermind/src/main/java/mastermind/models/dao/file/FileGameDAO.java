package mastermind.models.dao.file;

import mastermind.models.Game;
import mastermind.models.dao.GameDAO;

import java.io.FileWriter;

public class FileGameDAO extends GameDAO {

    FileWriter fileWrite;

    public FileGameDAO(Game game, FileWriter fileWrite) {
        super(game);
        this.fileWrite = fileWrite;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}
