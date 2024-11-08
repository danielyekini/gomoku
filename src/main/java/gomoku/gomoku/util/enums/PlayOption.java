package gomoku.gomoku.util.enums;

public enum PlayOption {
    USER_VS_USER(1), USER_VS_CPU(2), BACK(3);

    private final int value;

    PlayOption(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}