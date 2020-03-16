import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.swing.JLabel;

public class DynamicClock {

	private JLabel time;

	public DynamicClock(JLabel time) {
		this.time = time;
	}
	
	public DynamicClock() {
		
	}

	public void run() {

		DecimalFormat f = new DecimalFormat("00");

		Thread clock = new Thread() {
			public void run() {
				try {
					while (true) {
						LocalDateTime currentDate = LocalDateTime.now();

						int day = currentDate.getDayOfMonth();
						int month = currentDate.getMonthValue();
						int year = currentDate.getYear();

						int hour = currentDate.getHour();
						int minute = currentDate.getMinute();
						int second = currentDate.getSecond();

						time.setText(f.format(hour) + ":" + f.format(minute) + ":" + f.format(second) + " "
								+ f.format(day) + "/" + f.format(month) + "/" + year);

						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		clock.start();
	}
}