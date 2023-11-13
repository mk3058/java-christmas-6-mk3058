package christmas.message;

public enum Input {

    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    DATE_INPUT_PROMPT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_INPUT_PROMPT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PROMOTION_PROMPT("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("<주문 메뉴>\n%s"),
    TOTAL_COST("<할인 전 총주문 금액>\n%s"),
    GIFT_PROMOTION("<증정 메뉴>\n%s"),
    PROMOTION_LIST("<혜택 내역>\n%s"),
    PROMOTION_PRICE("<총혜택 금액>\n%s"),
    COST_AFTER_PROMOTION("<할인 후 예상 결제 금액>\n%s"),
    BADGE("<12월 이벤트 배지>\n%s");

    private final String message;

    Input(String message) {
        this.message = message;
    }

    public String toString(String value) {
        return String.format(message, value);
    }

    @Override
    public String toString() {
        return message;
    }
}
