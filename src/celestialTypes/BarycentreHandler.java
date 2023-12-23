package celestialTypes;

import celestialTypes.planet.gasGiant.GasGiant;
import helpers.MathHelper;

import java.util.HashMap;
import java.util.Map;

public class BarycentreHandler extends GasGiant {
    private Celestial partnerA;
    private Celestial partnerB;
    private float partnerB_sma;
    private float partnerA_sma;
    private Map<String, Float> partnerA_orbitals;
    private Map<String, Float> partnerB_orbitals;
    public BarycentreHandler(String name, float seperation, String type, Celestial objectA, Celestial objectB, Map<String, Float> orbitals, Map<String, Float> internal_randomness)
    {
        internal_randomness.put("eccentricity", 0f);
        this.partnerA = objectA;
        this.partnerB = objectB;
        this.setName(name);
        this.setOrbitVisibility(false);
        this.partnerA_orbitals = new HashMap<>(internal_randomness);;
        this.partnerB_orbitals = new HashMap<>(internal_randomness);;

        this.partnerB_sma = MathHelper.BarycentreDistance(partnerA.getMass(), partnerB.getMass(), seperation);
        this.partnerA_sma = MathHelper.BarycentreDistance(partnerB.getMass(), partnerA.getMass(), seperation);

        this.partnerA.setOrbitalInfo(this.partnerA_orbitals);
        this.partnerB.setOrbitalInfo(this.partnerB_orbitals);

        this.partnerA.setSemiMajorAxis(this.partnerA_sma);
        this.partnerB.setSemiMajorAxis(this.partnerB_sma);

        this.partnerB.setAoP(internal_randomness.get("argumentOfPeriapsis") + 180f);

        this.partnerA.setPeriod(getPeriod());
        this.partnerB.setPeriod(getPeriod());

        this.setSoi(partnerB_sma + (this.getPartnerSeperation() / 2) - 2000000);

        partnerA.setSoi((this.getPartnerSeperation() / 2) - 1000000);
        partnerB.setSoi((this.getPartnerSeperation() / 2) - 1000000);



        if(partnerA.getMass() > partnerB.getMass())
        {
            partnerA.setName(this.getName() + "-A");
            partnerB.setName(this.getName() + "-B");
        }
        else
        {
            partnerA.setName(this.getName() + "-B");
            partnerB.setName(this.getName() + "-A");
        }
        partnerA.setOrbitVisibility(true);
        partnerB.setOrbitVisibility(true);
        this.addOrbiter(partnerA);
        this.addOrbiter(partnerB);
        this.setOrbitalInfo(orbitals);

        this.setColor("#00ff2f");
    }
    @Override
    public float getRadius()
    {
        return 100000;
    }
    public float getPartnerSeperation()
    {
        if(partnerB_sma > partnerA_sma)
        {
            return partnerB_sma - partnerA_sma;
        }
        else
        {
            return partnerA_sma - partnerB_sma;
        }
    }
    @Override
    public float getMass()
    {
        return this.partnerA.getMass() + this.partnerB.getMass();
    }
    @Override
    public float getGeeASL()
    {
        return MathHelper.AccelerationFromMassRadius(this.partnerA.getMass() + this.partnerB.getMass(), this.partnerA.getRadius() + this.partnerB.getRadius());
    }
    public float getPeriod()
    {
        return MathHelper.KeplerThirdLaw(this.partnerA.getMass(), this.partnerB.getMass(), partnerB_sma + partnerA_sma);
    }

    public Celestial[] getPartners()
    {
        return new Celestial[]{partnerA, partnerB};
    }

}
