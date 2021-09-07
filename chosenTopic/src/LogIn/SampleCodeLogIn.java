package sampleCode;

import chosenTopic.LogIn;

public class SampleCodeLogIn {
    public static void main(String[] args) {
	String member_id1 = "Ricky";
	String pwd1 = "ricky1008";
	System.out.println(LogIn.logIn(member_id1, pwd1)); // success

	String member_id2 = "Ricky";
	String pwd2 = "wrongPwd";
	System.out.println(LogIn.logIn(member_id2, pwd2)); // fall
    }
}
