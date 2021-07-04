package CA.CAPS.util;

import org.springframework.stereotype.Component;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;

@Component
public class FaceUtil {
	private static final String APP_ID = "24478251";
	private static final String APP_KEY = "xxGzIaemhGmLSzounyfi3VcY";
	private static final String SECRET_KEY = "Kmo9EETvRgBEzXK89x5yoSGIk7FRXnMy";

	private static volatile AipFace client = new AipFace(APP_ID, APP_KEY, SECRET_KEY);

	public static AipFace getClient() {
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		return client;
	}

	public static String encodeBase64(byte[] form) {
		return Base64Util.encode(form);
	}

	public static byte[] decodeBase64(String data) {
		return Base64Util.decode(data);
	}
}