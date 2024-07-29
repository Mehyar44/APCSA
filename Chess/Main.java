import java.util.*;

public class Main {
    public static void main(String[] args) {
        String white = "\u001B[47m";
        String black = "\u001B[40m";
        String blue = "\u001B[34m";
        String red = "\u001B[31m";
        String space = "  \u001B[0m";
        String blueKing = blue + "♚ \u001B[0m";
        String redKing = red + "♚ \u001B[0m";
        String blueQueen = blue + "♛ \u001B[0m";
        String redQueen = red + "♛ \u001B[0m";
        String blueRook = blue + "♜ \u001B[0m";
        String redRook = red + "♜ \u001B[0m";
        String blueKnight = blue + "♞ \u001B[0m";
        String redKnight = red + "♞ \u001B[0m";
        String blueBishop = blue + "♝ \u001B[0m";
        String redBishop = red + "♝ \u001B[0m";
        String bluePawn = blue + "♟ \u001B[0m";
        String redPawn = red + "♟ \u001B[0m";
        boolean blueTurn = true; 
        String color = blue;
        String otherColor = red;
        boolean blueKingMove = true;
        boolean redKingMove = true;
        boolean blueLeftRookMove = true;
        boolean blueRightRookMove = true;
        boolean redLeftRookMove = true;
        boolean redRightRookMove = true;
        boolean kingMove;
        boolean leftRookMove;
        boolean rightRookMove;
        boolean castleCheck;

        String[][] board = 
            {{white+redRook,black+redKnight,white+redBishop,black+redQueen,white+redKing,black+redBishop,white+redKnight,black+redRook},
            {black+redPawn,white+redPawn,black+redPawn,white+redPawn,black+redPawn,white+redPawn,black+redPawn,white+redPawn},
            {white+space,black+space,white+space,black+space,white+space,black+space,white+space,black+space},
            {black+space,white+space,black+space,white+space,black+space,white+space,black+space,white+space},
            {white+space,black+space,white+space,black+space,white+space,black+space,white+space,black+space},
            {black+space,white+space,black+space,white+space,black+space,white+space,black+space,white+space},
            {white+bluePawn,black+bluePawn,white+bluePawn,black+bluePawn,white+bluePawn,black+bluePawn,white+bluePawn,black+bluePawn},
            {black+blueRook,white+blueKnight,black+blueBishop,white+blueQueen,black+blueKing,white+blueBishop,black+blueKnight,white+blueRook}};

        Scanner s = new Scanner(System.in);

        while (notWin(board)) {  
            System.out.println((blueTurn) ? ("Blue's turn") : ("Red's turn"));

            if(blueTurn) {
                kingMove = blueKingMove;
                leftRookMove = blueLeftRookMove;
                rightRookMove = blueRightRookMove;

                castleCheck = kingMove && leftRookMove && rightRookMove;
            }

            else {
                kingMove = redKingMove;
                leftRookMove = redLeftRookMove;
                rightRookMove = redRightRookMove;

                castleCheck = kingMove && leftRookMove && rightRookMove;
            }

            cleanBoard(board);
            showBoard(board);
            System.out.print("Choose a piece by picking a row and column (Ex - 73 where 7 is the row and 3 is the column): ");
            int from = s.nextInt();
            int rowF = from / 10 - 1;
            int colF = from % 10 - 1;

            if (rowF > 7 || rowF < 0 || colF > 7 || colF < 0) System.out.println("Invalid answer bad loc\n");

            else if (notValidPiece(board,rowF,colF,color)) System.out.println("Invalid answer bad color\n");

            else {
                showPath(board, rowF, colF, color, otherColor, castleCheck);
                showBoard(board);
                System.out.print("Choose a location to go to by picking a row and column or enter 0 to go back (Ex - 73 where 7 is the row and 3 is the column): ");
                int too = s.nextInt();
                if(too != 0) {
                    int rowT = too / 10 - 1;
                    int colT = too % 10 - 1;

                    if (rowT > 7 || rowT < 0 || colT > 7 || colT < 0) System.out.println("Invalid answer bad to loc\n");

                    else if (notValidMove(board, rowT, colT)) System.out.println("Invalid answer no green\n");

                    else {
                        movePiece(board, rowF, colF, rowT, colT);
                        cleanBoard(board);

                        checkMovement(board, blueKingMove, redKingMove, blueLeftRookMove, blueRightRookMove, redLeftRookMove, redRightRookMove);
                        castle(board, blueTurn, castleCheck);
                        int pawnRow = blueTurn ? 0 : 7; 
                        if (pawnSearch(board,pawnRow) > -1) pawnPromotion(board, color, pawnRow, pawnSearch(board,pawnRow));
                        
                        blueTurn = !blueTurn;
                        color = blueTurn ? blue : red;
                        otherColor = blueTurn ? red : blue;
                    }
                }

                System.out.println();
            }
        }
        System.out.println(winner(board) + "wins!");
        s.close();
    }

