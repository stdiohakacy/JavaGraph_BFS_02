package BFS._02;

public class App {
	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		String rootURL = "https://www.haravan.com/";
		crawler.discoverWeb(rootURL);
	}
}
