//import java.io.*;
//import java.util.ArrayList;
//import java.util.Collections;
//
//class allocType {
//	int allCovered = 0;
//	int partiallyCovered = 0;
//	int notCovered = 0;
//}
//
//class FileData {
//	ArrayList<Integer> startPoint = new ArrayList<>();
//	int rightMove;
//	int upMove;
//	
//	int type;
//}
//
//public class paper2 {
//	static int maxSize = (10*10*10*10) + 1;
//	static int[][] field = new int[maxSize][maxSize];
//
//	public static void main(String[] args) throws IOException {
//		String fname = "D:\\GitHub\\algorithm\\05.ColorPaper\\code\\paper.inp";
//		String outfname = "D:\\GitHub\\algorithm\\05.ColorPaper\\code\\paper.out";
//		FileReader fr = new FileReader(fname);
//		ArrayList<FileData> FileList = FileSet(fr);
//		
//		ArrayList<allocType> resultList = spaceAlloc(FileList);
//		
//		FilePrint(outfname, resultList);
//	}
//	
//	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
//		BufferedReader br_f = new BufferedReader(fr);
//		ArrayList<FileData> FileArray = new ArrayList<>();
//		
//		String line = "";
//		
//		int arrSize = Integer.parseInt(br_f.readLine());
//		
//		for(int i = 0; i < arrSize; i++) {	
//			FileData data = new FileData();
//			line = br_f.readLine();
//			
//			String[] strArr = line.split(" ");
//			int[] intArr = new int[strArr.length];
//			for (int j = 0; j < strArr.length; j++) {
//				intArr[j] = Integer.parseInt(strArr[j]);
//			}
//
//			int x = intArr[0];
//			int y = intArr[1];
//			int w = intArr[2];
//			int h = intArr[3];
//			
//			data.startPoint.add(x);
//			data.startPoint.add(y);
//			data.rightMove = w;
//			data.upMove = h;	
//
//			FileArray.add(data);
//			
//		}
//		
//		br_f.close();
//		
//		return FileArray;
//	}
//	
//	public static void FilePrint(String outfname, ArrayList<allocType> resultList) throws IOException {
//		BufferedOutputStream bs = null;
//		bs = new BufferedOutputStream(new FileOutputStream(outfname));
//		String str = "";
//		
//		for (allocType print : resultList) {
//			str += print.allCovered + " " + print.partiallyCovered + " " + print.notCovered;
//		}
//		
//		System.out.println(str);
//		
//		bs.write(str.getBytes());
//		
//		bs.close();
//	}
//	
//	public static ArrayList<allocType> spaceAlloc(ArrayList<FileData> FileList) {
//		ArrayList<Integer> resultList = new ArrayList<>();
//		ArrayList<allocType> resultType = new ArrayList<>();
//		
//		for (int i = FileList.size(); i > 0; i--) {
//			int nowPoint = i-1;
//
//			int x = FileList.get(nowPoint).startPoint.get(0);
//			int y = FileList.get(nowPoint).startPoint.get(1);
//			int w = FileList.get(nowPoint).rightMove;
//			int h = FileList.get(nowPoint).upMove;
//			
//			int hEnd = y+h;
//			int wEnd = x+w;
//			
//			int AllocCount = 0; 
//			
//			FileList.get(nowPoint).type = 2;
//			for (int height = y; height < hEnd; height++) {
//				for (int width = x; width < wEnd; width++) {
//					if (field[width][height] == 0) {
//						field[width][height] = i;
//						AllocCount++;
//					}
//					else {
//						FileList.get(nowPoint).type = 1;
//					}
//				}
//			}
//			if (AllocCount == 0) {
//				FileList.get(nowPoint).type = 0;
//			}
//			
//			resultList.add(FileList.get(nowPoint).type);
//		}
//		
//		allocType count = new allocType();
//		
//		for (int i = 0; i < resultList.size(); i++) {
//			if (resultList.get(i) == 0) {
//				count.allCovered += 1;
//			}
//			else if (resultList.get(i) == 1) {
//				count.partiallyCovered += 1;
//			}
//			else if (resultList.get(i) == 2) {
//				count.notCovered += 1;		
//			}
//		}
//		
//		resultType.add(count);
//		
//		return resultType;
//	}
//	
//}