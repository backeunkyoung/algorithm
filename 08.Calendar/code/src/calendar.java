import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.time.DateTimeException;
import java.time.DayOfWeek;
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
		
		System.out.println("\n --- 파일 출력 ----");
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
				
				System.out.println("---- type 0 ----");
				System.out.println("startDate : " + startDate + " , endDate : " + endDate);
				System.out.println("start : " + start + " , end : " + end);
				System.out.println("차이 : " + diffDays);
				System.out.println("--------");
				
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
			
			// 1/1 = 0 => 1 2 3 4
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
			System.out.println("----------------------");
			System.out.println("startDate : " + startDate + " , endDate : " + endDate);
			
			String[] startStr = startDate.split("-");
			int startYear = Integer.parseInt(startStr[0]);
			int startMonth = Integer.parseInt(startStr[1]);
			int startWeek = Integer.parseInt(startStr[2]);
			String startDayOfWeek = startStr[3];
			
			int startDay = whatDay(startDayOfWeek);
			int startNum = 0;
			
//			System.out.println(startYear + "년 " + startMonth + "월 " + startWeek + "번째  " + startDay + "일");
			
			Calendar startCal = Calendar.getInstance();
			startCal.set(startYear, startMonth-1, 1);
			int monthOfLastDay = startCal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			LocalDate monthStartDate = LocalDate.of(startYear, startMonth, 1);
			LocalDate monthEndDate = LocalDate.of(startYear, startMonth, monthOfLastDay);
			
			DayOfWeek dayOfWeek = monthStartDate.getDayOfWeek();
			int monthStartDay = (dayOfWeek.getValue()%7) + 1;
			
			dayOfWeek = monthEndDate.getDayOfWeek();
			int monthEndDay = (dayOfWeek.getValue()%7) + 1;
			
			if (startMonth == 2) {
				if (monthOfLastDay == 29) {
					if (startWeek == 5 && monthStartDay != startDay) {
						System.out.println("잘못된 날짜임 5번째 요일이 없음(2월)");
						startNum = 99;
					}
					else {
						ArrayList<Integer> dateList = getdayNum(monthOfLastDay, startYear, startMonth, startDay);
						startNum = dateList.get(startWeek-1);
					}
				}
				else {
					if (startWeek == 5) {
						System.out.println("잘못된 날짜임(2월)");
						startNum = 99;
					}
					else {
						ArrayList<Integer> dateList = getdayNum(monthOfLastDay, startYear, startMonth, startDay);
						startNum = dateList.get(startWeek-1);
					}
				}
			}
			else {
			
				if (startWeek == 5) {
					if (startDay < monthStartDay && startDay > monthEndDay) {
						System.out.println("잘못된 날짜임");
						startNum = 99;
					}
					else {
						ArrayList<Integer> dateList = getdayNum(monthOfLastDay, startYear, startMonth, startDay);
						startNum = dateList.get(startWeek-1);
					}
				}
				else {
					ArrayList<Integer> dateList = getdayNum(monthOfLastDay, startYear, startMonth, startDay);
					startNum = dateList.get(startWeek-1);
				}
			}
				
			String newStartDate = startYear + "-" + startMonth + "-" + startNum;
//			System.out.println("newStartDate : " + newStartDate);
			
			String[] endStr = endDate.split("-");
			int endYear = Integer.parseInt(endStr[0]);
			int endMonth = Integer.parseInt(endStr[1]);
			int endWeek = Integer.parseInt(endStr[2]);
			String endDayOfWeek = endStr[3];
			
			int endDay = whatDay(endDayOfWeek);
			int endNum = 0;
			
