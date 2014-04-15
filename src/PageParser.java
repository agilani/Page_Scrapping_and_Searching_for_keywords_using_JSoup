import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;


public class PageParser implements NodeVisitor {
	
	ArrayList<String> keywords;
	
	public PageParser(ArrayList<String> keywords)
	{
		this.keywords = keywords;
	}

	@Override
	public void head(Node arg0, int arg1) {
		if(arg0 instanceof Element)
		{
			String tagname = arg0.nodeName();
			System.out.println("Tag name: " + tagname);
			String content = "";
			if(tagname.equalsIgnoreCase("meta"))
			{
				Attributes attributes = ((Element) arg0).attributes();
				if(attributes.get("name").equalsIgnoreCase("keyword"))
				{
					content = attributes.get("content");
				}
			}
			else
			{
				content = ((Element) arg0).ownText();
			}
			System.out.println(content.toLowerCase());
			searchKeywords(tagname, content);
		}
	}

	@Override
	public void tail(Node arg0, int arg1) {
		 //System.out.println("Exiting tag: " + arg0.nodeName());
	}

	
	private void searchKeywords(String nodeName, String contents)
	{
		for(String keyword : keywords)
		{
			int lastIndex = 0;
			int count = 0;

			while(lastIndex != -1)
			{
			       lastIndex = contents.indexOf(keyword,lastIndex);
			       if( lastIndex != -1)
			       {
			             count ++;
			             lastIndex+=keyword.length();
			      }
			}
			
			if(count>0)
			{
				Map<String, Integer> tagAndOccuranceFrequency = new HashMap<String, Integer>();
				if(Runner.keywordsFound.containsKey(keyword))
				{
					tagAndOccuranceFrequency = Runner.keywordsFound.get(keyword);
					if(tagAndOccuranceFrequency.containsKey(nodeName))
					{
						Integer freq = tagAndOccuranceFrequency.get(nodeName);
						count = count + freq;
					}
				}
				tagAndOccuranceFrequency.put(nodeName, count);
				Runner.keywordsFound.put(keyword, tagAndOccuranceFrequency);
			}
		}
	}
	
}
