package draughts.views;

import draughts.controllers.Controller;
import draughts.controllers.ControllersVisitor;
import draughts.controllers.PlayController;
import draughts.controllers.ResumeController;
import draughts.controllers.StartController;

public class View implements ControllersVisitor {

    private StartView startView;

    private CommandView commandView;

    private ResumeView resumeView;

    public View(){
        this.startView = new StartView();
        this.commandView = new CommandView();
        this.resumeView = new ResumeView();
    }

    public void interact(Controller controller) {
        controller.accept(this);
    }

    @Override
    public void visit(StartController startController) {
        this.startView.interact(startController);
    }

    @Override
    public void visit(PlayController playController) {
        this.commandView.interact(playController);
    }

    @Override
    public void visit(ResumeController resumeController) {
        this.resumeView.interact(resumeController);
    }

}
