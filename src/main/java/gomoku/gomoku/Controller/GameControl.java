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
        GameConfig config;
        while (state != ProgramState.TERMINATE) {
            switch (state) {
                case MENU:
                    config = menu.getConfig();
                    state =  (config != null) ? 
                        configureGame(config) :
                        ProgramState.TERMINATE;
                    break;

                case ENDGAME:
                    config = menu.playAgain();
                    state =  (config != null) ? 
                        configureGame(config) :
                        ProgramState.MENU;
                    break;

                default:
                    break;
            }
        }
    }

    private ProgramState configureGame(GameConfig config) {
        
        if (config instanceof PlayConfig) {
            PlayState playState = PlayState.NEWGAME;
            while (playState == PlayState.NEWGAME) {
                playState = executePlay((PlayConfig) config);
            }

            switch (playState) {
                case MENU:
                    return ProgramState.MENU;
            
                case ENDGAME:
                    return ProgramState.ENDGAME;
                default:
                    break;
            }
            
        } else if (config instanceof SimulateConfig) {
            executeSimulate((SimulateConfig) config);
        } else if (config instanceof TrainConfig) {
            executeTrain((TrainConfig) config);
        } else {
            throw new IllegalArgumentException("Unknown GameConfig type");
        }

        return ProgramState.TERMINATE;
    }

    private PlayState executePlay(PlayConfig config) {
        // Initalise new board object
        Board board = config.initializeBoard();
        board.printBoard();

        // Initialise players
        Player p1 = config.initializePlayer1();
        Player p2 = config.initializePlayer2();
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
        
        switch (win) {
            case HORIZONTAL:
                System.out.println("\nHorizontal Win by player " + lastPlayer.getNumber() + "\n");
                break;
            case VERTICAL:
                System.out.println("\nVertical Win by player " + lastPlayer.getNumber() + "\n");
                break;
            case DIAGONALLEFTTORIGHT:
                System.out.println("\nDiagonal Win: Left to right by player " + lastPlayer.getNumber() + "\n");
                break;
            case DIAGONALRIGHTTOLEFT:
                System.out.println("\nDiagonal Win: Right to left by player " + lastPlayer.getNumber() + "\n");
                break;
            case DRAW:
                System.out.println("""
                    This game is a draw!
                """);
                break;
            default:
                break;
        }

        

        return PlayState.ENDGAME;
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
                board.placePosition(player.getNumber(), response.getPos());
                printLine();
                System.out.println("\nPlayer " + player.getNumber() + "'s Move: " + response.getPos());
                board.printBoard();
                return response.type;
            }
        }
    }

    private void printLine() {
        System.out.println("_".repeat(50));
    }
}
