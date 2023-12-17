package persistence.services;

public enum ColumnName {

    USER_NAME("UserName");

    private final String columnName;

    ColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}