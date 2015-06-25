package com.estebes.compactic2generators.init;

import com.estebes.compactic2generators.block.BaseBlock;
import com.estebes.compactic2generators.block.CobbleGenerator;
import com.estebes.compactic2generators.block.PCBAssembler;
import com.estebes.compactic2generators.block.SimpleGenerator;
import com.estebes.compactic2generators.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class BlockInit
{
    public static final SimpleGenerator simpleGenerator = new SimpleGenerator();
    public static final CobbleGenerator cobbleGenerator = new CobbleGenerator();
    public static final PCBAssembler pcbAssembler = new PCBAssembler();
    public static final BaseBlock mark3MachineCasing = new BaseBlock();

    public static void init()
    {
        simpleGenerator.setBlockName("SimpleGenerator");
        simpleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        simpleGenerator.setHarvestLevel("pickaxe", 3);
        GameRegistry.registerBlock(simpleGenerator, "SimpleGenerator");
        cobbleGenerator.setBlockName("CobbleGenerator");
        cobbleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(cobbleGenerator, "CobbleGenerator");
        pcbAssembler.setBlockName("PCBAssembler");
        pcbAssembler.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(pcbAssembler, "PCBAssembler");
        mark3MachineCasing.setBlockName("Machine Casing");
        mark3MachineCasing.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(mark3MachineCasing, "Machine Casing");
    }
}
