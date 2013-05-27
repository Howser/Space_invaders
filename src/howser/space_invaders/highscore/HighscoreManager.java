package howser.space_invaders.highscore;

import howser.space_invaders.gfx.Colour;
import howser.space_invaders.gfx.Font;
import howser.space_invaders.gfx.Frame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HighscoreManager {
	private Score[] scores;
	private String highscoreFile;
	private Font font;

	public HighscoreManager(String highscoreFile, Font font) {
		this.highscoreFile = highscoreFile;
		this.font = font;
		loadHighscoreFile();
	}

	public void loadHighscoreFile() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new FileInputStream(highscoreFile));
			scores = (Score[]) inputStream.readObject();
			inputStream.close();
		} catch (FileNotFoundException e) {
			scores = new Score[10];
			for (int i= 0; i < scores.length; i++){
				scores[i] = new Score("", 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveHighscoreFile() {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new FileOutputStream(highscoreFile));
			outputStream.writeObject(scores);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getScoreThreshold() {
		return scores[scores.length - 1].score;
	}

	public void addHighscore(Score score) {
		for (int i = 0; i < scores.length; i++) {
			if (score.score > scores[i].score) {
				Score t = scores[i];
				scores[i] = score;
				for (int j = i + 1; j < scores.length - 1; j++) {
					Score tt = scores[j];
					scores[j] = t;
					t = tt;
				}
				break;
			}
		}
	}

	public void render(Frame frame) {
		frame.renderString("Highscores", font, 40, 10, Colour.RED);
		for (int i = 0; i < scores.length; i++) {
			if (scores != null) {
				frame.renderString(scores[i].name, font, 40, 30 + (i * 10),
						Colour.WHITE);
				frame.renderString("" + scores[i].score, font, 200,
						30 + (i * 10), Colour.WHITE);
			}
		}
	}
}
