//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//class FileData {
//	String dealer;
//	String cardList;
//}
//
//class UserCard {
//	ArrayList<String> S = new ArrayList<>();
//	ArrayList<String> W = new ArrayList<>();
//	ArrayList<String> N = new ArrayList<>();
//	ArrayList<String> E = new ArrayList<>();
//}
//
//class SortCard {
//	ArrayList<String> C = new ArrayList<>();
//	ArrayList<String> D = new ArrayList<>();
//	ArrayList<String> S = new ArrayList<>();
//	ArrayList<String> H = new ArrayList<>();
//}
//
//
//public class bridge2 {	
//	static ArrayList<Integer> crossCountList = new ArrayList<Integer>();
//	
//	public static void main(String[] args) throws IOException {
//		String fname = "D:\\GitHub\\algorithm\\03.Bridge\\java\\bridge.inp";
//		String outfname = "D:\\GitHub\\algorithm\\03.Bridge\\java\\bridge.out";
//		FileReader fr = new FileReader(fname);
//		
//		ArrayList<FileData> FileList = FileSet(fr);
//		
//		ArrayList<UserCard> UserArray = deals(FileList);
//		
//		ArrayList<UserCard> ResultArray = sort(UserArray);
//		
////		for (UserCard print : ResultArray) {
////			System.out.println(print.S);
////			System.out.println(print.W);
////			System.out.println(print.N);
////			System.out.println(print.E);
////			System.out.println();
////		}
//
//		FilePrint(outfname, ResultArray);
//	}
//	
//	public static ArrayList<FileData> FileSet(FileReader fr) throws IOException {
//		BufferedReader br_f = new BufferedReader(fr);
//		ArrayList<FileData> FileArray = new ArrayList<>();
//		
//		String line = "";
//		
//		
//		while(true) {
//			FileData data = new FileData();
//			line = br_f.readLine();
//			
//			if (line.equals("#")) {
//				break;
//			}
//			
//			data.dealer = line;
//			data.cardList = br_f.readLine();
//			data.cardList += br_f.readLine();
//			
//			FileArray.add(data);
//			
//		}
//
////		for (FileData print : FileArray) {
////			System.out.println("dealer : " + print.dealer + " , cardList : " + print.cardList);
////		}
//		
//		br_f.close();
//		
//		return FileArray;
//	}
//	
//	public static void FilePrint(String outfname, ArrayList<UserCard> ResultArray) throws IOException {
//		BufferedOutputStream bs = null;
//		bs = new BufferedOutputStream(new FileOutputStream(outfname));
//		ArrayList<String> locate = new ArrayList<>(Arrays.asList("S", "W", "N", "E"));
//		
//		StringBuilder str = new StringBuilder();
//		
//		for (UserCard print : ResultArray) {
//			str.append("S: ");
//			for (String value : print.S) {
//				str.append(value);
//				str.append(" ");
//			}
//			str.append("\n");
//			str.append("W: ");
//			for (String value : print.W) {
//				str.append(value);
//				str.append(" ");
//			}
//			str.append("\n");
//			str.append("N: ");
//			for (String value : print.N) {
//				str.append(value);
//				str.append(" ");
//			}
//			str.append("\n");
//			str.append("E: ");
//			for (String value : print.E) {
//				str.append(value);
//				str.append(" ");
//			}
//			str.append("\n");
//		}
//		System.out.println(str);
//		
//		String text = str.toString();
//		
//		bs.write(text.getBytes());
//		
//		bs.close();
//	}
//	
//	public static ArrayList<UserCard> deals(ArrayList<FileData> FileList) {
//		ArrayList<UserCard> userArray = new ArrayList<>();
//		ArrayList<String> userLocate = new ArrayList<>(Arrays.asList("S", "W", "N", "E"));
//		
////		for (FileData print : FileList) {
////			System.out.println("dealer : " + print.dealer + " , cardList : " + print.cardList);
////		}
//		
//		String[][] card = new String[FileList.size()][52];
//		
//		for (int i = 0; i < FileList.size(); i++) {
//			UserCard user = new UserCard();
//			
//			ArrayList<String> S = new ArrayList<>();
//			ArrayList<String> W = new ArrayList<>();
//			ArrayList<String> N = new ArrayList<>();
//			ArrayList<String> E = new ArrayList<>();
//			
//			int count = 0;
//			for (int j = 0; j < (FileList.get(i).cardList.length()); j += 2) {
//				card[i][count] = FileList.get(i).cardList.substring(j, (j+2));
//				count++;
//			}
//			
//			String dealer = FileList.get(i).dealer;
//			int num = userLocate.indexOf(dealer);
//			
//			for (int t = 0; t < 52; t++) {
//				//System.out.println(userLocate.get((num+1)%4) + " " + (card[i][t]));
//				String inputUser = userLocate.get((num+1)%4);
//				String cardData = card[i][t];
//				
//				if (inputUser.equals("S")) {
//					S.add(cardData);
//				}
//				else if (inputUser.equals("W")) {
//					W.add(cardData);
//				}
//				else if (inputUser.equals("N")) {
//					N.add(cardData);
//				}
//				else if (inputUser.equals("E")) {
//					E.add(cardData);
//				}
//					
//				num++;
//			}
//			
//			user.S = S;
//			user.W = W;
//			user.N = N;
//			user.E = E;
//			
//			userArray.add(user);
//		}
//		
//		return userArray;
//	}
//	
//	public static ArrayList<UserCard> sort(ArrayList<UserCard> UserArray) {
//		ArrayList<UserCard> resultArray = new ArrayList<>();
//		ArrayList<String> userLocate = new ArrayList<>(Arrays.asList("S", "W", "N", "E"));
//		
//		for (int i = 0; i < UserArray.size(); i++) {
//			ArrayList<SortCard> result = new ArrayList<>();
//			
//			for (int j = 0; j < 4; j++) {
//				String locate = userLocate.get(j);
//				SortCard sort = new SortCard();
//				
//				ArrayList<String> C = new ArrayList<>();
//				ArrayList<String> D = new ArrayList<>();
//				ArrayList<String> S = new ArrayList<>();
//				ArrayList<String> H = new ArrayList<>();
//				ArrayList<ArrayList> T = new ArrayList<>();
//				T.add(C);
//				T.add(D);
//				T.add(S);
//				T.add(H);
//				
//				
//				for (int t = 0; t < 13; t++) {
//					if (locate.equals("S")) {
//						String TT= UserArray.get(i).S.get(t);
//						int index = 0;
//						if (TT.startsWith("C")) index = 0;
//						if (TT.startsWith("D")) index = 1;
//						if (TT.startsWith("S")) index = 2;
//						if (TT.startsWith("H")) index = 3;
//						
//						
//						
//						
//						
//						
//							T.get(index).add(TT);
//					}
//					else if (locate.equals("W")) {
//
//						if ((UserArray.get(i).W.get(t).substring(0, 1)).equals("C")) {
//							C.add(UserArray.get(i).W.get(t));
//						}
//						if ((UserArray.get(i).W.get(t).substring(0, 1)).equals("D")) {
//							D.add(UserArray.get(i).W.get(t));
//						}
//						if ((UserArray.get(i).W.get(t).substring(0, 1)).equals("S")) {
//							S.add(UserArray.get(i).W.get(t));
//						}
//						if ((UserArray.get(i).W.get(t).substring(0, 1)).equals("H")) {
//							H.add(UserArray.get(i).W.get(t));
//						}
//					}
//					else if (locate.equals("N")) {
//
//						if ((UserArray.get(i).N.get(t).substring(0, 1)).equals("C")) {
//							C.add(UserArray.get(i).N.get(t));
//						}
//						if ((UserArray.get(i).N.get(t).substring(0, 1)).equals("D")) {
//							D.add(UserArray.get(i).N.get(t));
//						}
//						if ((UserArray.get(i).N.get(t).substring(0, 1)).equals("S")) {
//							S.add(UserArray.get(i).N.get(t));
//						}
//						if ((UserArray.get(i).N.get(t).substring(0, 1)).equals("H")) {
//							H.add(UserArray.get(i).N.get(t));
//						}
//					}
//					else if (locate.equals("E")) {
//
//						if ((UserArray.get(i).E.get(t).substring(0, 1)).equals("C")) {
//							C.add(UserArray.get(i).E.get(t));
//						}
//						if ((UserArray.get(i).E.get(t).substring(0, 1)).equals("D")) {
//							D.add(UserArray.get(i).E.get(t));
//						}
//						if ((UserArray.get(i).E.get(t).substring(0, 1)).equals("S")) {
//							S.add(UserArray.get(i).E.get(t));
//						}
//						if ((UserArray.get(i).E.get(t).substring(0, 1)).equals("H")) {
//							H.add(UserArray.get(i).E.get(t));
//						}
//					}
//				}
//				
//				sort.C = C;
//				sort.D = D;
//				sort.S = S;
//				sort.H = H;
//				
//				result.add(sort);
//			}
//			
////			System.out.println("--- 1�� ���� ---");
////			for (SortCard print : result) {
////				System.out.println(print.C + " " + print.D + " " + print.S  + " " + print.H);
////			}
////			System.out.println("-------");
////			System.out.println();
//			
//			ArrayList<String> SouthList = new ArrayList<>();
//			ArrayList<String> WestList = new ArrayList<>();
//			ArrayList<String> NorthList = new ArrayList<>();
//			ArrayList<String> EastList = new ArrayList<>();
//			
//			UserCard sortingResult = new UserCard();
//			
//			for (int j = 0; j < 4; j++) {	// S, W, N, E
//				String locate = userLocate.get(j);
//				//System.out.println("locate : " + locate);
//				
//				ArrayList<String> inputList = new ArrayList<>();
//				
//				for (int t = 0; t < 4; t++) {	// C, D, S, H
//					ArrayList<String> Shape = new ArrayList<>(Arrays.asList("C", "D", "S", "H"));
//					String cardShape = Shape.get(t);
//					//System.out.println("cardShape : " + cardShape);
//					
//					ArrayList<String> CList = new ArrayList<>();
//					ArrayList<String> DList = new ArrayList<>();
//					ArrayList<String> SList = new ArrayList<>();
//					ArrayList<String> HList = new ArrayList<>();
//					
//					if (cardShape.equals("C")) {
//						
//						ArrayList<String> numStr = new ArrayList<>();
//						ArrayList<String> charStr = new ArrayList<>();
//						
//						ArrayList<String> newCharStr = new ArrayList<>();
//						
//						for (int v = 0; v < result.get(j).C.size(); v++) {
//							String nextChar = result.get(j).C.get(v).substring(1, 2);
//							int asciiChar = (int)nextChar.toCharArray()[0];
//							
//							if (asciiChar <= 57) {
//								numStr.add(result.get(j).C.get(v));
//							}
//							else {
//								charStr.add(result.get(j).C.get(v));
//							}
//						}
//						
//						for (int v = 0; v < numStr.size()-1; v++) {
//							for (int b = v; b < numStr.size(); b++) {
//								
//								int now = Integer.parseInt(numStr.get(v).substring(1, 2));
//								int next = Integer.parseInt(numStr.get(b).substring(1, 2));
//								
//								if (now > next) {								
//									String tmpV = numStr.get(v);
//									String tmpB = numStr.get(b);
//									
//									numStr.remove(v);
//									numStr.add(b, tmpV);
//									
//									numStr.remove(b-1);
//									numStr.add(v, tmpB);
//								}
//							}
//						}
//						
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("T")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("J")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("Q")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("K")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("A")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//
//						CList.addAll(numStr);
//						CList.addAll(newCharStr);
//						//System.out.println("CList : " + CList);
//					}
//					else if (cardShape.equals("D")) {
//						
//						ArrayList<String> numStr = new ArrayList<>();
//						ArrayList<String> charStr = new ArrayList<>();
//						
//						ArrayList<String> newCharStr = new ArrayList<>();
//						
//						for (int v = 0; v < result.get(j).D.size(); v++) {
//							String nextChar = result.get(j).D.get(v).substring(1, 2);
//							int asciiChar = (int)nextChar.toCharArray()[0];
//							
//							if (asciiChar <= 57) {
//								numStr.add(result.get(j).D.get(v));
//							}
//							else {
//								charStr.add(result.get(j).D.get(v));
//							}
//						}
//						
//						for (int v = 0; v < numStr.size()-1; v++) {
//							for (int b = v; b < numStr.size(); b++) {
//								
//								int now = Integer.parseInt(numStr.get(v).substring(1, 2));
//								int next = Integer.parseInt(numStr.get(b).substring(1, 2));
//								
//								if (now > next) {								
//									String tmpV = numStr.get(v);
//									String tmpB = numStr.get(b);
//									
//									numStr.remove(v);
//									numStr.add(b, tmpV);
//									
//									numStr.remove(b-1);
//									numStr.add(v, tmpB);
//								}
//							}
//						}
//						
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("T")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("J")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("Q")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("K")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("A")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//
//						DList.addAll(numStr);
//						DList.addAll(newCharStr);
//						//System.out.println("DList : " + DList);
//					}
//					else if (cardShape.equals("S")) {
//						
//						ArrayList<String> numStr = new ArrayList<>();
//						ArrayList<String> charStr = new ArrayList<>();
//						
//						ArrayList<String> newCharStr = new ArrayList<>();
//						
//						for (int v = 0; v < result.get(j).S.size(); v++) {
//							String nextChar = result.get(j).S.get(v).substring(1, 2);
//							int asciiChar = (int)nextChar.toCharArray()[0];
//							
//							if (asciiChar <= 57) {
//								numStr.add(result.get(j).S.get(v));
//							}
//							else {
//								charStr.add(result.get(j).S.get(v));
//							}
//						}
//						
//						for (int v = 0; v < numStr.size()-1; v++) {
//							for (int b = v; b < numStr.size(); b++) {
//								
//								int now = Integer.parseInt(numStr.get(v).substring(1, 2));
//								int next = Integer.parseInt(numStr.get(b).substring(1, 2));
//								
//								if (now > next) {								
//									String tmpV = numStr.get(v);
//									String tmpB = numStr.get(b);
//									
//									numStr.remove(v);
//									numStr.add(b, tmpV);
//									
//									numStr.remove(b-1);
//									numStr.add(v, tmpB);
//								}
//							}
//						}
//						
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("T")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("J")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("Q")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("K")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("A")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//
//						SList.addAll(numStr);
//						SList.addAll(newCharStr);
//						//System.out.println("SList : " + SList);
//					}
//					else if (cardShape.equals("H")) {
//						
//						ArrayList<String> numStr = new ArrayList<>();
//						ArrayList<String> charStr = new ArrayList<>();
//						
//						ArrayList<String> newCharStr = new ArrayList<>();
//						
//						for (int v = 0; v < result.get(j).H.size(); v++) {
//							String nextChar = result.get(j).H.get(v).substring(1, 2);
//							int asciiChar = (int)nextChar.toCharArray()[0];
//							
//							if (asciiChar <= 57) {
//								numStr.add(result.get(j).H.get(v));
//							}
//							else {
//								charStr.add(result.get(j).H.get(v));
//							}
//						}
//						
//						for (int v = 0; v < numStr.size()-1; v++) {
//							for (int b = v; b < numStr.size(); b++) {
//								
//								int now = Integer.parseInt(numStr.get(v).substring(1, 2));
//								int next = Integer.parseInt(numStr.get(b).substring(1, 2));
//								
//								if (now > next) {								
//									String tmpV = numStr.get(v);
//									String tmpB = numStr.get(b);
//									
//									numStr.remove(v);
//									numStr.add(b, tmpV);
//									
//									numStr.remove(b-1);
//									numStr.add(v, tmpB);
//								}
//							}
//						}
//						
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("T")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("J")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("Q")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("K")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//						for (int v = 0; v < charStr.size(); v++) {	
//							if(charStr.get(v).substring(1,2).equals("A")) {
//								newCharStr.add(charStr.get(v));
//							}		
//						}
//
//						HList.addAll(numStr);
//						HList.addAll(newCharStr);
//						//System.out.println("HList : " + HList);
//					}
//					
//					inputList.addAll(CList);
//					inputList.addAll(DList);
//					inputList.addAll(SList);
//					inputList.addAll(HList);
//					
//				}
//				
//				if (locate.equals("S")) {
//					SouthList.addAll(inputList);
//					sortingResult.S = SouthList;
//					//System.out.println("SouthList : " + SouthList);
//				}
//				else if (locate.equals("W")) {
//					WestList.addAll(inputList);
//					sortingResult.W = WestList;
//					//System.out.println("WestList : " + WestList);
//				}
//				else if (locate.equals("N")) {
//					NorthList.addAll(inputList);
//					sortingResult.N = NorthList;
//					//System.out.println("NorthList : " + NorthList);
//				}
//				else if (locate.equals("E")) {
//					EastList.addAll(inputList);
//					sortingResult.E = EastList;
//					//System.out.println("EastList : " + EastList);
//				}
//				
//			}
//			
//			resultArray.add(sortingResult);
//			//System.out.println();
//		}	
//		
//		return resultArray;
//	}
//}