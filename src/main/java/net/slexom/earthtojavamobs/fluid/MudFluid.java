package net.slexom.earthtojavamobs.fluid;


import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.slexom.earthtojavamobs.EarthtojavamobsModElements;

import java.util.Random;

@EarthtojavamobsModElements.ModElement.Tag
public class MudFluid extends EarthtojavamobsModElements.ModElement {
    @ObjectHolder("earthtojavamobs:mud_fluid")
    public static final FlowingFluidBlock block = null;
    @ObjectHolder("earthtojavamobs:mud_fluid_bucket")
    public static final Item bucket = null;
    private FlowingFluid flowing = null;
    private FlowingFluid still = null;
    private ForgeFlowingFluid.Properties fluidproperties = null;

    public MudFluid(EarthtojavamobsModElements instance) {
        super(instance, 6);
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void registerFluids(RegistryEvent.Register<Fluid> event) {
        event.getRegistry().register(still);
        event.getRegistry().register(flowing);
    }

    @Override
    public void clientLoad(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(still, RenderType.getSolid());
        RenderTypeLookup.setRenderLayer(flowing, RenderType.getSolid());
    }


    @Override
    public void initElements() {
        fluidproperties = new ForgeFlowingFluid.Properties(
                () -> still,
                () -> flowing,
                FluidAttributes.builder(
                        new ResourceLocation("earthtojavamobs", "/fluids/mud_still"),
                        new ResourceLocation("earthtojavamobs", "/fluids/mud_flow")
                )
                        .luminosity(0)
                        .density(1800)
                //.overlay(new ResourceLocation("earthtojavamobs", "/fluids/mud_overlay"))
        )
                .slopeFindDistance(4)
                .explosionResistance(100.F)
                .tickRate(20)
                .bucket(() -> bucket)
                .block(() -> block);
        still = (FlowingFluid) new ForgeFlowingFluid.Source(fluidproperties).setRegistryName("mud_fluid");
        flowing = (FlowingFluid) new ForgeFlowingFluid.Flowing(fluidproperties).setRegistryName("mud_fluid_flowing");
        elements.blocks.add(() -> new MudFlowingFluidBlock(still, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()) {
        }.setRegistryName("mud_fluid"));
        elements.items.add(() -> new BucketItem(still, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(ItemGroup.MISC))
                .setRegistryName("mud_fluid_bucket"));
    }


    @Override
    public void init(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                    biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, new LakesFeature(BlockStateFeatureConfig::deserialize) {
                        @Override
                        public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
                            DimensionType dimensionType = world.getDimension().getType();
                            boolean dimensionCriteria = false;
                            if (dimensionType == DimensionType.OVERWORLD)
                                dimensionCriteria = true;
                            if (!dimensionCriteria)
                                return false;
                            return super.place(world, generator, rand, pos, config);
                        }
                    }.withConfiguration(new BlockStateFeatureConfig(block.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(5))));
                }
            }
        });
    }


}
