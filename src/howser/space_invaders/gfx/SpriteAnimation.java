package howser.space_invaders.gfx;

public class SpriteAnimation {
	private Sprite[] frames;
	private int currentFrame;

	private int frameDelay;
	private int frameDelayTimer;
	private boolean playing;
	private boolean loop;

	public SpriteAnimation(Sprite[] frames, int frameDelay) {
		this.frames = frames;
		this.frameDelay = frameDelay;
		frameDelayTimer = 0;
		currentFrame = 0;
		playing = false;
		loop = false;
	}

	public void play() {
		currentFrame = 0;
		playing = true;
		loop = false;
	}

	public void loop(){
		currentFrame = 0;
		playing = true;
		loop = true;
	}
	
	public void stop() {
		playing = false;
		loop = false;
	}

	public void tick() {
		if (playing) {
			frameDelayTimer++;
			if (frameDelay < frameDelayTimer) {
				frameDelayTimer = 0;
				currentFrame++;
				if (currentFrame >= frames.length && loop) {
					currentFrame = 0;
				} else if (currentFrame >= frames.length) {
					playing = false;
					currentFrame = 0;
				}
			}
		}
	}

	public void render(Frame frame, int x, int y, float scale) {
		frame.renderToFrame(frames[currentFrame], x, y, scale);
	}
	
	public boolean isPlaying(){
		return playing;
	}
}
