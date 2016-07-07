package org.raunak.main.utill;

import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class MyStringUtill {
	public static boolean isNullOrEmpty(String dataStr) {
		boolean isEmpty = false;
		if (dataStr == null || (dataStr != null && (dataStr.length() == 0 || dataStr.trim().length() == 0)))
			isEmpty = true;
		return isEmpty;
	}

	public static boolean isNull(Object obj) {
		boolean isNull = false;
		if (obj == null)
			isNull = true;
		return isNull;
	}

	public static boolean isEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == "")
			isEmpty = true;
		return isEmpty;
	}

	public static String obj2Str(Object obj) {
		String str = "";
		if (obj != null) {
			str = obj.toString();
		}
		return str;
	}

	public static String parseListForQuery(List<String> list) {
		String parsedString = "";
		String str = null;
		if (list == null || (list != null && list.size() == 0)) {
			return null;
		}
		Iterator<String> itr = list.iterator();
		while (itr.hasNext()) {
			str = itr.next();
			parsedString = parsedString + "'" + str + "'" + ",";
		}
		if (parsedString.indexOf(",") != -1) {
			parsedString = parsedString.substring(0, parsedString.length() - 1);
		}
		return parsedString;
	}

	public static double generateRandomNumber() {
		Random randomno = new Random();
		return randomno.nextDouble();
	}

	public static boolean compareListData(List currentList, List availableList) {
		if ((currentList != null && currentList.size() > 0) && (availableList != null && availableList.size() > 0)
				&& currentList.size() == availableList.size()) {
			for (Object current : currentList) {
				if (!availableList.contains(current)) {
					return false;
				}
			}
			return true;
		} else if (MyUtill.isEmptyCollection(currentList) && MyUtill.isEmptyCollection(availableList)) {
			return true;
		}
		return false;
	}
}
