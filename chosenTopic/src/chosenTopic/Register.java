package chosenTopic;

import connectDB.Members;

public class Register extends Members {
    protected String member_id;
    protected String member_name;
    protected String pwd;
    protected String id_no;
    protected String checkPwd;

    public Register(String member_id) {
	super(member_id);
	this.member_id = member_id;
    }

    public Register(String member_id, String member_name, String pwd, String id_no, String checkPwd) {
	super(member_id, member_name, pwd, id_no);
	this.member_id = member_id;
	this.member_name = member_name;
	this.pwd = pwd;
	this.id_no = id_no;
	this.checkPwd = checkPwd;
    }

    public String newMember() {
	if (isThisMemberIdNotUsed(getMember_id()) && isPwdValid().contentEquals("")
		&& isIdNumberValid().contentEquals("") && checkPwd().contentEquals("")) {
	    Members newMember = new Members(member_id, member_name, pwd, id_no);
	    newMember.registerMember();
	    return "註冊成功";
	} else {
	    return "註冊失敗";
	}
    }

    public String isIdNumberValid() {
	String valueConvert = "ABCDEFGHIJKLMNPQRSTUVXYWZIO";
	int weightedNumber = 0;
	if (getId_no().length() != 10) {
	    return "長度有誤！";
	} else {
	    valueConvert.indexOf(Character.toUpperCase(getId_no().charAt(0)));
	    weightedNumber = ((valueConvert.indexOf(Character.toUpperCase(getId_no().charAt(0))) + 10) / 10) * 1
		    + ((valueConvert.indexOf(Character.toUpperCase(getId_no().charAt(0))) + 10) % 10) * 9;
	    for (int index = 1; index < 9; index++) {
		int number = Integer.parseInt(getId_no().substring(index, index + 1));
		weightedNumber += number * (9 - index);
	    }
	    weightedNumber += Integer.parseInt(getId_no().substring(9, 10));

	    if (weightedNumber % 10 == 0)
		return "";
	    else {
		return "非有效身分證";
	    }
	}
    }

    public String isPwdValid() {
	if (getPwd().length() > 32) {
	    return "超過密碼長度限制";
	} else if (getPwd().length() < 8) {
	    return "密碼長度不足";
	} else {
	    if (getPwd().substring(getPwd().length() - 1).contentEquals(" ")
		    || getPwd().substring(0, 1).contentEquals(" ")) {
		return "密碼首尾不得為空白字元";
	    } else
		return "";
	}
    }

    public String checkPwd() {
	if (getPwd().equals(getCheckPwd()))
	    return "";
	else
	    return "密碼有誤";
    }

    public String getMember_id() {
	return member_id;
    }

    public void setMember_id(String member_id) {
	this.member_id = member_id;
    }

    public String getMember_name() {
	return member_name;
    }

    public void setMember_name(String member_name) {
	this.member_name = member_name;
    }

    public String getPwd() {
	return pwd;
    }

    public void setPwd(String pwd) {
	this.pwd = pwd;
    }

    public String getId_no() {
	return id_no;
    }

    public void setId_no(String id_no) {
	this.id_no = id_no;
    }

    public String getCheckPwd() {
	return checkPwd;
    }

    public void setCheckPwd(String checkPwd) {
	this.checkPwd = checkPwd;
    }
}
