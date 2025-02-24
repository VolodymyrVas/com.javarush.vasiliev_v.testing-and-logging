import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS) // Ограничение на 22 секунды
    @Disabled// Тест отключён, можно включить вручную при необходимости
    void testMainExecutesWithin22Seconds() {
        String[] args = {}; // Если main требует аргументы
        try {
            Main.main(args); // Запуск метода main
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
