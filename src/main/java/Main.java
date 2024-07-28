import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessBoard chessBoard = new ChessBoard();

        while (true) {
            String command = scanner.nextLine();
            try {
                if (command.equalsIgnoreCase("start")) {
                    chessBoard.printBoard();
                } else if (command.equalsIgnoreCase("end")) {
                    break;
                } else {
                    throw new InvalidCommandException("잘못된 명령어: " + command);
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}