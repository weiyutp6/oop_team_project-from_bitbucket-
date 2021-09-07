package chosenTopic;

import connectDB.AvailableTrips;
import connectDB.Orders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class OrdersTrip extends Orders {
    private ArrayList<String> orderList = new ArrayList<String>();

    public Object[] addOrders(String member_id, String product_codeS, String adults_qtyS, String kids_qtyS, String babys_qtyS) {
		int product_code = Integer.parseInt(product_codeS);
		int adults_qty = Integer.parseInt(adults_qtyS);
		int kids_qty = Integer.parseInt(kids_qtyS);
		int babys_qty = Integer.parseInt(babys_qtyS);

		if (!isProductCodeValid(product_code).contentEquals("")
			|| !isNumberOfPeopleValid(product_code, adults_qty, kids_qty, babys_qty).contentEquals(""))
			return null;

		SimpleDateFormat sdFormat_order_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		SimpleDateFormat sdFormat_order_no = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		int order_no = Integer.parseInt(sdFormat_order_no.format(calendar.getTime()));
		int last_order_no = Integer.parseInt(getLastOrderNo());

		if (order_no > last_order_no / 100)
			order_no = order_no * 100 + 1;
		else
			order_no = last_order_no + 1;

		Orders newOrder = new Orders(order_no, member_id, product_code, adults_qty, kids_qty, babys_qty, sdFormat_order_date.format(calendar.getTime()));

		newOrder.addOrder();

		AvailableTrips trips = new AvailableTrips(product_code);
		orderList.add(Integer.toString(order_no));
		orderList.add(newOrder.getMember_id());
		orderList.add(trips.getStart_date());
		orderList.add(trips.getEnd_date());
		orderList.add(Integer.toString(newOrder.getTotal_price()));
		orderList.add(Integer.toString(newOrder.getAdults_qty()));
		orderList.add(Integer.toString(newOrder.getKids_qty()));
		orderList.add(Integer.toString(newOrder.getBabys_qty()));
		return orderList.toArray();
    }

    public String isNumberOfPeopleValid(int product_code, int adults_qty, int kids_qty, int babys_qty) {
	AvailableTrips trip = new AvailableTrips(product_code);
	if (adults_qty + kids_qty + babys_qty > trip.getRemaining()) {
	    return "超過出團人數上限！";
	}
	return "";
    }

    public String isProductCodeValid(int product_code) {
	AvailableTrips trip = new AvailableTrips(product_code);
	int startDate = Integer.parseInt(trip.getStart_date().substring(0, 4) + trip.getStart_date().substring(5, 7)
		+ trip.getStart_date().substring(8, 10));
	SimpleDateFormat sdFormat_order_no = new SimpleDateFormat("yyyyMMdd");
	Calendar calendar = Calendar.getInstance();
	int orderDate = Integer.parseInt(sdFormat_order_no.format(calendar.getTime()));
	if (trip.getProduct_code() == 0) {
	    return "無此產品代號！";
	}
	if (startDate <= orderDate) {
	    return "行程已逾期";
	} else
	    return "";
    }
}
