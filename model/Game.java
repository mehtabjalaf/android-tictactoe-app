package eecs1022.lab8.tictactoe.model;

public class Game {

        private String playerX;
        private String playerO;
        private String status;
        private String turn;
        private boolean gameTie = false;
        char[][] gameBoard = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };

        public Game() {
            this.status = "No game has been started";
        }

        public Game(String playerX, String playerO) {
            this.playerX = playerX;
            this.playerO = playerO;
            this.turn = playerX;
            this.status = String.format("%s's turn to play...", this.turn);
        }

        public String getCurrentPlayer() {
            return this.turn;
        }

        public String getStatus() {
            return this.status;
        }

        public char[][] getGameBoard() {
            return gameBoard;
        }

        public char[][] getBoard() {
                return gameBoard;
            }

        public void setWhoPlaysFirst(char xo) {
            if ((xo == 'o') || (xo == 'O')) {
                setTurn(this.playerO);
            }

            else if ((xo == 'x') || (xo == 'X')) {
                setTurn(this.playerX);
            }
        }

        public void move(int row, int col) {
            if (winStateChecker('x')) {
                this.status = "Error: game is already over with a winner.";
            }

            else if (winStateChecker('o')) {
                this.status = "Error: game is already over with a winner.";
            }

            else if (gameTie) {
                this.status = "Error: game is already over with a tie.";
            }

            else if ((row < 1) || (row>3)) {
               this.status = String.format("Error: row %d is invalid.", row);
            }

            else if ((col < 1) || (col > 3)) {
                this.status = String.format("Error: col %d is invalid.", col);
            }

            else if (this.gameBoard[row-1][col-1] != '_') {
                this.status = String.format("Error: slot @ (%d, %d) is already occupied.", row, col);
            }

            else {

                if (this.turn.equals(this.playerX)) {
                    this.gameBoard[row-1][col-1] = 'x';
                    setTurn(this.playerO);
                }

                else if (this.turn.equals(this.playerO)) {
                    this.gameBoard[row-1][col-1] = 'o';
                    setTurn(this.playerX);
                }

            }
        }

        public void setTurn(String player) {

            if (winStateChecker('x')) {
                this.status = String.format("Game is over with %s being the winner.", this.playerX);
                this.turn = null;
            }

            else if (winStateChecker('o')) {
                this.status = String.format("Game is over with %s being the winner.", this.playerO);
                this.turn = null;
            }

            else if (isBoardFull()) {
                this.status = String.format("Game is over with a tie between %s and %s." , this.playerX, this.playerO);
                gameTie = true;
                this.turn = null;
            }

            else {
                this.turn = player;
                this.status = String.format("%s's turn to play...", this.turn);
            }
        }

        public boolean winStateChecker(char xo) {
            boolean result = false;
            boolean diagonalCheck = false;
            int horizontalCount = 0;
            int verticalCount = 0;
            int hPlaceHolder = 0;
            int vPlaceHolder = 0;


            // here i am going to be checking for duplicates (across, up/down, and diagonal)
            // going to be returning true or false based on if x/o (par xo) has a winning state
            // boolean result will allow me to create if statements and update status

            // Horizontal Check
            for (int i = 0; i < 3; i++){
                if (this.gameBoard[hPlaceHolder][i] == xo) {
                    horizontalCount++;
                }
                else {
                    if (hPlaceHolder < 2) {
                        horizontalCount = 0;
                        hPlaceHolder++;
                    }
                }
            }

            // Vertical Check
            for (int x = 0; x < 3; x++){
                if (this.gameBoard[x][vPlaceHolder] == xo) {
                    verticalCount++;
                }
                else {
                    if (vPlaceHolder < 2) {
                        verticalCount = 0;
                        vPlaceHolder++;
                    }
                }
            }

            //Diagonal Check
            if (this.gameBoard[0][0] == xo){
                if (this.gameBoard[1][1] == xo) {
                    if (this.gameBoard[2][2] == xo) {
                        diagonalCheck = true;
                    }
                }
            }
            else {
                if (this.gameBoard[0][2] == xo) {
                    if (this.gameBoard[1][1] == xo) {
                        if (this.gameBoard[2][0] == xo) {
                            diagonalCheck = true;
                        }
                    }
                }
            }

            if ((horizontalCount == 3) || (verticalCount == 3) || (diagonalCheck)) {
                result = true;
            }

            return result;
        }

        public boolean isBoardFull() {
            boolean result = false;
            int count = 0;

            for (int i = 0; i < 3; i++) {
                for (int x = 0; x < 3; x++) {
                    if (this.gameBoard[i][x] != '_') {
                        count++;
                    }
                }
            }

            if (count >= 9) {
                result = true;
            }

            return result;
        }



}
