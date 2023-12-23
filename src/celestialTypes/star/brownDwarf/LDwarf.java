package celestialTypes.star.brownDwarf;

import celestialTypes.star.Star;
import celestialTypes.star.mainSequence.MainSequenceGenerator;
import helpers.MathHelper;

public class LDwarf extends Star {
    public LDwarf(float radius, float temperature)
    {
        this.setRadius(radius);
        this.setTemperature(temperature);
        this.setCelestialType("brown-dwarf-0");
        this.setDensity(100000f);
        this.setSoi(1e10f);
    }

    @Override
    public String getSpectralClass()
    {
        float luminosity = MathHelper.StefanBoltzmannFromRadiusTemperature(this.getRadius(), this.getTemperature());
        float temp = this.getTemperature();
        String subclass;
        String luminosity_class;
        if(temp > 2200) {subclass = "0";}
        else if (temp > 2000) {subclass = "1";}
        else if (temp > 1875) {subclass = "2";}
        else if (temp > 1750) {subclass = "3";}
        else if (temp > 1650) {subclass = "4";}
        else {subclass = "5";}

        if(luminosity > 2.5E+22f) {luminosity_class = "V";}
        else {luminosity_class = "VI";}

        return "L" + subclass + luminosity_class;
    }

    @Override
    public String InvokeGenerator() {
        return BrownDwarfGenerator.GenerateBrownDwarf(this);
    }
}
