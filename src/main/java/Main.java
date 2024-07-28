import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessBoard chessBoard = new ChessBoard();

        while (true) {
            System.out.print("명령어를 입력하세요: ");
            String command = scanner.nextLine();
            try {
                if (processCommand(command, chessBoard)) {
                    break;
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage() + " 다시 입력하세요.");
            }
        }
        scanner.close();
    }

    private static boolean processCommand(String command, ChessBoard chessBoard) throws InvalidCommandException {
        if (command.equalsIgnoreCase("start")) {
            chessBoard.printBoard();
            return false;
        }
        if (command.equalsIgnoreCase("end")) {
            return true;
        }
        if (command.startsWith("move ")) {
            String[] parts = command.split(" ");
            if (parts.length == 3) {
                String source = parts[1];
                String target = parts[2];
                chessBoard.printBeforeMove(source, target);
                chessBoard.movePiece(source, target);
                chessBoard.printAfterMove();
                return false;
            } else {
                throw new InvalidCommandException("잘못된 명령어 형식: " + command);
            }
        }
        throw new InvalidCommandException("잘못된 명령어: " + command);
    }
}