package fr.slypy.advent;

import java.io.IOException;

public class AdventSubmitter {
	
	public static String webhook;
	
	public static void submit(String s) {
		
		DiscordWebhook dis = new DiscordWebhook("https://discord.com/api/webhooks/963774036616310844/-eLTBcEDGzNjiZjnLJO74K0p1PAb1FaNfIlc9_OzxPa-jN7F25ucS6yA5eiEiOq2IWOc");

		dis.setContent("Result obtained : " + s);
		
		try {

			dis.execute();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
	
}
