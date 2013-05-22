package howser.space_invaders.gfx;

public class Sprite {
	private final int TRANSPARENT_COLOUR = 0xffff00ff;
	private int[] pixels;
	private int width;
	private int height;

	public boolean mirrorX;
	public boolean mirrorY;

	public Sprite(int[] pixelData, int width, int height) {
		this.pixels = pixelData;
		this.width = width;
		this.height = height;
	}

	public Sprite(int[] pixelData, int width, int height, int colour, int scale) {
		this.pixels = pixelData;
		this.width = width;
		this.height = height;
		setTint(colour);
	}

	public static Sprite getSpriteFromSheet(SpriteSheet sheet, int x, int y,
			int w, int h) {
		int[] data = new int[w * h];

		for (int ya = 0; ya < h; ya++) {
			for (int xa = 0; xa < w; xa++) {
				data[xa + ya * w] = sheet.pixels[(x + xa) + (y + ya)
						* sheet.width];
			}
		}
		return new Sprite(data, w, h);
	}

	public void setTint(int colour) {
		for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] != TRANSPARENT_COLOUR) {
				int r = (colour >> 16) & 0xff;
				int g = (colour >> 8) & 0xff;
				int b = (colour) & 0xff;

				int sr = (pixels[i] >> 16) & 0xff;
				int sg = (pixels[i] >> 8) & 0xff;
				int sb = (pixels[i]) & 0xff;

				r = r * sr / 255;
				g = g * sg / 255;
				b = b * sb / 255;
				pixels[i] = (r << 16) + (g << 8) + (b);
			}
		}
	}

	public int[] getPixels() {
		if (!mirrorX && !mirrorY) {
			return pixels;
		}
		int[] returnData = new int[pixels.length];
		if (mirrorX) {
			int dataIndex = 0;
			for (int y = 0; y < height; y++) {
				for (int x = width - 1; x > -1; x--) {
					returnData[dataIndex] = pixels[x + y * width];
					dataIndex++;
				}
			}
		}
		if (mirrorY) {
			int[] dataToMirror = pixels.clone();
			if (mirrorX) {
				dataToMirror = returnData.clone();
			}
			int dataIndex = 0;
			for (int y = height - 1; y > -1; y--) {
				for (int x = 0; x < width; x++) {
					returnData[dataIndex] = dataToMirror[x + y * width];
					dataIndex++;
				}
			}
		}

		return returnData;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
