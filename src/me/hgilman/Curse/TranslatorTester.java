package me.hgilman.Curse;

import java.util.Scanner;

public class TranslatorTester {

	private static Scanner in = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Translator Tester is ready");
		System.out.println("Type your input or \"!quit!\" to quit");
		String input = in.nextLine();
		while(!input.equalsIgnoreCase("!quit!")) {
			System.out.println("Input: \"" + input + "\"");
			CurseTranslator ct = new CurseTranslator(input);
			System.out.println("Output: \"" + ct.getTranslation() + "\"");
            input = in.nextLine();
        }
		System.out.println("Translator Tester has been quit");
	}

}
