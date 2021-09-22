import java.io.*;
import java.util.ArrayList;
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
		String fname = "calendar.inp";
		String outfname = "calendar.out";
		FileReader fr = new FileReader(fname);
		ArrayList<fileData> data = FileSet(fr);

		ArrayList<Long> resultList = calc(data);

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

		for (int i = 0; i < resultList.size(); i++) {
			str += resultList.get(i) + "\n";
		}

		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<Long> calc(ArrayList<fileData> inputData) {
		ArrayList<Long> resultList = new ArrayList<>();
		
		for (fileData data : inputData) {
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
	
	public static long zeroCalc(String inputStartDate, String inputEndDate) {
		long result = 0;
		
		String[] startArray = inputStartDate.split("-");
		String startDate = String.format("%04d-%02d-%02d", Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
		
		String[] endArray = inputEndDate.split("-");
		String endDate = String.format("%04d-%02d-%02d", Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));
		
		try {
			boolean startState = dateCheck(startDate);
			boolean endState = dateCheck(endDate);
			
			if (startState && endState) {
				long diffDays = getDiffDays(startDate, endDate);
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
	
	public static long oneCalc(String inputStartDate, String inputEndDate) {
		long result = 0;
		
		String[] startArray = inputStartDate.split("-");
		String startDate = String.format("%04d-%02d-%02d", Integer.parseInt(startArray[0]), Integer.parseInt(startArray[1]), Integer.parseInt(startArray[2]));
		
		try {	
			String newEndDate = typeChange(inputEndDate);
			
			boolean startState = dateCheck(startDate);
			boolean endState = dateCheck(newEndDate);
			
			if (startState && endState) {
				long diffDays = getDiffDays(startDate, newEndDate);
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
	
	public static long twoCalc(String inputStartDate, String inputEndDate) {
		long result = 0;
		
		String[] endArray = inputEndDate.split("-");
		String endDate = String.format("%04d-%02d-%02d", Integer.parseInt(endArray[0]), Integer.parseInt(endArray[1]), Integer.parseInt(endArray[2]));
		
		try {			
			String newStartDate = typeChange(inputStartDate);

			boolean startState = dateCheck(newStartDate);
			boolean endState = dateCheck(endDate);
			
			if (startState && endState) {
				long diffDays = getDiffDays(newStartDate, endDate);
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
	
	public static long threeCalc(String inputStartDate, String inputEndDate) {
		long result = 0;
		
		try {
			String newStartDate = typeChange(inputStartDate);
			String newEndDate = typeChange(inputEndDate);
			
			boolean startState = dateCheck(newStartDate);
			boolean endState = dateCheck(newEndDate);
			
			if (startState && endState) {
				long diffDays = getDiffDays(newStartDate, newEndDate);
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
		 ArrayList<Integer> dateList = new ArrayList<>();
		 for (int i = 1; i <= monthOfLastDay; i++) {
			 LocalDate date = LocalDate.of(year, month, i);
			 DayOfWeek dayOfWeek = date.getDayOfWeek();
			 int iDay = (dayOfWeek.getValue()%7) + 1;
			 if (day == iDay) {
				 dateList.add(i);
			 }
		 }
		 return dateList;
	 }
	
	public static String typeChange(String inputDate) {
		String newDate;
		String[] str = inputDate.split("-");
		int inputYear = Integer.parseInt(str[0]);
		int inputMonth = Integer.parseInt(str[1]);
		int inputWeek = Integer.parseInt(str[2]);
		String inputDayOfWeek = str[3];
		int inputDay = whatDay(inputDayOfWeek);
		int inputDateNum = 0;
		
		Calendar cal = Calendar.getInstance();
		cal.set(inputYear, inputMonth-1, 1);
		int monthOfLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		
		LocalDate monthStartDate = LocalDate.of(inputYear, inputMonth, 1);
		LocalDate monthEndDate = LocalDate.of(inputYear, inputMonth, monthOfLastDay);
		
		DayOfWeek dayOfWeek = monthStartDate.getDayOfWeek();
		int monthStartDay = (dayOfWeek.getValue()%7) + 1;
		
		dayOfWeek = monthEndDate.getDayOfWeek();
		int monthEndDay = (dayOfWeek.getValue()%7) + 1;
		
		if (inputMonth == 2) {
			if (monthOfLastDay == 29) {
				if (inputWeek == 5 && monthStartDay != inputDay) {
					inputDateNum = 99;
				}
				else {
					ArrayList<Integer> dateList = getdayNum(monthOfLastDay, inputYear, inputMonth, inputDay);
					inputDateNum = dateList.get(inputWeek-1);
				}
			}
			else {
				if (inputWeek == 5) {
					inputDateNum = 99;
				}
				else {
					ArrayList<Integer> dateList = getdayNum(monthOfLastDay, inputYear, inputMonth, inputDay);
					inputDateNum = dateList.get(inputWeek-1);
				}
			}
		}
		else {
			if (inputWeek == 5) {
				int max = monthEndDay;
				int min = monthStartDay;
				
				if (max < min) {
					int tmp = max;
					max = min;
					min = tmp;
				}
				
				if (inputDay < min || inputDay > max) {
					inputDateNum = 99;
				}
				else {
					ArrayList<Integer> dateList = getdayNum(monthOfLastDay, inputYear, inputMonth, inputDay);
					inputDateNum = dateList.get(inputWeek-1);
				}
			}
			else {
				ArrayList<Integer> dateList = getdayNum(monthOfLastDay, inputYear, inputMonth, inputDay);
				inputDateNum = dateList.get(inputWeek-1);
			}
		}
		
		newDate = String.format("%04d-%02d-%02d", inputYear, inputMonth, inputDateNum);

		return newDate;
	}
	
	public static long getDiffDays(String newStartDate, String newEndDate) {
		long diffDays = 0;
		
		LocalDate start = LocalDate.parse(newStartDate, DateTimeFormatter.ISO_DATE);
		LocalDate end = LocalDate.parse(newEndDate, DateTimeFormatter.ISO_DATE);
		
		diffDays = ChronoUnit.DAYS.between(start, end);
		diffDays = Math.abs(diffDays);

		return diffDays;
	}
}