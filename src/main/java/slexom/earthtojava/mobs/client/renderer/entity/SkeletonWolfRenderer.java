package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.model.SkeletonWolfModel;
import slexom.earthtojava.mobs.entity.monster.SkeletonWolfEntity;

@Environment(EnvType.CLIENT)
public class SkeletonWolfRenderer extends MobEntityRenderer<SkeletonWolfEntity, SkeletonWolfModel<SkeletonWolfEntity>> {

    public SkeletonWolfRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new SkeletonWolfModel<>(), 0.5F);
    }

    protected float handleRotationFloat(SkeletonWolfEntity livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    public Identifier getTexture(SkeletonWolfEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/wolf/skeleton_wolf/skeleton_wolf.png");
        Identifier textureAngry = new Identifier("earthtojavamobs:textures/mobs/wolf/skeleton_wolf/skeleton_wolf_angry.png");
        return entity.isAngry() ? textureAngry : texture;
    }

}
