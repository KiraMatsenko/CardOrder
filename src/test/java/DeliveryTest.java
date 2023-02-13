import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @Test
    void shouldSendWithSimpleCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("span[data-test-id=city] input").setValue("Сыктывкар");
        $("span[data-test-id=date] input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue("18.02.2023");
        $("[name=name]").setValue("Чупров Олег");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendWithDoubleCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("span[data-test-id=city] input").setValue("Санкт-Петербург");
        $("span[data-test-id=date] input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue("18.02.2023");
        $("[name=name]").setValue("Чупров Олег");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldSendWithDoubleName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("span[data-test-id=city] input").setValue("Кострома");
        $("span[data-test-id=date] input").sendKeys(Keys.CONTROL, Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue("18.02.2023");
        $("[name=name]").setValue("Петров-Водкин Кузьма");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("span[data-test-id=city] input").setValue("Ка");
        $x("//span[text()='Казань']").click();
        $("[class=input__icon]").click();
        $x("//td[text()='21']").click();
        $("[name=name]").setValue("Чупров Олег");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
