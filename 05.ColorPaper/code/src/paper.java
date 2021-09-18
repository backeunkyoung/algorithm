import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class allocType {
	int allCovered = 0;
	int partiallyCovered = 0;
	int notCovered = 0;
}

class FileData {
	int x;
	int y;
	int w;
	int h;
	
	int type;
}

public class paper {
//	static int maxSize = (10*10*10*10) + 1;
	//static int[][] field = new int[maxSize][maxSize]; 
	
//	fiedtY.add([0,1,0])
//	fieldY[{0, 1, 2}, 1, 2, ]

	public static void main(String[] args) throws IOException {
		String fname = "D:\\GitHub\\algorithm\\05.ColorPaper\\code\\paper.inp";
		String outfname = "D:\\GitHub\\algorithm\\05.ColorPaper\\code\\paper.out";
		FileReader fr = new FileReader(fname);
		ArrayList<FileData> FileList = FileSet(fr);
		
		ArrayList<allocType> resultList = spaceAlloc(FileList);
		
//		for (allocType print : resultList) {
//			System.out.println(print.allCovered + " " + print.partiallyCovered + " " + print.notCovered);
//		}

		//FilePrint(outfname, resultList);
	}
	
	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		ArrayList<FileData> FileArray = new ArrayList<>();
		
		String line = "";
		
		int arrSize = Integer.parseInt(br_f.readLine());
		
		for(int i = 0; i < arrSize; i++) {	
			FileData data = new FileData();
			line = br_f.readLine();
			
			String[] strArr = line.split(" ");
			int[] intArr = new int[strArr.length];
			for (int j = 0; j < strArr.length; j++) {
				intArr[j] = Integer.parseInt(strArr[j]);
			}

			int x = intArr[0];
			int y = intArr[1];
			int w = intArr[2];
			int h = intArr[3];
			
			data.x = x;
			data.y = y;
			data.w = w;
			data.h = h;
					
			FileArray.add(data);
			
		}
		
		br_f.close();
		
		return FileArray;
	}
	
	public static void FilePrint(String outfname, ArrayList<allocType> resultList) throws IOException {
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		String str = "";
		
		for (allocType print : resultList) {
			//System.out.println(print.allCovered + " " + print.partiallyCovered + " " + print.notCovered);
			str += print.allCovered + " " + print.partiallyCovered + " " + print.notCovered;
		}
		
		System.out.println(str);
		
		//System.out.println("resultList : " + resultList);
		
//		for (int i = 0; i < resultList.size(); i++) {
//			//System.out.println(resultList.get(i));
//			str += resultList.get(i) + "\n";
//		}
		

		bs.write(str.getBytes());
		
		bs.close();
	}
	
	public static ArrayList<allocType> spaceAlloc(ArrayList<FileData> FileList) {
		ArrayList<Integer> resultList = new ArrayList<>();
		ArrayList<allocType> resultType = new ArrayList<>();
		
		int maxSize = (10*10*10*10*10*10*10*10) + 1;
		
		//ArrayList<int[]> field = new ArrayList<>();
		
		ArrayList<Data> spaceData = new ArrayList<>();
		
		int size = FileList.size();
		
		for (int i = 0; i < size; i++) {
			int y = FileList.get(i).y;
			int h = FileList.get(i).h;
			int x = FileList.get(i).x;
			int xw = x + FileList.get(i).w;

			spaceData.add(new Data(y, h, x, xw, i));
		}
		
		Collections.sort(spaceData);	// y 오름차순 정렬
		for (Data print : spaceData) {
			System.out.println("y : " + print.y + ", h : " + print.h + ", x : " + print.x + ", xw : " + print.xw + ", num : " + print.num);
		}
		
		int startY = spaceData.get(0).y;
		int endY = spaceData.get(size-1).y;
		
		int preY = -1;	// 이전 h 값
		
		ArrayList<ArrayList <int []>> newData = new ArrayList<>();
		ArrayList<int []> nowSpace = new ArrayList<>();
		
		int count = 0;
		for (Data data : spaceData) {
			count++;
			
			int nowY = data.y;
			
			int y = nowY;
			int h = data.h;
			int x = data.x;
			int xw = data.xw;
			int num = data.num;
			
			
			if (preY != nowY) {
				if (nowSpace.size() != 0) {
					newData.add(nowSpace);
					nowSpace = new ArrayList<>();
				}
			}

			int[] arr = {y, h, x, xw, num};
			nowSpace.add(arr);
			
			preY = nowY;
			
			if (count == size) {
				newData.add(nowSpace);
			}
		}
		
		
		System.out.println("------------------------------");
		for (int i = 0; i < newData.size(); i++) {
			for (int j = 0; j < newData.get(i).size(); j++) {
				for (int t = 0; t < 5; t++) {
					System.out.print(newData.get(i).get(j)[t] + " ");
				}
				System.out.print(" , ");
			}
			System.out.println();
		}
		System.out.println("------------------------------");
		
		System.out.println("startY : " + startY + " , endY : " + endY);
		
		System.out.println();
		
		ArrayList<int[]> field = new ArrayList<>();
		int[] fieldData = new int[maxSize];
		
		for (int i = 0; i < newData.size(); i++) {
			for (int j = 0; j < newData.get(i).size(); j++) {
				int startT = newData.get(i).get(j)[0];
				int endT = startT + newData.get(i).get(j)[1];

				for (int t = newData.get(i).get(j)[0]; t < endT; t++) {
					System.out.print(newData.get(i).get(j)[4] + " | ");
				}
				
//				for (int t = 0; t < 5; t++) {
//					System.out.print(newData.get(i).get(j)[t] + " ");
//				}
				System.out.println();
			}
			System.out.println("----------");
		}
	
		return resultType;
	}
	
	public static class Data implements Comparable<Data> {
		private int y;
		private int h;
		private int x;
		private int xw;
		private int num;
		
		public Data(int y, int h, int x, int xw, int num) {
			this.y = y;
			this.h = h;
			this.x = x;
			this.xw = xw;
			this.num = num;
		}
		
		@Override
		public int compareTo(Data data) {
			if (data.y < y) {
				return 1;
			}
			else if (data.y > y) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
}