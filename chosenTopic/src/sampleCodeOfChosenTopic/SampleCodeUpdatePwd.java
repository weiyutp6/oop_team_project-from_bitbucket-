import chosenTopic.UpdatePwd;

public class SampleCodeUpdatePwd {
    public static void main(String[] args) {
	UpdatePwd update=new UpdatePwd("Bill");
	String oldPwd = "billbill";
	String newPwd = "billbill1";
	String checkNewPwd = "billbill1";
	update.updatePwd(oldPwd, newPwd, checkNewPwd);
    }
}
