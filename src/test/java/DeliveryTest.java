import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSendWithSimpleCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:7777/");
        $("span[data-test-id=city] input").setValue("Сыктывкар");
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue(generateDate(4));
        $("[name=name]").setValue("Чупров Олег");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(4)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    @Test
    void shouldSendWithDoubleCity() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:7777/");
        $("span[data-test-id=city] input").setValue("Санкт-Петербург");
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue(generateDate(4));
        $("[name=name]").setValue("Чупров Олег");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(4)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    @Test
    void shouldSendWithDoubleName() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:7777/");
        $("span[data-test-id=city] input").setValue("Кострома");
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id=date] input").setValue(generateDate(4));
        $("[name=name]").setValue("Петров-Водкин Кузьма");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(4)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    @Test
    void optionalTest() {
        String date = "'" + LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("d")) + "']";
        Configuration.holdBrowserOpen = true;
        open("http://localhost:7777/");
        $("span[data-test-id=city] input").setValue("Ка");
        $x("//span[text()='Казань']").click();
        $("[class=input__icon]").click();
        if (!LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("MM")).equals(LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("MM")))) {
            $x("//div[@class='calendar__arrow calendar__arrow_direction_right']").click();
        }
        $x("//td[text()=" + date).click();
        $("[name=name]").setValue("Чупров Олег");
        $("[name=phone]").setValue("+79111111111");
        $x("//span[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//span[text()='Забронировать']").click();
        $x("//div[text()='Успешно!']").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(7)), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
