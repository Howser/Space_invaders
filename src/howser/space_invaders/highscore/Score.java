package howser.space_invaders.highscore;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;
	public int score;

	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}
}
