package homework_03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {

	public static void main(String[] args) {

		String text = readTheBook("voina_i_mir.txt");
		int totalNumberOfCharacters = text.length();
		System.out.println("Number of characters in the text: " + totalNumberOfCharacters);
		int numberOfCharacters = text.length() / 10;

		String text1 = text.substring(0, numberOfCharacters);
		String text2 = text.substring(numberOfCharacters, numberOfCharacters * 2);
		String text3 = text.substring(numberOfCharacters * 2, numberOfCharacters * 3);
		String text4 = text.substring(numberOfCharacters * 3, numberOfCharacters * 4);
		String text5 = text.substring(numberOfCharacters * 4, numberOfCharacters * 5);
		String text6 = text.substring(numberOfCharacters * 5, numberOfCharacters * 6);
		String text7 = text.substring(numberOfCharacters * 6, numberOfCharacters * 7);
		String text8 = text.substring(numberOfCharacters * 7, numberOfCharacters * 8);
		String text9 = text.substring(numberOfCharacters * 8, numberOfCharacters * 9);
		String text10 = text.substring(numberOfCharacters * 9, numberOfCharacters * 10);

		ArrayList<Runnable> tasks = new ArrayList<>();
		tasks.add(new WarAndPeaceReaders("Ivan1", text1));
		tasks.add(new WarAndPeaceReaders("Ivan2", text2));
		tasks.add(new WarAndPeaceReaders("Ivan3", text3));
		tasks.add(new WarAndPeaceReaders("Ivan4", text4));
		tasks.add(new WarAndPeaceReaders("Ivan5", text5));
		tasks.add(new WarAndPeaceReaders("Ivan6", text6));
		tasks.add(new WarAndPeaceReaders("Ivan7", text7));
		tasks.add(new WarAndPeaceReaders("Ivan8", text8));
		tasks.add(new WarAndPeaceReaders("Ivan9", text9));
		tasks.add(new WarAndPeaceReaders("Ivan10", text10));

		@SuppressWarnings("rawtypes")
		ArrayList<Future> futures = new ArrayList<>();
		System.out.println("start!");
		long start = System.currentTimeMillis();

		ExecutorService projectManager = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (Runnable r : tasks) {
			futures.add(projectManager.submit(r));
		}

		boolean allThreadsDead = true;
		while (true) {

			for (Future<?> f : futures) {
				allThreadsDead &= f.isDone();
			}

			if (allThreadsDead) {
				break;
			}
			allThreadsDead = true;
		}
		projectManager.shutdown();
		System.out.println("It took " + (System.currentTimeMillis() - start) + " milliseconds for all threads to run and be terminated.");

	}

	private static String readTheBook(String filePath) {

		File file = new File(filePath);
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder(1569275);
		try {
			br = new BufferedReader(new FileReader(file));
			String contentLine = br.readLine();
			sb.append(contentLine);
			while (br.readLine() != null) {
				contentLine = br.readLine();
				sb.append(contentLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
