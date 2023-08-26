/**
 * @author- Omoze Oyarebu
 * 
 *
 * Project- Write a program that gives a solution to the Knight's Tour problem recursively and
 *           only takes less than 30 seconds to solve.
 *
 * Notes- selected starting (x,y) positions such as (0,5),(0,7),(0,1),(5,5),(7,7) etc all give efficient solutions
 *        under 30 seconds.
 *
 * Version 1.0
 */

#include <iostream>
#include <cstdio>
#include <iomanip>

const int X_MOVES[] = {1,1,-1,-1,2,2,-2,-2};
const int Y_MOVES[] = {2,-2,2,-2,1,-1,1,-1};
const int VISITED = -1;
const int BOARD_SIZE = 8;

//starting x position on the grid
int startX_Position() {
    int x_start = 0;
    return x_start;
}

//starting y position on the grid
int startY_Position() {
    int y_start  = 1;
    return y_start;
}

//printing the chessboard
void printBoard(int b[][BOARD_SIZE]) {
     for (int row = 0; row < BOARD_SIZE; row++) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            printf("%-4d",b[row][col] + 1);
        }
        std::cout << std::endl;
     }
}

//makes sure we don't go out of bounds in the grid
//checks to see if a square has been visited or not
bool safeSquare(int x, int y, int sol[][BOARD_SIZE]) {
    return (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && sol[x][y] == -1);
}

//recursive helper function to use in solution()
bool kt_Backtracking(int x, int y, int num_of_moves, int boardGrid[][BOARD_SIZE]) {
    int knight_moves;
    int nextX_Move;
    int nextY_Move;

    if (num_of_moves == BOARD_SIZE * BOARD_SIZE) {
        return true;
    }

    for (knight_moves = 0; knight_moves < 8; knight_moves++) {
        nextX_Move = x + X_MOVES[knight_moves];
        nextY_Move = y + Y_MOVES[knight_moves];
        if (safeSquare(nextX_Move, nextY_Move, boardGrid)) {
            boardGrid[nextX_Move][nextY_Move] = num_of_moves;
            if (kt_Backtracking(nextX_Move, nextY_Move, num_of_moves + 1, boardGrid)) {
                return true;
            } else {
                boardGrid[nextX_Move][nextY_Move] = VISITED;
            }
        }
    }
    return false;
}

//solution
void solution() {
    int boardGrid[BOARD_SIZE][BOARD_SIZE];

    for (int row = 0; row < BOARD_SIZE; row++) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            boardGrid[row][col] = VISITED;
        }
    }

    boardGrid[startX_Position()][startY_Position()] = 0;

    int x = startX_Position();
    int y = startY_Position();

    if (!kt_Backtracking(x, y, 1, boardGrid)) {
        std::cout << "No solution found with starting point x=" << x << " and y=" << y << std::endl;
    } else {
        printBoard(boardGrid);
    }
}



int main() {
  solution();  
}
