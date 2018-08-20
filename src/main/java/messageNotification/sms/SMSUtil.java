package messageNotification.sms;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class SMSUtil {

	private static String getURL(String recipient,String message) throws Exception {
		String requestUrl = "";
		try {
					requestUrl  = Settings.GATEWAY_URL+"/api?action=sendmessage&" +
					"username=" + URLEncoder.encode(Settings.GATEWAY_USERNAME, "UTF-8") +
					"&password=" + URLEncoder.encode(Settings.GATEWAY_PASSWORD, "UTF-8") +
					"&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
					"&messagetype=SMS:TEXT" +
					"&messagedata=" + URLEncoder.encode(message, "UTF-8") +
					"&originator=" + URLEncoder.encode(Settings.ORIGINATOR, "UTF-8") +
					"&serviceprovider=GSMModem1" +
	 				"&responseformat=html";
		}catch (Exception e) {
			throw new Exception("URL encoding exception");
		}
		return requestUrl;
	}
	
	public static void sendSMS(String recipient,String message) throws Exception {
		try {
			URL url = new URL(getURL(recipient, message));
			HttpURLConnection uc = (HttpURLConnection)url.openConnection();
			uc.disconnect();
		}catch (Exception e) {
			throw e;
		}
	}
}
