package Tests;

import SocialNetworks.Gmail;

public class TestGmail {

	
	/**
	 * @return verdadeiro ou falso, caso o gmail tenha sido creado ou nao
	 */
	public boolean createanemail () {
		Gmail test = new Gmail("alberto", "abc");
		if(test != null)
			return true;
		return false;
	}
	
	
}
