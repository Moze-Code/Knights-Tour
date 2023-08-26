/**
 * @author- Omoze Oyarebu
 * Course Section- CS 280-011
 * Student ID number- 31560659
 *
 * Project- Write a program that gives a solution to the Knight's Tour problem recursively and
 *           only takes less than 30 seconds to solve.
 *
 * Notes- selected starting (x,y) positions such as (0,5),(0,7),(0,1),(5,5),(7,7) etc all give efficient solutions
 *        under 30 seconds.
 *
 * Version 1.0
 */


public class KnightsTour {


    public static final int[] X_MOVES = {1,1,-1,-1,2,2,-2,-2};
    public static final int[] Y_MOVES = {2,-2,2,-2,1,-1,1,-1};
    public static final int VISITED = -1;
    public static final int BOARD_SIZE = 8;

    public static void main(String[] args) {
        solution();
    }

    //printing the board
    public static void printBoard(int b[][]) {
        //creating the board
        for (int row = 0; row <BOARD_SIZE; row++) {
            for(int col = 0; col <BOARD_SIZE;col++) {
                System.out.printf("%-4d",b[row][col] + 1);
            }
            System.out.println();
        }

    }

    //pick the starting x position on the grid
    public static int startingX_Position() {
        int x_Start = 0;
        return x_Start;
    }
    //starting y position
    public static int startingY_Position() {
        int y_Start = 5;
        return y_Start;
    }

    public static void solution() {

        int[][] boardGrid = new int[BOARD_SIZE][BOARD_SIZE];

        for (int row = 0; row <BOARD_SIZE; row++) {
            for(int col = 0; col <BOARD_SIZE;col++) {
                boardGrid[row][col] = VISITED; // used to track none visited squares
            }
        }

        //initialize the starting point on the board
        boardGrid[startingX_Position()][startingY_Position()] = 0;

        //recursive part to try all legal moves and backtrack if stuck
        int x = startingX_Position();
        int y = startingY_Position();

        if (!kt_Backtracking(x,y,1,boardGrid)) {
            System.out.println("No solution found with starting point x= " + x + " and y= " + y);
        } else {
            printBoard(boardGrid);
        }
    }

    //recursive helper function decide moves
    public static boolean kt_Backtracking(int x, int y, int num_of_moves, int[][] boardGrid) {

        //counter to track the x and y moves
        int knight_moves;

        int nextX_Move;
        int nextY_Move;

        //checking whether we reached all squares in the board
        if (num_of_moves == BOARD_SIZE * BOARD_SIZE) {
            return true;
        }

        for (knight_moves = 0; knight_moves<8; knight_moves++ ) {
            nextX_Move = x + X_MOVES[knight_moves];
            nextY_Move = y + Y_MOVES[knight_moves];
            if (safeSquare(nextX_Move,nextY_Move, boardGrid)) {
                boardGrid[nextX_Move][nextY_Move] = num_of_moves;
                if (kt_Backtracking(nextX_Move,nextY_Move,num_of_moves + 1,boardGrid)) {
                    return true;
                } else {
                    boardGrid[nextX_Move][nextY_Move] = VISITED;
                }
            }
        }
        return false;
    }

    //makes sure we don't go out of bounds in the grid
    //checks to see if a square has been visited or not
    public static boolean safeSquare(int x, int y, int sol[][]) {

        return ((x >= 0) && (x < BOARD_SIZE) && (y >= 0) && (y < BOARD_SIZE) && (sol[x][y] == -1));

    }

}
