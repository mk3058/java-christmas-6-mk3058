package christmas.menu.domain;

import java.math.BigDecimal;

public enum Main implements MenuItem {

    T_BONE_STEAK("티본스테이크", new BigDecimal(55_000)),
    BARBECUE_RIBS("바비큐립", new BigDecimal(54_000)),
    SEAFOOD_PASTA("해산물파스타", new BigDecimal(35_000)),
    CHRISTMAS_PASTA("크리스마스파스타", new BigDecimal(25_000));

    private final String name;
    private final BigDecimal price;

    Main(String name, BigDecimal price) {
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
