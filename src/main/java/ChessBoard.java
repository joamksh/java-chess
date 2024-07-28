import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    private final List<List<String>> board;

    public ChessBoard() {
        board = Arrays.asList(
                Arrays.asList("R", "N", "B", "Q", "K", "B", "N", "R"),
                Arrays.asList("P", "P", "P", "P", "P", "P", "P", "P"),
                Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."),
                Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."),
                Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."),
                Arrays.asList(".", ".", ".", ".", ".", ".", ".", "."),
                Arrays.asList("p", "p", "p", "p", "p", "p", "p", "p"),
                Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r")
        );
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board.get(i).get(j));
            }
            System.out.println(" ");
//            System.out.println(" " + (i + 1));
        }
//        System.out.println("abcdefgh");
    }
}