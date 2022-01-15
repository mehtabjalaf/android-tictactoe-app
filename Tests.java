package eecs1022.lab8.tictactoe;

import org.junit.Test;

import eecs1022.lab8.tictactoe.model.*; /* import all classes from the model package */

import static org.junit.Assert.*;

/* These tests together with the Lab8 instructions PDF document the requirements of the game.
 * When in doubts, always first consult with the relevant sections in the Lab8 instructions PDF.
 *
 * Here is a tutorial video demonstrating some example usages of the app to build:
 * https://www.youtube.com/watch?v=bSi5n6WwXsc&list=PL5dxAmCmjv_7YgI2LgcwjWTHiNZSR-aQX&index=3
 */

public class Tests {
    @Test
    public void test_01a() {
        /* Create a new game with names of, respectively, player X and player O. */
        Game g = new Game("Suyeon", "Yuna");

        /* By default, the 'x' player plays first. */
        String whoPlaysFirst = g.getCurrentPlayer();
        assertEquals("Suyeon", whoPlaysFirst);

        /* See Lab8 instructions PDF for the definition of a game's status.
         */
        String status = g.getStatus();
        assertEquals("Suyeon's turn to play...", status);

        /* Each unoccupied slot is denoted by an underscore '_'.
         */
        char[][] board = g.getBoard();
        char[][] expectedBoard = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard, board);
    }

    @Test
    public void test_01b() {
        Game g = new Game("Suyeon", "Yuna");

        /* You can assume that the argument character is either
         * 'x' (for player x) or 'o' (for player o).
         * There is no error checking needed on the input character.
         *
         * You can also assume that the `setWhoPlaysFirst` is invoked only once
         * for each Game object.
         */
        g.setWhoPlaysFirst('o');

        assertEquals("Yuna", g.getCurrentPlayer());

        /* See Lab8 instructions PDF for the definition of a game's status.
         */
        assertEquals("Yuna's turn to play...", g.getStatus());

        char[][] expectedBoard = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard, g.getBoard());

        g = new Game("Suyeon", "Yuna");
        g.setWhoPlaysFirst('x');
        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        assertArrayEquals(expectedBoard, g.getBoard());
    }

    @Test
    public void test_02() {
        Game g = new Game("Suyeon", "Yuna");
        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard, g.getBoard());

        /*
         * From the external user's point of view,
         * row and column numbers are in the range from 1 to 3.
         */
        g.move(-2, 4);

        /* Both input row and column numbers are invalid.
         * But the row number error has a higher priority.
         * See the error table in Lab8 instructions PDF.
         */
        assertEquals("Error: row -2 is invalid.", g.getStatus());
        /* When an error occurs, only the game status changed. */
        assertEquals("Suyeon", g.getCurrentPlayer());
        assertArrayEquals(expectedBoard, g.getBoard());

        g.move(2, 4);

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Error: col 4 is invalid.", g.getStatus());
        assertArrayEquals(expectedBoard, g.getBoard());
    }

    @Test
    public void test_03() {
        Game g = new Game("Suyeon", "Yuna");
        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard0 = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard0, g.getBoard());

        g.move(2, 2); /* Suyeon, player x, placed an 'x' at the specified slot. */

        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard1 = {
                {'_', '_', '_'},
                {'_', 'x', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard1, g.getBoard());

        g.move(1, 3); /* Yuna, player o, placed an 'o' at the specified slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard2 = {
                {'_', '_', 'o'},
                {'_', 'x', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard2, g.getBoard());

        g.move(1, 3); /* Suyeon, player x, attempted to place an 'x' at some occupied slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Error: slot @ (1, 3) is already occupied.", g.getStatus());
        assertArrayEquals(expectedBoard2, g.getBoard());

        g.move(3, 3); /* Suyeon, player x, place an 'x' at the specified slot. */

        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard3 = {
                {'_', '_', 'o'},
                {'_', 'x', '_'},
                {'_', '_', 'x'}
        };
        assertArrayEquals(expectedBoard3, g.getBoard());

        g.move(1, 2); /* Yuna, player o, place an 'o' at the specified slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard4 = {
                {'_', 'o', 'o'},
                {'_', 'x', '_'},
                {'_', '_', 'x'}
        };
        assertArrayEquals(expectedBoard4, g.getBoard());

        g.move(1, 1); /* Suyeon, player x, place an 'x' at the specified slot. */

        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        assertEquals("Game is over with Suyeon being the winner.", g.getStatus());
        char[][] expectedBoard5 = {
                {'x', 'o', 'o'},
                {'_', 'x', '_'},
                {'_', '_', 'x'}
        };
        assertArrayEquals(expectedBoard5, g.getBoard());

        g.move(4, -2);

        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Refer to the error table in Lab8 PDF instructions to see
         * which error has the highest priority.
         */
        assertEquals("Error: game is already over with a winner.", g.getStatus());
        assertArrayEquals(expectedBoard5, g.getBoard());

        g.move(1, -2);

        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Refer to the error table in Lab8 PDF instructions to see
         * which error has the highest priority.
         */
        assertEquals("Error: game is already over with a winner.", g.getStatus());
        assertArrayEquals(expectedBoard5, g.getBoard());

        g.move(1, 2);
        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Refer to the error table in Lab8 PDF instructions to see
         * which error has the highest priority.
         */
        assertEquals("Error: game is already over with a winner.", g.getStatus());
        assertArrayEquals(expectedBoard5, g.getBoard());
    }

    @Test
    public void test_04() {
        Game g = new Game("Suyeon", "Yuna");
        g.setWhoPlaysFirst('o');

        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard0 = {
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard0, g.getBoard());

        g.move(2, 3); /* Yuna, player o, placed an 'o' at the specified slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard1 = {
                {'_', '_', '_'},
                {'_', '_', 'o'},
                {'_', '_', '_'}
        };
        assertArrayEquals(expectedBoard1, g.getBoard());

        g.move(3, 1); /* Suyeon, player x, placed an 'x' at the specified slot. */

        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard2 = {
                {'_', '_', '_'},
                {'_', '_', 'o'},
                {'x', '_', '_'}
        };
        assertArrayEquals(expectedBoard2, g.getBoard());

        g.move(2, 1); /* Yuna, player o, place an 'o' at the specified slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard3 = {
                {'_', '_', '_'},
                {'o', '_', 'o'},
                {'x', '_', '_'}
        };
        assertArrayEquals(expectedBoard3, g.getBoard());

        g.move(2, 2); /* Suyeon, player x, place an 'x' at the specified slot. */

        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard4 = {
                {'_', '_', '_'},
                {'o', 'x', 'o'},
                {'x', '_', '_'}
        };
        assertArrayEquals(expectedBoard4, g.getBoard());

        g.move(1, 3); /* Yuna, player o, place an 'o' at the specified slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard5 = {
                {'_', '_', 'o'},
                {'o', 'x', 'o'},
                {'x', '_', '_'}
        };
        assertArrayEquals(expectedBoard5, g.getBoard());

        g.move(3, 3); /* Suyeon, player x, place an 'x' at the specified slot. */

        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard6 = {
                {'_', '_', 'o'},
                {'o', 'x', 'o'},
                {'x', '_', 'x'}
        };
        assertArrayEquals(expectedBoard6, g.getBoard());

        g.move(1, 1); /* Yuna, player o, place an 'o' at the specified slot. */

        assertEquals("Suyeon", g.getCurrentPlayer());
        assertEquals("Suyeon's turn to play...", g.getStatus());
        char[][] expectedBoard7 = {
                {'o', '_', 'o'},
                {'o', 'x', 'o'},
                {'x', '_', 'x'}
        };
        assertArrayEquals(expectedBoard7, g.getBoard());

        g.move(1, 2); /* Suyeon, player x, place an 'x' at the specified slot. */

        /* Your app only needs to determine if there is a tie when all 9 slots on the board have been occupied.
         * That is, there is your app need not declare a tie
         * when it is already known that no possible subsequent moves can result in a winner.
         */
        assertEquals("Yuna", g.getCurrentPlayer());
        assertEquals("Yuna's turn to play...", g.getStatus());
        char[][] expectedBoard8 = {
                {'o', 'x', 'o'},
                {'o', 'x', 'o'},
                {'x', '_', 'x'}
        };
        assertArrayEquals(expectedBoard8, g.getBoard());

        g.move(3, 2); /* Yuna, player o, place an 'o' at the specified slot. */

        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Tie is displayed between names of player-x and player-o. */
        assertEquals("Game is over with a tie between Suyeon and Yuna.", g.getStatus());
        char[][] expectedBoard9 = {
                {'o', 'x', 'o'},
                {'o', 'x', 'o'},
                {'x', 'o', 'x'}
        };
        assertArrayEquals(expectedBoard9, g.getBoard());

        g.move(4, -2);

        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Refer to the error table in Lab8 PDF instructions to see
         * which error has the highest priority.
         */
        assertEquals("Error: game is already over with a tie.", g.getStatus());
        assertArrayEquals(expectedBoard9, g.getBoard());

        g.move(1, -2);

        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Refer to the error table in Lab8 PDF instructions to see
         * which error has the highest priority.
         */
        assertEquals("Error: game is already over with a tie.", g.getStatus());
        assertArrayEquals(expectedBoard9, g.getBoard());

        g.move(1, 2);
        assertNull(g.getCurrentPlayer()); /* No more move as the game is over. */
        /* Refer to the error table in Lab8 PDF instructions to see
         * which error has the highest priority.
         */
        assertEquals("Error: game is already over with a tie.", g.getStatus());
        assertArrayEquals(expectedBoard9, g.getBoard());
    }
}