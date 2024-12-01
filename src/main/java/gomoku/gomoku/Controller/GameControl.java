package gomoku.gomoku.Controller;
import gomoku.gomoku.Model.*;
import gomoku.gomoku.util.Menu;
import gomoku.gomoku.util.configure.*;
import gomoku.gomoku.util.enums.ProgramState;
import gomoku.gomoku.util.enums.WinType;

public class GameControl {
    Menu menu;

    public GameControl(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        ProgramState state = ProgramState.MENU;
        while (state != ProgramState.TERMINATE) {
            switch (state) {
                case MENU:
                    
                    GameConfig config = menu.getConfig();
                    state = configureGame(config);
                    break;
            
                default:
                    break;
            }
        }
    }

    private ProgramState configureGame(GameConfig config) {

        if (config == null) {
            return ProgramState.TERMINATE;
        }
        
        if (config instanceof PlayConfig) {
            if (executePlay((PlayConfig) config) == null) {
                return ProgramState.MENU;
            };
        } else if (config instanceof SimulateConfig) {
            executeSimulate((SimulateConfig) config);
        } else if (config instanceof TrainConfig) {
            executeTrain((TrainConfig) config);
        } else {
            throw new IllegalArgumentException("Unknown GameConfig type");
        }

        return ProgramState.TERMINATE;
    }

    private ProgramState executePlay(GameConfig config) {
        // Initalise new board object
        Board board = config.getBoard();
        board.printBoard();

        // Assign players
        Player p1 = config.getPlayer1();
        Player p2 = config.getPlayer2();
        Player lastPlayer = null;

        // Run game
        WinType winType = WinType.NOWIN;

        while (winType == WinType.NOWIN) {
            if (takeTurn(p1, board) == null) {
                lastPlayer = p1;
                winType = board.checkWin();

                if (winType == WinType.NOWIN) {
                    if (takeTurn(p2, board) != null) {
                        return ProgramState.MENU;
                    }
                    lastPlayer = p2;
                } else {
                    break;
                }
            } else {
                return ProgramState.MENU;
            }
        }

        if (config instanceof PlayConfig) {
            if (winType == WinType.HORIZONTAL) {
                System.out.println("\nHorizontal Win by player " + lastPlayer.number + "\n");
            } else if (winType == WinType.VERTICAL) {
                System.out.println("\nVertical Win by player " + lastPlayer.number + "\n");
            } else if (winType == WinType.DIAGONALLEFTTORIGHT) {
                System.out.println("\nDiagonal Win: Left to right by player " + lastPlayer.number + "\n");
            } else if (winType == WinType.DIAGONALRIGHTTOLEFT) {
                System.out.println("\nDiagonal Win: Right to left by player " + lastPlayer.number + "\n");
            } else if (winType == WinType.DRAW) {
                System.out.println("\nThis game is a draw!" + "\n");
            }
        }

        return null;
    }

    private void executeSimulate(SimulateConfig config) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeSimulate'");
    }

    private void executeTrain(TrainConfig config) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeTrain'");
    }

    private ProgramState takeTurn(Player player, Board board) {
        String pos = player.play(board);
        if (pos != null) {
            board.placePosition(player.number, pos);
            board.printBoard();
            return null;
        }

        return ProgramState.MENU;
    }
}
