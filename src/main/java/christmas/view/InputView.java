package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {

    private static final String MENU_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";

    public int getDate() {
        System.out.println(InputMessage.DATE_INPUT_PROMPT.toString());
        String input = Console.readLine();
        int date = toInteger(input);
        if (!hasCorrectRange(date, 1, 31)) {
            throw new IllegalArgumentException(ErrorMessage.INVALUD_DATE.toString());
        }
        return date;
    }

    public Map<String, Integer> getMenus() {
        System.out.println(InputMessage.ORDER_INPUT_PROMPT.toString());
        String input = Console.readLine();
        return parseMenu(input);
    }

    private Map<String, Integer> parseMenu(String input) {
        Map<String, Integer> orders = new HashMap<>();
        List<String> menuTokens = StringUtil.split(input, MENU_DELIMITER);

        if (menuTokens.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALUD_ORDER.toString());
        }
        menuTokens.forEach(menuToken -> {
            List<String> order = StringUtil.split(menuToken, QUANTITY_DELIMITER);
            if (!hasCorrectSize(order, 2)) {
                throw new IllegalArgumentException(ErrorMessage.INVALUD_ORDER.toString());
            }
            orders.put(order.get(0), toInteger(order.get(1)));
        });
        return orders;
    }

    private boolean hasCorrectSize(List<String> tokens, int expected) {
        return tokens.size() == expected;
    }

    private boolean hasCorrectRange(int num, int min, int max) {
        return min <= num && num <= max;
    }

    private int toInteger(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_A_NUMBER.toString());
        }
    }
}
