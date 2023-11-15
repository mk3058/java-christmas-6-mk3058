package christmas.menu.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuCategoryTest {

    @Test
    @DisplayName("allMenus: 모든 메뉴 검색에 성공한다")
    void allMenus() {
        Set<MenuItem> menuItems = new HashSet<>(MenuCategory.allMenus());
        int originMenuItemCount = menuItems.size();

        menuItems.addAll(List.of(Appetizer.values()));
        menuItems.addAll(List.of(Beverage.values()));
        menuItems.addAll(List.of(Desert.values()));
        menuItems.addAll(List.of(Main.values()));
        assertThat(originMenuItemCount).isEqualTo(menuItems.size());
    }

    @Test
    @DisplayName("findMenuItemByName: 이름으로 메뉴 검색에 성공한다")
    void findMenuItemByName() {
        List<String> names = MenuCategory.allMenus().stream()
                .map(MenuItem::getName)
                .toList();

        names.forEach(name -> {
            assertThat(MenuCategory.findMenuItemByName(name).getName()).isEqualTo(name);
        });
    }

    @Test
    @DisplayName("findCategoryByMenuItem: MenuItem으로 Category 검색에 성공한다")
    void findCategoryByMenuItem() {
        List<MenuItem> items = MenuCategory.allMenus();

        items.forEach(item -> {
            assertThat(MenuCategory.findCategoryByMenuItem(item).menus().contains(item)).isTrue();
        });
    }
}