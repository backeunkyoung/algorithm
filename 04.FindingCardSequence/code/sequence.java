import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class FileData {
	ArrayList<Integer> S = new ArrayList<>();
	ArrayList<Integer> C = new ArrayList<>();
}

public class sequence {	

	public static void main(String[] args) throws IOException {
		String fname = "sequence.inp";
		String outfname = "sequence.out";
		FileReader fr = new FileReader(fname);
		ArrayList<FileData> FileList = FileSet(fr);
		
		ArrayList<Integer> resultList = sequenceCalc(FileList);

		FilePrint(outfname, resultList);
	}
	
	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		ArrayList<FileData> FileArray = new ArrayList<>();
		
		String line = "";
		
		int arrSize = Integer.parseInt(br_f.readLine());
		FileData data = new FileData();
		
		for(int i = 0; i < arrSize; i++) {	
			int value = Integer.parseInt(br_f.readLine());
			data.S.add(value);	
		}
		for(int i = 0; i < arrSize; i++) {	
			int value = Integer.parseInt(br_f.readLine());
			data.C.add(value);	
		}
		
		FileArray.add(data);
			
		br_f.close();
		
		return FileArray;
	}
	
	public static void FilePrint(String outfname, ArrayList<Integer> resultList) throws IOException {
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		String str = "";
		
		for (int i = 0; i < resultList.size(); i++) {
			str += resultList.get(i) + "\n";
		}

		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<Integer> sequenceCalc(ArrayList<FileData> FileList) {
		
		for (int i = 0; i < FileList.get(0).S.size(); i++) {
			for (int j = i+1; j < FileList.get(0).S.size(); j++) {
				
				if (FileList.get(0).S.get(i) > FileList.get(0).S.get(j)) {
					int tmpI = FileList.get(0).S.get(i);
					int tmpJ = FileList.get(0).S.get(j);
					
					FileList.get(0).S.remove(i);
					FileList.get(0).S.add(j, tmpI);

					FileList.get(0).S.remove(j-1);
					FileList.get(0).S.add(i, tmpJ);
				}
			}
		}
		
		ArrayList newSList = new ArrayList<>();
		for (int i = FileList.get(0).C.size()-1; i >= 0; i--) {
			int removeValue = FileList.get(0).C.get(i);
			
			newSList.add(FileList.get(0).S.get(removeValue));
			
			FileList.get(0).S.remove(removeValue);
		}
		
		Collections.reverse(newSList);
		
		return newSList;
	}
	
}