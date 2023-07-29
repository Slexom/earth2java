package slexom.earthtojava.init.renderer;

public class RendererInit {
	private RendererInit() {
		throw new IllegalStateException("Utility class");
	}

	public static void init() {
		BlockRendererInit.init();
		EntityRendererInit.init();
	}
}
