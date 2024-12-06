package gomoku.gomoku.Controller;
import gomoku.gomoku.Model.Board;
import gomoku.gomoku.Model.Players.Player;
import gomoku.gomoku.util.Menu;
import gomoku.gomoku.util.PlayerResponse;
import gomoku.gomoku.util.configure.GameConfig;
import gomoku.gomoku.util.configure.PlayConfig;
import gomoku.gomoku.util.configure.SimulateConfig;
import gomoku.gomoku.util.configure.TrainConfig;
import gomoku.gomoku.util.enums.PlayState;
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

    private PlayState executePlay(GameConfig config) {
        // Initalise new board object
        Board board = config.getBoard();
        board.printBoard();

        // Assign players
        Player p1 = config.getPlayer1();
        Player p2 = config.getPlayer2();
        Player lastPlayer = null;

        // Run game
        WinType win = WinType.NOWIN;

        while (win == WinType.NOWIN) {
            // Player 1 turn
            switch (takeTurn(p1, board)) {
                case NEWGAME -> {
                    // Check for new game request
                    return PlayState.NEWGAME;
                }
                case MENU -> {
                    // Check for menu request
                    return PlayState.MENU;
                }

                default -> {
                    // TRYNEXTTURN
                    // End game if player wins
                    lastPlayer = p1;
                    win = board.checkWin();
                    
                    if (win != WinType.NOWIN) { 
                        break;
                    }
                    
                    // Player 2 turn
                    switch (takeTurn(p2, board)) {
                        case NEWGAME -> {
                            // Check for new game request
                            return PlayState.NEWGAME;
                    }
                        case MENU -> {
                            // Check for menu request
                            return PlayState.MENU;
                    }
                    
                        default -> {
                            // TRYNEXTTURN
                            lastPlayer = p2;
                            win = board.checkWin();
                    }
                    }
                }
            }
        }

        if (config instanceof PlayConfig) {
            switch (win) {
                case HORIZONTAL:
                    System.out.println("\nHorizontal Win by player " + lastPlayer.number + "\n");
                    break;
                case VERTICAL:
                    System.out.println("\nVertical Win by player " + lastPlayer.number + "\n");
                    break;
                case DIAGONALLEFTTORIGHT:
                    System.out.println("\nDiagonal Win: Left to right by player " + lastPlayer.number + "\n");
                    break;
                case DIAGONALRIGHTTOLEFT:
                    System.out.println("\nDiagonal Win: Right to left by player " + lastPlayer.number + "\n");
                    break;
                case DRAW:
                    System.out.println("\nThis game is a draw!" + "\n");
                    break;
                default:
                    break;
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

    private PlayState takeTurn(Player player, Board board) {

        PlayerResponse response = player.play(board);

        switch (response.type) {
            case NEWGAME -> {
                return response.type;
            }
            case MENU -> {
                return response.type;
            }
            default -> {
                board.placePosition(player.number, response.getPos());
                board.printBoard();
                return response.type;
            }
        }
    }
}
