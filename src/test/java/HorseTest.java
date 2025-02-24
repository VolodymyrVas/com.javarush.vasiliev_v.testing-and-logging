import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HorseTest {

    @Test
    void testConstructorsThrowExceptionIfFirstParameterIsNull() {
        // Проверяем оба конструктора в одном тесте
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.2));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.2, 50.2));
    }

    @Test
    void testConstructorsThrowExceptionWithCorrectMessageIfFirstParameterIsNull() {
        // Проверяем второй конструктор так как у него есть выброс exceptions
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                          () -> new Horse(null, 7.7, 51.2)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "   ", "\r\n", "\r"})
    void testConstructorThrowsExceptionForBlankStrings(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(invalidName, 2.2));
        assertThrows(IllegalArgumentException.class, () -> new Horse(invalidName, 2.2, 50.2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "   ", "\r\n", "\r"})
    void testConstructorsThrowExceptionWithCorrectMessageIfFirstParameterIsBlankString(String invalidName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(invalidName, 2.2, 50.2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100, -999}) // Передаём разные отрицательные числа
    void testConstructorThrowsExceptionForNegativeNumbers(int invalidNumber) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("BlackBlade", invalidNumber));
    }

    @Test
    void testConstructorsThrowExceptionWithCorrectMessageIfSecondParameterIsNegativeNumbers() {
        // Проверяем второй конструктор так как у него есть выброс exceptions вот теперь точно есть ощущение что както это все можно пособирать в кучу!!!
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                          () -> new Horse("BlackBlade", -7.7, 51.2)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100, -999}) // Передаём разные отрицательные числа
    void testConstructorThrowsExceptionForNegativeNumbersForThirdParameter(int invalidNumber) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("BlackBlade", 5.5, invalidNumber));
    }

    @Test
    void testConstructorsThrowExceptionWithCorrectMessageIfThirdParameterIsNegativeNumbers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                          () -> new Horse("BlackBlade", 7.7, -51.2)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void testGetNameReturnsCorrectValue() {
        String expectedName = "Thunderbolt";
        Horse horseFirstConstructor = new Horse(expectedName, 5.0, 10.0);
        Horse horseSecondConstructor = new Horse(expectedName, 5.0, 10.0);

        assertEquals(expectedName, horseFirstConstructor.getName());
        assertEquals(expectedName, horseSecondConstructor.getName());
    }

    @Test
    void testGetSpeedReturnsCorrectValue() {
        double expectedSpeed = 5.5;
        Horse horseFirstConstructor = new Horse("Thunderbolt", expectedSpeed, 100.12);
        Horse horseSecondConstructor = new Horse("Thunderbolt", expectedSpeed);

        assertEquals(expectedSpeed, horseFirstConstructor.getSpeed());
        assertEquals(expectedSpeed, horseSecondConstructor.getSpeed());
    }

    @Test
    void testGetDistanceReturnsCorrectValue() {
        double expectedDistance = 150.5;
        Horse horse = new Horse("Thunderbolt", 5.0, expectedDistance);

        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    void testGetDistanceReturnsZeroIfCreatedWithTwoParameters() {
        Horse horse = new Horse("Thunderbolt", 5.0);

        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void testMoveCallsGetRandomDoubleWithCorrectParameters() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Thunderbolt", 5.0, 10.0);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 10.0, 0.5",
            "7.2, 20.0, 0.8",
            "3.5, 0.0, 0.3"
    })
    void testMoveUpdatesDistanceCorrectly(double speed, double initialDistance, double randomValue) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            // Указываем, что getRandomDouble(0.2, 0.9) всегда возвращает randomValue
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            // Создаём объект Horse с обычным конструктором (без изменений в классе)
            Horse horse = new Horse("Thunderbolt", speed, initialDistance);

            // Вызываем move()
            horse.move();

            // Ожидаемое значение distance по формуле: distance + speed * randomValue
            double expectedDistance = initialDistance + speed * randomValue;

            // Проверяем, что distance обновился правильно
            assertEquals(expectedDistance, horse.getDistance());
        }
    }

}
