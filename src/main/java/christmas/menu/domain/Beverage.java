package christmas.menu.domain;

import java.math.BigDecimal;

public enum Beverage implements MenuItem {

    ZERO_COKE("제로콜라", new BigDecimal(3_000)),
    RED_WINE("레드와인", new BigDecimal(6_000)),
    CHAMPAGNE("샴페인", new BigDecimal(25_000));

    private final String name;
    private final BigDecimal price;

    Beverage(String name, BigDecimal price) {
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
}
