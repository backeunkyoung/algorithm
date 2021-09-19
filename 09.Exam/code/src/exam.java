//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//
//class FileData {
//	String str;
//	String p;
//	int qNum;
//	ArrayList<String> q = new ArrayList();
//}
//
//public class exam {
//
//	public static void main(String[] args) throws IOException {
//		String fname = "D:\\GitHub\\algorithm\\09.Exam\\code\\exam.inp";
//		String outfname = "D:\\GitHub\\algorithm\\09.Exam\\code\\exam.out";
//		FileReader fr = new FileReader(fname);
//		ArrayList<FileData> data = FileSet(fr);
//
//		ArrayList<String> resultList = check(data);
//		
//		//System.out.println("\nresultList : " + resultList);
//
//		FilePrint(outfname, resultList);
//	}
//	
//	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
//		BufferedReader br_f = new BufferedReader(fr);
//		
//		ArrayList<FileData> returnData = new ArrayList<>();
//		
//		int arrayListSize = Integer.parseInt(br_f.readLine());
//		
//		for (int i = 0; i < arrayListSize; i++) {
//			FileData input = new FileData();
//			
//			input.str = br_f.readLine();
//			input.p = br_f.readLine();
//			input.qNum = Integer.parseInt(br_f.readLine());
//			ArrayList<String> q = new ArrayList<>();
//			
//			for (int j = 0; j < input.qNum; j++) {
//				q.add(br_f.readLine());
//			}
//			input.q = q;
//			
//			returnData.add(input);
//		}
//		
//		br_f.close();
//		
//		return returnData;
//	}
//	
//	public static void FilePrint(String outfname, ArrayList<String> resultList) throws IOException {
//		BufferedOutputStream bs = null;
//		bs = new BufferedOutputStream(new FileOutputStream(outfname));
//		String str = "";
//		
//		//System.out.println("resultList : " + resultList);
//		
//		for (int i = 0; i < resultList.size(); i++) {
//			//System.out.println(resultList.get(i));
//			str += resultList.get(i) + "\n";
//		}
//		
//		System.out.println("\n\n\n" + str);
//
//		bs.write(str.getBytes());
//		
//		bs.close();
//	}
//	
//	public static ArrayList<String> check(ArrayList<FileData> getData) {
//		ArrayList<String> resultList = new ArrayList<>();
//		
//		for (FileData print : getData) {
//			System.out.println(print.str + " " + print.p + " " + print.qNum + " " + print.q);
//		}
//		System.out.println();
//		
//		for (int i = 0; i < getData.size(); i++) {
//			String text = "Test Case: #" + (i+1);
//			resultList.add(text);
//			
//			System.out.println("\n" + text + "\n");
//			
//			String[] goodStr = getData.get(i).str.split("");
//			ArrayList<String> goodChar = new ArrayList<String>(Arrays.asList(goodStr));
//
//			System.out.println("goodChar : " + goodChar);
//			System.out.println("---------------------");
//			
//			String[] strP = getData.get(i).p.split("");
//			ArrayList<String> p = new ArrayList<>(Arrays.asList(strP));
//			
//			System.out.println("p : " + p);
//			System.out.println("---------------------");
//			
//			ArrayList<String> front = new ArrayList<>();
//			ArrayList<String> back = new ArrayList<>();
//			if (p.contains("*")) {
//				boolean division = false;
//				for (int t = 0; t < p.size(); t++) {
//					if (p.get(t).equals("*")) {
//						division = true;
//						continue;
//					}
//					
//					if (division) {
//						back.add(p.get(t));
//					}
//					else {
//						front.add(p.get(t));
//					}
//				}
//				System.out.println("front : " + front);
//				System.out.println("back : " + back);
//				System.out.println("---------------------------");
//			}
//
//			int qNum = getData.get(i).qNum;
//			
//			for (int j = 0; j < qNum; j++) {
//				boolean makeNo = false;
//				String[] strQ = getData.get(i).q.get(j).split("");
//				ArrayList<String> q = new ArrayList<>(Arrays.asList(strQ));
//				
//				System.out.println("\n\nq : " + q);
//				
//				if (p.contains("*")) {
//					ArrayList<Integer> removeIndex = new ArrayList<>();
//					
//					System.out.println("\n----- front 처리 ------");
//					for (int t = 0; t < front.size(); t++) {
//						if (front.get(t).equals("?")) {
//							ArrayList<String> qChar = new ArrayList<>(Arrays.asList(q.get(t)));
//							
//							if (goodChar.containsAll(qChar)) {
//								System.out.println(qChar + "은 좋은 집합의 부분집합 들어감, 생성 가능(front)");
//								removeIndex.add(t);
//							}
//							else {
//								System.out.println("front 생성 불가\n\n");
//								resultList.add("No");
//								makeNo = true;
//								break;
//							}
//						}
//						else {
//							if (p.get(t).equals(q.get(t))) {
//								System.out.println("P와 같은 문자임,삭제하기 " + q.get(t));
//								removeIndex.add(t);
//							}
//							else {
//								System.out.println("그냥 다른 문자이다");
//								resultList.add("No");
//								makeNo = true;
//								break;
//							}
//						}
//					}
//					System.out.println("removeIndex : " + removeIndex);
//					
//					if (!makeNo) {
//						System.out.println("\n----- back 처리 ------");
//						int indexNum = q.size();
//						int count = 0;
//						for (int t = back.size()-1; t >= 0; t--) {
//							indexNum--;
//							count++;
//							int pIndexNum = p.size() - count;
//							
//							System.out.println("t : " + t + " , indexNum : " + indexNum);
////							System.out.println(q.get(indexNum));
//							
//							System.out.println("check : " + q.get(indexNum));
//							if (back.get(t).equals("?")) {
//								ArrayList<String> qChar = new ArrayList<>(Arrays.asList(q.get(indexNum)));
//
//								if (goodChar.containsAll(qChar)) {
//									System.out.println("qChar : " + qChar);
//									System.out.print("back 부분집합 들어감, 생성 가능\n\n");
//									removeIndex.add(indexNum);
//								}
//								else {
//									System.out.println("back 생성 불가\n\n");
//									resultList.add("No");
//									makeNo = true;
//									break;
//								}
//							}
//							else {
//								if (p.get(pIndexNum).equals(q.get(indexNum))) {
//									System.out.println("P와 같은 문자임,삭제하기 " + q.get(indexNum));
//									removeIndex.add(indexNum);
//								}
//								else {
//									System.out.println("그냥 다른 문자이다");
//									resultList.add("No");
//									break;
//								}
//							}
//						}
//						Collections.sort(removeIndex);
//						System.out.println("removeIndex : " + removeIndex);
//					}
//					
//					if (!makeNo) {
//						while(true) {
//							if (removeIndex.size() == 0) {
//								break;
//							}
//							int removeNum = removeIndex.get(0);
//							q.set(removeIndex.get(0), "0");
//							removeIndex.remove(0);
//						}
////						System.out.println("q : " + q);
//						
//						while(q.remove("0")) {};
//						System.out.println("*확인할 q : " + q);
//						
//						boolean starTrue = false;
//						if (q.size() == 0) {
//							System.out.println("길이가 0");
//							starTrue = true;
//						}
//						for (int t = 0; t < q.size(); t++) {
//							ArrayList<String> starCheck = new ArrayList<>();
//							starCheck.add(q.get(t));
//
//							if (!(goodChar.containsAll(starCheck))) {
//								System.out.println("나쁜 문자 포함");
//								starTrue = true;
//								break;
//							}
//						}
//						
//						if (starTrue) {
//							resultList.add("Yes");
//							System.out.println("* 성립, 생성 가능");
//						}
//						else {
//							resultList.add("No");
//							System.out.println("* 어김, 생성 불가");
//						}
//					}
//					
//				}
//				else {
//					for (int t = 0; t < p.size(); t++) {
//						if (p.get(t).equals("?")) {
//							ArrayList<String> qChar = new ArrayList<>(Arrays.asList(q.get(t)));
//
//							if (goodChar.containsAll(qChar)) {
//								resultList.add("Yes");
//								System.out.print("? 가능, 부분집합 들어감, 생성 가능\n\n");
//							}
//							else {
//								resultList.add("No");
//								System.out.println("? 불가, 생성 불가\n\n");
//							}
//						}
//					}
//				}
//				
//			}
//			
//		}
//		
//		
//		return resultList;
//	}
//	
//}