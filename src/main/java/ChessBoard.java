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

    public void movePiece(String source, String target) throws InvalidCommandException {
        int[] sourcePos = convertPosition(source);
        int[] targetPos = convertPosition(target);

        if (!isValidMove(sourcePos, targetPos)) {
            throw new InvalidCommandException("유효하지 않은 이동: " + source + "에서 " + target + "로");
        }

        String piece = board.get(sourcePos[0]).get(sourcePos[1]);
        if (piece.equals(".")) {
            throw new InvalidCommandException("출발 위치에 말이 없습니다: " + source);
        }

        board.get(sourcePos[0]).set(sourcePos[1], ".");
        board.get(targetPos[0]).set(targetPos[1], piece);
    }

    private int[] convertPosition(String position) {
        char file = position.charAt(0);
        int rank = position.charAt(1) - '1';
        int fileIndex = file - 'a';
        return new int[]{7 - rank, fileIndex};
    }

    private boolean isValidMove(int[] sourcePos, int[] targetPos) {
        return true;
    }
}
