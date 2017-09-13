package homework_01;

import java.io.File;
import java.io.IOException;

public class Demo {

	public static void main(String[] args) throws IOException {

		
		String longestWord = WarAndPeace.printTheLongestWord();
		File mainDirectory = WarAndPeace.createFilesForWordsWithDifferentLength(longestWord);
		WarAndPeace.populateFilesWithWords(mainDirectory);
		WarAndPeace.printMostOftenRepeatedWord();
		WarAndPeace.checkTwoWords("война", "мир");

	}

}
