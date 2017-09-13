package homework_01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public final class WarAndPeace {

	private static File file = new File("voina_i_mir.txt");
	private static Map<String, Integer> map;

	static {
		try {
			map = populateMap(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private WarAndPeace() {

	}

	public static String printTheLongestWord() throws IOException {

		String longestWord = "";
		for (String word : map.keySet()) {
			if ((longestWord.length() < word.length()) && (word.chars().allMatch(Character::isLetter))) {
				longestWord = word;
			}
		}
		System.out.println("The longes word: " + longestWord);
		return longestWord;
	}

	public static File createFilesForWordsWithDifferentLength(String longestWord) throws IOException {

		File mainDirectory = new File("ParentDirectory");
		if (!mainDirectory.exists()) {
			mainDirectory.mkdir();
		}
		for (int i = 0; i < longestWord.length(); i++) {
			File file = new File("ParentDirectory" + File.separator + "Contains_" + (i + 1) + "_character_words.");
			if (!file.exists()) {
				file.createNewFile();
			}
		}

		return mainDirectory;
	}

	public static void populateFilesWithWords(File mainDirectory) throws IOException {

		ArrayList<Set<String>> arrayListOfTreeSets = new ArrayList<>();
		for (int i = 0; i < mainDirectory.listFiles().length; i++) {
			arrayListOfTreeSets.add(new TreeSet<>((a, b) -> a.compareTo(b)));
		}

		for (String word : map.keySet()) {
			if (word.chars().allMatch(Character::isLetter) && word.length() > 0) {
				arrayListOfTreeSets.get(word.length() - 1).add(word);
			}
		}

		FileWriter fw;
		for (int i = 0; i < arrayListOfTreeSets.size(); i++) {
			fw = new FileWriter("ParentDirectory" + File.separator + "Contains_" + (i + 1) + "_character_words.");
			fw.write(arrayListOfTreeSets.get(i).toString());
			fw.flush();
			fw.close();
		}
	}

	public static void printMostOftenRepeatedWord() throws IOException {

		Set<Entry<String, Integer>> entrySet = new TreeSet<>((a, b) -> b.getValue() - a.getValue());
		entrySet.addAll(map.entrySet());
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(
					"The most often word used: " + entry.getKey() + ". It was used: " + entry.getValue() + " times.");
			break;
		}
	}

	private static Map<String, Integer> populateMap(File file) throws IOException {

		Map<String, Integer> map = new HashMap<>();
		Scanner sc = new Scanner(file);
		String word;
		while (sc.hasNext()) {
			word = sc.next();
			if (!map.containsKey(word)) {
				map.put(word, 1);
			} else {
				Integer count = map.get(word);
				map.put(word, count + 1);
			}
		}
		sc.close();
		return map;
	}

	public static void checkTwoWords(String word1, String word2) throws IOException {

		System.out.println("The word: \"" + word1 + "\" appears in the text " + map.get(word1) + " times.");
		System.out.println("The word: \"" + word2 + "\" appears in the text " + map.get(word2) + " times.");
	}
}
