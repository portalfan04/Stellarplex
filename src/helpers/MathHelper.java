package helpers;

import celestialTypes.Celestial;

import java.util.HashMap;
import java.util.Map;

public class MathHelper {
    static final float gravitationalConstant = 6.67E-11F;
    static final float pi = 3.1415926535F;
    static final float wien = 2.8978E-3f;
    static final float sb = 5.67E-8f;
    static final float solar_mass = 4E+30f;
    static final float solar_radius = 696000000f;
    static final float solar_luminosity = 3.846E+26f;
    static final float zero_point_luminosity = 3.0128E28f;
    static int[][] thresholds = {{60000, 55000, 50000, 44900, 42900, 41400, 39500, 37100, 35100, 33300}, {31400, 26000, 20600, 17000, 16400, 15700, 14500, 14000, 12300, 10700}, {9700, 9300, 8800, 8600, 8250, 8100, 7910, 7760, 7590, 7400}, {7220, 7020, 6820, 6750, 6670, 6550, 6350, 6280, 6180, 6050}, {5930, 5860, 5770, 5720, 5680, 5660, 5600, 5550, 5480, 5380},{5270, 5170, 5100, 4830, 4600, 4440, 4300, 4100, 3990, 3930},{3850, 3660, 3560, 3430, 3210, 3060, 2810, 2680, 2570, 2380}};
    public static float AccelerationFromDensityRadius(float density, float radius) {
        return (4F/3F) * gravitationalConstant * pi * radius * density;
    }
    public static float AccelerationFromMassRadius(float mass, float radius)
    {
        return ((gravitationalConstant * mass) / (radius * radius));
    }
    public static float WienDisplacementLaw(float temperature) {
        return wien / temperature;
    }

    public static String SpectralSubgroup(String harvard, float temperature) {
        int row = 0;
        switch(harvard)
        {
            case "O":
            {
                row = 0;
                break;
            }
            case "B":
            {
                row = 1;
                break;
            }
            case "A":
            {
                row = 2;
                break;
            }
            case "F":
            {
                row = 3;
                break;
            }
            case "G":
            {
                row = 4;
                break;
            }
            case "K":
            {
                row = 5;
                break;
            }
            case "M":
            {
                row = 6;
                break;
            }
            case "default":
            {
                return null;
            }
        }
        int subgroup = 0;
        for(int threshold_temperature : thresholds[row])
        {
            if(temperature >= threshold_temperature)
            {
                return String.valueOf(subgroup);
            }
            else {subgroup++;}
        }
        return "9";
    }

    public static float MainSequenceMass(float starRadius) {
        return (float) Math.pow(starRadius / solar_radius, (5f/4f)) * solar_mass;
    }
    public static float MainSequenceLuminosity(float starRadius) {
        return (float) (Math.pow(MainSequenceMass(starRadius) / solar_mass, 3.5) * solar_luminosity);
    }
    public static float MainSequenceTemperature(float starRadius) {
        return (float) Math.pow(MainSequenceLuminosity(starRadius) / (sb * 4 * pi * starRadius * starRadius), 0.25);
    }

    public static float BarycentreDistance(float partnerA_mass, float partnerB_mass, float distance)
    {
        return ((distance) * (1 + (partnerA_mass / partnerB_mass)));
    }

    public static Map<String, Float> DefaultOrbital()
    {
        Map<String, Float> orbitalInfo = new HashMap<>();
        orbitalInfo.put("semiMajorAxis", 0f);
        orbitalInfo.put("eccentricity", 0f);
        orbitalInfo.put("inclination", 0f);
        orbitalInfo.put("longitudeOfAscendingNode", 0f);
        orbitalInfo.put("argumentOfPeriapsis", 0f);
        return orbitalInfo;
    }
    public static float DensityFromRadius(float mass, float radius)
    {
        return (float) (mass / ((4f/3f) * pi * Math.pow(radius, 3)));
    }

    public static float KeplerThirdLaw(float mass_1, float mass_2, float distance) {
        float top = 4 * pi * pi;
        float bottom = gravitationalConstant * (mass_1 + mass_2);
        double dist_mult = Math.pow(distance, 3);
        double inside = (top / bottom) * dist_mult;
        return (float) (Math.pow(inside, 0.5));
    }
    public static float StefanBoltzmannFromRadiusTemperature(float radius, float temperature) {
        float area = (float) (4 * pi * Math.pow(radius * 10, 2));
        return (float) (sb * area * Math.pow(temperature, 4));
    }
    public static float absoluteMagnitude(float radius, float temperature)
    {
        float luminosity = StefanBoltzmannFromRadiusTemperature(radius, temperature);
        return (float) (-2.5 * Math.log10(luminosity / zero_point_luminosity));
    }
    public static String RedGiantLuminosityClass(float radius, float temperature)
    {
        float mag = absoluteMagnitude(radius, temperature);
        if(Math.abs(mag) > 4.5f)
        {
            return "II";
        }
        else if(Math.abs(mag) > 1.5f)
        {
            return "III";
        }
        else {
            return "IV";
        }
    }
    public static float SphereVolume(float radius)
    {
        return (float) (pi * (4f/3f) * Math.pow(radius, 3));
    }
    public static float SpectralClassMult(String spec_class)
    {
        switch(spec_class)
        {
            case "O": {return 10;}
            case "B": {return 5;}
            case "A": {return 3;}
            case "F": {return 2;}
            case "G": {return 1;}
            case "K": {return 0.75f;}
            case "M": {return 0.5f;}
        }
        return 1;
    }
}
