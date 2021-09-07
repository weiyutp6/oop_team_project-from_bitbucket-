import connectDB.*;
public class SampleCodeOfCheckMemberData {
    public static void main(String[] args) {
        Members member = new Members("Ricky");
        System.out.println(member.getMember_id());
        System.out.println(member.getMember_name());
        System.out.println(member.getPwd());
        System.out.println(member.getId_no());
    }
}
