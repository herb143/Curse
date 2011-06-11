package me.hgilman.Curse;

import java.util.StringTokenizer;

public class CurseTranslator {

	private static char[] vowels ={'a', 'e', 'i', 'o', 'u'};
	private  StringTokenizer tokenizer;
	private String word;
	private String returnValue;
	
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
		int length = text.length();
	//	System.out.println(length);
		for(int x = 0; x < vowels.length; x++)
		{
			if(text.charAt(0) == vowels[x])
			{
		//		System.out.println("vowel found");
				text+="yay";
				return text;
			}
		}
		//System.out.println("flag1");
		text += text.charAt(0);
		//System.out.println("0 pos is" + text.charAt(0));
		
		text = text.substring(1, length);
		text+="ay";
		return text;
	}
	
	public String getTranslation()
	{
		return returnValue;
	}

}
