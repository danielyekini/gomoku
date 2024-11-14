package gomoku.gomoku.util.enums;

public enum MainOption {
    MAIN(0), PLAY(1), SIMULATE(2), TRAIN(3), EXIT(4);

    private final int value;

    MainOption(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static MainOption fromInt(int value) {
        for (MainOption o : MainOption.values()) {
            if (o.value == value) {
                return o;
            }
        }

        return null;
    }
}
