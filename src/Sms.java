import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber; 
 
import java.net.URI; 
import java.math.BigDecimal; 
 
public class Sms { 
    // Find your Account Sid and Token at twilio.com/console 
    public static final String ACCOUNT_SID = "AC01d31869d82aa6ae069736a193abc0fe"; 
    public static final String AUTH_TOKEN = "[AuthToken]"; 
 
    public static void main(String[] args) { 
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+21654058190"), 
                new com.twilio.type.PhoneNumber("+15129007568"),  
                "welcom to geeks")      
            .create(); 
 
        System.out.println(message.getSid()); 
    } 
}