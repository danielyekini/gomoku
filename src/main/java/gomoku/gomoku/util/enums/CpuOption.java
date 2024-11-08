package gomoku.gomoku.util.enums;

public enum CpuOption {
    RANDOM(1), PROXIMITY(2), BACK(3);

    private final int value;

    CpuOption(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
