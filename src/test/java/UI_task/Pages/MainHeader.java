package UI_task.Pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

/**
 * Created by IVlasyuk on 20.04.2018.
 */
public class MainHeader {

    String mainLink = "//a[contains(text(),'ГЛАВНАЯ')]";
    String fotogaleryLink = "//a[contains(text(),'ФОТОГАЛЕРЕЯ')]";
    String priceSheetLink =  "//a[contains(text(),'ПРАЙС-ЛИСТ')]";
    String calculatorLink = "//a[contains(text(),'КАЛЬКУЛЯТОР')]";
    String illuminatorsLink = "//a[contains(text(),'СВЕТИЛЬНИКИ И ЛЮСТРЫ')]";
    String articlesLink = "//a[contains(text(),'ПОЛЕЗНЫЕ СТАТЬИ')]";
    String yourQuestionsLink = "//a[contains(text(),'ВАШИ ВОПРОСЫ')]";
    String contactsLink = "//a[contains(text(),'КОНТАКТЫ')]";

    public MainHeader checkMenu(){
        assertTrue($x(mainLink).is(visible));
        assertTrue($x(fotogaleryLink).is(visible));
        assertTrue($x(priceSheetLink).is(visible));
        assertTrue($x(calculatorLink).is(visible));
        assertTrue($x(illuminatorsLink).is(visible));
        assertTrue($x(articlesLink).is(visible));
        assertTrue($x(yourQuestionsLink).is(visible));
        assertTrue($x(contactsLink).is(visible));
        return this;
    }

    public FotoGaleryPage goToFotoGalery(){
        $x(fotogaleryLink).click();
        return new FotoGaleryPage();
    }

    public PriceSheetPage goToPriceSheetPage() {
        $x(priceSheetLink).click();
        return new PriceSheetPage();
    }

    public CalculatorPage goToCalculatorPage() {
        $x(calculatorLink).click();
        return new CalculatorPage();
    }

    public ContactsPage goToContactsPage() {
        $x(contactsLink).click();
        return new ContactsPage();
    }
}
