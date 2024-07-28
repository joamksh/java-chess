import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import java.util.List;

public class ChessBoardTest {
    @Test
     void testInitializeBoard() {
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
     void testPrintBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.printBoard();
        // 콘솔 출력을 수동으로 확인하거나 콘솔 출력을 캡처하고 assert 하는 라이브러리 사용
    }

    @Test
    public void testMovePiece() {
        ChessBoard chessBoard = new ChessBoard();
        try {
            chessBoard.movePiece("b2", "b3");
            List<List<String>> board = chessBoard.getBoard();
            assertEquals(".", board.get(6).get(1)); // 이전 위치가 비었는지 확인
            assertEquals("p", board.get(5).get(1)); // 새로운 위치에 말이 이동했는지 확인
        } catch (InvalidCommandException e) {
            fail("유효한 이동에서 예외 발생: " + e.getMessage());
        }
    }

    @Test
    void testCaptureDifferentColor() {
        ChessBoard chessBoard = new ChessBoard();
        try {
            chessBoard.movePiece("b2", "b3"); // 백색 폰 이동
            chessBoard.movePiece("b7", "b6"); // 흑색 폰 이동
            chessBoard.movePiece("b3", "b4"); // 백색 폰 이동
            chessBoard.movePiece("b6", "b5"); // 흑색 폰 이동
            chessBoard.movePiece("b4", "b5"); // 백색 폰이 흑색 폰을 캡처
            chessBoard.printBoard();
            List<List<String>> board = chessBoard.getBoard();

            // 이동 후 체스판 상태를 확인
            assertEquals(".", board.get(6).get(1)); // b2
            assertEquals(".", board.get(5).get(1)); // b3
            assertEquals(".", board.get(4).get(1)); // b4
            assertEquals("p", board.get(3).get(1)); // b5
            assertEquals(".", board.get(2).get(1)); // b6
            assertEquals(".", board.get(1).get(1)); // b7
        } catch (InvalidCommandException e) {
            fail("유효한 이동에서 예외 발생: " + e.getMessage());
        }
    }


}







