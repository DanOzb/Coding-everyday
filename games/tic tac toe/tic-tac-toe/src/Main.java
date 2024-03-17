import java.util.Scanner;

public class Main {
	String[][] board = { { ".", ".", "." }, { ".", ".", "." }, { ".", ".", "." } };
	private boolean EXIT_COMMAND = false;
	Scanner input;

	public void commandLoop() {
		while (EXIT_COMMAND == false) {
			input = new Scanner(System.in);
			inputReader();
			EXIT_COMMAND = checkBoard();
			showBoard();
		}
	}

	private void inputReader() {
		System.out.print("Enter Column: ");
		int column = input.nextInt();
		System.out.println();
		System.out.print("Enter Row: ");
		int row = input.nextInt();
		updateBoard(column, row);
	}

	private void updateBoard(int column, int row) {
		board[column - 1][row - 1] = "x";
	}

	private void showBoard() {
		String updatedBoard = String.format("| %s | %s | %s |\n| %s |" + " %s | %s |\n| %s | %s | %s |\n", board[0][0],
				board[0][1], board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
		System.out.print(updatedBoard);
	}

	private boolean checkBoard() {
		if (board[0][0] == "x" && board[1][1] == "x" && board[2][2] == "x") {
			return true;
		} else if (board[0][2] == "x" && board[1][1] == "x" && board[2][0] == "x") {
			return true;
		}
		String line = "";
		for (int i = 0; i < 3; i++) {
			line = board[i][0] + board[i][1] + board[i][2];
			if (line.contains("xxx")) {
				return true;
			}
			line = board[0][i] + board[1][i] + board[2][i];
			if (line.contains("xxx")) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		new Main().commandLoop();
	}

}
