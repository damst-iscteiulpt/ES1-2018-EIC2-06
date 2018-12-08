package GUI;

public enum Platform {
	FACEBOOK("facebookIcon.png", "facebook.jpg"), TWITTER("twitterIcon.png", "twitter.png"), EMAIL("gmailIcon.png", "gmail.jpg");
	
	private static String IMAGES_PATH = "images/";
	
	private String iconPath;
	private String backgroundImage;
	
	private Platform(String iconPath, String backgroundImage) {
		this.iconPath = iconPath;
		this.backgroundImage = backgroundImage;
	}
	
	public String getIconPath() {
		return IMAGES_PATH + iconPath;
	}
	
	public String getBackgroundImage() {
		return IMAGES_PATH + backgroundImage;
	}
	
	public String prettyName() {
		return name().charAt(0) + name().substring(1, name().length()).toLowerCase();
	}
}
