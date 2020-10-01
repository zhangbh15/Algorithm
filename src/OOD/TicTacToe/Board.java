package OOD.TicTacToe;

public class Board {
    private User[] users;
    private Boolean[][] board;

    public Board(final int size) {
        this.board = new Boolean[size][size];
    }
    public Board() {
        this(DEFAULT_SIZE);
    }

    public boolean setUser(int idx, User user) {
        if (users[idx] != null) {
            return false;
        }

        this.users[idx] = user;
        return true;
    }

    public void removeUser(int idx) {
        this.users[idx] = null;
    }

    public User markPos(int i, int j, int user) {
        if (this.board[i][j] != null) {
            throw new IllegalArgumentException("Invalid position.");
        }
        if (user != 0 && user != 1) {
            throw new IllegalArgumentException("Invalid user index.");
        }

        this.board[i][j] = user == 0;
        return checkWinner(i, j, user);
    }

    public User checkWinner(int i, int j, int user) {
        if (checkRow(i, user) || checkCol(j, user) || checkDiag(i, j, user)) {
            return this.users[user];
        }
        return null;
    }


    private static final int DEFAULT_SIZE = 3;

    private boolean checkRow(int row, int user) {
        Boolean flag = user == 0;
        for (int i = 0; i < this.board[row].length; i++) {
            if (this.board[row][i] != flag) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol(int col, int user) {
        Boolean flag = user == 0;
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][col] != flag) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiag(int i, int j, int user) {
        int size = this.board.length;
        if (i != j && i + j != size) {
            return false;
        }

        Boolean flag = user == 0;

        for (int m = 0; m < size; m++) {
            if (this.board[m][m] != flag && this.board[m][size - 1 - m] != flag) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Boolean flag1 = null;
        Boolean flag2 = null;
        System.out.println(flag1 == flag2);
    }
}


class User {
    private int id;
    private String username;
}

