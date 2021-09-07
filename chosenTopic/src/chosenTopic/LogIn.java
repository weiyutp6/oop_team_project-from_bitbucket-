package chosenTopic;

import connectDB.Members;

public class LogIn extends Members {
    public LogIn(String member_id) {
	super(member_id);
    }

    public static String logIn(String member_id, String pwd) {
	Members member = new Members(member_id);
	if (member.getPwd() != null) {
	    if (pwd.contentEquals(member.getPwd())) {
			return "登入成功!";
		}
	    else {
			return "密碼錯誤!";
	    }
	} 
	else {
	    return "無此帳號ID!";
	}
    }
}
