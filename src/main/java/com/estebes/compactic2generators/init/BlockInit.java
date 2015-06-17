package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.block.CobbleGenerator;
import com.estebes.compactic2generators.block.SimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class BlockInit
{
    public static final SimpleGenerator simpleGenerator = new SimpleGenerator();
    public static final CobbleGenerator cobbleGenerator = new CobbleGenerator();

    public static void init()
    {
        simpleGenerator.setBlockName("SimpleGenerator");
        simpleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(simpleGenerator, "SimpleGenerator");
        cobbleGenerator.setBlockName("CobbleGenerator");
        cobbleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(cobbleGenerator, "CobbleGenerator");
    }
}
