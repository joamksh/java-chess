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
            System.out.println(" "); // 행 번호 출력
        }
        System.out.println("abcdefgh"); // 열 문자 출력
    }

    public void movePiece(String source, String target) throws InvalidCommandException {
        validatePosition(source);
        validatePosition(target);

        int[] sourcePos = convertPosition(source);
        int[] targetPos = convertPosition(target);

        if (!isValidMove(sourcePos, targetPos)) {
            throw new InvalidCommandException("유효하지 않은 이동: " + source + "에서 " + target + "로");
        }

        String piece = board.get(sourcePos[0]).get(sourcePos[1]);
        if (piece.equals(".")) {
            throw new InvalidCommandException("출발 위치에 말이 없습니다: " + source);
        }

        board.get(sourcePos[0]).set(sourcePos[1], "."); // 출발 위치를 비움
        board.get(targetPos[0]).set(targetPos[1], piece); // 목표 위치에 말 이동
    }

    private void validatePosition(String position) throws InvalidCommandException {
        if (position.length() != 2) {
            throw new InvalidCommandException("잘못된 위치: " + position);
        }
        char file = position.charAt(0);
        char rank = position.charAt(1);
        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            throw new InvalidCommandException("잘못된 위치: " + position);
        }
    }

    private int[] convertPosition(String position) {
        char file = position.charAt(0);
        int rank = position.charAt(1) - '1';
        int fileIndex = file - 'a';
        return new int[]{7 - rank, fileIndex}; // 체스보드는 하단에서 상단으로 가면서 번호가 증가하므로 7 - rank를 사용
    }

    private boolean isValidMove(int[] sourcePos, int[] targetPos) {
        // 간단한 이동 유효성 검사, 실제 체스 규칙을 반영하려면 더 복잡한 로직 필요
        return true;
    }

    public void printBeforeMove(String source, String target) {
        System.out.println("원래 체스보드:");
        printBoard();
        System.out.println("\n이동: " + source + " -> " + target);
    }

    public void printAfterMove() {
        System.out.println("\n변경된 체스보드:");
        printBoard();
    }
}