package com.randysdoom.evolution.capability.player;

import javafx.util.Pair;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

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

    void putIntelligenceLevel(int VALUE);

    int getIntelligenceExperience();

    void putIntelligenceExperience(int VALUE, boolean CALCULATE);

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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getCharismaLevel(), getCharismaExperience(), VALUE);
                setCharismaLevel(PROCESSED_CHANGE.getKey());
                this.CHARISMA_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.CHARISMA_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getConstitutionLevel(), getConstitutionExperience(), VALUE);
                setConstitutionLevel(PROCESSED_CHANGE.getKey());
                this.CONSTITUTION_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.CONSTITUTION_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getDexterityLevel(), getDexterityExperience(), VALUE);
                setDexterityLevel(PROCESSED_CHANGE.getKey());
                this.DEXTERITY_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.DEXTERITY_EXPERIENCE = VALUE;
            }
        }

        @Override
        public int getIntelligenceLevel()
        {
            return INTELLIGENCE_LEVEL;
        }

        @Override
        public void putIntelligenceLevel(int VALUE)
        {
            this.INTELLIGENCE_LEVEL = VALUE;
        }

        @Override
        public int getIntelligenceExperience()
        {
            return INTELLIGENCE_EXPERIENCE;
        }

        @Override
        public void putIntelligenceExperience(int VALUE, boolean CALCULATE)
        {
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getIntelligenceLevel(), getIntelligenceExperience(), VALUE);
                putIntelligenceLevel(PROCESSED_CHANGE.getKey());
                this.INTELLIGENCE_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.INTELLIGENCE_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getLuckLevel(), getLuckExperience(), VALUE);
                setLuckLevel(PROCESSED_CHANGE.getKey());
                this.LUCK_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.LUCK_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getPerceptionLevel(), getPerceptionExperience(), VALUE);
                setPerceptionLevel(PROCESSED_CHANGE.getKey());
                this.PERCEPTION_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.PERCEPTION_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getStrengthLevel(), getStrengthExperience(), VALUE);
                setStrengthLevel(PROCESSED_CHANGE.getKey());
                this.STRENGTH_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.STRENGTH_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getWillpowerLevel(), getWillpowerExperience(), VALUE);
                setWillpowerLevel(PROCESSED_CHANGE.getKey());
                this.WILLPOWER_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.WILLPOWER_EXPERIENCE = VALUE;
            }
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
            if (CALCULATE)
            {
                Pair<Integer, Integer> PROCESSED_CHANGE = processExperienceChange(getWisdomLevel(), getWisdomExperience(), VALUE);
                setWisdomLevel(PROCESSED_CHANGE.getKey());
                this.WISDOM_EXPERIENCE = PROCESSED_CHANGE.getValue();
            } else
            {
                this.WISDOM_EXPERIENCE = VALUE;
            }
        }

        private Pair<Integer, Integer> processExperienceChange(int CURRENT_LEVEL, int CURRENT_EXPERIENCE, int CHANGE_VALUE)
        {
            if (CURRENT_LEVEL < 100)
            {
                int NEW_LEVEL_VALUE = CURRENT_LEVEL;
                int NEW_EXPERIENCE_VALUE;
                int MAX_EXPERIENCE_FOR_LEVEL = CURRENT_LEVEL > 1 ? (int) ((25 * CURRENT_LEVEL) * GLOBAL_EXPERIENCE_SCALE) : 25;
                if (CHANGE_VALUE > MAX_EXPERIENCE_FOR_LEVEL)
                {
                    NEW_LEVEL_VALUE++;
                    NEW_EXPERIENCE_VALUE = (CHANGE_VALUE - MAX_EXPERIENCE_FOR_LEVEL);
                } else
                {
                    NEW_EXPERIENCE_VALUE = CHANGE_VALUE;
                }
                return new Pair<>(NEW_LEVEL_VALUE, NEW_EXPERIENCE_VALUE);
            } else
            {
                int NEW_EXPERIENCE_VALUE = 0;
                if (CURRENT_EXPERIENCE > 0)
                {
                    NEW_EXPERIENCE_VALUE = 0 - CURRENT_EXPERIENCE;
                }
                return new Pair<>(CURRENT_LEVEL, NEW_EXPERIENCE_VALUE);
            }
        }

    }

    class Storage implements Capability.IStorage<IAttributes>
    {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IAttributes> capability, IAttributes instance, Direction side)
        {
            CompoundNBT nbtTag = new CompoundNBT();

            CompoundNBT charismaTag = new CompoundNBT();
            charismaTag.putInt("level ", instance.getCharismaLevel());
            charismaTag.putInt("experience", instance.getCharismaExperience());
            nbtTag.put("charisma", charismaTag);


            CompoundNBT constitutionTag = new CompoundNBT();
            constitutionTag.putInt("level", instance.getConstitutionLevel());
            constitutionTag.putInt("experience", instance.getConstitutionExperience());
            nbtTag.put("constitution", constitutionTag);


            CompoundNBT dexterityTag = new CompoundNBT();
            dexterityTag.putInt("level", instance.getDexterityLevel());
            dexterityTag.putInt("experience", instance.getDexterityExperience());
            nbtTag.put("dexterity", dexterityTag);


            CompoundNBT intelligenceTag = new CompoundNBT();
            intelligenceTag.putInt("level", instance.getIntelligenceLevel());
            intelligenceTag.putInt("experience", instance.getIntelligenceExperience());
            nbtTag.put("intelligence", intelligenceTag);


            CompoundNBT luckTag = new CompoundNBT();
            luckTag.putInt("level", instance.getLuckLevel());
            luckTag.putInt("experience", instance.getLuckExperience());
            nbtTag.put("luck", luckTag);


            CompoundNBT perceptionTag = new CompoundNBT();
            perceptionTag.putInt("level", instance.getPerceptionLevel());
            perceptionTag.putInt("experience", instance.getPerceptionExperience());
            nbtTag.put("perception", perceptionTag);


            CompoundNBT strengthTag = new CompoundNBT();
            strengthTag.putInt("level", instance.getStrengthLevel());
            strengthTag.putInt("experience", instance.getStrengthExperience());
            nbtTag.put("strength", strengthTag);


            CompoundNBT willpowerTag = new CompoundNBT();
            willpowerTag.putInt("level", instance.getWillpowerLevel());
            willpowerTag.putInt("experience", instance.getWillpowerExperience());
            nbtTag.put("willpower", willpowerTag);


            CompoundNBT wisdomTag = new CompoundNBT();
            wisdomTag.putInt("level", instance.getWisdomLevel());
            wisdomTag.putInt("experience", instance.getWisdomExperience());
            nbtTag.put("wisdom", wisdomTag);

            return nbtTag;
        }

        @Override
        public void readNBT(Capability<IAttributes> capability, IAttributes instance, Direction side, INBT nbt)
        {
            CompoundNBT nbtTag = (CompoundNBT) nbt;

            CompoundNBT charismaTag = nbtTag.getCompound("charisma");
            instance.setCharismaLevel(charismaTag.getInt("level"));
            instance.setCharismaExperience(charismaTag.getInt("experience"), false);

            CompoundNBT constitutionTag = nbtTag.getCompound("constitution");
            instance.setConstitutionLevel(constitutionTag.getInt("level"));
            instance.setConstitutionExperience(constitutionTag.getInt("experience"), false);

            CompoundNBT dexterityTag = nbtTag.getCompound("dexterity");
            instance.setDexterityLevel(dexterityTag.getInt("level"));
            instance.setDexterityExperience(dexterityTag.getInt("experience"), false);

            CompoundNBT intelligenceTag = nbtTag.getCompound("intelligence");
            instance.putIntelligenceLevel(intelligenceTag.getInt("level"));
            instance.putIntelligenceExperience(intelligenceTag.getInt("experience"), false);

            CompoundNBT luckTag = nbtTag.getCompound("luck");
            instance.setLuckLevel(luckTag.getInt("level"));
            instance.setLuckExperience(luckTag.getInt("experience"), false);

            CompoundNBT perceptionTag = nbtTag.getCompound("perception");
            instance.setPerceptionLevel(perceptionTag.getInt("level"));
            instance.setPerceptionExperience(perceptionTag.getInt("experience"), false);

            CompoundNBT strengthTag = nbtTag.getCompound("strength");
            instance.setStrengthLevel(strengthTag.getInt("level"));
            instance.setStrengthExperience(strengthTag.getInt("experience"), false);

            CompoundNBT willpowerTag = nbtTag.getCompound("willpower");
            instance.setWillpowerLevel(willpowerTag.getInt("level"));
            instance.setWillpowerExperience(willpowerTag.getInt("experience"), false);

            CompoundNBT wisdomTag = nbtTag.getCompound("wisdom");
            instance.setWisdomLevel(wisdomTag.getInt("level"));
            instance.setWisdomExperience(wisdomTag.getInt("experience"), false);
        }

    }

    class Provider implements ICapabilitySerializable<CompoundNBT>
    {

        @CapabilityInject(IAttributes.class)
        public static Capability<IAttributes> CAPABILITY = null;

        private IAttributes instance = CAPABILITY.getDefaultInstance();

        @Nullable
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing)
        {
            if (capability == CAPABILITY)
            {
                return (LazyOptional<T>) this.instance;
            } else return null;
        }

        @Override
        public CompoundNBT serializeNBT()
        {
            return (CompoundNBT) CAPABILITY.getStorage().writeNBT(CAPABILITY, this.instance, null);
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt)
        {
            CAPABILITY.getStorage().readNBT(CAPABILITY, this.instance, null, nbt);
        }

    }

}
