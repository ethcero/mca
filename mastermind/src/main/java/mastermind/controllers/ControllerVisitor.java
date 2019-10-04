package mastermind.controllers;

/**
 * @author fran
 */
public interface ControllerVisitor {

    void visit(StartController controller);
    void visit(ProposeController controller);
    void visit(ResumeController controller);

}
