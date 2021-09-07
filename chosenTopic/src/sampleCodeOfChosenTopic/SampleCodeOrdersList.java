import chosenTopic.*;

public class SampleCodeOrdersList {

    public static void main(String[] args) {
	String member_id = "Ricky";

	OrdersList ordersList = new OrdersList(member_id);

	Object[] list = ordersList.getList();

	for (int i = 0; i < list.length; i++) {
	    for (int j = 0; j < 9; j++) {
		System.out.println(((String[]) list[i])[j]);
	    }
	}

    }
}
