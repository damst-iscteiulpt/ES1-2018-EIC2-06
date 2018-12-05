package Tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import SocialNetworks.Gmail;

public class TestGmail {

	
	/**
	 * verifica se o e-mail foi criado ou nao
	 */
	@Test
	public void createanemail () {
		Gmail test = new Gmail ("alberto", "abc");
		assertTrue(test != null);
	}
	
	/**
	 * verifica se foi estabelicida uma ligaçao
	 */
	public void testConection() {
		
	}
	
	/**
	 * verifica se foi enviado o email
	 */
	public boolean testsendemail() {
		return true;
	}
	
	/**
	 * verifica se o gmail fechou
	 */
	public boolean testclose() {
		return true;
	}
	
}
