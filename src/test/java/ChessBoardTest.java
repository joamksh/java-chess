import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChessBoardTest {
    @Test
    public void testInitializeBoard() {
        ChessBoard chessBoard = new ChessBoard();
        List<List<String>> board = chessBoard.getBoard();

        String[][] expectedBoard = {
                {"R", "N", "B", "Q", "K", "B", "N", "R"},
                {"P", "P", "P", "P", "P", "P", "P", "P"},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "."},
                {"p", "p", "p", "p", "p", "p", "p", "p"},
                {"r", "n", "b", "q", "k", "b", "n", "r"}
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertEquals(expectedBoard[i][j], board.get(i).get(j), "불일치 발생 위치 (" + i + ", " + j + ")");
            }
        }
    }

    @Test
    public void testPrintBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.printBoard();
        // 콘솔 출력을 수동으로 확인하거나 콘솔 출력을 캡처하고 assert 하는 라이브러리 사용
    }

}
