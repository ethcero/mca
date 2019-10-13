package mastermind.models.dao;

import mastermind.models.Result;

public abstract class ResultDAO {

    Result result;

    public ResultDAO(Result result) {
        this.result = result;
    }

    public abstract void save();
    public abstract void load();
}
