public class SampleCodeAvailableTripsList {
    public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		AvailableTripsList list = new AvailableTripsList();
		String keyWord = "張家界 北歐 巴拉望";
		String startDateA = "20200101";
		String startDateB = "20210331";

		Object[] titleOfTrips = list.searchByKeyWord(keyWord, startDateA, startDateB);
		for (Object titleOfTrip : titleOfTrips) {
			System.out.println(titleOfTrip);
		}
		long endTime=System.currentTimeMillis();
		System.out.println("程式執行時間： " + (((double)endTime-(double)startTime)/1000) + "s");
		System.out.println(titleOfTrips.length);

	// 索引j 陣列元素->Product_code(), Start_date, End_date, getPrice, Lower_bound,
	// Upper_bound, getRemaining, (*Title)
//		Object[][] detailedList = list.detailedList("【菲向巴拉望】虎航★巴拉望三跳島+空中飛人+按摩+一島一飯店6日遊 兩人成行 含稅簽(參團當地即贈送芒果乾一包)");
//		for (Object[] i : detailedList) {
//			for (Object j : i) {
//				System.out.println(j);
//			}
//			System.out.println("---------");
//		}

//	Object[] sortedList = list.descendingDate();
//	for (int i = 0; i < sortedList.length; i++) {
//	    for (int j = 0; j < 7; j++) {
//		System.out.println(((String[]) sortedList[i])[j]);
//	    }
//	}
    }
}
