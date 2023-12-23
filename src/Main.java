import celestialTypes.BarycentreHandler;
import celestialTypes.Celestial;
import celestialTypes.star.Star;
import celestialTypes.star.brownDwarf.BrownDwarfGenerator;
import celestialTypes.star.brownDwarf.LDwarf;
import celestialTypes.star.brownDwarf.TDwarf;
import celestialTypes.star.mainSequence.MainSequence;
import celestialTypes.star.mainSequence.RedGiant;
import helpers.MathHelper;
import helpers.RandomHelper;
import helpers.TextureHandler;

import javax.script.ScriptException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    RandomHelper randomHelper;
    int celestialID;
    ArrayList<Celestial> celestials;
    public static void finaliseCelestial(Celestial celestial)
    {
        String filePath = "Stellarplex/Gens/Stars/" + celestial.getName() + ".cfg";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the multi-line string to the file
            writer.write(celestial.InvokeGenerator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Celestial orbiter : celestial.getOrbiters())
        {
            finaliseCelestial(orbiter);
        }
    }
    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        ArrayList<Celestial> celestials = new ArrayList<>();
        Long seed = 3L;
        int celestialID = 0;
        RandomHelper randomHelper = new RandomHelper(seed);
        for (int i = 0; i < 50; i++) {
            Celestial starA;
            float roll = randomHelper.FloatInRange(0, 1f);
            if(roll >= 0.9)
            {
                starA = new RedGiant(randomHelper.GenerateRedGiantRadius(), randomHelper.GenerateRedGiantTemperature());
            }
            else if(roll >= 0.8)
            {
                starA = new LDwarf(randomHelper.GenerateLClassRadius(), randomHelper.GenerateLClassTemperature());
                TextureHandler.TexBrownDwarf(String.valueOf(celestialID), celestialID);
            }
            else if(roll >= 0.7)
            {
                starA = new TDwarf(randomHelper.GenerateTClassRadius());
                TextureHandler.TexBrownDwarf(String.valueOf(celestialID), celestialID);
            }
            else
            {
                starA = new MainSequence(randomHelper.GenerateMainSequenceRadius());
            }
            MainSequence starB = new MainSequence(randomHelper.GenerateMainSequenceRadius());
            BarycentreHandler barycentreHandler = new BarycentreHandler(String.valueOf(celestialID), randomHelper.GenerateBinarySeperation(), null, starA, starB, randomHelper.GenerateRandomOrbital(), randomHelper.GenerateRandomOrbital());
            celestials.add(barycentreHandler);
            celestialID++;
        }
        for (int i = 0; i < 50; i++)
        {
            MainSequence star = new MainSequence(randomHelper.GenerateMainSequenceRadius());
            star.setName(String.valueOf(celestialID));
            star.setOrbitalInfo(randomHelper.GenerateRandomOrbital());
            celestials.add(star);
            celestialID++;
        }
        for (int i = 0; i < 50; i++)
        {
            RedGiant star = new RedGiant(randomHelper.GenerateRedGiantRadius(), randomHelper.GenerateRedGiantTemperature());
            star.setName(String.valueOf(celestialID));
            star.setOrbitalInfo(randomHelper.GenerateRandomOrbital());
            celestials.add(star);
            celestialID++;
        }
        for (int i = 0; i < 50; i++)
        {
            LDwarf dwarf = new LDwarf(randomHelper.GenerateLClassRadius(), randomHelper.GenerateLClassTemperature());
            dwarf.setName(String.valueOf(celestialID));
            dwarf.setOrbitalInfo(randomHelper.GenerateRandomOrbital());
            celestials.add(dwarf);
            TextureHandler.TexBrownDwarf(String.valueOf(celestialID), celestialID);
            celestialID++;
        }
        for (int i = 0; i < 50; i++)
        {
            TDwarf dwarf = new TDwarf(randomHelper.GenerateTClassRadius());
            dwarf.setName(String.valueOf(celestialID));
            dwarf.setOrbitalInfo(randomHelper.GenerateRandomOrbital());
            celestials.add(dwarf);
            TextureHandler.TexBrownDwarf(String.valueOf(celestialID), celestialID);
            celestialID++;
        }
        for(Celestial celestial : celestials)
        {
            finaliseCelestial(celestial);
        }
    }
    public Star LDwarfParse()
    {
        LDwarf brownDwarf = new LDwarf(randomHelper.GenerateLClassRadius(), randomHelper.GenerateLClassTemperature());
        return brownDwarf;
    }
}