package seniorproject;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Txt {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC737ff464c131bed1229fc325fd44ea4b";
  public static final String AUTH_TOKEN = "e1f05f903bd62339189468a0b0888bbd";

  public static final PhoneNumber FROM_PHONE = new PhoneNumber("+19145954135");
  public static final PhoneNumber TEST_PHONE = new PhoneNumber("+17279027098");
  public static final String SERVICE_COMPLETE = "Your service at Tire Shop is complete. Please come pick up your vehicle at your convenience.";
  
  public static void setup() {Twilio.init(ACCOUNT_SID, AUTH_TOKEN);}
  
  public static void sendMessage() {
	  Message message = Message.creator(TEST_PHONE, FROM_PHONE, SERVICE_COMPLETE).create();
	  System.out.println(message.getSid());
  }
  
  public static void sendMessage(String toPhone) {
	  Message message = Message.creator(new PhoneNumber(toPhone), FROM_PHONE, SERVICE_COMPLETE).create();
	  System.out.println(message.getSid());
  }
  
  public static void sendMessage(String toPhone, String content) {
	  Message message = Message.creator(new PhoneNumber(toPhone), FROM_PHONE, content).create();
	  System.out.println(message.getSid());
  }
	  
    


}