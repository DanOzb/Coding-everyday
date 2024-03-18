import java.util.Scanner;

public class Main {
	String[][] board = { { ".", ".", "." }, { ".", ".", "." }, { ".", ".", "." } };
	private boolean EXIT_COMMAND = false;
	Scanner input;
	private int player = 1;
	private int answers = 0;

	public void commandLoop() {
		while (EXIT_COMMAND == false) {
			showBoard();
			input = new Scanner(System.in);
			if(inputReader() == false) {
				continue;
			}
			EXIT_COMMAND = checkBoard();
			player = player * -1;
		}
	}

	private boolean inputReader() {
		int column;
		int row;
		
		String turn = player > 0 ? "Player One" : "Player Two";
		System.out.println(turn);
		System.out.print("Enter Column: ");
		column = input.nextInt();
		System.out.print("Enter Row: ");
		row = input.nextInt();
		System.out.println();
		return updateBoard(column, row, player > 0 ? "x" : "o");
	}

	private boolean updateBoard(int column, int row, String move) {
		if(column > 3 || column < 1) {
			System.err.println("Pick a coolumn between 1 and 3");
			return false;
		} else if(row > 3 || row < 0) {
			System.err.println("Pick a row between 1 and 3");
			return false;
		} else if(board[column-1][row-1].contains("x") || board[column-1][row-1].contains("o")) {
			System.err.println("Can't put an x there!");
			return false;
		} 
		board[column - 1][row - 1] = move;
		answers++;
		return true;
	}

	private void showBoard() {
		String updatedBoard = String.format("| %s | %s | %s |\n| %s |" + " %s | %s |\n| %s | %s | %s |\n", board[0][0],
				board[0][1], board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
		System.out.print(updatedBoard);
		System.out.println();
	}

	private boolean checkBoard() {
		if(answers == 9) {
			draw();
			return true;
		}
		String line = "";
		line = board[0][0] + board[1][1] + board[2][2];
		if (line.contains("xxx") || line.contains("ooo")) {
			hasWon();
			return true;
		} 
		line = board[0][2] + board[1][1] + board[2][0];
		if (line.contains("xxx") || line.contains("ooo")) {
			hasWon();
			return true;
		}
		for (int i = 0; i < 3; i++) {
			line = board[i][0] + board[i][1] + board[i][2];
			if (line.contains("xxx") || line.contains("ooo")) {
				hasWon();
				return true;
			}
			line = board[0][i] + board[1][i] + board[2][i];
			if (line.contains("xxx") || line.contains("ooo")) {
				hasWon();
				return true;
			}
		}
		return false;
	}

	private void draw() {
		System.out.println("It's a draw!!!");
	}
	
	private void hasWon() {
		String turn = player > 0 ? "Player One" : "Player Two";
		System.out.println(turn + " has won!!!");
	}

	public static void main(String[] args) {
		new Main().commandLoop();
	}

}
