package SocialNetworks;

import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public final class Twitter  {
	public static void main(String[] args) {
		// http://twitter4j.org
		// http://twitter4j.org/en/code-examples.html
		// https://www.youtube.com/watch?v=uYPmkzMpnxw
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("wIySB1tWdiYz6CxbinpmxwmyC")
        	  .setOAuthConsumerSecret("WWOQ52V9sfSXOgJa8aun0JU5JELW6E02zNFGBrKPEJHGze13ch")
        	  .setOAuthAccessToken("1064583685142384642-DXSOsNuttiu832IlGwfmurMRdPPi8U")
        	  .setOAuthAccessTokenSecret("UtI1YwWI21I38XkrnllX1HFhjMSG5cK1XXQCjVL0aqm73");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	twitter4j.Twitter twitter = tf.getInstance();        		
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("------------------------\n Showing home timeline \n------------------------");
    		int counter=0;
    		int counterTotal = 0;
            for (Status status : statuses) {
				// Filters only tweets from user "catarina"
				if (status.getUser().getName() != null && status.getUser().getName().contains("9GAG")) {
					System.out.println(status.getUser().getName() + ":" + status.getText());
					counter++;
				}
				counterTotal++;
            }
    		System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { System.out.println(e.getMessage()); }
     }
}