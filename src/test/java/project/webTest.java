package project;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class webTest {

    @Test
    void checkSteamInterface() {
        open("https://store.steampowered.com/");
        $x("//a[@data-tooltip-content='.submenu_store' and contains(text(), 'Store') " +
                "or @data-tooltip-content='.submenu_store' and contains(text(), 'Магазин')]")
                .shouldBe(Condition.appears);
    }
}
