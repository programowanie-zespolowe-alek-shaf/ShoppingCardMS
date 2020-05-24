package pl.agh.shopping.card.common.util;

public enum FieldName {

    USERNAME("username");

    private final String name;

    FieldName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
