package chosenTopic;

import connectDB.Members;

public class UpdatePwd extends LogIn {
    private String member_id;

    public UpdatePwd(String member_id) {
	super(member_id);
	this.member_id = member_id;
    }

    public String updatePwd(String oldPwd, String newPwd, String checkNewPwd) {
	if (checkOldPwd(oldPwd).contentEquals("") && isNewPwdValid(oldPwd, newPwd).contentEquals("")
		&& checkNewPwd(newPwd, checkNewPwd).contentEquals("")) {
	    Members.UpdatePwd(member_id, newPwd);
	    return "更改成功";
	} else {
	    return "更改失敗";
	}
    }

    public String checkOldPwd(String oldPwd) {
	Members member = new Members(member_id);
	if (oldPwd.contentEquals(member.getPwd()))
	    return "";
	else {
	    return "密碼錯誤";
	}
    }

    public String isNewPwdValid(String oldPwd, String newPwd) {
	if (newPwd.length() > 32) {
	    return "超過密碼長度限制";
	} else if (newPwd.length() < 8) {
	    return "密碼長度不足";
	} else if (newPwd.contentEquals(oldPwd)) {
	    return "不得與舊密碼相同";
	} else {
	    if (newPwd.substring(newPwd.length() - 1).contentEquals(" ") || newPwd.substring(0, 1).contentEquals(" ")) {
		return "密碼首尾不得為空白字元";
	    } else
		return "";
	}
    }

    public String checkNewPwd(String newPwd, String checkNewPwd) {
	if (newPwd.equals(checkNewPwd))
	    return "";
	else
	    return "密碼輸入錯誤";
    }
}
