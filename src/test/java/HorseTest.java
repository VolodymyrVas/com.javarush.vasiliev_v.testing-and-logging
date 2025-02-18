import org.junit.jupiter.api.Test;

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




}
