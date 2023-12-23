package celestialTypes.planet.gasGiant;

import celestialTypes.Celestial;
import celestialTypes.star.mainSequence.MainSequenceGenerator;

public class GasGiant extends Celestial {

    public GasGiant() {
        this.setCelestialType("gas-giant-0");
    }

    @Override
    public String InvokeGenerator() {
        return GasGiantGenerator.generateGasGiant(this);
    }
}
