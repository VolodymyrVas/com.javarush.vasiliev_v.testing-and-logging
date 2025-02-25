
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



//@PrepareForTest(Horse.class) // Указываем, что будем мокировать класс Horse
public class HippodromeTest {
    @Test
    void testHippodromeConstructorThrowsWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testHippodromeConstructorThrowsMessageWhenNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                         () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testHippodromeConstructorThrowsWhenEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void testHippodromeConstructorThrowsMessageWhenEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                                          () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void testGetHorsesReturnsSameList() {
        // 1. Создаём список из 30 разных лошадей
        List<Horse> horseList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horseList.add(new Horse("Horse " + i, i, i * 10)); // У каждой лошади своё имя, скорость и дистанция
        }

        // 2. Создаём объект Hippodrome с этим списком
        Hippodrome hippodrome = new Hippodrome(horseList);

        // 3. Получаем список из getHorses()
        List<Horse> returnedHorses = hippodrome.getHorses();

        // 4. Проверяем, что список содержит те же объекты в том же порядке
        assertEquals(horseList.size(), returnedHorses.size(), "Размеры списков должны совпадать");
        assertIterableEquals(horseList, returnedHorses, "Списки должны быть идентичны по порядку и содержимому");
    }

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Инициализируем моки вручную
//    }
//
//    @Test
//    void testMoveCallsMoveOnAllHorses() {
//        List<Horse> horseMocks = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            horseMocks.add(mock(Horse.class));
//        }
//
//        Hippodrome hippodrome = new Hippodrome(horseMocks);
//        hippodrome.move();
//
//        for (Horse horse : horseMocks) {
//            verify(horse, times(1)).move();
//        }
//    }

    @Test
    void testGetWinnerReturnsHorseWithMaxDistance() {
        // 1. Создаём список лошадей с разными дистанциями
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse 1", 5, 100));
        horses.add(new Horse("Horse 2", 6, 200));
        horses.add(new Horse("Horse 3", 7, 300)); // <- Эта лошадь должна победить (самая большая дистанция)
        horses.add(new Horse("Horse 4", 8, 250));
        horses.add(new Horse("Horse 5", 9, 150));

        // 2. Создаём объект Hippodrome
        Hippodrome hippodrome = new Hippodrome(horses);

        // 3. Проверяем, что getWinner() вернёт лошадь с максимальной дистанцией
        assertEquals(horses.get(2), hippodrome.getWinner(), "Метод getWinner() должен возвращать лошадь с наибольшей дистанцией");
    }
}
