package christmas.message;

public enum Output {

    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    PROMOTION_PROMPT("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("\n<주문 메뉴>\n%s"),
    TOTAL_COST("\n<할인 전 총주문 금액>\n%s"),
    GIFT_PROMOTION("\n<증정 메뉴>\n%s"),
    PROMOTION_LIST("\n<혜택 내역>\n%s"),
    PROMOTION_PRICE("\n<총혜택 금액>\n%s"),
    COST_AFTER_PROMOTION("\n<할인 후 예상 결제 금액>\n%s"),
    BADGE("\n<12월 이벤트 배지>\n%s");

    private final String message;

    Output(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public String toString(String value) {
        return String.format(message, value);
    }
}
