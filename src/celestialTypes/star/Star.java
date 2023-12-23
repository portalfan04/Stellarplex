package celestialTypes.star;

import celestialTypes.Celestial;
import celestialTypes.star.mainSequence.MainSequenceGenerator;
import helpers.MathHelper;

public class Star extends Celestial {
    private float temperature;
    private String luminosity_class;
    public String getLuminosity_class() {
        return luminosity_class;
    }
    public void setLuminosity_class(String luminosity_class) {
        this.luminosity_class = luminosity_class;
    }
    public float getTemperature() {
        return temperature;
    }
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public String getBlackBodyHex()
    {
        switch(Character.toString(this.getSpectralClass().charAt(0)))
        {
            case "O":
            {
                return "#8fb1ff";
            }
            case "B":
            {
                return "#bdd1ff";
            }
            case "A":
            {
                return "#d2defc";
            }
            case "F":
            {
                return "#ffffff";
            }
            case "G":
            {
                return "#fcf7f5";
            }
            case "K":
            {
                return "#f7dfc6";
            }
            case "M":
            {
                return "#f5aa56";
            }
            default:
                return "#000000";
        }
    }
    public String getSpectralClass() { //easily overridable
        String harvard;
        if (temperature > 33300f) {
            harvard = "O";
        } else if (temperature > 10000f) {
            harvard = "B";
        } else if (temperature > 7285f) {
            harvard = "A";
        } else if (temperature > 6000) {
            harvard = "F";
        } else if (temperature > 5300f) {
            harvard = "G";
        } else if (temperature > 3900f) {
            harvard = "K";
        } else {
            harvard = "M";
        }
        return harvard + MathHelper.SpectralSubgroup(harvard, this.getTemperature()) + this.getLuminosity_class();

    }
    @Override
    public float getDensity()
    {
        return MathHelper.DensityFromRadius(this.getMass(), this.getRadius());
    }
    @Override
    public float getMass()
    {
        return MathHelper.MainSequenceMass(this.getRadius());
    }

}
