package mastermind.controllers;

import mastermind.models.*;
import mastermind.models.Error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class ProposalController extends Controller{

    public ProposalController(Session session) {
        super(session);
    }

    @Override
    public void next() {
        if(session.isFinished()) {
            super.next();
        }
    }

    public Error addProposal(List<Color> colorList) {
        List<Color> selected = new ArrayList<>();
        if (colorList.size() != Combination.getWidth()) {
            return mastermind.models.Error.WRONG_LENGTH;
        } else {
            for (Color c : colorList) {
                if(selected.size() == 0) {
                    selected.add(c);
                }else {

                    if(selected.contains(c)) {
                        return mastermind.models.Error.DUPLICATED;
                    }else {
                        selected.add(c);
                    }
                }
            }
        }

        this.addProposedCombination(new ProposedCombination(colorList));

        return null;
    }

    private void addProposedCombination(ProposedCombination combination) {
        session.addProposedCombination(combination);
        next();
    }



}
