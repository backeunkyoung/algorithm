import java.io.*;
import java.util.ArrayList;

class FileData {
	String str;
	String p;
	int qNum;
	ArrayList<String> q = new ArrayList();
}

public class exam {

	public static void main(String[] args) throws IOException {
		String fname = "exam.inp";
		String outfname = "exam.out";
		FileReader fr = new FileReader(fname);
		ArrayList<FileData> data = FileSet(fr);

		ArrayList<String> resultList = check(data);

		FilePrint(outfname, resultList);
	}
	
	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		
		ArrayList<FileData> returnData = new ArrayList<>();
		
		int arrayListSize = Integer.parseInt(br_f.readLine());
		
		for (int i = 0; i < arrayListSize; i++) {
			FileData input = new FileData();
			
			input.str = br_f.readLine();
			input.p = br_f.readLine();
			input.qNum = Integer.parseInt(br_f.readLine());
			ArrayList<String> q = new ArrayList<>();
			
			for (int j = 0; j < input.qNum; j++) {
				q.add(br_f.readLine());
			}
			input.q = q;
			
			returnData.add(input);
		}
		
		br_f.close();
		
		return returnData;
	}
	
	public static void FilePrint(String outfname, ArrayList<String> resultList) throws IOException {
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		String str = "";
		
		for (int i = 0; i < resultList.size(); i++) {
			str += resultList.get(i) + "\n";
		}
		
		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<String> check(ArrayList<FileData> getData) {
		ArrayList<String> resultList = new ArrayList<>();
		
		for (int i = 0; i < getData.size(); i++) {
			String text = "Test Case: #" + (i+1);
			resultList.add(text);
			
			String goodChar = getData.get(i).str;
			
			String p = getData.get(i).p;
			boolean starCheck = (p.indexOf('*') != -1);
			
			String front = p;
			String back = "";
			
			if (starCheck) {
				int starIndex = p.indexOf('*');
				front = p.substring(0,starIndex);
				back = p.substring(starIndex+1);
			}

			int qNum = getData.get(i).qNum;
			
			for (int j = 0; j < qNum; j++) {
				String q = getData.get(i).q.get(j);
				int frontLen = front.length();
				int backLen =  back.length();
				int qLen = q.length();
				boolean makeNo = false;
				
				String starString = q.substring(frontLen, qLen - backLen);
				String qFront = q.substring(0, frontLen);
				String qBack = q.substring(qLen - backLen);
								
				for (int t = 0; t < frontLen; t++) {
					char pChar = front.charAt(t);
					char qChar = q.charAt(t);
					
					if (pChar == '?') {
						if (goodChar.indexOf(qChar) == -1) {
							resultList.add("No");
							makeNo = true;
							break;
						}
					}
					else {
						if (pChar != qChar) {
							resultList.add("No");
							makeNo = true;
							break;
						}
					}
				}
				
				if (!makeNo) {
					
					for (int t = 0; t < backLen; t++) {
						char pChar = back.charAt(t);
						char qChar = q.charAt(qLen - backLen + t);
							
						
						if (pChar == '?') {
							
							if (goodChar.indexOf(qChar) == -1) {
								resultList.add("No");
								makeNo = true;
								break;
							}
						}
						else {
							if (pChar != qChar) {
								resultList.add("No");
								makeNo = true;
								break;
							}
						}
					}
				}
				
				if (!makeNo) {
					int starLen = starString.length();
					
					if (starLen == 0) resultList.add("Yes");
					
					for (int t = 0; t < starLen; t++) {
						char starChar = starString.charAt(t);
						if (goodChar.indexOf(starChar) == -1) {
							resultList.add("Yes");
							break;
						}
						
						if (t == starLen - 1) {
							resultList.add("No");
						}
					}
				}
			}
		}
		
		
		return resultList;
	}
	
}