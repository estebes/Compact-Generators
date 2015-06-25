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
    public static final BaseBlock mark3MachineCasing = new BaseBlock(Reference.LOWERCASE_MOD_ID + ":" + "Mark3MachineCasing");
    public static final BaseBlock mark4MachineCasing = new BaseBlock(Reference.LOWERCASE_MOD_ID + ":" + "Mark4MachineCasing");

    public static void init()
    {
        simpleGenerator.setBlockName("SimpleGenerator");
        simpleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(simpleGenerator, "SimpleGenerator");
        cobbleGenerator.setBlockName("CobbleGenerator");
        cobbleGenerator.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(cobbleGenerator, "CobbleGenerator");
        pcbAssembler.setBlockName("PCBAssembler");
        pcbAssembler.setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "SimpleParticles");
        GameRegistry.registerBlock(pcbAssembler, "PCBAssembler");
        mark3MachineCasing.setBlockName("Mark 3 Machine Casing");
        GameRegistry.registerBlock(mark3MachineCasing, "Mark 3 Machine Casing");
        mark4MachineCasing.setBlockName("Mark 4 Machine Casing");
        GameRegistry.registerBlock(mark4MachineCasing, "Mark 4 Machine Casing");
    }
}
