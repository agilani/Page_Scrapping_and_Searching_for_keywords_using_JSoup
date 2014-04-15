import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeTraversor;


public class Runner {

	public static Map<String, Map<String, Integer>> keywordsFound = new HashMap<String, Map<String,Integer>>();
	
	public static void main(String[] args) throws IOException
	{
		ArrayList<String> keywords = new ArrayList<String>();
		
		keywords.add("am");
		keywords.add("you");
		keywords.add("h1");
		keywords.add("body");
		
		String html = "<html><head><meta name='keyword' content='you, body, h1, am'><meta name='description' content='Awesome Description Here'> <meta http-equiv='content-type' content='text/html;charset=UTF-8'><title>i am title</title></head><body>i am body <h1>h1 tag</h1>. I am body again</body></html>";
		//Document doc = Jsoup.connect("http://csb.stanford.edu/class/public/pages/sykes_webdesign/05_simple.html").get();
		Document doc = Jsoup.parse(html);
		PageParser pp = new PageParser(keywords);
		NodeTraversor nt = new NodeTraversor(pp);
		nt.traverse(doc);
		
		for(String keyword : keywords)
		{
			System.out.println("Keyword: " + keyword);
			if(keywordsFound.containsKey(keyword))
			{
				Map<String, Integer> map = keywordsFound.get(keyword);
				for(Entry<String, Integer> entrySet : map.entrySet())
				{
					System.out.println(entrySet.getValue() + " times in " + entrySet.getKey() + " tag");
				}
				System.out.println();
			}
			else
			{
				System.out.println("Nothing found\r\n");
			}
		}
	}
}
