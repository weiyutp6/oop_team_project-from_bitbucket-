package chosenTopic;

import java.util.ArrayList;

import connectDB.*;

public class OrdersList extends SearchOrder {
    private int[] order_no;
    private ArrayList<String[]> order = new ArrayList<String[]>();

    public OrdersList(String member_id) {
	order_no = getOrderNos(member_id);
    }

    public Object[] getList() {
	SearchOrder myOrder = new SearchOrder();
	for (int i = 0; i < order_no.length; i++) {
	    order.add(new String[] { myOrder.orderContents(order_no[i]).getMember_id(), Integer.toString(order_no[i]),
		    Integer.toString(myOrder.orderContents(order_no[i]).getProduct_code()),
		    myOrder.orderContents(order_no[i]).getTitle(), myOrder.orderContents(order_no[i]).getProduct_key(),
		    myOrder.orderContents(order_no[i]).getStart_date(),
		    myOrder.orderContents(order_no[i]).getEnd_date(),
		    Integer.toString(myOrder.orderContents(order_no[i]).getAdults_qty()
			    + myOrder.orderContents(order_no[i]).getKids_qty()
			    + myOrder.orderContents(order_no[i]).getBabys_qty()),
		    Integer.toString(myOrder.orderContents(order_no[i]).getTotal_price()),
		    myOrder.orderContents(order_no[i]).getOrder_date() });
	}
	return order.toArray();
    }
}
