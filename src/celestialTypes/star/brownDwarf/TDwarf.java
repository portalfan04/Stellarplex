package celestialTypes.star.brownDwarf;

import celestialTypes.planet.gasGiant.GasGiant;

public class TDwarf extends GasGiant {

    public TDwarf(float radius)
    {
        this.setRadius(radius);
        this.setDensity(10000f);
        this.setCelestialType("t-class-dwarf-0");
        this.setColor("#870531");
        this.setSoi(1e10f);
    }
}
