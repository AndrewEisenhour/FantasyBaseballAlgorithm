import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class GetData {

	public static void main(String[] args) throws IOException {

		// Make a URL to the web page
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setPrintContentOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = webClient.getPage("https://fantasy.espn.com/baseball/players/projections");

			webClient.getCurrentWindow().getJobManager().removeAllJobs();
			webClient.close();

			String titleString = page.getTitleText();
			System.out.println(titleString);
			System.out.println(page.asNormalizedText());

			List<?> tableList = page
					.getByXPath("//a[@class='AnchorLink link clr-link pointer']");
			for (int i = 0; i < tableList.size(); i++) {
				HtmlAnchor link = (HtmlAnchor) tableList.get(i);
				System.out.println(link.toString());
				System.out.println(link.getVisibleText());
			}

		} catch (IOException e) {
			System.out.println("An error occurred: " + e);
		}
		FileWriter outfile = new FileWriter("C:\\Users\\aseis\\eclipse-workspace\\Fantasy\\src\\webData.txt");
	}
}