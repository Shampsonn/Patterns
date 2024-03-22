import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    public static String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[]{"Ярославль", "Якутск", "Москва", "Смоленск", "Челябинск", "Чебоксанры", "Уфа",
                "Брянск", "Кызыл", "Вологда", "Мурманск", "Тула", "Липецк", "Омск", "Рязань", "Тюмень"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();

    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Reg {
        private Reg() {
        }


        public static GenerateUser person(String locale) {
            return new GenerateUser(generateCity(), generateName("ru"), generatePhone("ru"));
        }
    }

    @Value
    public static class GenerateUser {
        String city;
        String name;
        String phone;

    }
}

