//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Iterator;
//
//public class family {
//
//	public static void main(String[] args) throws IOException {
//		String fname = "D:\\GitHub\\algorithm\\07.FamilyTree\\code\\family.inp";
//		String outfname = "D:\\GitHub\\algorithm\\07.FamilyTree\\code\\family.out";
//		FileReader fr = new FileReader(fname);
//		FileSet(fr, outfname);
//
//		//ArrayList<Integer> resultList = calc(data);
//		
//		//System.out.println("\nresultList : " + resultList);
//
//		//FilePrint(outfname, resultList);
//	}
//	
//	public static void FileSet(FileReader fr, String outfname) throws IOException {
//		BufferedReader br_f = new BufferedReader(fr);
//		BufferedOutputStream bs = null;
//		bs = new BufferedOutputStream(new FileOutputStream(outfname));
//		
//		int arrayListSize = Integer.parseInt(br_f.readLine());
//		
//		String str = "";
//		
//		for (int i = 0; i < arrayListSize; i++) {
//			Map<Integer, ArrayList<Integer>> map = new HashMap<>();
//			
//			int dataNum = Integer.parseInt(br_f.readLine());
//			
//			System.out.println("----------------");
//			ArrayList<Integer> childList = new ArrayList<>();
//			for (int j = 0; j < dataNum-1; j++) {
//				
//				String[] strArr = br_f.readLine().split(" "); // 3 5
//				Integer parent = Integer.valueOf(strArr[0]);
//				Integer child = Integer.parseInt(strArr[1]);
//				
//				System.out.println(" parent : " + parent + ", child : " + child);
//				
//				if (map.get(parent) == null) {
//					map.put(parent, new ArrayList<>(Arrays.asList(child)));
//				} else {
//					ArrayList<Integer> newChildList = new ArrayList<>(map.get(parent));
//					newChildList.add(child);
//					
//					//System.out.println("childLIst : " + childList);
//					map.put(parent, newChildList);
//				}
//				childList.add(child);
//			}
//			System.out.println("childList : " + childList);
//			
//			
//			// root find
//			Integer root = 0;
//			Iterator<Integer> iter = map.keySet().iterator();
//			while(iter.hasNext()) {
//				Integer key = iter.next(); 
//				if (!childList.contains(key)) {
//					root = key;
//					break;
//				}
//			}
//			System.out.println("root : " + root);
//			ArrayList<Integer> keyList = new ArrayList(Arrays.asList(root));
//			System.out.println("keyList : " + keyList);
//			
//			int depth = 0;
//			while(true) {
//				ArrayList<Integer> childArray = new ArrayList<>();
//				for (int t=0; t< keyList.size() ; t++) {
//					ArrayList<Integer> nowChildList = map.get(keyList.get(t));
//					
//					System.out.println("key : " + keyList.get(t) + " nowChildList : " + nowChildList);
//					if (nowChildList != null) {
//						for (int k=0; k<nowChildList.size(); k++) {
//							childArray.add(nowChildList.get(k));
//						}
//					}
//				}
//				keyList = childArray;
//				
//				depth++;
//				if (keyList.size() == 0) break;
//			}
//			System.out.println("depth : " + depth);
//			str += depth + "\n";
//					
//			// search Tree
//		}
//		bs.write(str.getBytes());
//
//		
//		br_f.close();
//		bs.close();
//	}
//	
////	public static void FilePrint(String outfname, ArrayList<ArrayList<int[]>> resultList) throws IOException {
////		BufferedOutputStream bs = null;
////		bs = new BufferedOutputStream(new FileOutputStream(outfname));
////		String str = "";
////		
////		//System.out.println("resultList : " + resultList);
////		
////		for (int i = 0; i < resultList.size(); i++) {
////			//System.out.println(resultList.get(i));
////			str += resultList.get(i) + "\n";
////		}
////		
////		System.out.println(str);
////
////		bs.write(str.getBytes());
////		
////		bs.close();
////	}
////	
////	public static ArrayList<Integer> calc(ArrayList<ArrayList <int []>> data) {
////		ArrayList<Integer> resultList = new ArrayList<>();
////		ArrayList<Data> inform = new ArrayList<>();
////		
//////		for (int i = 0; i < data.size(); i++) {
//////			for (int j = 0; j < data.get(i).size(); j++) {
//////				System.out.print(data.get(i).get(j)[0] + " " + data.get(i).get(j)[1] + " | ");
//////			}
//////			System.out.println();
//////		}
//////		System.out.println();
////		
//////		for (int i = 0; i < data.size(); i++) {
//////			for (int j = 0; j < data.get(i).size()-1; j++) {
//////				int now = data.get(i).get(j)[0];
//////				
//////				for (int t = j+1; t < data.get(i).size(); t++) {
//////					int next = data.get(i).get(t)[1];
//////					
//////					if (now == next) {
//////						System.out.println("자식으로 존재, now : " + now + " , next : " + next);
//////					}
//////				}
//////				
//////				
////////				System.out.println(data.get(i).get(j)[0] + " " + data.get(i).get(j)[1] + " | ");
////////				System.out.print("비교  => ");
////////				for (int t = j+1; t < data.get(i).size(); t++) {
////////					System.out.print(data.get(i).get(t)[0] + " " + data.get(i).get(t)[1] + " | ");
////////				}
////////				System.out.println();
//////			}
//////			System.out.println("-----------");
//////		}
////		
////		
////		
////		return resultList;
////	}
//	
//}