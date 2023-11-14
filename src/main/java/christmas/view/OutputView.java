package christmas.view;

import christmas.message.Output;
import christmas.user.domain.Order;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class OutputView {

    public void printOrder(Order order) {
        String menu = order.toString();
        System.out.println(Output.ORDERED_MENU.toString(menu));
    }

    public void printTotalPrice(Order order) {
        String price = formattingPrice(order.getPrice());
        System.out.println(Output.TOTAL_COST.toString(price));
    }

    private String formattingPrice(BigDecimal price) {
        String formattedPrice = NumberFormat.getInstance(Locale.KOREA).format(price);
        return formattedPrice + "원";
    }
}
