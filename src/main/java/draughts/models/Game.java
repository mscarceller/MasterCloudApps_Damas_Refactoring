package draughts.models;

public class Game {

	private Board board;

	private Turn turn;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.board.put(coordinate, piece);
				}
			}
		}
	}

	private Piece getInitialPiece(Coordinate coordinate) {
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			Color color = null;
			if (row <= 2) {
				color = Color.BLACK;
			} else if (row >= 5) {
				color = Color.WHITE;
			}
			if (color != null) {
				return new Piece(color);
			}
		}
		return null;
	}

	public Error move(Coordinate origin, Coordinate target) {
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		Piece piece = this.board.getPiece(origin);
		if (this.turn.getColor() != piece.getColor()) {
			return Error.OPPOSITE_PIECE;
		}
		if (!piece.isDiagonalMovement(origin,target)){
			return Error.NOT_DIAGONAL;
		}
		if (!piece.isAdvancedMovement(origin,target)){
			return Error.NOT_ADVANCED;
		}
		if (piece.isBadDistanceMovement(origin,target)){
			return Error.BAD_DISTANCE;
		}
		if (!this.board.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (piece.isEatingMovement(origin,target)){
			Coordinate between = piece.getEatedPieceCoordinate(origin, target);
			if (this.board.getPiece(between) == null) {
				return Error.EATING_EMPTY;
			}
			this.board.remove(between);
		}
		this.board.move(origin, target);
		this.turn.change();
		return null;
	}

	public Board getBoard(){
		return this.board;
	}

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.board.getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}
}