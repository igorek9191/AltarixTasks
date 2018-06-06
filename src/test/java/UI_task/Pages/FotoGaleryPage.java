package UI_task.Pages;

import com.codeborne.selenide.collections.ExactTexts;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class FotoGaleryPage extends MainHeader {

    String fotoGaleryTitle = "//div[contains(text(),'Фотогалерея')]";

    String typesOfCeilingsButton = "//div[contains(text(),'ВИДЫ ПОТОЛКОВ')]";
    String subsectionsLinks_TypesOfCellings = "div#catalog_cat > div div:nth-child(2) a";

    String roomTypeButton = "//div[contains(text(),'ТИП ПОМЕЩЕНИЯ')]";
    String subsectionsLinks_RoomType = "div#catalog_cat a";

    String chandeliersAndLampsButton = "//div[contains(text(),'ЛЮСТРЫ И СВЕТИЛЬНИКИ')]";
    String directoryOfColorsButton = "//div[contains(text(),'КАТАЛОГ ЦВЕТОВ И ФАКТУР')]";

    public FotoGaleryPage checkFotoGaleryTitle() {
        assertTrue($x(fotoGaleryTitle).is(visible));
        return this;
    }

    public FotoGaleryPage checkTypesOfCeilingsIsActive() {
        assertTrue($x(typesOfCeilingsButton).has(attribute("style")));
        return this;
    }

    public FotoGaleryPage checkSubsections_TypesOfCellings() {
        $$(subsectionsLinks_TypesOfCellings).shouldHave(
                new ExactTexts("Одноуровневые потолки","Двухуровневые потолки","Тканевые потолки","\"Парящие потолки\"","Криволинейная спайка потолка","Фотопечать"));
        return this;
    }

    public FotoGaleryPage checkRoomTypeWithSubsections() {
        $x(roomTypeButton).click();
        assertTrue($x(roomTypeButton).has(attribute("style")));
        $$(subsectionsLinks_RoomType).shouldHave(
                new ExactTexts("Квартиры, частные дома", "Офисные помещения"));
        return this;
    }

    public FotoGaleryPage checkChandeliersAndLampsIsActive() {
        $x(chandeliersAndLampsButton).click();
        assertTrue($x(chandeliersAndLampsButton).has(attribute("style")));
        return this;
    }

    public FotoGaleryPage checkDirectoryOfColorsIsActive() {
        $x(directoryOfColorsButton).click();
        assertTrue($x(directoryOfColorsButton).has(attribute("style")), "Проверка что активности каталога цветов");
        return this;
    }

}
