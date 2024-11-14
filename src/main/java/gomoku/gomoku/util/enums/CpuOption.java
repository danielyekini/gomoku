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

    public static CpuOption fromInt(int value) {
        for (CpuOption o : CpuOption.values()) {
            if (o.value == value) {
                return o;
            }
        }

        return null;
    }
}
