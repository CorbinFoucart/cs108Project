package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class PerformanceTest {

	@Test
	public void Timingtest() {
		Quiz q = new Quiz("Doggies", false, false, false, "Bo");
		Performance perf = new Performance(q, "bo", 0);
		
		perf.startTimer();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		perf.stopTimer();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		perf.startTimer();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		perf.stopTimer();
		long time = perf.getTimeTaken();
		System.out.println(time);
	}

}
