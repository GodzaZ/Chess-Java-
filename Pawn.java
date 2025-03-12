package piece;

import main.GamePanel;
import main.Type;


public class Pawn extends Piece {

    public Pawn(int color, int col, int row) {
        super(color, col, row);

        type = Type.PAWN;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-pawn");
        } else {
            image = getImage("/piece/b-pawn");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {
            //moveValue a szine alapjan
            int moveValue;
            if (color == GamePanel.WHITE) {
                moveValue = -1;
            } else {
                moveValue = 1;
            }

            //megnezzuk hogy ut-e mas babut
            hittingP = getHittingP(targetCol, targetRow);
            // 1 negyzet lepes
            if (targetCol == preCol && targetRow == preRow + moveValue && hittingP == null) {
                return true;
            }

            // 2 negyzet lepes
            if (targetCol == preCol && targetRow == preRow + moveValue * 2 && hittingP == null && moved == false && pieceIsOnStraightLine(targetCol, targetRow) == false) {
                return true;
            }

            // ferden babuk levetele, ha pont elotte van
            if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingP != null && hittingP.color != color) {
                return true;
            }

            //en passant
            if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue) {
                for (Piece piece : GamePanel.simPieces) {
                    if (piece.col == targetCol && piece.row == preRow && piece.twoStepped == true) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
