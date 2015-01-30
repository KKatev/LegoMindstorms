import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;


public class SimpleThread extends FunctionThread {

	public SimpleThread(DataExchange d, DifferentialPilot p) {
		super(d, p);
		getDE().setDoBarcodeScan(true);
		LCD.drawString("SIMPLE start", 0, 1);
	}

	@Override
	public void run() {
		while(isRunning()) {
			getPilot().travel(DATA.travelDist);
			if (getDE().getChallengeEnded()) {
				LCD.drawString("TASK1 ENDED! " , 0, 1);
				DATA.taskNr++;
				DATA.tasks.get(DATA.taskNr).start();
				getDE().setDoBarcodeScan(false);
				return; 
			}
		}
	}

}
