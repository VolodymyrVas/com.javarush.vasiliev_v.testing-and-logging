import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testConstructorThrowsExceptionForNegativeNumbersforThirdParameter(int invalidNumber) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("BlackBlade", 5.5, invalidNumber));
    }

    @Test
    void testConstructorsThrowExceptionWithCorrectMessageIfThirdParameterIsNegativeNumbers() {
        // Проверяем второй конструктор так как у него есть выброс exceptions вот теперь точно есть ощущение что както это все можно пособирать в кучу!!!
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                          () -> new Horse("BlackBlade", 7.7, -51.2)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
}
