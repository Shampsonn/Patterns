import com.codeborne.selenide.Condition;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {


    @Test
    void ShouldBeDeliverySuccess() {
        DataGenerator.GenerateUser user = new DataGenerator.GenerateUser();
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(user.city);
        String planningDate = DataGenerator.generateDate(5, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue(user.name);
        $("[data-test-id='phone'] input").setValue(user.phone);
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        planningDate = DataGenerator.generateDate(10, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("button.button").click();
        $$(".button__content").find(Condition.exactText("Перепланировать")).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + planningDate));


    }
}

