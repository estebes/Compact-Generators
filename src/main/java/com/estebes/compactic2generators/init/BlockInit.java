package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.block.SimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class BlockInit
{
    public static final SimpleGenerator simpleGenerator = new SimpleGenerator();

    public static void init()
    {
        simpleGenerator.setBlockName("SimpleGenerator");
        simpleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(simpleGenerator, "SimpleGenerator");
    }
}
