package draughts.controllers;

import draughts.models.Board;
import draughts.models.Color;
import draughts.models.Coordinate;
import draughts.models.Piece;
import draughts.models.State;
import draughts.models.Error;
import draughts.models.Game;

public class PlayController extends Controller {

	private CancelController cancelController;
	private MoveController moveController;

    public PlayController(Game game, State state) {
		super(game, state);
		this.cancelController = new CancelController(state);
		this.moveController = new MoveController(game);
	}

	public Error move(Coordinate origin, Coordinate target){
		return this.moveController.move(origin, target);
	}

	public void cancelGame(){
		this.cancelController.cancelGame();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}

	public Board getBoard(){
		return this.game.getBoard();
	}

	public Color getColor() {
		return this.game.getColor();
	}

	public void gameOver(){
		this.state.next();
	}
	
	public boolean isBlocked() {
		return this.game.isBlocked();
	}	

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

}