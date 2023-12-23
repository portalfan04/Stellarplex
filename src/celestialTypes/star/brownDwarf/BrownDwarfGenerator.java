package celestialTypes.star.brownDwarf;

import celestialTypes.star.Star;
import helpers.GenerationHelpers;

import java.util.Map;

public class BrownDwarfGenerator {
    public static String GenerateBrownDwarf(Star brownDwarf)
    {
        String period_option = "";
        String soi_option = "";
        if(brownDwarf.getPeriod() != -1)
        {
            period_option = "\t\t\tperiod = " + String.valueOf(brownDwarf.getPeriod()) + "\n";
        }
        if(brownDwarf.getSoi() != -1)
        {
            soi_option = "\t\t\tsphereOfInfluence = " + String.valueOf(brownDwarf.getSoi()) + "\n";
        }


        Map<String, Float> orbitalInfo = brownDwarf.getOrbitalInfo();
        String color = brownDwarf.getBlackBodyHex();
        String output =
                "@Kopernicus:FOR[StellarPlex]\n" +
                        "{\n" +
                        "    Body\n" +
                        "    {\n" +
                        "\t\tTemplate\n" +
                        "\t\t{\n" +
                        "\t\t\tname = Sun\n" +
                        "\t\t}\n" +
                        "        name = " + brownDwarf.getName() + "\n" +
                        "        cacheFile = Stellarplex_Cache/" + brownDwarf.getName() + ".bin\n" +
                        "        Orbit\n" +
                        "        {\n" +
                        "            referenceBody = " + brownDwarf.getReferenceBody() + "\n" + period_option +
                        "            mode = " + GenerationHelpers.ProcessOrbitVisibility(brownDwarf.getOrbitVisbility()) + "\n" +
                        "            semiMajorAxis = " + String.valueOf(orbitalInfo.get("semiMajorAxis")) + "\n" + //i hate this
                        "            eccentricity = " + String.valueOf(orbitalInfo.get("eccentricity")) + "\n" +
                        "            inclination = " + String.valueOf(orbitalInfo.get("inclination")) + "\n" +
                        "            longitudeOfAscendingNode = " + String.valueOf(orbitalInfo.get("longitudeOfAscendingNode")) + "\n" +
                        "            argumentOfPeriapsis = " + String.valueOf(orbitalInfo.get("argumentOfPeriapsis")) + "\n" +
                        "            color = #ad0000\n" +
                        "        }\n" +
                        "        Properties\n" +
                        "        {\n" +
                        "            description = density: " + String.valueOf(brownDwarf.getDensity()) + ", stellar classification: " + brownDwarf.getSpectralClass() + "\n" +
                        "            radius = " + String.valueOf(brownDwarf.getRadius()) + "\n" +
                        "            mass = " + String.valueOf(brownDwarf.getMass()) + "\n" + soi_option +
                        "        }\n" +
                        "        ScaledVersion\n" +
                        "        {\n" +
                        "            Material\n" +
                        "            {\n" +
                        "                rimColor = #ad0000\n" +
                        "                rimPower = 0.5\n" +
                        "                rimBlend = 2.5\n"+
                        "                sunspotTex = Stellarplex/PluginData/Stars/BrownDwarfMaps/" + brownDwarf.getName() + ".png\n" +
                        "            }\n" +
                        "            Light\n" +
                        "            {\n" +
                        "                sunFlare = Stellarplex/PluginData/Stars/flare.unity3d:sun_flare\n" +
                        "                sunlightColor = #ad0000\n" +
                        "                sunlightIntensity = 0.4\n" +
                        "                scaledSunlightColor = #ad0000\n" +
                        "                scaledSunlightIntensity = 0.4\n" +
                        "                IVASunColor = #ad0000\n" +
                        "                IVASunIntensity = 0.4\n" +
                        "                sunLensFlareColor = #ad0000\n" +
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
                        "\t\t\t\t\tkey = 0 0 0 0.5\n" +
                        "                    key = 10 1 0 0\n" +
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
                        "                        texture = Stellarplex\\PluginData\\Stars\\corona_red.dds\n" +
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
                        "                        texture = Stellarplex\\PluginData\\Stars\\corona_red.dds\n" +
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
