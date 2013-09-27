package twitter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Streaming {
	private static final String CONSUMER_KEY = "";
	private static final String CONSUMER_SECRET = "";
	private static final String ACCESS_TOKEN = "";
	private static final String ACCESS_TOKEN_SECRET = "";

	static class MyStatusListener implements StatusListener {

		public void onStatus(Status status) {
			System.out.println("@" + status.getUser().getScreenName() + " | " + status.getText() + " 【 https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId() + " 】");
			//ex. @x71713 | ヤフーが、君の答えを聞こう。 http://yahoo.jp/37l7Cb  #バルス 【 https://twitter.com/x71713/status/363309232159854593 】
		}

		public void onDeletionNotice(StatusDeletionNotice sdn) {
			System.out.println("onDeletionNotice.");
		}

		public void onTrackLimitationNotice(int i) {
			System.out.println("onTrackLimitationNotice.(" + i + ")");
		}

		public void onScrubGeo(long lat, long lng) {
			System.out.println("onScrubGeo.(" + lat + ", " + lng + ")");
		}

		public void onException(Exception excptn) {
			System.out.println("onException.");
		}

		@Override
		public void onStallWarning(StallWarning arg0) {
			System.out.println("onStallWarning.");
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration configuration = new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET).setOAuthAccessToken(ACCESS_TOKEN).setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET).build();

		TwitterStream twStream = new TwitterStreamFactory(configuration).getInstance();
		twStream.addListener(new MyStatusListener());

		twStream.sample();
	}
}