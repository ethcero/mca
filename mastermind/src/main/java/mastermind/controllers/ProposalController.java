package mastermind.controllers;

import mastermind.models.*;
import mastermind.models.Error;
import mastermind.views.AbstractFactoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
public class ProposalController extends Controller{

    public ProposalController(AbstractFactoryView factoryView, Session session) {
        super(factoryView, session);
    }

    @Override
    public void next() {
        if(session.isFinished()) {
            super.next();
        }
    }

    private Error addProposal(List<Color> colorList) {
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
    }

    @Override
    public void control() {
        this.addProposal(this.factoryView.createProposalView().read());
        this.factoryView.createGameView().writeln();
        this.next();
    }
}