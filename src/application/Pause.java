package application;

public class Pause {
	
	private long stopDur, stopMil;
	private boolean pauseGame;
	
	public Pause() {
		stopDur = 1000;
		stopMil = -stopDur;
		pauseGame = false;
	}
	
	public void setStop() {
		stopMil = System.currentTimeMillis();
	}
	
	public void setPauseGame(boolean pg) {
		pauseGame = pg;
	}
	
	public boolean getPauseGame() {
		return pauseGame;
	}
	
	public boolean paused() {
		return pauseGame || System.currentTimeMillis() - stopMil < stopDur;
	}
}
