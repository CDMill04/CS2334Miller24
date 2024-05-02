
public class IllegalMoveException extends Exception {

		private static final long serialVersionUID = 1L;
		IllegalMoveException(String moveInfo)
		{
			super(moveInfo);
		}
}
