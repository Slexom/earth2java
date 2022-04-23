package slexom.earthtojava;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import slexom.earthtojava.init.EntityTypesInit;

public class Earth2JavaEarlyRiser implements Runnable {

    @Override
    public void run() {
        MappingResolver mappingResolver = FabricLoader.getInstance().getMappingResolver();
        String Raid = mappingResolver.mapClassName("intermediary", "net.minecraft.class_3765$class_3766");
        String EntityType = 'L' + mappingResolver.mapClassName("intermediary", "net.minecraft.class_1299") + ';';

        ClassTinkerers
                .enumBuilder(Raid, EntityType, int[].class)
                .addEnum("VILER_WITCH", () -> new Object[]{EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, new int[]{0, 0, 0, 0, 0, 0, 1, 1}})
                .build();
    }

}
