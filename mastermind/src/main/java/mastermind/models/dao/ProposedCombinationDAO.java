package mastermind.models.dao;

import mastermind.models.ProposedCombination;

public abstract class ProposedCombinationDAO implements DAO{

    ProposedCombination combination;

    public ProposedCombinationDAO(ProposedCombination combination) {
        this.combination = combination;
    }

    public abstract void save();
    public abstract void load();
}
