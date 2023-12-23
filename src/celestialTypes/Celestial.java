package celestialTypes;

import helpers.MathHelper;
import sun.security.provider.Sun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Celestial {
    private ArrayList<Celestial> orbiters = new ArrayList<Celestial>();
    private Map<String, Float> orbitalInfo = new HashMap<>();
    private float radius;
    private float density;
    private String celestialType;
    private String name;
    private boolean hide_orbit = false;
    private float period = -1;

    private float soi = -1;

    private String color = "";
    private String referenceBody = "Sun";
    public Map<String, Float> getOrbitalInfo() {
        return orbitalInfo;
    }

    public void setOrbitalInfo(Map<String, Float> orbitalInfo) {
        this.orbitalInfo = orbitalInfo;
    }
    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }
    public float getGeeASL() {
        return MathHelper.AccelerationFromDensityRadius(this.density, this.getRadius()) / 9.81F;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCelestialType() {
        return celestialType;
    }

    public void setCelestialType(String celestialType) {
        this.celestialType = celestialType;
    }
    public float getMass()
    {
        return this.getDensity() * this.getRadius();
    }
    public void setSemiMajorAxis(float new_sma)
    {
        this.orbitalInfo.put("semiMajorAxis", new_sma);
    }
    public void setAoP(float new_AoP)
    {
        this.orbitalInfo.put("argumentOfPeriapsis", new_AoP);
    }
    public String InvokeGenerator() {
        return null;
    }

    public String getReferenceBody() {
        return referenceBody;
    }

    public void setReferenceBody(String referenceBody) {
        this.referenceBody = referenceBody;
    }
    public ArrayList<Celestial> getOrbiters() {
        return orbiters;
    }
    public void addOrbiter(Celestial celestial) {
        celestial.setReferenceBody(this.getName());
        orbiters.add(celestial);
    }
    public boolean getOrbitVisbility()
    {
        return hide_orbit;
    }

    public void setOrbitVisibility(boolean hide_orbit) {
        this.hide_orbit = hide_orbit;
    }
    public float getPeriod()
    {
        return this.period;
    }
    public void setPeriod(float new_period)
    {
        this.period = new_period;
    }
    public float getSoi() {
        return soi;
    }
    public void setSoi(float soi) {
        this.soi = soi;
    }
    public String getColor() {return this.color;}
    public void setColor(String color) {
        this.color = color;
    }
}
