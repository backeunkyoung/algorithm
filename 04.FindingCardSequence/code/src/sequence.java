import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class FileData {
	ArrayList<Integer> S = new ArrayList<>();
	ArrayList<Integer> C = new ArrayList<>();
}

public class sequence {	

	public static void main(String[] args) throws IOException {
		String fname = "D:\\GitHub\\algorithm\\04.FindingCardSequence\\code\\sequence.inp";
		String outfname = "D:\\GitHub\\algorithm\\04.FindingCardSequence\\code\\sequence.out";
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
		
		//System.out.println("resultList : " + resultList);
		
		for (int i = 0; i < resultList.size(); i++) {
			//System.out.println(resultList.get(i));
			str += resultList.get(i) + "\n";
		}
		
		//System.out.println(str);

		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<Integer> sequenceCalc(ArrayList<FileData> FileList) {
		
//		for (FileData print : FileList) {
//			System.out.println("S : " + print.S + "\nC : " + print.C);
//		}
		
		// S 배열을 오름차순으로 정렬
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
		
		
//		for (FileData print : FileList) {
//			System.out.println("오름차순 S : " + print.S);
//		}
		
		ArrayList newSList = new ArrayList<>();
		for (int i = FileList.get(0).C.size()-1; i >= 0; i--) {
			int removeValue = FileList.get(0).C.get(i);
			
			newSList.add(FileList.get(0).S.get(removeValue));
			
			FileList.get(0).S.remove(removeValue);
			
//			System.out.println("newS : " + newSList);
//			
//			for (FileData print : FileList) {
//				System.out.println("S : " + print.S);
//			}
//			
//			System.out.println("-----");
		}
		
		Collections.reverse(newSList);
		
		return newSList;
	}
	
}