//			System.out.println(endYear + "년 " + endMonth + "월 " + endWeek + "번째  " + endDay + "일");
			
			Calendar endCal = Calendar.getInstance();
			endCal.set(endYear, endMonth-1, 1);
			int endMonthOfLastDay = endCal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			LocalDate endMonthStartDate = LocalDate.of(endYear, endMonth, 1);
			LocalDate endMonthEndDate = LocalDate.of(endYear, endMonth,endMonthOfLastDay);
			
			System.out.println(endMonthStartDate + " " + endMonthEndDate);
			
			DayOfWeek dayOfWeek2 = endMonthStartDate.getDayOfWeek();
			int endMonthStartDay = (dayOfWeek2.getValue()%7) + 1;
			
			dayOfWeek = monthEndDate.getDayOfWeek();
			int endMonthEndDay = (dayOfWeek2.getValue()%7) + 1;
			
			if (endMonth == 2) {
				if (endMonthOfLastDay == 29) {
					if (endWeek == 5 && endMonthStartDay != endDay) {
						System.out.println("잘못된 날짜임 5번째 요일이 없음(2월)");
						endNum = 99;
					}
					else {
						ArrayList<Integer> dateList = getdayNum(endMonthOfLastDay, endYear, endMonth, endDay);
						endNum = dateList.get(endWeek-1);
					}
				}
				else {
					if (endWeek == 5) {
						System.out.println("잘못된 날짜임(2월)");
						endNum = 99;
					}
					else {
						ArrayList<Integer> dateList = getdayNum(endMonthOfLastDay, endYear, endMonth, endDay);
						endNum = dateList.get(endWeek-1);
					}
				}
			}
			else {
				if (endWeek == 5) {
					System.out.println(endDay + " " + endMonthStartDay + " " + endMonthEndDay);
					if (endDay < endMonthStartDay && endDay > endMonthEndDay) {
						System.out.println("잘못된 날짜임");
						startNum = 99;
					}
					else {
						System.out.println("end쪽 날짜 리스트");
						ArrayList<Integer> dateList = getdayNum(endMonthOfLastDay, endYear, endMonth, endDay);
						endNum = dateList.get(endWeek-1);
					}
				}
				else {
					ArrayList<Integer> dateList = getdayNum(endMonthOfLastDay, endYear, endMonth, endDay);
					endNum = dateList.get(endWeek-1);
				}
			}
			
			
			String newEndDate = endYear + "-" + endMonth + "-" + endNum;
			System.out.println("newEndDate : " + newEndDate);

			String[] startArray = newStartDate.split("-");
			String finalStartDate = String.format("%04d-%02d-%02d", Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
			
			String[] endArray = newEndDate.split("-");
			String finalEndDate = String.format("%04d-%02d-%02d", Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));
			
			System.out.println("finalStart : " + finalStartDate + " , finalEnd : " + finalEndDate);
			
			boolean startState = dateCheck(finalStartDate);
			boolean endState = dateCheck(finalEndDate);
//
//			//System.out.println("startState : " + startState + " , endState : " + endState);
//			
			if (startState && endState) {
				System.out.println("finalStartDate : " + finalStartDate + " , newEndDate : " + finalEndDate);
				LocalDate start = LocalDate.parse(finalStartDate, DateTimeFormatter.ISO_DATE);
				LocalDate end = LocalDate.parse(finalEndDate, DateTimeFormatter.ISO_DATE);
				
				System.out.println("start : " + start + " , end : " + end);
				
				long diffDays = ChronoUnit.DAYS.between(start, end) + 1;
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
//			
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
	
	 public static int whatDay(String weekOfDay) {
		 String w = weekOfDay;
		 int day = -1;
		 
		 if (w.equals("Sun")) { day = 1; }
			else if (w.equals("Mon")) { day = 2; }
			else if (w.equals("Tue")) { day = 3; }
			else if (w.equals("Wed")) { day = 4; }
			else if (w.equals("Thu")) { day = 5; }
			else if (w.equals("Fri")) { day = 6; }
			else if (w.equals("Sat")) { day = 7; }
		 
		 return day;
	 }
	 
	 public static ArrayList<Integer> getdayNum(int monthOfLastDay, int year, int month, int day) {
		 
		 int count = 0;
		 ArrayList<Integer> dateList = new ArrayList<>();
		 Calendar cal = Calendar.getInstance();
		 for (int i = 1; i <= monthOfLastDay; i++) {
			 cal.set(year, month-1, i);
			 int nowDay = cal.get(Calendar.DAY_OF_WEEK);
			 if (day == nowDay) {
				 count++;
				 dateList.add(i);
			 }
		 }
		
		 System.out.println("dateList : " + dateList);
		
		 return dateList;
	 }
	
	 
}