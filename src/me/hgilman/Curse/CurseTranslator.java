package me.hgilman.Curse;

import java.util.Arrays;
import java.util.StringTokenizer;

public class CurseTranslator {

	private static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	private static char[] extendedVowels = {'a', 'e', 'i', 'o', 'u', 'y'};
	private  StringTokenizer tokenizer;
	private String word;
	private String returnValue;
	
	static {
		Arrays.sort(vowels);
		Arrays.sort(extendedVowels);
	}
	
	public CurseTranslator(String text)
	{
		tokenizer = new StringTokenizer(text);
		
		while(tokenizer.hasMoreTokens())
		{
			word = tokenizer.nextToken();
			if (returnValue != null)
			{
			returnValue = returnValue + " " + shifter(word);
			}
			else
			{
				returnValue = shifter(word);
			}
		}
	}
	
	private static String shifter(String text)
	{
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
		text += text.charAt(0);
		for(; Arrays.binarySearch(extendedVowels, text.charAt(i)) < 0; i++) {
			//System.out.println("flag1");
			text += text.charAt(i);
			//System.out.println("0 pos is" + text.charAt(0));
		}
		text = text.substring(i);
		text+="ay";
		return text;
	}
	
	public String getTranslation()
	{
		return returnValue;
	}

}
