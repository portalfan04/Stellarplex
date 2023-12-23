package celestialTypes.star.mainSequence;

import celestialTypes.star.Star;
import helpers.GenerationHelpers;

import java.util.Map;

public class MainSequenceGenerator {

    public static String GenerateMainSequence(Star star)
    {
        String period_option = "";
        String soi_option = "";
        String corona = "";
        if(star.getPeriod() != -1)
        {
            period_option = "\t\t\tperiod = " + String.valueOf(star.getPeriod()) + "\n";
        }
        if(star.getSoi() != -1)
        {
            soi_option = "\t\t\tsphereOfInfluence = " + String.valueOf(star.getSoi()) + "\n";
        }

        if(star.getSpectralClass().contains("M") || star.getSpectralClass().contains("K"))
        {
            corona = "cool";
        }
        else if(star.getSpectralClass().contains("O") || star.getSpectralClass().contains("B"))
        {
            corona = "hot";
        }
        else
        {
            corona = "generic";
        }

        Map<String, Float> orbitalInfo = star.getOrbitalInfo();
        String color = star.getBlackBodyHex();
        String output =
                "@Kopernicus:FOR[StellarPlex]\n" +
                        "{\n" +
                        "    Body\n" +
                        "    {\n" +
                        "\t\tTemplate\n" +
                        "\t\t{\n" +
                        "\t\t\tname = Sun\n" +
                        "\t\t}\n" +
                        "        name = " + star.getName() + "\n" +
                        "        cacheFile = Stellarplex_Cache/" + star.getName() + ".bin\n" +
                        "        Orbit\n" +
                        "        {\n" +
                        "            referenceBody = " + star.getReferenceBody() + "\n" + period_option +
                        "            mode = " + GenerationHelpers.ProcessOrbitVisibility(star.getOrbitVisbility()) + "\n" +
                        "            semiMajorAxis = " + String.valueOf(orbitalInfo.get("semiMajorAxis")) + "\n" + //i hate this
                        "            eccentricity = " + String.valueOf(orbitalInfo.get("eccentricity")) + "\n" +
                        "            inclination = " + String.valueOf(orbitalInfo.get("inclination")) + "\n" +
                        "            longitudeOfAscendingNode = " + String.valueOf(orbitalInfo.get("longitudeOfAscendingNode")) + "\n" +
                        "            argumentOfPeriapsis = " + String.valueOf(orbitalInfo.get("argumentOfPeriapsis")) + "\n" +
                        "            color = " + color + "\n" +
                        "        }\n" +
                        "        Properties\n" +
                        "        {\n" +
                        "            description = density: " + String.valueOf(star.getDensity()) + ", stellar classification: " + star.getSpectralClass() + "\n" +
                        "            radius = " + String.valueOf(star.getRadius()) + "\n" +
                        "            mass = " + String.valueOf(star.getMass()) + "\n" + soi_option +
                        "        }\n" +
                        "        ScaledVersion\n" +
                        "        {\n" +
                        "            Material\n" +
                        "            {\n" +
                        "                rimColor = " + color + "\n" +
                        "                rimPower = 0.5\n" +
                        "                rimBlend = 2.5\n"+
                        "            }\n" +
                        "            Light\n" +
                        "            {\n" +
                        "                sunFlare = Stellarplex/PluginData/Stars/flare.unity3d:sun_flare\n" +
                        "                sunlightColor = " + color + "\n" +
                        "                sunlightIntensity = 0.4\n" +
                        "                scaledSunlightColor = " + color + "\n" +
                        "                scaledSunlightIntensity = 0.4\n" +
                        "                IVASunColor = " + color + "\n" +
                        "                IVASunIntensity = 0.4\n" +
                        "                sunLensFlareColor = " + color + "\n" +
                        "\t\t\t\tIntensityCurve\n" +
                        "\t\t\t\t{ \n" +
                        "\t\t\t\t\tkey = 0\t\t\t\t1 \t0 \t0\n" +
                        "\t\t\t\t\tkey = 5000000000 \t0.7 0 \t0 \t\n" +
                        "\t\t\t\t\tkey = 10000000000 \t0.5 0 \t0 \n" +
                        "\t\t\t\t\tkey = 175000000000 \t0\t0 \t0 \n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t\tScaledIntensityCurve\n" +
                        "\t\t\t\t{  \n" +
                        "\t\t\t\t\tkey = 0 \t\t\t1 \t0 \t0\n" +
                        "\t\t\t\t\tkey = 800000 \t\t0.7 0 \t0 \n" +
                        "\t\t\t\t\tkey = 1666666\t\t0.5 0\t0\n" +
                        "\t\t\t\t\tkey = 30000000\t\t0 \t0 \t0 \n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t\tIVAIntensityCurve\n" +
                        "\t\t\t\t{ \n" +
                        "\t\t\t\t\tkey = 0 \t\t\t1 \t0 \t0\n" +
                        "\t\t\t\t\tkey = 800000 \t\t0.7 0 \t0 \n" +
                        "\t\t\t\t\tkey = 1666666\t\t0.5 0\t0\n" +
                        "\t\t\t\t\tkey = 30000000\t\t0 \t0 \t0 \n" +
                        "\t\t\t\t}\n" +
                        "\t\t\t\tbrightnessCurve\n" +
                        "\t\t\t\t{\n" +
                        "\t\t\t\t\tkey = 0 0.001 0 15\n" +
                        "\t\t\t\t\tkey = 0.01 0.01 0 15\n" +
                        "                    key = 0.1 0.1 0 0\n" +
                        "                    key = 1 1 0 0\n" +
                        "                    key = 5 2 0 0\n" +
                        "                    key = 50 2 0 0\n" +
                        "                    key = 500 2 0 0\n" +
                        "\t\t\t\t}\n" +
                        "            }\n" +
                        "            Coronas\n" +
                        "            {\n" +
                        "                Corona\n" +
                        "                {\n" +
                        "                    rotation = 0\n" +
                        "                    speed = -1\n" +
                        "                    updateInterval = 5\n" +
                        "                    scaleLimitX = 5\n" +
                        "                    scaleLimitY = 5\n" +
                        "                    scaleSpeed = 0.007\n" +
                        "\n" +
                        "                    Material\n" +
                        "                    {\n" +
                        "                        texture = Stellarplex\\PluginData\\Stars\\corona_" + corona + ".dds\n" +
                        "                        inverseFade = 2.553731\n" +
                        "                    }\n" +
                        "                }\n" +
                        "                Corona\n" +
                        "                {\n" +
                        "                    rotation = 0\n" +
                        "                    speed = 1\n" +
                        "                    updateInterval = 5\n" +
                        "                    scaleLimitX = 5\n" +
                        "                    scaleLimitY = 5\n" +
                        "                    scaleSpeed = 0.009\n" +
                        "\n" +
                        "                    Material\n" +
                        "                    {\n" +
                        "                        texture = Stellarplex\\PluginData\\Stars\\corona_" + corona + ".dds\n" +
                        "                        inverseFade = 2.553731\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
        return output;
    }
}
