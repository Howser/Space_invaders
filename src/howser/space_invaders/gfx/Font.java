package howser.space_invaders.gfx;

public class Font {
	private SpriteSheet fontSheet;

	// Top row of the sheet is lowercase
	// middle row is UPPERCASE
	// bottom row is num3r1c + symb0l5

	private String chars = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789.,:'\"!?$%()-+/= ";

	private int width;
	private int height;
	private int tileWidth;
	private int tileHeight;

	public Font(String fontPath) {
		fontSheet = new SpriteSheet(fontPath);
		this.width = fontSheet.width;
		this.height = fontSheet.height;
		this.tileWidth = width / 26;
		this.tileHeight = height / 3;
	}

	public Sprite[] getStringSprites(String s) {
		Sprite[] sprites = new Sprite[s.length()];
		for (int i = 0; i < s.length(); i++) {
			int charIndex = chars.indexOf(s.charAt(i));

			int mod = 0;
			if (s.charAt(i) == 'j' || s.charAt(i) == 'p' || s.charAt(i) == 'q'
					|| s.charAt(i) == 'g' || s.charAt(i) == 'y') {
				mod = 3;
			}
			sprites[i] = Sprite.getSpriteFromSheet(fontSheet, charIndex % 26
					* tileWidth, charIndex / 26 * tileHeight - mod, tileWidth,
					tileHeight + mod);
			if (mod == 3){
				for (int j = 0; j < mod*tileWidth; j++){
					sprites[i].getPixels()[j] = 0xffff00ff;
				}
			}
		}
		return sprites;
	}
}
