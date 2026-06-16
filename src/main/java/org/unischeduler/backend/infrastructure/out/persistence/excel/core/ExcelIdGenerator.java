package org.unischeduler.backend.infrastructure.out.persistence.excel.core;

import org.odftoolkit.simple.table.Table;

public class ExcelIdGenerator {

    public static String generateNextId(Table table, int idColumnIndex) {
        int max = 0;
        int rowCount = table.getRowCount();

        for (int i = 1; i < rowCount; i++) { // skip header
            try {
                String value = table.getCellByPosition(idColumnIndex, i).getStringValue();

                if (value != null && !value.isEmpty()) {
                    int id = Integer.parseInt(value.trim());
                    max = Math.max(max, id);
                }

            } catch (NumberFormatException ignored) {
                // ignora filas corruptas o no numéricas
            } catch (Exception ignored) {
                // seguridad extra por celdas null o raras
            }
        }

        return String.valueOf(max + 1);
    }
}