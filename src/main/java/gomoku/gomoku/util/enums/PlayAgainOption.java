package gomoku.gomoku.util.enums;

public enum PlayAgainOption {
    MENU(0), YES(1), NO(2);

    private final int value;

    PlayAgainOption(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static PlayAgainOption fromInt(int value) {
        for (PlayAgainOption o : PlayAgainOption.values()) {
            if (o.value == value) {
                return o;
            }
        }

        return null;
    }
}
