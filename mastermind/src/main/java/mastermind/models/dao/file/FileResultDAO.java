package mastermind.models.dao.file;

import mastermind.models.Result;
import mastermind.models.dao.ResultDAO;

import java.io.FileWriter;

public class FileResultDAO extends ResultDAO {

    FileWriter fileWrite;

    public FileResultDAO(Result result, FileWriter fileWrite) {
        super(result);
        this.fileWrite = fileWrite;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}
