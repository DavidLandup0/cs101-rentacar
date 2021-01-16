package com.metropolitan.rentacar.domain.enums;

public enum CAR_COLOR {
    WHITE("Bela"), BLACK("Crna"), RED("Crvena");

    public final String label;

    CAR_COLOR(String label) {
        this.label = label;
    }

    /**
     * Value of label heating source.
     *
     * @param label the label
     * @return the heating source
     */
    public static CAR_COLOR valueOfLabel(String label) {
        for (CAR_COLOR cc : values()) {
            if (cc.label.equals(label)) {
                return cc;
            }
        }
        return null;
    }
}
