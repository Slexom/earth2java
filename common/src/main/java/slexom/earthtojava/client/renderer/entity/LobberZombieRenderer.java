package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.LobberZombieModel;
import slexom.earthtojava.entity.monster.LobberZombieEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class LobberZombieRenderer extends ZombieBaseEntityRenderer<LobberZombieEntity, LobberZombieModel<LobberZombieEntity>> {

	public LobberZombieRenderer(EntityRendererFactory.Context context) {
		this(context, EntityModelLayersInit.LOBBER_ZOMBIE_ENTITY_MODEL_LAYER, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR);
	}

	public LobberZombieRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, new LobberZombieModel<>(ctx.getPart(layer)), new LobberZombieModel<>(ctx.getPart(legsArmorLayer)), new LobberZombieModel<>(ctx.getPart(bodyArmorLayer)));
	}

	public Identifier getTexture(LobberZombieEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("zombie", RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("zombie", RegistryNames.LOBBER_ZOMBIE_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}