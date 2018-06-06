package UI_task.Pages;

import com.codeborne.selenide.collections.ExactTexts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

/**
 * Created by IVlasyuk on 21.04.2018.
 */
public class PriceSheetPage extends MainHeader {

    String pricesTitle = "//span[contains(text(),'Цены на натяжные потолки')]";
    String firstColumnOfTable = "#contact > div:nth-child(4) > table td:nth-child(1)";
    String secondColumnOfTable = "#contact > div:nth-child(4) > table td:nth-child(2)";
    String thirdColumnOfTable = "#contact > div:nth-child(4) > table td:nth-child(3)";
    String callGaugerLink = "//span[contains(text(),'Вызывайте замерщика »')]";

    public PriceSheetPage checkPricesTitle(){
        assertTrue($x(pricesTitle).is(visible));
        return this;
    }

    public PriceSheetPage checkFirstTable() {
        $$(firstColumnOfTable).shouldHave(
            new ExactTexts("Что входит", "Полотно", "Монтаж", "Разводка электричества", "Выезд замерщика", "Доставка бригады и материалов"));
        $$(secondColumnOfTable).shouldHave(
                new ExactTexts("Другие компании", " ✔", "-", "-", " ✔", "-"));
        $$(thirdColumnOfTable).shouldHave(
                new ExactTexts("«Потолкоff58»", "  ✔ ", " ✔", " ✔", " ✔", " ✔"));
        return this;
    }

    public PriceSheetPage checkCallGaugerLink() {
        assertTrue($x(callGaugerLink).is(visible));
        return this;
    }
}
