package com.estebes.compactic2generators.data;

public enum CobbleMachineTier
{
    Mark1(1, "Cobble Generator Mark 1"),
    Mark2(2, "Cobble Generator Mark 2"),
    Mark3(3, "Cobble Generator Mark 3"),
    Mark4(4, "Cobble Generator Mark 4");

    private int machineTier;
    private String machineName;

    private CobbleMachineTier(int machineTier, String machineName)
    {
        this.machineTier = machineTier;
        this.machineName = machineName;
    }

    public int getMachineTier()
    {
        return this.machineTier;
    }
}
