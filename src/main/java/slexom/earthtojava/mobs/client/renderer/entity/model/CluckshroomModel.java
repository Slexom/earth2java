package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import slexom.earthtojava.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomModel<T extends CluckshroomEntity> extends ChickenEntityModel<T> {

    public CluckshroomModel(ModelPart root) {
        super(root);
    }

    public ModelPart getHead() {
        return this.head;
    }
}
