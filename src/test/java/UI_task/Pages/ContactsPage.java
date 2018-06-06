package UI_task.Pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

/**
 * Created by IVlasyuk on 21.04.2018.
 */
public class ContactsPage extends MainHeader {

    String contactsTitle = "//h1[contains(text(),'Контакты')]";
    String nameInput = "//input[@placeholder = 'Имя']";
    String emailInput = "//input[@placeholder = 'E-mail']";
    String messageInput = "//textarea[@placeholder = 'Ваше сообщение']";
    String sendMessageButton = "//div[contains(text(),'ОТПРАВИТЬ')]";

    public ContactsPage checkContactsTitle() {
        assertTrue($x(contactsTitle).is(visible));
        return this;
    }

    public ContactsPage checkRelationWithCorrectEmail() {
        $x(nameInput).setValue("David");
        $x(emailInput).setValue("123@gmail.com");
        $x(messageInput).setValue("Hello");
        $x(sendMessageButton).click();
        assertTrue($x("//div[contains(text(),'Ваше сообщение отправлено! В самое ближайшее время наш менеджер свяжется с вами. Спасибо!')]").is(visible));
        return this;
    }

    public ContactsPage checkRelationWithWrongEmail() {
        $x(nameInput).setValue("David");
        $x(emailInput).setValue("123-gmail.com");
        $x(messageInput).setValue("Hello");
        $x(sendMessageButton).click();
        assertTrue($x("//span[contains(text(),'Поле Email заполнено не верно!')]").is(visible));
        return this;
    }
}
