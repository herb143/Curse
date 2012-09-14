package me.hgilman.Curse;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurseTranslator {

	private static char[] vowels = {'a', '�', '�', '�', '�', '�', 'e', '�', '�', '�', '�', 'i', '�', '�', '�', '�', 'o', '�', '�', '�', '�', '�', 'u', '�', '�', '�', '�'};
	private static char[] extendedVowels = {'a', '�', '�', '�', '�', '�', 'e', '�', '�', '�', '�', 'i', '�', '�', '�', '�', 'o', '�', '�', '�', '�', '�', 'u', '�', '�', '�', '�', '�', 'y'};
	Matcher matcher;
	private String word;
	private String nonwordStuff;
	private StringBuilder returnValue = new StringBuilder();
	
	static {
		Arrays.sort(vowels);
		Arrays.sort(extendedVowels);
	}
	
	public CurseTranslator(String text)
	{
		matcher = Pattern.compile("([^\\pL]+)|(\\pL+)").matcher(text); //matches all sequences of alphabetic characters preceded by
																		 //a non-alphabetic character, I add the space to the front of
		while(matcher.find())											 //the text to make sure it sees the first word
		{
			word = matcher.group(2);
			nonwordStuff = matcher.group(1);
			returnValue.append((nonwordStuff != null ? nonwordStuff : "") + shifter(word));
		}
	}
	
	private static String shifter(String text)
	{
		if(text == null || text.length() < 1) return "";
	//	text = tokenizer.nextToken();
	//	System.out.println(length);
		if(Arrays.binarySearch(vowels, text.charAt(0)) >= 0) {
		//		System.out.println("vowel found");
				text+="yay";
				return text;
		} else if(text.toLowerCase().charAt(0) == 'q' && text.toLowerCase().charAt(1) == 'u') {
			text = text.substring(2);
			text += "quay";
			return text;
		}
		int i = 1;
		text += text.toLowerCase().charAt(0);
		for(; Arrays.binarySearch(extendedVowels, text.charAt(i)) < 0; i++) {
			//System.out.println("flag1");
			text += text.toLowerCase().charAt(i);
			//System.out.println("0 pos is" + text.charAt(0));
		}
		text = text.substring(i);
		text+="ay";
		return text;
	}
	
	public String getTranslation()
	{
		return returnValue.toString();
	}

}
