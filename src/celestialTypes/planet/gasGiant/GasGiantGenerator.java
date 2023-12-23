package celestialTypes.planet.gasGiant;

import helpers.GenerationHelpers;

import java.util.Map;

public class GasGiantGenerator {
    public static String generateGasGiant(GasGiant gasGiant)
    {
            Map<String, Float> orbitalInfo = gasGiant.getOrbitalInfo();
            String type;
            String color_option = "";
            if(gasGiant.getCelestialType().equals("t-class-dwarf-0"))
            {
                type = "Stars/BrownDwarfMaps";
            }
            else
            {
                type = "GasPlanet";
            }
            if(!gasGiant.getColor().equals(""))
            {
                color_option = "\t\t\t\tcolor = "+gasGiant.getColor() + "\n";
            }
            String output =
                    "@Kopernicus:FOR[Stellarplex]\n" +
                    "{\n" +
                    "    Body\n" +
                    "    {\n" +
                    "\t\tTemplate\n" +
                    "\t\t{\n" +
                    "\t\t\tname = Jool\n" +
                    "\t\t}\n" +
                    "        name = " + gasGiant.getName() + "\n" +
                    "        cacheFile = Stellarplex_Cache/" + gasGiant.getName() + ".bin\n" +
                    "        Orbit\n" +
                    "        {\n" +
                    "            mode = " + GenerationHelpers.ProcessOrbitVisibility(gasGiant.getOrbitVisbility()) + "\n" +
                    "            referenceBody = " + gasGiant.getReferenceBody() + "\n" +
                    "            semiMajorAxis = " + String.valueOf(orbitalInfo.get("semiMajorAxis")) + "\n" + //i hate this
                    "            eccentricity = " + String.valueOf(orbitalInfo.get("eccentricity")) + "\n" +
                    "            inclination = " + String.valueOf(orbitalInfo.get("inclination")) + "\n" +
                    "            longitudeOfAscendingNode = " + String.valueOf(orbitalInfo.get("longitudeOfAscendingNode")) + "\n" +
                    "            argumentOfPeriapsis = " + String.valueOf(orbitalInfo.get("argumentOfPeriapsis")) + "\n" + color_option +
                    "        }\n" +
                    "        Properties\n" +
                    "        {\n" +
                    "            description = density: " + String.valueOf(gasGiant.getDensity()) + "\n" +
                    "            radius = " + String.valueOf(gasGiant.getRadius()) + "\n" +
                    "            geeASL = " + String.valueOf(gasGiant.getGeeASL()) + "\n" +
                    "        }\n" +
                    "        ScaledVersion\n"+
                    "        {\n"+
                    "             Material\n"+
                    "             {\n"+
                    "                   texture = StellarPlex/PluginData/"+type+"/"+gasGiant.getName()+".png\n" + color_option +
                    "                   rimBlend = 0\n" +
                    "                   rimPower = 1\n" +
                    "             }\n"+
                    "        }\n"+
                    "    }\n" +
                    "}";
            return output;
    }

}
