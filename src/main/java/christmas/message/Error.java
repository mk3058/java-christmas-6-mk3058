package christmas.message;

public enum Error {

    INVALUD_DATE("유효하지 않은 날짜입니다."),
    INVALUD_ORDER("유효하지 않은 주문입니다."),
    NOT_A_NUMBER("숫자 형식이 아닙니다."),
    MAXIMUM_ORDER_COUNT_EXCEEDED("메뉴는 한번에 최대 20개 까지 주문할 수 있습니다."),
    ORDER_ITEM_COMPOSED_OF_BEVERAGE("음료만 주문할 수 없습니다.");

    private final static String PREFIX = "[ERROR] ";
    private final static String POSTFIX = " 다시 입력해 주세요.";
    private final String message;

    Error(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return PREFIX + message + POSTFIX;
    }
}
