package celestialTypes.star.mainSequence;

import celestialTypes.Celestial;
import celestialTypes.star.Star;
import helpers.MathHelper;

public class MainSequence extends Star {
    public MainSequence(float starRadius)
    {
        this.setCelestialType("main-sequence-0");
        this.setLuminosity_class("V");
        this.setOrbitVisibility(false);
        this.setRadius(starRadius);

        this.setSoi(MathHelper.SpectralClassMult(this.getSpectralClass().substring(0, 1)) * 1e11f);

        //mass = radius ** (5f/4f)
        this.setDensity(MathHelper.MainSequenceMass(starRadius) / starRadius);
        this.setTemperature(MathHelper.MainSequenceTemperature(this.getRadius() * 10));

    }
    @Override
    public String InvokeGenerator() {
        return MainSequenceGenerator.GenerateMainSequence(this);
    }
}
