package SocialNetworks;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {
	
	private String accessToken;
	private FacebookClient client;
	private User user;
	
	public Facebook(String accessToken) {
		this.accessToken = accessToken;
		connect();
	}
	
	public void connect() {
		client = new DefaultFacebookClient(accessToken, Version.LATEST);
		user = client.fetchObject("me", User.class);
	}
	
	public boolean isConnected() {
		return false;
	}
	
	public Connection<Post> getPosts() {
		Connection<Post> result = client.fetchConnection("me/feed", Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			System.out.println(counterTotal);
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null) {
					System.out.println("---- Post " + counter5 + " ----");
					System.out.println("Id: " + "fb.com/" + aPost.getId());
					System.out.println("Message: " + aPost.getMessage());
					System.out.println("Created: " + aPost.getCreatedTime());
					counter5++;
				}
				counterTotal++;
			}
		}
		return result;
	}

}
