package restful2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class RequestHelper {
    private static final String utf8 = "UTF-8";
    private static final String hmacAlg = "HmacSHA256";
    private static final String requestUri = "/onca/xml";
    private static final String requestMethod = "GET";

    private String endpoint = null;
    private String accessKeyId = null;
    private String secretKey = null;
    private SecretKeySpec secretKeySpec = null;
    private Mac mac = null;

    public RequestHelper(String endpoint, 
			 String accessKeyId, 
			 String secretKey) {
        if (endpoint == null || endpoint.length() == 0)
	    throw new RuntimeException("The endpoint is null or empty."); 
        if (null == accessKeyId || accessKeyId.length() == 0) 
            throw new RuntimeException("The accessKeyId is null or empty."); 
        if (null == secretKey || secretKey.length() == 0)   
            throw new RuntimeException("The secretKey is null or empty."); 
        
        this.endpoint = endpoint.toLowerCase();
        this.accessKeyId = accessKeyId;
        this.secretKey = secretKey;

	try {
	    byte[ ] secretKeyBytes = this.secretKey.getBytes(utf8);
	    this.secretKeySpec = new SecretKeySpec(secretKeyBytes, hmacAlg);
	    this.mac = Mac.getInstance(hmacAlg);
	    this.mac.init(this.secretKeySpec);
	}
	catch(Exception e) { throw new RuntimeException(e); }
    }

    public String sign(Map<String, String> params) {
        params.put("AWSAccessKeyId", this.accessKeyId);
        params.put("Timestamp", this.timestamp());

        // The parameters need to be processed in lexicographical order, with
        // sorting on the first byte: a TreeMap is perfect for this.
        SortedMap<String, String> sortedParamMap = new TreeMap<String, String>(params);
        
        // Ensure canonical form of the query string, as Amazon REST is fussy.
        String canonicalQS = this.canonicalize(sortedParamMap);
        
	// Prepare the signature with grist for the mill.
        String toSign = 
            requestMethod + "\n" 
            + this.endpoint + "\n"
            + requestUri + "\n"
            + canonicalQS;

        String hmac = this.hmac(toSign);
        String sig = null;
	try {
	    sig = URLEncoder.encode(hmac, utf8);
	}
	catch(UnsupportedEncodingException e) { System.err.println(e); }

        String url = 
            "http://" + this.endpoint + requestUri + "?" + canonicalQS + "&Signature=" + sig;
        return url;
    }

    public String sign(String queryString) {
        Map<String, String> params = this.createParameterMap(queryString);
        return this.sign(params);
    }

    private String hmac(String stringToSign) {
        String signature = null;
        byte[ ] data;
        byte[ ] rawHmac;
        try {
            data = stringToSign.getBytes(utf8);
            rawHmac = mac.doFinal(data);
            Base64 encoder = new Base64();
            signature = new String(encoder.encode(rawHmac));
        } 
	catch (UnsupportedEncodingException e) {
            throw new RuntimeException(utf8 + " is unsupported!", e);
        }
        return signature;
    }

    // Amazon requires an ISO-8601 timestamp. 
    private String timestamp() {
        String timestamp = null;
        Calendar cal = Calendar.getInstance();
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dfm.setTimeZone(TimeZone.getTimeZone("GMT"));
        timestamp = dfm.format(cal.getTime());
        return timestamp;
    }

    private String canonicalize(SortedMap<String, String> sortedParamMap) {
        if (sortedParamMap.isEmpty()) return "";

        StringBuffer buffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

	while (iter.hasNext()) {
	    Map.Entry<String, String> kvpair = iter.next();
	    buffer.append(encodeRfc3986(kvpair.getKey()));
	    buffer.append("=");
	    buffer.append(encodeRfc3986(kvpair.getValue()));
	    if (iter.hasNext()) buffer.append("&");
	} 
        return buffer.toString();
    }

    // Amazon requires RFC 3986 encoding, which the URLEncoder may not get right.
    private String encodeRfc3986(String s) {
        String out;
        try {
            out = URLEncoder.encode(s, utf8)
                .replace("+", "%20")
                .replace("*", "%2A")
                .replace("%7E", "~");
        } 
	catch (UnsupportedEncodingException e) {
            out = s;
        }
        return out;
    }

    private Map<String, String> createParameterMap(String queryString) {
        Map<String, String> map = new HashMap<String, String>();
        String[ ] pairs = queryString.split("&");

        for (String pair : pairs) {
            if (pair.length() < 1) {
                continue;
            }

            String[ ] tokens = pair.split("=",2);
            for(int j = 0; j<tokens.length; j++) {
                try {
                    tokens[j] = URLDecoder.decode(tokens[j], utf8);
                } 
		catch (UnsupportedEncodingException e) { }
            }
            switch (tokens.length) {
                case 1: {
                    if (pair.charAt(0) == '=') map.put("", tokens[0]);
                    else map.put(tokens[0], "");
                    break;
                }
                case 2: {
                    map.put(tokens[0], tokens[1]);
                    break;
                }
            }
        }
        return map;
    }
}

