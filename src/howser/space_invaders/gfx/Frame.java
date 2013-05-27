package howser.space_invaders.gfx;

public class Frame {

	public int[] pixels;
	private int width;
	private int height;
	public final int TRANSPARENT_COLOUR1 = 0xffff00ff;
	public final int TRANSPARENT_COLOUR2 = 0xff57007F;

	public Frame(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void getPixels(int[] pixelArray) {
		for (int i = 0; i < pixelArray.length; i++) {
			pixelArray[i] = pixels[i];
		}
	}

	public void renderToFrame(int[] data, int xa, int ya, int w, int h) {
		int dataIndex = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (data[dataIndex] != TRANSPARENT_COLOUR1
						&& data[dataIndex] != TRANSPARENT_COLOUR2) {
					if (0 >= xa + x || xa + x >= width || 0 >= ya + y
							|| ya + y >= height) {
						dataIndex++;
						continue;
					}
					pixels[(xa + x) + (ya + y) * width] = data[dataIndex];
				}
				dataIndex++;
			}
		}
	}

	public void renderToFrame(Sprite sprite, int x, int y, float scale) {
		int[] baseData = sprite.getPixels();
		int nW = (int) (sprite.getWidth() * scale);
		int nH = (int) (sprite.getHeight() * scale);
		int oW = sprite.getWidth();
		int oH = sprite.getHeight();

		int[] newData = new int[nW * nH];

		for (int ya = 0; ya < nH; ya++) {
			for (int xa = 0; xa < nW; xa++) {
				newData[xa + ya * nW] = baseData[(xa * oW / nW)
						+ (ya * oH / nH) * oW];
			}
		}
		renderToFrame(newData, x, y, nW, nH);
	}

	public void renderString(String string, Font font, int x, int y, int colour) {
		Sprite[] sprites = font.getStringSprites(string);

		for (int i = 0; i < sprites.length; i++) {
			sprites[i].setTint(colour);
			renderToFrame(sprites[i].getPixels(),
					x + i * sprites[i].getWidth(), y, sprites[i].getWidth(),
					sprites[i].getHeight());
		}
	}

	public void renderRectangle(int x, int y, int width, int height, int colour) {
		int[] data = new int[width * height];
		for (int i = 0; i < data.length; i++) {
			data[i] = colour;
		}
		renderToFrame(data, x, y, width, height);
	}

	public void renderBorderedRectangle(int x, int y, int w, int h,
			int borderColour, int fillColour) {
		int[] data = new int[w * h];
		for (int yy = 0; yy < h; yy++) {
			for (int xx = 0; xx < w; xx++) {
				if (yy == 0 || yy == h - 1 || xx == 0 || xx == w-1){
					data[yy*w + xx] = borderColour;
				} else {
					data[yy*w + xx] = fillColour;
				}
			}
		}
		renderToFrame(data, x, y, w, h);
	}

	public void clear(int colour) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = colour;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
