package sampleCode;

import chosenTopic.AvailableTripsList;

public class SampleCodeAvailableTripsList {
    public static void main(String[] args) {
	AvailableTripsList list = new AvailableTripsList();
	String keyWord = "少女峰 國王湖 馬達加斯加";
	String startDateA = "20200215";
	String startDateB = "20200720";

	Object[] titleOfTrips = list.serachByKeyWord(keyWord, startDateA, startDateB);
	for (int i = 0; i < titleOfTrips.length; i++) {
	    System.out.println(titleOfTrips[i]);
	}

	// 索引j 陣列元素->Product_code(), Start_date, End_date, getPrice, Lower_bound,
	// Upper_bound, getRemaining, (*Title)
	Object[] detailedList = list.detailedList("【夏戀奧捷】國王湖•中世紀童話古鎮•布拉格三連泊•波希米亞五風味餐10天");
	for (int i = 0; i < detailedList.length; i++) {
	    for (int j = 0; j < 7; j++) {
		System.out.println(((String[]) detailedList[i])[j]);
	    }
	}

	Object[] sortedList = list.descendingDate();
	for (int i = 0; i < sortedList.length; i++) {
	    for (int j = 0; j < 7; j++) {
		System.out.println(((String[]) sortedList[i])[j]);
	    }
	}
    }
}
