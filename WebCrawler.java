package BFS._02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
	private Queue<String> queue;
	private List<String> discoverWebsiteList;
	
	public WebCrawler() {
		this.queue = new LinkedList<>();
		this.discoverWebsiteList = new ArrayList<>();
	}
	
	public String readURL(String v) {
		String rawHTML = "";
		try {
			URL url = new URL(v);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				rawHTML += inputLine;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rawHTML;
	}
	
	public void discoverWeb(String root) {
		this.queue.add(root);
		this.discoverWebsiteList.add(root);
		while (!queue.isEmpty()) {
			String v = this.queue.remove();
			String rawHTML = readURL(v);
			
			String regexp = "http://(\\w+\\.)*(\\w+)";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(rawHTML);
			
			while (matcher.find()) {
				String actualURL = matcher.group();
				
				if(!discoverWebsiteList.contains(actualURL)) {
					discoverWebsiteList.add(actualURL);
					System.out.println("Has been found with URL : " + actualURL);
					queue.add(actualURL);
				}
			}
		}
	}
}
