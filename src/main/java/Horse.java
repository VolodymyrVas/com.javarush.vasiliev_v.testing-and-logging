import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class Horse {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Horse.class);

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            logger.error("Horse: Name is null", new IllegalArgumentException("Name cannot be null."));
            throw new IllegalArgumentException("Name cannot be null.");
        } if (name.isBlank()) {
            logger.error("Horse: Name is blank. Throwing IllegalArgumentException",
                         new IllegalArgumentException("Name cannot be blank."));
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error("Horse: Speed is negative [{}]. Throwing IllegalArgumentException", speed);
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error("Horse: Distance is negative [{}]. Throwing IllegalArgumentException", distance);
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;

        // Лог успешного создания объекта
        logger.debug("Horse created: {} (Speed: {}, Distance: {})", name, speed, distance);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
