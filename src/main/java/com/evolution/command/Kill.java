package com.evolution.command;

import net.minecraft.command.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.*;

public class Kill extends CommandBase
{

    List<Class<? extends EntityLiving>> PASSIVE_MOBS = new ArrayList<>();
    List<Class<? extends EntityLiving>> HOSTILE_MOBS = new ArrayList<>();

    @Override
    public String getName()
    {
        return "e_kill";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "evolution.command.kill";
    }

    private void populateLists()
    {
        Collections.addAll(PASSIVE_MOBS, EntityCow.class, EntityPig.class, EntitySheep.class, EntityChicken.class, EntityHorse.class, EntityMooshroom.class, EntityOcelot.class, EntityRabbit.class, EntityPolarBear.class, EntityWolf.class);
        Collections.addAll(HOSTILE_MOBS, EntitySkeleton.class, EntityZombie.class, EntityCreeper.class, EntitySpider.class, EntityCaveSpider.class, EntityBlaze.class, EntityPigZombie.class, EntityEnderman.class, EntityDragon.class, EntitySilverfish.class, EntityWither.class, EntityWitch.class);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
                List<String> Tab_Options = new ArrayList<>();
        populateLists();

        if(!args[0].equals(""))
        {
            switch(args[0].toLowerCase())
            {
                case "player":
                {
                    int size = server.getPlayerList().getPlayers().size();
                    for(int i = 0; i < size; i++)
                    {
                        Tab_Options.add(server.getPlayerList().getPlayers().get(i).getName());
                    }
                }
                case "mob":
                {
                    if(!args[1].equals(""))
                    {
                        Tab_Options.clear();
                        switch(args[1])
                        {
                            case "all":
                            {
                                for(Class<? extends EntityLiving> entityClass : PASSIVE_MOBS)
                                {
                                    for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                    {
                                        Tab_Options.add(entityClass.getClass().getSimpleName().toLowerCase().replace("entity", "") + "_" + entity.getPersistentID().toString());
                                    }
                                }

                                for(Class<? extends EntityLiving> entityClass : HOSTILE_MOBS)
                                {
                                    for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                    {
                                        Tab_Options.add(entityClass.getClass().getSimpleName().toLowerCase().replace("entity", "") + "_" + entity.getPersistentID().toString());
                                    }
                                }
                            }
                            case "hostile":
                            {
                                switch(args[2])
                                {
                                    case "all":
                                    {
                                        for(Class<? extends EntityLiving> entityClass : HOSTILE_MOBS)
                                        {
                                            for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                            {
                                                Tab_Options.add(entityClass.getClass().getSimpleName().toLowerCase().replace("entity", "") + "_" + entity.getPersistentID().toString());
                                            }
                                        }
                                    }
                                    case "skeleton":
                                    {
                                        for(EntitySkeleton entity : sender.getEntityWorld().getEntities(EntitySkeleton.class, null))
                                        {
                                            Tab_Options.add("skeleton_" + entity.getPersistentID().toString());
                                        }
                                    }
                                }
                            }
                            case "passive":
                            {

                            }
                            default: {}
                        }
                    }
                    else
                    {
                        Tab_Options.clear();
                        Collections.addAll(Tab_Options, "all", "hostile", "passive");
                    }
                }
            }
        }
        else
        {
            Collections.addAll(Tab_Options, "player", "mob");
        }

        return Tab_Options;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(args[0].equals(""))
        {
            //Kills Command Sender if no variables are given.
            sender.getCommandSenderEntity().onKillCommand();
        }
        else
        {
            switch(args[0].toLowerCase())
            {
                case "player":
                {
                    if(!args[1].equals(""))
                    {
                        @Nullable EntityPlayer player = sender.getEntityWorld().getPlayerEntityByName(args[1]);
                        if(player != null)
                        {
                            player.onKillCommand();
                        }
                        else
                        {
                            sender.sendMessage(new TextComponentString("The player name given is not a valid argument. It is either mis-spelled, or the player specified is not currently on the server."));                        }
                    }
                }
                case "mob":
                {
                    switch(args[1])
                    {
                        case "all":
                        {
                            for(Class<? extends EntityLiving> entityClass : PASSIVE_MOBS)
                            {
                                for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                {
                                    entity.onKillCommand();
                                }
                            }

                            for(Class<? extends EntityLiving> entityClass : HOSTILE_MOBS)
                            {
                                for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                {
                                    entity.onKillCommand();
                                }
                            }
                        }
                        case "hostile":
                        {
                            if(args[2].equals(""))
                            {
                                for(Class<? extends EntityLiving> entityClass : HOSTILE_MOBS)
                                {
                                    for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                    {
                                        entity.onKillCommand();
                                    }
                                }
                            }
                            else
                            {
                                for(Class<? extends EntityLiving> entityClass : HOSTILE_MOBS)
                                {
                                    for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                    {
                                        if(args[2].equals(entityClass.getSimpleName().toLowerCase().replace("entity", "") + "_" + entity.getPersistentID().toString()));
                                        {
                                            entity.onKillCommand();
                                        }
                                    }
                                }
                            }
                        }
                        case "passive":
                        {
                            if(args[2].equals(""))
                            {
                                for(Class<? extends EntityLiving> entityClass : PASSIVE_MOBS)
                                {
                                    for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                    {
                                        entity.onKillCommand();
                                    }
                                }
                            }
                            else
                            {
                                for(Class<? extends EntityLiving> entityClass : PASSIVE_MOBS)
                                {
                                    for(EntityLiving entity : server.getEntityWorld().getEntities(entityClass, null))
                                    {
                                        if(args[2].equals(entityClass.getSimpleName().toLowerCase().replace("entity", "") + "_" + entity.getPersistentID().toString()));
                                        {
                                            entity.onKillCommand();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                default: {}
            }
        }

    }
}
