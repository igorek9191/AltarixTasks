package UI_task.Pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertTrue;

/**
 * Created by IVlasyuk on 21.04.2018.
 */
public class CalculatorPage extends MainHeader {

    String calculatorTitle = "//h1[contains(text(),'Онлайн-калькулятор')]";
    String canvasTypeSelect = "#sel_par";
    String photoPrintingCheckYes = "#check3";
    String photoPrintingCheckNo = "#check4";
    String ceilingAreaInput = "#fl3 > input";
    String photoSizeInput = "#fl5 > input";
    String anglesNumberInput = "#fl6 > input";
    String pipesNumberInput = "#fl7 > input";
    String chandeliersNumberInput = "#fl8 > input";
    String lampsNumberInput = "#fl9 > input";
    String curtainLengthInput = "#fl10 > input";
    String submitButton = "div#submit_but";
    String calculateResult = "#itogo";

    public CalculatorPage checkCalculatorTitle(){
        assertTrue($x(calculatorTitle).is(visible));
        return this;
    }

    public CalculatorPage calcPriceWithPhotoPrinting() {
        $(canvasTypeSelect).selectOption("Матовый");
        $(photoPrintingCheckYes).click();
        $(ceilingAreaInput).setValue("10");
        $(photoSizeInput).setValue("10");
        $(anglesNumberInput).setValue("4");
        $(pipesNumberInput).setValue("1");
        $(chandeliersNumberInput).setValue("1");
        $(lampsNumberInput).setValue("2");
        $(curtainLengthInput).setValue("3");
        $(submitButton).click();
        assertTrue($(calculateResult).getText().contains("10730"));
        return this;
    }

    public CalculatorPage calcPriceWithoutPhotoPrinting() {
        $(canvasTypeSelect).selectOption("Матовый");
        $(photoPrintingCheckNo).click();
        $(ceilingAreaInput).setValue("10");
        $(anglesNumberInput).setValue("4");
        $(pipesNumberInput).setValue("1");
        $(chandeliersNumberInput).setValue("1");
        $(lampsNumberInput).setValue("2");
        $(curtainLengthInput).setValue("3");
        $(submitButton).click();
        assertTrue($(calculateResult).getText().contains("4730"));
        return this;
    }


}
