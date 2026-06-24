package org.unischeduler.ui.viewmodel.schedule;

public enum DayOfWeek {

    DOMINGO(1),
    LUNES(2),
    MARTES(3),
    MIERCOLES(4),
    JUEVES(5),
    VIERNES(6),
    SABADO(7);

    private final int columnIndex;

    DayOfWeek(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int columnIndex() {
        return columnIndex;
    }
}