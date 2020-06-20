package net.slexom.earthtojavamobs.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;

public class EntityContainer<T extends Entity> {
    public String registryName;
    public RegistryObject<? extends EntityType<? extends Entity>> registryObject;
    public int eggPrimaryColor;
    public int eggSecondaryColor;
    public String[] spawnBiomes;

    public EntityContainer(String registryName, RegistryObject<? extends EntityType<? extends Entity>> registryObject, int eggPrimaryColor, int eggSecondaryColor, String[] spawnBiomes) {
        this.registryName = registryName;
        this.registryObject = registryObject;
        this.eggPrimaryColor = eggPrimaryColor;
        this.eggSecondaryColor = eggSecondaryColor;
        this.spawnBiomes = spawnBiomes;
    }

    public static final class Builder<T extends Entity> {
        public String registryName = "";
        public  RegistryObject<? extends EntityType<? extends Entity>> registryObject = null;
        public int eggPrimaryColor = 0x000000;
        public int eggSecondaryColor = 0x000000;
        public String[] spawnBiomes = null;

        private Builder() {  }

        public static <T extends Entity> Builder<T> create() {
            return new Builder<>();
        }

        public Builder<T> withRegistryName(String registryName) {
            this.registryName = registryName;
            return this;
        }

        public Builder<T> withRegistryObject(RegistryObject<? extends EntityType<? extends Entity>> registryObject) {
            this.registryObject = registryObject;
            return this;
        }

        public Builder<T> withEggPrimaryColor(int eggPrimaryColor) {
            this.eggPrimaryColor = eggPrimaryColor;
            return this;
        }

        public Builder<T> withEggSecondaryColor(int eggSecondaryColor) {
            this.eggSecondaryColor = eggSecondaryColor;
            return this;
        }

        public Builder<T> withSpawnBiomes(String[] spawnBiomes) {
            this.spawnBiomes = spawnBiomes;
            return this;
        }

        public EntityContainer<T> build() {
            return new EntityContainer<>(this.registryName, this.registryObject, this.eggPrimaryColor, this.eggSecondaryColor, this.spawnBiomes);
        }


    }
}