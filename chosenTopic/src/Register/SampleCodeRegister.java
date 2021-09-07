package sampleCode;

import chosenTopic.Register;

public class SampleCodeRegister {
    public static void main(String[] args) {
 	String member_id = "Bill";
 	String member_name = "梁瑞翔";
 	String pwd = "54564531";
 	String id_no = "A112233449";
 	String checkPwd = "54564531";
 	Register newMember = new Register(member_id, member_name, pwd, id_no, checkPwd);
 	newMember.newMember();
     }
}
