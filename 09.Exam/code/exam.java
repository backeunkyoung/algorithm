import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
			
			String[] goodStr = getData.get(i).str.split("");
			ArrayList<String> goodChar = new ArrayList<String>(Arrays.asList(goodStr));
			
			String[] strP = getData.get(i).p.split("");
			ArrayList<String> p = new ArrayList<>(Arrays.asList(strP));
			
			ArrayList<String> front = new ArrayList<>();
			ArrayList<String> back = new ArrayList<>();
			if (p.contains("*")) {
				boolean division = false;
				for (int t = 0; t < p.size(); t++) {
					if (p.get(t).equals("*")) {
						division = true;
						continue;
					}
					
					if (division) {
						back.add(p.get(t));
					}
					else {
						front.add(p.get(t));
					}
				}
			}

			int qNum = getData.get(i).qNum;
			
			for (int j = 0; j < qNum; j++) {
				boolean makeNo = false;
				String[] strQ = getData.get(i).q.get(j).split("");
				ArrayList<String> q = new ArrayList<>(Arrays.asList(strQ));
				
				if (p.contains("*")) {
					ArrayList<Integer> removeIndex = new ArrayList<>();
					
					for (int t = 0; t < front.size(); t++) {
						if (front.get(t).equals("?")) {
							ArrayList<String> qChar = new ArrayList<>(Arrays.asList(q.get(t)));
							
							if (goodChar.containsAll(qChar)) {
								removeIndex.add(t);
							}
							else {
								resultList.add("No");
								makeNo = true;
								break;
							}
						}
						else {
							if (p.get(t).equals(q.get(t))) {
								removeIndex.add(t);
							}
							else {
								resultList.add("No");
								makeNo = true;
								break;
							}
						}
					}
					
					if (!makeNo) {
						int indexNum = q.size();
						int count = 0;
						for (int t = back.size()-1; t >= 0; t--) {
							indexNum--;
							count++;
							int pIndexNum = p.size() - count;
							
							if (back.get(t).equals("?")) {
								ArrayList<String> qChar = new ArrayList<>(Arrays.asList(q.get(indexNum)));

								if (goodChar.containsAll(qChar)) {
									removeIndex.add(indexNum);
								}
								else {
									resultList.add("No");
									makeNo = true;
									break;
								}
							}
							else {
								if (p.get(pIndexNum).equals(q.get(indexNum))) {
									removeIndex.add(indexNum);
								}
								else {
									resultList.add("No");
									break;
								}
							}
						}
						Collections.sort(removeIndex);
					}
					
					if (!makeNo) {
						while(true) {
							if (removeIndex.size() == 0) {
								break;
							}
							int removeNum = removeIndex.get(0);
							q.set(removeIndex.get(0), "0");
							removeIndex.remove(0);
						}
						
						while(q.remove("0")) {};
						
						boolean starTrue = false;
						if (q.size() == 0) {
							starTrue = true;
						}
						for (int t = 0; t < q.size(); t++) {
							ArrayList<String> starCheck = new ArrayList<>();
							starCheck.add(q.get(t));

							if (!(goodChar.containsAll(starCheck))) {
								starTrue = true;
								break;
							}
						}
						
						if (starTrue) {
							resultList.add("Yes");
						}
						else {
							resultList.add("No");
						}
					}
					
				}
				else {
					for (int t = 0; t < p.size(); t++) {
						if (p.get(t).equals("?")) {
							ArrayList<String> qChar = new ArrayList<>(Arrays.asList(q.get(t)));

							if (goodChar.containsAll(qChar)) {
								resultList.add("Yes");
							}
							else {
								resultList.add("No");
							}
						}
					}
				}
				
			}
			
		}
		
		
		return resultList;
	}
	
}