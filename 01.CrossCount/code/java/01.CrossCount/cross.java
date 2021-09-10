import java.io.*;
import java.util.ArrayList;

class FileData {
	int arrSize;
	String s1;
	String s2;
}

public class cross {	
	static ArrayList<Integer> crossCountList = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		String fname = "cross.inp";
		String outfname = "cross.out";
		FileReader fr = new FileReader(fname);
		ArrayList<FileData> FileList = FileSet(fr);
		
		crossCalc(FileList);

		FilePrint(outfname);
	}
	
	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		ArrayList<FileData> FileArray = new ArrayList<>();
		
		String line = "";
		
		int numOfCount = Integer.parseInt(br_f.readLine());

		for(int i = 0; i < numOfCount; i++) {
			FileData data = new FileData();
			
			int valueCount = Integer.parseInt(br_f.readLine());
			data.arrSize = valueCount;
			
			line = br_f.readLine();
			data.s1 = line;
			
			line = br_f.readLine();
			data.s2 = line;
			
			FileArray.add(data);
		}
			
		br_f.close();
		
		return FileArray;
	}
	
	public static void FilePrint(String outfname) throws IOException {
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		String str = "";
		
		for (int i = 0; i < crossCountList.size(); i++) {
			str += "Case " + (i + 1) + ": " + crossCountList.get(i) + "\n";
		}

		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static void crossCalc(ArrayList<FileData> FileList) {
		int crossCount = 0;

		for (int i = 0; i < FileList.size(); i++) {
			crossCount = 0;
			
			String strS1[] = FileList.get(i).s1.split(" ");
			String strS2[] = FileList.get(i).s2.split(" ");
			
			int locate[] = new int[strS1.length];
			int arrS1[] = new int[strS1.length];
			int arrS2[] = new int[strS1.length];
			
			for (int j = 0; j < strS1.length; j++) {
				arrS1[j] = Integer.parseInt(strS1[j]);
				arrS2[j] = Integer.parseInt(strS2[j]);
			}
			
			for (int j = 0; j < strS1.length; j++) {
				for (int t = 0; t < strS1.length; t++) {
					if (arrS1[j] == arrS2[t]) {
						locate[j] = t;
						break;
					}
				}
			}		
			
			for (int j = 0; j < strS1.length; j++) {
				for (int t = j+1; t < strS1.length; t++) {
					if (locate[j] > locate[t]) {
						crossCount += 1;
					}
				}
			}
			
			crossCountList.add(crossCount);
		}
		
	}
}