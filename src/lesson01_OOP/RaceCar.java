package lesson01_OOP;

/**
 * gkislin
 * 12.12.2014.
 */
public class RaceCar extends AbstractCar {
    RaceCar() {
        speed = 200;
    }

    @Override
    public double getEngineVolume() {
        return 3.5;
    }
}
