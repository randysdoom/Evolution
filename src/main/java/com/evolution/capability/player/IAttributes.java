package com.evolution.capability.player;

import javafx.util.Pair;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IAttributes
{

    int getCharismaLevel();

    void setCharismaLevel(int VALUE);

    int getCharismaExperience();

    void setCharismaExperience(int VALUE, boolean CALCULATE);

    int getConstitutionLevel();

    void setConstitutionLevel(int VALUE);

    int getConstitutionExperience();

    void setConstitutionExperience(int VALUE, boolean CALCULATE);

    int getDexterityLevel();

    void setDexterityLevel(int VALUE);

    int getDexterityExperience();

    void setDexterityExperience(int VALUE, boolean CALCULATE);

    int getIntelligenceLevel();

    void setIntelligenceLevel(int VALUE);

    int getIntelligenceExperience();

    void setIntelligenceExperience(int VALUE, boolean CALCULATE);

    int getLuckLevel();

    void setLuckLevel(int VALUE);

    int getLuckExperience();

    void setLuckExperience(int VALUE, boolean CALCULATE);

    int getPerceptionLevel();

    void setPerceptionLevel(int VALUE);

    int getPerceptionExperience();

    void setPerceptionExperience(int VALUE, boolean CALCULATE);

    int getStrengthLevel();

    void setStrengthLevel(int VALUE);

    int getStrengthExperience();

    void setStrengthExperience(int VALUE, boolean CALCULATE);

    int getWillpowerLevel();

    void setWillpowerLevel(int VALUE);

    int getWillpowerExperience();

    void setWillpowerExperience(int VALUE, boolean CALCULATE);

    int getWisdomLevel();

    void setWisdomLevel(int VALUE);

    int getWisdomExperience();

    void setWisdomExperience(int VALUE, boolean CALCULATE);

    class Impl implements IAttributes
    {

        int CHARISMA_LEVEL = 1;
        int CONSTITUTION_LEVEL = 1;
        int DEXTERITY_LEVEL = 1;
        int INTELLIGENCE_LEVEL = 1;
        int LUCK_LEVEL = 1;
        int PERCEPTION_LEVEL = 1;
        int STRENGTH_LEVEL = 1;
        int WILLPOWER_LEVEL = 1;
        int WISDOM_LEVEL = 1;

        int CHARISMA_EXPERIENCE = 0;
        int CONSTITUTION_EXPERIENCE = 0;
        int DEXTERITY_EXPERIENCE = 0;
        int INTELLIGENCE_EXPERIENCE = 0;
        int LUCK_EXPERIENCE = 0;
        int PERCEPTION_EXPERIENCE = 0;
        int STRENGTH_EXPERIENCE = 0;
        int WILLPOWER_EXPERIENCE = 0;
        int WISDOM_EXPERIENCE = 0;

        double GLOBAL_EXPERIENCE_SCALE = 1.25;

        @Override
        public int getCharismaLevel()
        {
            return CHARISMA_LEVEL;
        }

        @Override
        public void setCharismaLevel(int VALUE)
        {
            this.CHARISMA_LEVEL = VALUE;
        }

        @Override
        public int getCharismaExperience()
        {
            return CHARISMA_EXPERIENCE;
        }

        @Override
        public void setCharismaExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getCharismaLevel(), getCharismaExperience(), VALUE);
                setCharismaLevel(PROCESSED_CHANGE.getKey());
                this.CHARISMA_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.CHARISMA_EXPERIENCE = VALUE; }
        }

        @Override
        public int getConstitutionLevel()
        {
            return CONSTITUTION_LEVEL;
        }

        @Override
        public void setConstitutionLevel(int VALUE)
        {
            this.CONSTITUTION_LEVEL = VALUE;
        }

        @Override
        public int getConstitutionExperience()
        {
            return CONSTITUTION_EXPERIENCE;
        }

        @Override
        public void setConstitutionExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getConstitutionLevel(), getConstitutionExperience(), VALUE);
                setConstitutionLevel(PROCESSED_CHANGE.getKey());
                this.CONSTITUTION_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.CONSTITUTION_EXPERIENCE = VALUE; }
        }

        @Override
        public int getDexterityLevel()
        {
            return DEXTERITY_LEVEL;
        }

        @Override
        public void setDexterityLevel(int VALUE)
        {
            this.DEXTERITY_LEVEL = VALUE;
        }

        @Override
        public int getDexterityExperience()
        {
            return DEXTERITY_EXPERIENCE;
        }

        @Override
        public void setDexterityExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getDexterityLevel(), getDexterityExperience(), VALUE);
                setDexterityLevel(PROCESSED_CHANGE.getKey());
                this.DEXTERITY_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.DEXTERITY_EXPERIENCE = VALUE; }
        }

        @Override
        public int getIntelligenceLevel()
        {
            return INTELLIGENCE_LEVEL;
        }

        @Override
        public void setIntelligenceLevel(int VALUE)
        {
            this.INTELLIGENCE_LEVEL = VALUE;
        }

        @Override
        public int getIntelligenceExperience()
        {
            return INTELLIGENCE_EXPERIENCE;
        }

        @Override
        public void setIntelligenceExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getIntelligenceLevel(), getIntelligenceExperience(), VALUE);
                setIntelligenceLevel(PROCESSED_CHANGE.getKey());
                this.INTELLIGENCE_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.INTELLIGENCE_EXPERIENCE = VALUE; }
        }

        @Override
        public int getLuckLevel()
        {
            return LUCK_LEVEL;
        }

        @Override
        public void setLuckLevel(int VALUE)
        {
            this.LUCK_LEVEL = VALUE;
        }

        @Override
        public int getLuckExperience()
        {
            return LUCK_EXPERIENCE;
        }

        @Override
        public void setLuckExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getLuckLevel(), getLuckExperience(), VALUE);
                setLuckLevel(PROCESSED_CHANGE.getKey());
                this.LUCK_EXPERIENCE= PROCESSED_CHANGE.getValue();
            }else{ this.LUCK_EXPERIENCE = VALUE; }
        }

        @Override
        public int getPerceptionLevel()
        {
            return PERCEPTION_LEVEL;
        }

        @Override
        public void setPerceptionLevel(int VALUE)
        {
            this.PERCEPTION_LEVEL = VALUE;
        }

        @Override
        public int getPerceptionExperience()
        {
            return PERCEPTION_EXPERIENCE;
        }

        @Override
        public void setPerceptionExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getPerceptionLevel(), getPerceptionExperience(), VALUE);
                setPerceptionLevel(PROCESSED_CHANGE.getKey());
                this.PERCEPTION_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.PERCEPTION_EXPERIENCE = VALUE; }
        }

        @Override
        public int getStrengthLevel()
        {
            return STRENGTH_LEVEL;
        }

        @Override
        public void setStrengthLevel(int VALUE)
        {
            this.STRENGTH_LEVEL = VALUE;
        }

        @Override
        public int getStrengthExperience()
        {
            return STRENGTH_EXPERIENCE;
        }

        @Override
        public void setStrengthExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getStrengthLevel(), getStrengthExperience(), VALUE);
                setStrengthLevel(PROCESSED_CHANGE.getKey());
                this.STRENGTH_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.STRENGTH_EXPERIENCE = VALUE; }
        }

        @Override
        public int getWillpowerLevel()
        {
            return WILLPOWER_LEVEL;
        }

        @Override
        public void setWillpowerLevel(int VALUE)
        {
            this.WILLPOWER_LEVEL = VALUE;
        }

        @Override
        public int getWillpowerExperience()
        {
            return WILLPOWER_EXPERIENCE;
        }

        @Override
        public void setWillpowerExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getWillpowerLevel(), getWillpowerExperience(), VALUE);
                setWillpowerLevel(PROCESSED_CHANGE.getKey());
                this.WILLPOWER_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.WILLPOWER_EXPERIENCE = VALUE; }
        }

        @Override
        public int getWisdomLevel()
        {
            return WISDOM_LEVEL;
        }

        @Override
        public void setWisdomLevel(int VALUE)
        {
            this.WISDOM_LEVEL = VALUE;
        }

        @Override
        public int getWisdomExperience()
        {
            return WISDOM_EXPERIENCE;
        }

        @Override
        public void setWisdomExperience(int VALUE, boolean CALCULATE)
        {
            if(CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getWisdomLevel(), getWisdomExperience(), VALUE);
                setWisdomLevel(PROCESSED_CHANGE.getKey());
                this.WISDOM_EXPERIENCE = PROCESSED_CHANGE.getValue();
            }else{ this.WISDOM_EXPERIENCE = VALUE; }
        }

        private Pair<Integer, Integer> processExperienceChange(int CURRENT_LEVEL, int CURRENT_EXPERIENCE, int CHANGE_VALUE)
        {
            if(CURRENT_LEVEL < 100)
            {
                int NEW_LEVEL_VALUE = CURRENT_LEVEL;
                int NEW_EXPERIENCE_VALUE;
                int MAX_EXPERIENCE_FOR_LEVEL = CURRENT_LEVEL > 1 ? (int)((25 * CURRENT_LEVEL) * GLOBAL_EXPERIENCE_SCALE) : 25;
                if(CHANGE_VALUE > MAX_EXPERIENCE_FOR_LEVEL)
                {
                    NEW_LEVEL_VALUE++;
                    NEW_EXPERIENCE_VALUE = (CHANGE_VALUE - MAX_EXPERIENCE_FOR_LEVEL);
                }
                else{ NEW_EXPERIENCE_VALUE = CHANGE_VALUE; }
                return new Pair<>(NEW_LEVEL_VALUE, NEW_EXPERIENCE_VALUE);
            }
            else
            {
                int NEW_EXPERIENCE_VALUE = 0;
                if(CURRENT_EXPERIENCE > 0){ NEW_EXPERIENCE_VALUE = 0 - CURRENT_EXPERIENCE; }
                return new Pair<>(CURRENT_LEVEL, NEW_EXPERIENCE_VALUE);
            }
        }

    }

    class Storage implements Capability.IStorage<IAttributes>
    {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IAttributes> capability, IAttributes instance, EnumFacing side)
        {
            NBTTagCompound nbtTag = new NBTTagCompound();

            NBTTagCompound charismaTag = new NBTTagCompound();
            charismaTag.setInteger("level ", instance.getCharismaLevel());
            charismaTag.setInteger("experience", instance.getCharismaExperience());
            nbtTag.setTag("charisma", charismaTag);


            NBTTagCompound constitutionTag = new NBTTagCompound();
            constitutionTag.setInteger("level", instance.getConstitutionLevel());
            constitutionTag.setInteger("experience", instance.getConstitutionExperience());
            nbtTag.setTag("constitution", constitutionTag);


            NBTTagCompound dexterityTag = new NBTTagCompound();
            dexterityTag.setInteger("level", instance.getDexterityLevel());
            dexterityTag.setInteger("experience", instance.getDexterityExperience());
            nbtTag.setTag("dexterity", dexterityTag);


            NBTTagCompound intelligenceTag = new NBTTagCompound();
            intelligenceTag.setInteger("level", instance.getIntelligenceLevel());
            intelligenceTag.setInteger("experience", instance.getIntelligenceExperience());
            nbtTag.setTag("intelligence", intelligenceTag);


            NBTTagCompound luckTag = new NBTTagCompound();
            luckTag.setInteger("level", instance.getLuckLevel());
            luckTag.setInteger("experience", instance.getLuckExperience());
            nbtTag.setTag("luck", luckTag);


            NBTTagCompound perceptionTag = new NBTTagCompound();
            perceptionTag.setInteger("level", instance.getPerceptionLevel());
            perceptionTag.setInteger("experience", instance.getPerceptionExperience());
            nbtTag.setTag("perception", perceptionTag);


            NBTTagCompound strengthTag = new NBTTagCompound();
            strengthTag.setInteger("level", instance.getStrengthLevel());
            strengthTag.setInteger("experience", instance.getStrengthExperience());
            nbtTag.setTag("strength", strengthTag);


            NBTTagCompound willpowerTag = new NBTTagCompound();
            willpowerTag.setInteger("level", instance.getWillpowerLevel());
            willpowerTag.setInteger("experience", instance.getWillpowerExperience());
            nbtTag.setTag("willpower", willpowerTag);


            NBTTagCompound wisdomTag = new NBTTagCompound();
            wisdomTag.setInteger("level", instance.getWisdomLevel());
            wisdomTag.setInteger("experience", instance.getWisdomExperience());
            nbtTag.setTag("wisdom", wisdomTag);

            return nbtTag;
        }

        @Override
        public void readNBT(Capability<IAttributes> capability, IAttributes instance, EnumFacing side, NBTBase nbt)
        {
            NBTTagCompound nbtTag = (NBTTagCompound)nbt;

            NBTTagCompound charismaTag = nbtTag.getCompoundTag("charisma");
            instance.setCharismaLevel(charismaTag.getInteger("level"));
            instance.setCharismaExperience(charismaTag.getInteger("experience"), false);

            NBTTagCompound constitutionTag = nbtTag.getCompoundTag("constitution");
            instance.setConstitutionLevel(constitutionTag.getInteger("level"));
            instance.setConstitutionExperience(constitutionTag.getInteger("experience"), false);

            NBTTagCompound dexterityTag = nbtTag.getCompoundTag("dexterity");
            instance.setDexterityLevel(dexterityTag.getInteger("level"));
            instance.setDexterityExperience(dexterityTag.getInteger("experience"), false);

            NBTTagCompound intelligenceTag = nbtTag.getCompoundTag("intelligence");
            instance.setIntelligenceLevel(intelligenceTag.getInteger("level"));
            instance.setIntelligenceExperience(intelligenceTag.getInteger("experience"), false);

            NBTTagCompound luckTag = nbtTag.getCompoundTag("luck");
            instance.setLuckLevel(luckTag.getInteger("level"));
            instance.setLuckExperience(luckTag.getInteger("experience"), false);

            NBTTagCompound perceptionTag = nbtTag.getCompoundTag("perception");
            instance.setPerceptionLevel(perceptionTag.getInteger("level"));
            instance.setPerceptionExperience(perceptionTag.getInteger("experience"), false);

            NBTTagCompound strengthTag = nbtTag.getCompoundTag("strength");
            instance.setStrengthLevel(strengthTag.getInteger("level"));
            instance.setStrengthExperience(strengthTag.getInteger("experience"), false);

            NBTTagCompound willpowerTag = nbtTag.getCompoundTag("willpower");
            instance.setWillpowerLevel(willpowerTag.getInteger("level"));
            instance.setWillpowerExperience(willpowerTag.getInteger("experience"), false);

            NBTTagCompound wisdomTag = nbtTag.getCompoundTag("wisdom");
            instance.setWisdomLevel(wisdomTag.getInteger("level"));
            instance.setWisdomExperience(wisdomTag.getInteger("experience"), false);
        }

    }

    class Provider implements ICapabilitySerializable<NBTTagCompound>
    {

        @CapabilityInject(IAttributes.class)
        public static Capability<IAttributes> CAPABILITY = null;

        private IAttributes instance = CAPABILITY.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
        {
            return capability == CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
        {
            if(capability == CAPABILITY)
            {
                return CAPABILITY.cast(this.instance);
            }else return null;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            return (NBTTagCompound) CAPABILITY.getStorage().writeNBT(CAPABILITY, this.instance, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            CAPABILITY.getStorage().readNBT(CAPABILITY, this.instance, null, nbt);
        }

    }

}
