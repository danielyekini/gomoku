package gomoku.gomoku.util;

import gomoku.gomoku.util.enums.PlayState;

public class PlayerResponse {
    public PlayState type;
    private String move;

    public PlayerResponse(PlayState type) {
        this.type = type;
        this.move = null;
    }

    public PlayerResponse(PlayState response, String move) {
        this.type = response;
        this.move = move;
    }

    public String getPos() {
        return move;
    }
}
