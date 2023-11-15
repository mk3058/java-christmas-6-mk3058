package christmas.menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.menu.domain.Appetizer;
import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import christmas.menu.presentation.dto.MenuResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuServiceTest {

    private MenuService menuService;

    @BeforeEach
    void setup() {
        menuService = new MenuService();
    }

    @Test
    @DisplayName("findItemsByCategory: 카테고리가 주어졌을 때 알맞은 메뉴가 반환된다")
    void findItemsByCategory() {
        MenuCategory category = MenuCategory.MAIN;
        List<String> expected = category.menus().stream()
                .map(MenuItem::getName)
                .toList();
        List<String> actual = menuService.findItemsByCategory(category).stream()
                .map(MenuResponse::name)
                .toList();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("findAllItems: 모든 메뉴가 반환된다")
    void findAllItems() {
        List<String> expected = MenuCategory.allMenus().stream()
                .map(MenuItem::getName)
                .toList();
        List<String> actual = menuService.findAllItems().stream()
                .map(MenuResponse::name)
                .toList();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("findItemByName: 이름을 입력했을 때 알맞은 메뉴가 반환된다")
    void findItemByName() {
        String name = "타파스";
        MenuItem expected = Appetizer.TAPAS;

        assertThat(menuService.findItemByName(name).name()).isEqualTo(expected.getName());
    }

    @Test
    @DisplayName("findItemByName: 존재하지 않는 이름을 입력했을 때 예외가 발생한다")
    void findItemByName_invalid_name() {
        String name = "없는 메뉴";

        assertThatThrownBy(() -> menuService.findItemByName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}