

import java.util.HashMap;
import java.util.Map;

/**
 * @author A
 * This class stores the HTML tag name and they details of keywords and the frequency of their occurrence in this particular tag
 */
public class KeywordsInHTMLTag
{
	private String nameOfTag = "";
	private Map<String, Integer> keywordAndFrequency = new HashMap<String, Integer>();
	
	/**Initialize the class with the the string of the name of HTML tag
	 * @param tagName
	 */
	public KeywordsInHTMLTag(String tagName)
	{
		this.setNameOfTag(tagName);
	}
	
	/** Add/Update the table of keyword and the frequency of their occurrence in this particular tag
	 * @param keyword
	 * @param frequency
	 */
	public void updateKeywordFrequencyMap(String keyword, int frequency)
	{
		int prevCount = 0;
		if(keywordAndFrequency.containsKey(keyword))
		{
			prevCount = keywordAndFrequency.get(keyword);
		}
		keywordAndFrequency.put(keyword, frequency + prevCount);
	}
	
	/** Return the Matching keywords and the frequency of their occurrence in this particular tag
	 * @return Map&gt;String, Integer&lt;
	 */
	public Map<String, Integer> getKeywordFrequencyMap()
	{
		return keywordAndFrequency;
	}

	/**
	 * @return the nameOfTag
	 */
	public String getNameOfTag() {
		return nameOfTag;
	}

	/**
	 * @param nameOfTag the nameOfTag to set
	 */
	public void setNameOfTag(String nameOfTag) {
		this.nameOfTag = nameOfTag;
	}
}