    public static void showBoard(String[][] arr2d) {
        int count = 1;
        
        for (String[] arr : arr2d) {
            System.out.print(count + " ");
            count++;
            for (String piece : arr) {
                System.out.print(piece);
            }
            System.out.println();
        }
        System.out.println("  1 2 3 4 5 6 7 8 \n");
    }

    public static void cleanBoard(String[][] arr) {
        String white = "\u001B[47m";
        String black = "\u001B[40m";
        String green = "\u001B[42m";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (arr[i][j].contains(green)) {
                    if ((i+j) % 2 == 0) arr[i][j] = white + arr[i][j].substring(5);
                    else arr[i][j] = black + arr[i][j].substring(5);
                }
            }
        }
    }

    public static void showPath(String[][] board, int row, int col, String color, String otherColor, boolean castleCheck) {
        if (board[row][col].contains("♟")) {
            pawnPath(board, color, otherColor, row, col);
        } else if (board[row][col].contains("♞")) {
            knightPath(board, color, otherColor, row, col);
        } else if (board[row][col].contains("♝")) {
            bishopPath(board, color, otherColor, row, col);
        } else if (board[row][col].contains("♜")) {
            rookPath(board, color, otherColor, row, col);
        } else if (board[row][col].contains("♛")) {
            queenPath(board, color, otherColor, row, col);
        } else if (board[row][col].contains("♚")) {
            kingPath(board, color, otherColor, row, col, castleCheck);
        }
    }

    public static void bishopPath(String[][] arr, String color, String otherColor, int row, int col) {
        String green = "\u001B[42m";
        String space = "  \u001B[0m";

        // Top Right 
        for (int i = 1; row - i >= 0 && col + i < 8; i++) {
            if (arr[row - i][col + i].contains(otherColor)) {
                arr[row - i][col + i] = green + arr[row - i][col + i].substring(5);
                break;
            }
            else if (arr[row - i][col + i].contains(color)) break;
            else arr[row - i][col + i] = green + space; 
        }

        // Top Left 
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (arr[row - i][col - i].contains(otherColor)) {
                arr[row - i][col - i] = green + arr[row - i][col - i].substring(5);
                break;
            } 
            else if (arr[row - i][col - i].contains(color)) break;
            else arr[row - i][col - i] = green + space;
        }

        // Bottom Right
        for (int i = 1; row + i < 8 && col + i < 8; i++) {
            if (arr[row + i][col + i].contains(otherColor)) {
                arr[row + i][col + i] = green + arr[row + i][col + i].substring(5);
                break;
          } 
            else if (arr[row + i][col + i].contains(color)) break;
            else arr[row + i][col + i] = green + space;
        }

        // Bottom Left 
        for (int i = 1; row + i < 8 && col - i >= 0; i++) {
            if (arr[row + i][col - i].contains(otherColor)) {
                arr[row + i][col - i] = green + arr[row + i][col - i].substring(5);
                break;
            } 
            else if (arr[row + i][col - i].contains(color)) break;
            else arr[row + i][col - i] = green + space;
        }
    }

    public static void rookPath(String[][] arr, String color, String otherColor, int row, int col) {
        String green = "\u001B[42m";
        String space = "  \u001B[0m";

        // Up 
        for (int i = row - 1; i >= 0; i--) {
            if (arr[i][col].contains(otherColor)) {
                arr[i][col] = green + arr[i][col].substring(5);
                break;
            } 
            else if (arr[i][col].contains(color)) break;
            else arr[i][col] = green + space;
        }

        // Down 
        for (int i = row + 1; i < 8; i++) {
            if (arr[i][col].contains(otherColor)) {
                arr[i][col] = green + arr[i][col].substring(5);
                break;
            } 
            else if (arr[i][col].contains(color)) break;
            else arr[i][col] = green + space;
        }

        // Left 
        for (int i = col - 1; i >= 0; i--) {
            if (arr[row][i].contains(otherColor)) {
                arr[row][i] = green + arr[row][i].substring(5);
                break;
            } 
            else if (arr[row][i].contains(color)) break;
            else arr[row][i] = green + space;
        }

        // Right 
        for (int i = col + 1; i < 8; i++) {
            if (arr[row][i].contains(otherColor)) {
                arr[row][i] = green + arr[row][i].substring(5);
                break;
            } 
            else if (arr[row][i].contains(color)) break;
            else arr[row][i] = green + space;
        }
    }

    public static void queenPath(String[][] arr, String color, String otherColor, int row, int col) {
        rookPath(arr, color, otherColor, row, col);
        bishopPath(arr, color, otherColor, row, col);
    }

    public static void knightPath(String[][] arr, String color, String otherColor, int row, int col) {
        String green = "\u001B[42m";

        int[][] moves = 
            {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                if (!arr[newRow][newCol].contains(color)) {
                    arr[newRow][newCol] = green + arr[newRow][newCol].substring(5);
                }
            }
        }
    }

    public static void kingPath(String[][] arr, String color, String otherColor, int row, int col, boolean castleCheck) {
        String green = "\u001B[42m";
        String space = "  \u001B[0m";
        int castleRow = color.equals("\u001B[34m") ? 7 : 0;

        int[][] moves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                if (!arr[newRow][newCol].contains(color)) {
                    arr[newRow][newCol] = green + arr[newRow][newCol].substring(5);
                }
            }
        }

        if (castleCheck) {
            if (arr[castleRow][5].contains(space) && arr[castleRow][6].contains(space)) arr[castleRow][6] = green + arr[castleRow][6].substring(5);
            if (arr[castleRow][1].contains(space) && arr[castleRow][2].contains(space) && arr[castleRow][3].contains(space)) arr[castleRow][2] = green + arr[castleRow][2].substring(5);
        }
    }

    public static void pawnPath(String[][] arr, String color, String otherColor, int row, int col) {
        String green = "\u001B[42m";
        String space = "  \u001B[0m";

        int direction = color.contains("\u001B[34m") ? -1 : 1; 
        int startRow = color.contains("\u001B[34m") ? 6 : 1;

        // Forward
        if (arr[row + direction][col].contains(space)) {
            arr[row + direction][col] = green + space;

            // Double Forward 
            if (row == startRow && arr[row + 2 * direction][col].contains(space)) {
                arr[row + 2 * direction][col] = green + space;
            }
        }

        // Diagonal Capture
        if (col - 1 >= 0 && arr[row + direction][col - 1].contains(otherColor)) {
            arr[row + direction][col - 1] = green + arr[row + direction][col - 1].substring(5);
        }
        if (col + 1 < 8 && arr[row + direction][col + 1].contains(otherColor)) {
            arr[row + direction][col + 1] = green + arr[row + direction][col + 1].substring(5);
        }
    }

    public static boolean notValidMove(String[][] arr, int row, int col) {
        if(arr[row][col].contains("\u001B[42m")) return false;
        return true;
    }

    public static boolean notValidPiece(String[][] arr, int row, int col, String color) {
        if (arr[row][col].contains(color)) return false;
        return true;
    }

    public static void movePiece(String[][] arr, int rowF, int colF, int rowT, int colT) {
        String white = "\u001B[47m";
        String black = "\u001B[40m";
        String space = "  \u001B[0m";

        arr[rowT][colT] = ((rowT + colT) % 2 == 0) ? white + arr[rowF][colF].substring(5) : black + arr[rowF][colF].substring(5);
        arr[rowF][colF] = ((rowF + colF) % 2 == 0) ? white + space : black + space;
    }

    public static boolean notWin(String[][] arr2d) {
        int count = 0;
        for (String[] arr : arr2d) {
            for (String piece : arr) {
                if (piece.contains("♚")) count++;
            }
        }
        return count==2;
    }

    public static String winner(String[][] arr2d) {
        for (String[] arr : arr2d) {
            for (String piece : arr) {
                if (piece.contains("\u001B[34m♚")) return "Blue";
            }
        }
        return "Red";
    }

    public static int pawnSearch(String[][] arr, int row) {
        for (int i=0; i<8; i++) {
            if (arr[row][i].contains("♟")) return i;
        }
        return -1;
    }

    public static void pawnPromotion(String[][] arr, String color, int row, int col) {
        String white = "\u001B[47m";
        String black = "\u001B[40m";
        int ans = 0;
        String piece = color;
        Scanner a = new Scanner(System.in);
        
        while (ans < 1 || ans > 4) {
            System.out.print("\nChoose a pawn promotion (1-Knight, 2-Rook, 3-Bishop, 4-Queen): ");
            ans = a.nextInt();

            if (ans < 1 || ans > 4) System.out.print("Invalid number");
        }
        a.close();

        if (ans == 1) piece += "♞ \u001B[0m";
        if (ans == 2) piece += "♜ \u001B[0m";
        if (ans == 3) piece += "♝ \u001B[0m";
        if (ans == 4) piece += "♛ \u001B[0m";
        
        if ((row+col) % 2 == 0) arr[row][col] = white + piece;
        else arr[row][col] = black + piece;
    }

    public static void checkMovement(String[][] arr, boolean bkm, boolean rkm, boolean blrm, boolean brrm, boolean rlrm, boolean rrrm) {
        String space = "  \u001B[0m";
        
        if (arr[7][4].contains(space)) bkm = false;
        if (arr[0][4].contains(space)) rkm = false;
        if (arr[7][0].contains(space)) blrm = false;
        if (arr[7][7].contains(space)) brrm = false;
        if (arr[0][0].contains(space)) rlrm = false;
        if (arr[0][7].contains(space)) rrrm = false;
    }

    public static void castle (String[][] arr, boolean blueTurn, boolean castleCheck) {
        if (blueTurn) {
            if (arr[7][6].contains("\u001B[34m♚") && castleCheck) movePiece(arr, 7, 7, 7, 5);
            if (arr[7][2].contains("\u001B[34m♚") && castleCheck) movePiece(arr, 7, 0, 7, 3);
        }
        else {
            if (arr[7][6].contains("\u001B[31m♚") && castleCheck) movePiece(arr, 7, 7, 7, 5);
            if (arr[7][2].contains("\u001B[31m♚") && castleCheck) movePiece(arr, 7, 0, 7, 3);
        }
    }
}
