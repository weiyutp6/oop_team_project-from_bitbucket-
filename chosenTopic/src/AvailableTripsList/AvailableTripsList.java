package chosenTopic;

import connectDB.AvailableTrips;
import java.util.*;

public class AvailableTripsList {
    private ArrayList<String> titleOfTrips = new ArrayList<String>();
    private ArrayList<String[]> detailOfTheTrip = new ArrayList<String[]>();
    private ArrayList<String[]> detail;

    public Object[] searchByArea(String area, String startDateA, String startDateB) {
		int startDate1 = Integer.parseInt(startDateA);
		int startDate2 = Integer.parseInt(startDateB);

		return AvailableTrips.searchRelatedTitle(area, startDate1, startDate2);
    }

    public Object[] searchByKeyWord(String keyWord, String startDateA, String startDateB) {
		int startDate1 = Integer.parseInt(startDateA);
		int startDate2 = Integer.parseInt(startDateB);

		return AvailableTrips.searchAvailableTitle(keyWord, startDate1, startDate2);
    }

    public Object[][] detailedList(String title, String startDateA, String startDateB) {
		return AvailableTrips.searchDetailByTitle(title, startDateA, startDateB);
    }

    public static String getRemainingByProductCode(String productCode) {
        int product_code = Integer.parseInt(productCode);

        AvailableTrips trip = new AvailableTrips(product_code);
        return Integer.toString(trip.getRemaining());
    }

//    public Object[] ascendingPrice() {
//		ArrayList<String[]> clone = new ArrayList<String[]>(detail);
//		Collections.sort(clone, new Comparator<String[]>() {
//			public int compare(String[] strings, String[] otherStrings) {
//			return strings[3].compareTo(otherStrings[3]);
//			}
//		});
//
//		ArrayList<String[]> sortedList = new ArrayList<String[]>();
//		for (String[] is : clone) {
//			sortedList.add(is);
//		}
//
//		return sortedList.toArray();
//    }
//
//    public Object[] descendingPrice() {
//		ArrayList<String[]> clone = new ArrayList<String[]>(detail);
//		Collections.sort(clone, new Comparator<String[]>() {
//			public int compare(String[] strings, String[] otherStrings) {
//			return otherStrings[3].compareTo(strings[3]);
//			}
//		});
//
//		ArrayList<String[]> sortedList = new ArrayList<String[]>();
//		for (String[] is : clone) {
//			sortedList.add(is);
//		}
//
//		return sortedList.toArray();
//    }
//
//    public Object[] ascendingDate() {
//		ArrayList<String[]> clone = new ArrayList<String[]>(detail);
//		Collections.sort(clone, new Comparator<String[]>() {
//			public int compare(String[] strings, String[] otherStrings) {
//			return strings[1].compareTo(otherStrings[1]);
//			}
//		});
//
//		ArrayList<String[]> sortedList = new ArrayList<String[]>();
//		for (String[] is : clone) {
//			sortedList.add(is);
//		}
//
//		return sortedList.toArray();
//    }
//
//    public Object[] descendingDate() {
//		ArrayList<String[]> clone = new ArrayList<String[]>(detail);
//		Collections.sort(clone, new Comparator<String[]>() {
//			public int compare(String[] strings, String[] otherStrings) {
//			return otherStrings[1].compareTo(strings[1]);
//			}
//		});
//
//		ArrayList<String[]> sortedList = new ArrayList<String[]>();
//		for (String[] is : clone) {
//			sortedList.add(is);
//		}
//
//		return sortedList.toArray();
//    }
}
