import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class FileData {
	String color;
	
	ArrayList<String> Dice1 = new ArrayList<>();
	ArrayList<String> Dice2 = new ArrayList<>();
}

public class cube {

	public static void main(String[] args) throws IOException {
		String fname = "D:\\GitHub\\algorithm\\06.CubePainting\\code\\cube.inp";
		String outfname = "D:\\GitHub\\algorithm\\06.CubePainting\\code\\cube.out";
		FileReader fr = new FileReader(fname);
		ArrayList<FileData> FileList = FileSet(fr);

		ArrayList<String> resultList = equalsCheck(FileList);
		
		//System.out.println("\nresultList : " + resultList);

		FilePrint(outfname, resultList);
	}
	
	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		
		ArrayList<FileData> FileArray = new ArrayList<>();
		
		String line = "";
		
		while(true) {
			ArrayList<String> dice1 = new ArrayList<>();
			FileData data = new FileData();
			
			line = br_f.readLine();
			
			if (line.equals("rrrrrrrrrrrr") || line.equals("gggggggggggg") || line.equals("bbbbbbbbbbbb")) {
				break;
			}
			
			data.color = line;
			
			String[] strArray = data.color.split("");
			
			// check1
			for (int i = 0; i < 6; i++) {
				data.Dice1.add(strArray[i]);
			}
			for (int i = 6; i < 12; i++) {
				data.Dice2.add(strArray[i]);
			}
				
			FileArray.add(data);
		}
			
		br_f.close();
		
		return FileArray;
	}
	
	public static void FilePrint(String outfname, ArrayList<String> resultList) throws IOException {
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		String str = "";
		
		//System.out.println("resultList : " + resultList);
		
		for (int i = 0; i < resultList.size(); i++) {
			//System.out.println(resultList.get(i));
			str += resultList.get(i) + "\n";
		}
		
		System.out.println(str);

		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<String> equalsCheck(ArrayList<FileData> FileList) {
		ArrayList<String> resultList = new ArrayList<>();
		
		int[][] allCase = { {0, 1, 2, 3, 4, 5}, {0, 3, 1, 4, 2, 5}, {0, 4, 3, 2, 1, 5}, {0, 2, 4, 1, 3, 5},
							{5, 1, 3, 2, 4, 0}, {5, 2, 1, 4, 3, 0}, {5, 4, 2, 3, 1, 0}, {5, 3, 4, 1, 2, 0},
							{1, 5, 2, 3, 0, 4}, {1, 3, 5, 0, 2, 4}, {1, 0, 3, 2, 5, 4}, {1, 2, 0, 5, 3, 4},
							{4, 5, 3, 2, 0, 1}, {4, 2, 5, 0, 3, 1}, {4, 0, 2, 3, 5, 1}, {4, 3, 0, 5, 2, 1},
							{2, 1, 5, 0, 4, 3}, {2, 0, 1, 4, 5, 3}, {2, 4, 0, 5, 1, 3}, {2, 5, 4, 1, 0, 3},
							{3, 1, 0, 5, 4, 2}, {3, 5, 1, 4, 0, 2}, {3, 4, 5, 0, 1, 2}, {3, 0, 4, 1, 5, 2}
						  };
		
//		for (FileData print : FileList) {
//			System.out.println(print.Dice1 + " " + print.Dice2);
//		}
		
		
		for (int i = 0; i < FileList.size(); i++) {
			//System.out.println(FileList.get(i).Dice2.get(1));
			for (int j = 0; j < allCase.length; j++) {
				ArrayList<String> newDice = new ArrayList<>();
				
				for (int t = 0; t < allCase[0].length; t++) {
					int num = allCase[j][t];
					//System.out.print("num : " + num + " ");
					//System.out.print(FileList.get(i).Dice2.get(num) + " ");
					newDice.add(FileList.get(i).Dice2.get(num));
				}
//				System.out.println("Dice1 : " + FileList.get(i).Dice1);
//				System.out.println("newDice : " + newDice);
				
				if (FileList.get(i).Dice1.equals(newDice)) {
					//System.out.println("°°´Ù");
					resultList.add("TRUE");
					break;
				}
				
				if (j == allCase.length-1) {
					resultList.add("FALSE");
				}
//				
//				System.out.println("Dice1 : " + FileList.get(i).Dice1);
//				System.out.println();
			}
//			System.out.println("------");
		}
		
		
		resultList.add("TRUE");
		return resultList;
	}
	
}