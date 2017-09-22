package homework_03;

import java.util.Scanner;

public class WarAndPeaceReaders implements Runnable {

	private String readerName;
	private long duration;
	private String textToRead;

	WarAndPeaceReaders(String name, String textToRead) {
		this.readerName = name;
		this.duration = 0;
		this.textToRead = textToRead;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(textToRead);
		while (sc.hasNextLine()) {
			sc.nextLine();
		}
		sc.close();
		duration = System.currentTimeMillis() - start;
		System.out.println("The reader " + readerName + " read his part of the book for " + duration + " milliseconds.");
	}

}
