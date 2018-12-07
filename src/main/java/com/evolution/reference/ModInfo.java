package com.evolution.reference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModInfo
{

    public static final String MOD_ID = "evolution";
    public static final String MOD_NAME = "Evolution";
    public static final String MOD_VERSION = "1.0.0-alpha";

    public static final String CLIENT_PROXY = "com.evolution.proxy.ClientProxy";
    public static final String SERVER_PROXY = "com.evolution.proxy.ServerProxy";

    public static final Logger MOD_LOGGER = LogManager.getLogger(MOD_NAME);

}
