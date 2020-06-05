package net.slexom.earthtojavamobs.fluid;

import net.minecraft.fluid.IFluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class MudFluidSource extends ForgeFlowingFluid
{
    public MudFluidSource(Properties properties)
    {
        super(properties);
    }

    public int getLevel(IFluidState state) {
        return 6;
    }

    public boolean isSource(IFluidState state) {
        return true;
    }
}