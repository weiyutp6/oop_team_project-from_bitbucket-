import connectDB.*;

public class SampleCodeOfRegisterMember {
    public static void main(String[] args) {
        Members newMember = new Members("Weiyu Chen", "陳威宇", "weiyu666", "A112233445");
        newMember.registerMember();
    }
}