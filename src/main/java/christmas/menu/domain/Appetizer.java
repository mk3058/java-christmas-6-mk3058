package christmas.menu.domain;

import java.math.BigDecimal;

public enum Appetizer implements MenuItem {

    BUTTON_MUSHROOM_SOUP("양송이수프", new BigDecimal(6_000)),
    TAPAS("타파스", new BigDecimal(5_500)),
    CAESAR_SALAD("시저샐러드", new BigDecimal(8_000));

    private final String name;
    private final BigDecimal price;

    Appetizer(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
