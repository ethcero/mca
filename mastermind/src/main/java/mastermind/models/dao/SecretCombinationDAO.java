package mastermind.models.dao;

import mastermind.models.SecretCombination;

public abstract class SecretCombinationDAO implements DAO {

    SecretCombination combination;

    public SecretCombinationDAO(SecretCombination combination) {
        this.combination = combination;
    }

    public abstract void save();
    public abstract void load();
}
