import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Data {
	int floor;
	int parent;
	int child;
}

public class family {

	public static void main(String[] args) throws IOException {
		String fname = "D:\\GitHub\\algorithm\\07.FamilyTree\\code\\family.inp";
		String outfname = "D:\\GitHub\\algorithm\\07.FamilyTree\\code\\family.out";
		FileReader fr = new FileReader(fname);
		ArrayList<ArrayList<Data>> data = FileSet(fr);

		//ArrayList<Integer> resultList = calc(data);
		
		//System.out.println("\nresultList : " + resultList);

		//FilePrint(outfname, resultList);
	}
	
	public static ArrayList<ArrayList<Data>> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		
		ArrayList<ArrayList<Data>> returnData = new ArrayList<>();
		
		int arrayListSize = Integer.parseInt(br_f.readLine());
		
		for (int i = 0; i < arrayListSize; i++) {
			ArrayList<Data> input = new ArrayList<>();
			
			int dataNum = Integer.parseInt(br_f.readLine());
			
			for (int j = 0; j < dataNum-1; j++) {
				Data addData = new Data();
				String[] strArr = br_f.readLine().split(" ");
				addData.parent = Integer.parseInt(strArr[0]);
				addData.child = Integer.parseInt(strArr[1]);
				
				input.add(addData);
			}
			
			returnData.add(input);
		}

		for (int i = 0; i < returnData.size(); i++) {
			for (int j = 0; j < returnData.get(i).size(); j++) {
				System.out.print(returnData.get(i).get(j).parent + " " + returnData.get(i).get(j).child + " | ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < returnData.size(); i++) {
			for (int j = 0; j < returnData.get(i).size(); j++) {
				int now = returnData.get(i).get(j).parent;
				
				for (int t = i+1; t < returnData.size();t++) {
					for (int v = 0; v < returnData.get(t).size(); v++) {
						if (now == returnData.get(t).get(v).child) {
							System.out.println(now + " 는 " + returnData.get(t).get(v).parent + " 의 자식이다.");
						}
					}
				}
			}
			System.out.println();
			System.out.println("----------");
		}
		
		br_f.close();
		
		return returnData;
	}
	
	public static void FilePrint(String outfname, ArrayList<ArrayList<int[]>> resultList) throws IOException {
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
	
	public static ArrayList<Integer> calc(ArrayList<ArrayList <int []>> data) {
		ArrayList<Integer> resultList = new ArrayList<>();
		ArrayList<Data> inform = new ArrayList<>();
		
//		for (int i = 0; i < data.size(); i++) {
//			for (int j = 0; j < data.get(i).size(); j++) {
//				System.out.print(data.get(i).get(j)[0] + " " + data.get(i).get(j)[1] + " | ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
//		for (int i = 0; i < data.size(); i++) {
//			for (int j = 0; j < data.get(i).size()-1; j++) {
//				int now = data.get(i).get(j)[0];
//				
//				for (int t = j+1; t < data.get(i).size(); t++) {
//					int next = data.get(i).get(t)[1];
//					
//					if (now == next) {
//						System.out.println("자식으로 존재, now : " + now + " , next : " + next);
//					}
//				}
//				
//				
////				System.out.println(data.get(i).get(j)[0] + " " + data.get(i).get(j)[1] + " | ");
////				System.out.print("비교  => ");
////				for (int t = j+1; t < data.get(i).size(); t++) {
////					System.out.print(data.get(i).get(t)[0] + " " + data.get(i).get(t)[1] + " | ");
////				}
////				System.out.println();
//			}
//			System.out.println("-----------");
//		}
		
		
		
		return resultList;
	}
	
}