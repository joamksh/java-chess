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
            System.out.println();
        }
        System.out.println("abcdefgh"); // 열 문자 출력
    }

    public void movePiece(String source, String target) throws InvalidCommandException {
        validatePosition(source);
        validatePosition(target);

        int[] sourcePos = convertPosition(source);
        int[] targetPos = convertPosition(target);

        String piece = board.get(sourcePos[0]).get(sourcePos[1]);
        String targetPiece = board.get(targetPos[0]).get(targetPos[1]);
        if (piece.equals(".")) {
            throw new InvalidCommandException("출발 위치에 말이 없습니다: " + source);
        }

        if (isSameColor(piece, targetPiece)) {
            throw new InvalidCommandException("같은 색의 말이 있습니다: " + target);
        }

        if (!isValidMove(piece, sourcePos, targetPos)) {
            throw new InvalidCommandException("유효하지 않은 이동: " + source + "에서 " + target + "로");
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

    private boolean isSameColor(String piece, String targetPiece) {
        if (piece.equals(".") || targetPiece.equals(".")) {
            return false;
        }
        return (Character.isUpperCase(piece.charAt(0)) && Character.isUpperCase(targetPiece.charAt(0))) ||
                (Character.isLowerCase(piece.charAt(0)) && Character.isLowerCase(targetPiece.charAt(0)));
    }

    private boolean isValidMove(String piece, int[] sourcePos, int[] targetPos) {
        int rowDiff = targetPos[0] - sourcePos[0];
        int colDiff = targetPos[1] - sourcePos[1];

        switch (Character.toLowerCase(piece.charAt(0))) {
            case 'p':
                return isValidPawnMove(piece, sourcePos, targetPos, rowDiff, colDiff);
            case 'r':
                return isValidRookMove(sourcePos, targetPos);
            case 'n':
                return isValidKnightMove(rowDiff, colDiff);
            case 'b':
                return isValidBishopMove(sourcePos, targetPos);
            case 'q':
                return isValidQueenMove(sourcePos, targetPos);
            case 'k':
                return isValidKingMove(rowDiff, colDiff);
            default:
                return false;
        }
    }

    private boolean isValidPawnMove(String piece, int[] sourcePos, int[] targetPos, int rowDiff, int colDiff) {
        if (Character.isUpperCase(piece.charAt(0))) { // 흑색 폰
            if (sourcePos[0] == 1 && rowDiff == 2 && colDiff == 0 && board.get(targetPos[0]).get(targetPos[1]).equals(".")) {
                return true; // 처음에 두 칸 전진
            }
            if (rowDiff == 1 && colDiff == 0 && board.get(targetPos[0]).get(targetPos[1]).equals(".")) {
                return true; // 한 칸 전진
            }
            if (rowDiff == 1 && Math.abs(colDiff) == 1 && !board.get(targetPos[0]).get(targetPos[1]).equals(".")) {
                return true; // 대각선 공격
            }
        } else { // 백색 폰
            if (sourcePos[0] == 6 && rowDiff == -2 && colDiff == 0 && board.get(targetPos[0]).get(targetPos[1]).equals(".")) {
                return true; // 처음에 두 칸 전진
            }
            if (rowDiff == -1 && colDiff == 0 && board.get(targetPos[0]).get(targetPos[1]).equals(".")) {
                return true; // 한 칸 전진
            }
            if (rowDiff == -1 && Math.abs(colDiff) == 1 && !board.get(targetPos[0]).get(targetPos[1]).equals(".")) {
                return true; // 대각선 공격
            }
        }
        return false;
    }

    private boolean isValidRookMove(int[] sourcePos, int[] targetPos) {
        if (sourcePos[0] != targetPos[0] && sourcePos[1] != targetPos[1]) {
            return false; // 루크는 같은 행 또는 같은 열로만 이동 가능
        }
        return isPathClear(sourcePos, targetPos);
    }

    private boolean isValidKnightMove(int rowDiff, int colDiff) {
        return (Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 1) || (Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 2);
    }

    private boolean isValidBishopMove(int[] sourcePos, int[] targetPos) {
        if (Math.abs(sourcePos[0] - targetPos[0]) != Math.abs(sourcePos[1] - targetPos[1])) {
            return false; // 비숍은 대각선으로만 이동 가능
        }
        return isPathClear(sourcePos, targetPos);
    }

    private boolean isValidQueenMove(int[] sourcePos, int[] targetPos) {
        return isValidRookMove(sourcePos, targetPos) || isValidBishopMove(sourcePos, targetPos);
    }

    private boolean isValidKingMove(int rowDiff, int colDiff) {
        return Math.abs(rowDiff) <= 1 && Math.abs(colDiff) <= 1;
    }

    private boolean isPathClear(int[] sourcePos, int[] targetPos) {
        int rowStep = Integer.compare(targetPos[0], sourcePos[0]);
        int colStep = Integer.compare(targetPos[1], sourcePos[1]);

        int row = sourcePos[0] + rowStep;
        int col = sourcePos[1] + colStep;

        while (row != targetPos[0] || col != targetPos[1]) {
            if (!board.get(row).get(col).equals(".")) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
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