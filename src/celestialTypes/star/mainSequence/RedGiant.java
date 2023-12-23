package celestialTypes.star.mainSequence;

import celestialTypes.star.Star;
import helpers.MathHelper;

public class RedGiant extends Star {
    public RedGiant(float starRadius, float temperature)
    {
        this.setCelestialType("red-giant-0");
        this.setRadius(starRadius);
        this.setTemperature(temperature);
        this.setLuminosity_class(MathHelper.RedGiantLuminosityClass(starRadius, this.getTemperature()));
        this.setOrbitVisibility(false);
        this.setDensity(10f);

        this.setSoi(MathHelper.SpectralClassMult(this.getSpectralClass().substring(0, 1)) * 1e12f);

    }
    @Override
    public float getDensity()
    {
        return 10f;
    }
    @Override
    public float getMass()
    {
        return MathHelper.SphereVolume(this.getRadius()) / 1f;
    }
    @Override
    public String InvokeGenerator() {
        return MainSequenceGenerator.GenerateMainSequence(this);
    }
}
