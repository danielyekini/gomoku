package gomoku.gomoku.util.enums;

public enum PlayerOption {
    PLAYER_1(1), PLAYER_2(2), BACK(3);
    
    private final int value;

    PlayerOption(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
