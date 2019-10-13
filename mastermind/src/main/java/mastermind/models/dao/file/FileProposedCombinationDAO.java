package mastermind.models.dao.file;

import mastermind.models.ProposedCombination;
import mastermind.models.dao.ProposedCombinationDAO;

import java.io.FileWriter;

public class FileProposedCombinationDAO extends ProposedCombinationDAO {

    FileWriter fileWrite;

    public FileProposedCombinationDAO(ProposedCombination combination, FileWriter fileWrite) {
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
