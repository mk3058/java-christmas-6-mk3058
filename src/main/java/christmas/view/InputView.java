package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.message.Error;
import christmas.message.Input;
import christmas.util.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {

    private static final String MENU_DELIMITER = ",";
    private static final String QUANTITY_DELIMITER = "-";

    public int getDate() {
        System.out.println(Input.DATE_INPUT_PROMPT.toString());
        String input = Console.readLine();
        return toInteger(input);
    }

    public Map<String, Integer> getMenus() {
        System.out.println(Input.ORDER_INPUT_PROMPT.toString());
        String input = Console.readLine();
        return parseMenu(input);
    }

    private Map<String, Integer> parseMenu(String input) {
        Map<String, Integer> orders = new HashMap<>();
        List<String> menuTokens = StringUtil.split(input, MENU_DELIMITER);

        if (menuTokens.isEmpty()) {
            throw new IllegalArgumentException(Error.INVALUD_ORDER.toString());
        }
        menuTokens.forEach(menuToken -> {
            List<String> order = StringUtil.split(menuToken, QUANTITY_DELIMITER);
            if (!hasCorrectSize(order, 2)) {
                throw new IllegalArgumentException(Error.INVALUD_ORDER.toString());
            }
            orders.put(order.get(0), toInteger(order.get(1)));
        });
        return orders;
    }

    private boolean hasCorrectSize(List<String> tokens, int expected) {
        return tokens.size() == expected;
    }

    private int toInteger(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Error.NOT_A_NUMBER.toString());
        }
    }
}
