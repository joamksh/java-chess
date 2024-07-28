import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessBoard chessBoard = new ChessBoard();

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("start")) {
                chessBoard.printBoard();
            } else if (command.equalsIgnoreCase("end")) {
                break;
            }
        }
        scanner.close();
    }
}
