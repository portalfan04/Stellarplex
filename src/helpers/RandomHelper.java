package helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomHelper {
    public Random rand = null;
    //{chance of occurence, gradient, y-cept}
    float[][] stellarFrequencies = {{0.01675f, 750f, 250f}, {0.03125f, 100, 150}, {0.0625f, 25, 125}, {0.125f, 50, 75}, {0.25f, 22.5f, 52.5f}, {0.5f, 12.5f, 40}, {1, 32.5f, 7.5f}};
    public RandomHelper(Long seed) {
        rand = new Random(seed);
    }
    public float FloatInRange(float min, float max)
    {
        return min + rand.nextFloat() * (max - min);
    }
    public Map<String, Float> GenerateRandomOrbital()
    {
        Map<String, Float> orbitalInfo = new HashMap<>();
        orbitalInfo.put("semiMajorAxis", FloatInRange(1E+12F, 1E+17F));
        orbitalInfo.put("eccentricity", FloatInRange(0, 0.99F));
        orbitalInfo.put("inclination", FloatInRange(-180F, 180F));
        orbitalInfo.put("longitudeOfAscendingNode", FloatInRange(0, 360F));
        orbitalInfo.put("argumentOfPeriapsis", FloatInRange(0, 360F));
        return orbitalInfo;
    }
    public float GenerateNormalDensity(String object)
    {
        float density_deviation = 0f;
        float mean_density = 0f;
        switch(object)
        {
            case "gas-giant-0":
            {
                density_deviation = 0.918575899f;
                mean_density = 1.097679017f;
            }
        }
        return (float) (density_deviation * rand.nextGaussian() + mean_density);
    }
    public float GenerateNormalRadius(String object)
    {
        float radius_deviation = 0f;
        float mean_radius = 0f;
        switch(object)
        {
            case "gas-giant-0":
            {
                radius_deviation = 3061434.164f;
                mean_radius = 6803356.485f;
            }
        }
        return (float) (radius_deviation * rand.nextGaussian() + mean_radius);
    }
    public float GenerateMainSequenceRadius()
    {
        float frequencyDeterminant = rand.nextFloat();
        for (float[] stellarLinearRegression : stellarFrequencies)
        {
            if(frequencyDeterminant <= stellarLinearRegression[0])
            {
                float compressedRegressionXVal = frequencyDeterminant / stellarLinearRegression[0];
                float gradient = stellarLinearRegression[1];
                float ycept = stellarLinearRegression[2];

                return ((gradient * compressedRegressionXVal) + ycept) * 1000000;
            }
        }
        return -1f;
    }
    public float GenerateRedGiantTemperature()
    {
        return FloatInRange(3000, 5500);
    }
    public float GenerateRedGiantRadius()
    {
        float mean = (1E+9f + 1E10f) / 2.0f;  // Calculate the mean
        float stdDev = (1E10f - 1E+9f) / 4.0f;  // Choose a standard deviation (adjust as needed)

        // Generate two random numbers with a normal distribution using Box-Muller transform
        float u1 = rand.nextFloat();
        float u2 = rand.nextFloat();
        float z0 = (float) (Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2));

        // Apply the mean and standard deviation
        float radius = mean + stdDev * z0;

        // Ensure the generated value is within the specified range
        return Math.max(750000000, Math.min(1.5E10f, radius));
    }
    public float GenerateBinarySeperation()
    {
        return FloatInRange(1.5E6f, 4E10f);
    }
    public float GenerateLClassRadius() {return FloatInRange(5e+6f, 8e+6f);}
    public float GenerateLClassTemperature() {return FloatInRange(1500, 2000);}

    public float GenerateTClassRadius() { return FloatInRange(10000000, 20000000);
    }
}
