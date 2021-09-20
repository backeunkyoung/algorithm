import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class fileData {
	int type;
	String startDate;
	String endDate;
}

public class calendar {

	public static void main(String[] args) throws IOException {
		String fname = "D:\\GitHub\\algorithm\\08.Calendar\\code\\calendar.inp";
		String outfname = "D:\\GitHub\\algorithm\\08.Calendar\\code\\calendar.out";
		FileReader fr = new FileReader(fname);
		ArrayList<fileData> data = FileSet(fr);

		ArrayList<Long> resultList = calc(data);
		
		//System.out.println("\nresultList : " + resultList);

		FilePrint(outfname, resultList);
	}
	
	public static ArrayList<fileData> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		
		ArrayList<fileData> returnData = new ArrayList<>();
		
		while(true) {
			fileData data = new fileData();
			
			String line = br_f.readLine();
			
			if (line.equals("-1")) {
				break;
			}
			
			String arr[] = line.split(" ");
			
			data.type = Integer.parseInt(arr[0]);
			data.startDate = arr[1];
			data.endDate = arr[2];
			
			returnData.add(data);
		}
		
		br_f.close();
		
		return returnData;
	}
	
	public static void FilePrint(String outfname, ArrayList<Long> resultList) throws IOException {
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		String str = "";
		
		//System.out.println("resultList : " + resultList);
		
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
			str += resultList.get(i) + "\n";
		}
		
		//System.out.println(str);

		//bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<Long> calc(ArrayList<fileData> inputData) {
		ArrayList<Long> resultList = new ArrayList<>();
		
		
		for (fileData data : inputData) {
			//System.out.println("\n" + data.type + " " + data.startDate + " " + data.endDate);
			long result = 0;
			
			if (data.type == 0) {
				result = zeroCalc(data.startDate, data.endDate);
			}
			else if (data.type == 1) {
				result = oneCalc(data.startDate, data.endDate);
			}
			else if (data.type == 2) {
				result = twoCalc(data.startDate, data.endDate);
			}
			else if (data.type == 3) {
				result = threeCalc(data.startDate, data.endDate);
			}
			
			//System.out.println(result);
			resultList.add(result);
		}

		
		return resultList;
	}
	
	public static long zeroCalc(String oldStartDate, String oldEndDate) {	// 연도-월-일, 연도-월-일
		long result = 0;
		
		String[] startArray = oldStartDate.split("-");
		String startDate = String.format("%04d-%02d-%02d", Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
		
		String[] endArray = oldEndDate.split("-");
		String endDate = String.format("%04d-%02d-%02d", Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));
		
		
		try {
			boolean startState = dateCheck(startDate);
			boolean endState = dateCheck(endDate);
			
			if (startState && endState) {
				LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
				LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
				
				long diffDays = ChronoUnit.DAYS.between(start, end);
				diffDays = Math.abs(diffDays);
				
//				System.out.println("---- type 0 ----");
//				System.out.println(startDate + " " + endDate);
//				System.out.println("차이 : " + diffDays);
//				System.out.println("--------");
				
				result = diffDays;
			}
			else {
				result = -1;
			}
		}
		catch(DateTimeException e) {
			
		}
		 
		return result; 
	}
	public static long oneCalc(String oldStartDate, String oldEndDate) {	// 연도-월-일, 연도-월-횟수-요일
		long result = 0;
		
		String[] startArray = oldStartDate.split("-");
		String startDate = String.format("%04d-%02d-%02d", Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
		
		String[] endArray = oldEndDate.split("-");
		String endDate = String.format("%04d-%02d-%02d", Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));
		
		try {
			String[] endStr = endDate.split("-");
			int endYear = Integer.parseInt(endStr[0]);
			int endMonth = Integer.parseInt(endStr[1]);
			int endWeek = Integer.parseInt(endStr[2]);
			String endDayOfWeek = endStr[3];
			int endDay = 0;
			
			if (endStr[3].equals("Sun")) { endDay = 1; }
			else if (endStr[3].equals("Mon")) { endDay = 2; }
			else if (endStr[3].equals("Tue")) { endDay = 3; }
			else if (endStr[3].equals("Wed")) { endDay = 4; }
			else if (endStr[3].equals("Thu")) { endDay = 5; }
			else if (endStr[3].equals("Fri")) { endDay = 6; }
			else if (endStr[3].equals("Sat")) { endDay = 7; }
			
			Calendar endCal = Calendar.getInstance();
			endCal.set(endYear, endMonth-1, 1);
			int monthOfEndDate2 = endCal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			String MonthStart2 = Integer.toString(endYear) + "-" + Integer.toString(endMonth) + "-1";
			String MonthEnd2 = Integer.toString(endYear) + "-" + Integer.toString(endMonth) + "-" + Integer.toString(monthOfEndDate2);
			//System.out.println(MonthStart2 + " ~ " + MonthEnd2);
			
			int dayCount2 = 0;
			ArrayList<Integer> dateList2 = new ArrayList<>();
			for (int i = 1; i <= monthOfEndDate2; i++) {
				endCal.set(endYear, endMonth-1, i);
				int day = endCal.get(Calendar.DAY_OF_WEEK);
				if (endDay == day) {
					dayCount2++;
					dateList2.add(i);
				}
			}
			
			if (endWeek <= dayCount2) {
				endDay = dateList2.get(endWeek-1);
				//System.out.println(endYear + "-" + endMonth + "-" + endDay);
			}
			else {
				endDay = -1;
			}
			
			String newEndDate = endYear + "-" + endMonth + "-" + endDay;
			
			boolean startState = dateCheck(startDate);
			boolean endState = dateCheck(newEndDate);
			
			//System.out.println("startState : " + startState + " , endState : " + endState);
			
			if (startState && endState) {
				LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
				LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
				
				long diffDays = ChronoUnit.DAYS.between(start, end);
				diffDays = Math.abs(diffDays);
				
//				System.out.println("---- type 1 ----");
//				System.out.println(startDate + " " + newEndDate);
//				System.out.println("차이 : " + diffDays);
//				System.out.println("--------");
				
				result = diffDays;
			}
			else {
				result = -1;
			}
			
		}
		catch(DateTimeException e) {

		}
		 
		return result; 
	}
	public static long twoCalc(String oldStartDate, String oldEndDate) {	// 연도-월-횟수-요일 , 연도-월-일
		long result = 0;
		
		String[] startArray = oldStartDate.split("-");
		String startDate = String.format("%04d-%02d-%02d", Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
		
		String[] endArray = oldEndDate.split("-");
		String endDate = String.format("%04d-%02d-%02d", Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));
		
		try {
			String[] startStr = startDate.split("-");
			int startYear = Integer.parseInt(startStr[0]);
			int startMonth = Integer.parseInt(startStr[1]);
			int startWeek = Integer.parseInt(startStr[2]);
			String startDayOfWeek = startStr[3];
			int startDay = 0;
			
			if (startStr[3].equals("Sun")) { startDay = 1; }
			else if (startStr[3].equals("Mon")) { startDay = 2; }
			else if (startStr[3].equals("Tue")) { startDay = 3; }
			else if (startStr[3].equals("Wed")) { startDay = 4; }
			else if (startStr[3].equals("Thu")) { startDay = 5; }
			else if (startStr[3].equals("Fri")) { startDay = 6; }
			else if (startStr[3].equals("Sat")) { startDay = 7; }
			
			Calendar startCal = Calendar.getInstance();
			startCal.set(startYear, startMonth-1, 1);
			int monthOfEndDate = startCal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			String MonthStart = Integer.toString(startYear) + "-" + Integer.toString(startMonth) + "-1";
			String MonthEnd = Integer.toString(startYear) + "-" + Integer.toString(startMonth) + "-" + Integer.toString(monthOfEndDate);
			
			int dayCount = 0;
			ArrayList<Integer> dateList = new ArrayList<>();
			for (int i = 1; i <= monthOfEndDate; i++) {
				startCal.set(startYear, startMonth-1, i);
				int day = startCal.get(Calendar.DAY_OF_WEEK);
				if (startDay == day) {
					dayCount++;
					dateList.add(i);
				}
			}
			
			if (startWeek <= dayCount) {
				startDay = dateList.get(startWeek-1);
				//System.out.println(startYear + "-" + startMonth + "-" + startDay);
			}
			else {
				startDay = -1;
			}
			String newStartDate = startYear + "-" + startMonth + "-" + startDay;
			
			boolean startState = dateCheck(newStartDate);
			boolean endState = dateCheck(endDate);
			
			//System.out.println("startState : " + startState + " , endState : " + endState);
			
			if (startState && endState) {
				LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
				LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
				
				long diffDays = ChronoUnit.DAYS.between(start, end);
				diffDays = Math.abs(diffDays);
				
//				System.out.println("---- type 2 ----");
//				System.out.println(newStartDate + " " + endDate);
//				System.out.println("차이 : " + diffDays);
//				System.out.println("--------");
				
				result = diffDays;
			}
			else {
				result = -1;
			}
			
		}
		catch(DateTimeException e) {

		} 
		
		
		return result; 
	}
	public static long threeCalc(String startDate, String endDate) {	// 연도-월-횟수-요일 , 연도-월-횟수-요일
		long result = 0;
		
		try {
			String[] startStr = startDate.split("-");
			int startYear = Integer.parseInt(startStr[0]);
			int startMonth = Integer.parseInt(startStr[1]);
			int startWeek = Integer.parseInt(startStr[2]);
			String startDayOfWeek = startStr[3];
			int startDay = 0;
			
			if (startStr[3].equals("Sun")) { startDay = 1; }
			else if (startStr[3].equals("Mon")) { startDay = 2; }
			else if (startStr[3].equals("Tue")) { startDay = 3; }
			else if (startStr[3].equals("Wed")) { startDay = 4; }
			else if (startStr[3].equals("Thu")) { startDay = 5; }
			else if (startStr[3].equals("Fri")) { startDay = 6; }
			else if (startStr[3].equals("Sat")) { startDay = 7; }
			
			Calendar startCal = Calendar.getInstance();
			startCal.set(startYear, startMonth-1, 1);
			int monthOfEndDate = startCal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			String MonthStart = Integer.toString(startYear) + "-" + Integer.toString(startMonth) + "-1";
			String MonthEnd = Integer.toString(startYear) + "-" + Integer.toString(startMonth) + "-" + Integer.toString(monthOfEndDate);
			
			int dayCount = 0;
			ArrayList<Integer> dateList = new ArrayList<>();
			for (int i = 1; i <= monthOfEndDate; i++) {
				startCal.set(startYear, startMonth-1, i);
				int day = startCal.get(Calendar.DAY_OF_WEEK);
				if (startDay == day) {
					dayCount++;
					dateList.add(i);
				}
			}
			
			if (startWeek <= dayCount) {
				startDay = dateList.get(startWeek-1);
				//System.out.println(startYear + "-" + startMonth + "-" + startDay);
			}
			else {
				startDay = -1;
			}
			String newStartDate = startYear + "-" + startMonth + "-" + startDay;
			
			String[] endStr = endDate.split("-");
			int endYear = Integer.parseInt(endStr[0]);
			int endMonth = Integer.parseInt(endStr[1]);
			int endWeek = Integer.parseInt(endStr[2]);
			String endDayOfWeek = endStr[3];
			int endDay = 0;
			
			System.out.println("요일 : " + endStr[3]);
			
			if (endStr[3].equals("Sun")) { endDay = 1; }
			else if (endStr[3].equals("Mon")) { endDay = 2; }
			else if (endStr[3].equals("Tue")) { endDay = 3; }
			else if (endStr[3].equals("Wed")) { endDay = 4; }
			else if (endStr[3].equals("Thu")) { endDay = 5; }
			else if (endStr[3].equals("Fri")) { endDay = 6; }
			else if (endStr[3].equals("Sat")) { endDay = 7; }
			
			Calendar endCal = Calendar.getInstance();
			endCal.set(endYear, endMonth-1, 1);
			int monthOfEndDate2 = endCal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			String MonthStart2 = Integer.toString(endYear) + "-" + Integer.toString(endMonth) + "-1";
			String MonthEnd2 = Integer.toString(endYear) + "-" + Integer.toString(endMonth) + "-" + Integer.toString(monthOfEndDate2);
			//System.out.println(MonthStart2 + " ~ " + MonthEnd2);
			
			int dayCount2 = 0;
			ArrayList<Integer> dateList2 = new ArrayList<>();
			for (int i = 1; i <= monthOfEndDate2; i++) {
				endCal.set(endYear, endMonth-1, i);
				int day = endCal.get(Calendar.DAY_OF_WEEK);
				if (endDay == day) {
					dayCount2++;
					dateList2.add(i);
				}
			}
			
			System.out.println("endWeek : " + endWeek + " , dayCount2 : " + dayCount2);
			if (endWeek <= dayCount2) {
				endDay = dateList2.get(endWeek-1);
				System.out.println(endYear + "-" + endMonth + "-" + endDay);
			}
			else {
				endDay = -1;
			}
			
			String newEndDate = endYear + "-" + endMonth + "-" + endDay;
			
			
			String[] newStartArray = newStartDate.split("-");
			newStartDate = String.format("%04d-%02d-%02d", Integer.parseInt(newStartArray[0]), Integer.parseInt(newStartArray[1]), Integer.parseInt(newStartArray[2]));
			
			String[] newEndArray = newEndDate.split("-");
			System.out.println(newEndArray[0] + " " + newEndArray[1] + " " + newEndArray[2]);
			newEndDate = String.format("%04d-%02d-%02d", Integer.parseInt(newEndArray[0]), Integer.parseInt(newEndArray[1]), Integer.parseInt(newEndArray[2]));
			
			boolean startState = dateCheck(newStartDate);
			boolean endState = dateCheck(newEndDate);

			//System.out.println("startState : " + startState + " , endState : " + endState);
			
			if (startState && endState) {
				System.out.println("newStartDate : " + newStartDate + " , newEndDate : " + newEndDate);
				LocalDate start = LocalDate.parse(newStartDate, DateTimeFormatter.ISO_DATE);
				LocalDate end = LocalDate.parse(newEndDate, DateTimeFormatter.ISO_DATE);
				
				long diffDays = ChronoUnit.DAYS.between(start, end);
				diffDays = Math.abs(diffDays);
				
				System.out.println("---- type 3 ----");
				System.out.println(newStartDate + " " + newEndDate);
				System.out.println("차이 : " + diffDays);
				System.out.println("--------");
				
				result = diffDays;
			}
			else {
				result = -1;
			}
			
		}
		catch(DateTimeException e) {
			System.out.println("에러");
		}
		
		return result; 
	}
	
	public static boolean dateCheck(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		
        try {
            format.parse(date);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
	}
	
}