package christmas.menu.domain;

import java.math.BigDecimal;

public enum Desert implements MenuItem {

    CHOCOLATE_CAKE("초코케이크", new BigDecimal(15_000)),
    ICE_CREAM("아이스크림", new BigDecimal(5_000));

    private final String name;
    private final BigDecimal price;

    Desert(String name, BigDecimal price) {
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
