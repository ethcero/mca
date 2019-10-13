package mastermind.models.dao.file;

import mastermind.models.SecretCombination;
import mastermind.models.dao.SecretCombinationDAO;

import java.io.FileWriter;

public class FileSecretCombinationDAO extends SecretCombinationDAO {

    FileWriter fileWrite;

    public FileSecretCombinationDAO(SecretCombination combination, FileWriter fileWrite) {
        super(combination);
        this.fileWrite = fileWrite;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }
}
