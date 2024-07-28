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

}