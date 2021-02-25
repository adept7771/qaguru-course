package tests;

import helpers.TestConfigurator;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testSteps.AuthorisationSteps;
import testSteps.CartSteps;
import testSteps.NavigationSteps;
import testSteps.SearchSteps;

public class SteamTest extends TestConfigurator {

    final String owner = "Dmitry Potapov";
    final String url = "https://store.steampowered.com/";
    final NavigationSteps navigationSteps = new NavigationSteps();
    final SearchSteps searchSteps = new SearchSteps();
    final CartSteps cartSteps = new CartSteps();
    final AuthorisationSteps authorisationSteps = new AuthorisationSteps();

    @Test
    @DisplayName("Проверить, что стим может быть установлен")
    @Feature("Issues")
    @Story("Навигация по магазину")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void steamCanBeInstalledTest() {
        navigationSteps.openStartPage();
        navigationSteps.installSteam();
        navigationSteps.checkSteamCanBeInstalled();
    }

    @Test
    @DisplayName("Поиск выдает хотя бы 1 совпадение искомого запроса")
    @Feature("Issues")
    @Story("Поиск по магазину")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void searchGameTest() {
        navigationSteps.openStartPage();
        String gameName = "Bannerlord";
        searchSteps.searchInSearchInput(gameName);
        searchSteps.checkSearchResultsForOneMatch(gameName);
    }

    @Test
    @DisplayName("Все языки в выпадающем списке уникальны")
    @Feature("Issues")
    @Story("Навигация по магазину")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkAllLanguagesIsUniqueTest() {
        navigationSteps.openStartPage();
        navigationSteps.languagesMenuEnter();
        navigationSteps.languageMenuUniqueCheck();
    }

    @Test
    @DisplayName("Счетчик товаров в корзине изменяется")
    @Feature("Issues")
    @Story("Проверка корзины")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkCartCounterTest() {
        navigationSteps.openStartPage();
        String gameName = "Bannerlord";
        searchSteps.searchInSearchInput(gameName);
        cartSteps.addFirstItemToCart();
        cartSteps.checkCartIcon("1");
    }

    @Test
    @DisplayName("Вход с не корректными регистрационными данными")
    @Feature("Issues")
    @Story("Работа логина")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void incorrectSignInTest() {
        navigationSteps.openStartPage();
        authorisationSteps.goToLoginPage();
        authorisationSteps.loginWithData("wrong_login", "wrong_password");
        authorisationSteps.checkWrongCredsNotification();
    }

    @Test
    @DisplayName("Тест, который всегда падает")
    @Feature("Issues")
    @Story("Работа логина")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void alwaysFallTest() {
        Assertions.fail("Этот тест всегда падает и должен просто существовать в отчете.");
    }
}