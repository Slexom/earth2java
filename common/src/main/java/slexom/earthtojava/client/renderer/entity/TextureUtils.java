package slexom.earthtojava.client.renderer.entity;

import net.minecraft.util.Identifier;
import slexom.earthtojava.utils.Utils;

import java.text.MessageFormat;

public class TextureUtils {
	private TextureUtils() {
	}

	public static Identifier getTextureIdentifier(String entityType, String registryName) {
		String resourceTexture = MessageFormat.format("textures/mobs/{0}/{1}/{1}.png", entityType, registryName);

		return Utils.modIdentifierOf(resourceTexture);
	}

	public static Identifier getTextureIdentifier(String entityType, String registryName, String modifier) {
		String resourceTexture = MessageFormat.format("textures/mobs/{0}/{1}/{1}_{2}.png", entityType, registryName, modifier);

		return Utils.modIdentifierOf(resourceTexture);
	}


}
