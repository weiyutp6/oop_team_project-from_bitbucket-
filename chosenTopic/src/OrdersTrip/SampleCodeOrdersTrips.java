package sampleCode;

import chosenTopic.OrdersTrip;

public class SampleCodeOrdersTrips {

    public static void main(String[] args) {
	String member_id = "Bill";
	String product_code = "4720";
	String adults_qty = "2";
	String kids_qty = "1";
	String babys_qty = "1";
	OrdersTrip newOrdersTrip = new OrdersTrip();
	Object[] orderList = newOrdersTrip.addOrders(member_id, product_code, adults_qty, kids_qty, babys_qty);
	if (null != orderList) {
	    for (int i = 0; i < 8; i++)
		System.out.println(orderList[i]);
	}
    }
}
