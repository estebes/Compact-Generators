package com.estebes.compactic2generators.configuration;


import com.estebes.compactic2generators.utility.Logger;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ConfigurationHandler
{

    public static void init(File configFile)
    {
        Configuration configuration = new Configuration(configFile);

        boolean isEnabled = true;

        try
        {
            configuration.load();
            Property enabled = configuration.get(Configuration.CATEGORY_GENERAL, "generatorsEnabled", true, "If this mod items are craftable.");
            isEnabled = enabled.getBoolean(true);
        }
        catch(Exception e)
        {
            Logger.error(e);
            throw new RuntimeException(e);
        }
        finally
        {
            configuration.save();
        }
    }
}
