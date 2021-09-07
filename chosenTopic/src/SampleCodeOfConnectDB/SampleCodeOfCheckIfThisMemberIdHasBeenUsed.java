import connectDB.*;

public class SampleCodeOfCheckIfThisMemberIdHasBeenUsed {
    public static void main(String[] args) {
        System.out.println(Members.isThisMemberIdNotUsed("Weiyu"));
        System.out.println(Members.isThisMemberIdNotUsed("Weiyu Chen"));
        System.out.println(Members.isThisMemberIdNotUsed("Ricky"));
        System.out.println(Members.isThisMemberIdNotUsed("James"));
        System.out.println(Members.isThisMemberIdNotUsed("Jonathan"));
    }
}