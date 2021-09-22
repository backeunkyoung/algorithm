import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class family2 {

	public static void main(String[] args) throws IOException {
		String fname = "D:\\GitHub\\algorithm\\07.FamilyTree\\code\\family.inp";
		String outfname = "D:\\GitHub\\algorithm\\07.FamilyTree\\code\\family.out";
		FileReader fr = new FileReader(fname);
		FileSet(fr, outfname);
	}
	
	public static void FileSet(FileReader fr, String outfname) throws IOException {
		BufferedReader br_f = new BufferedReader(fr);
		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(outfname));
		
		int arrayListSize = Integer.parseInt(br_f.readLine());
		
		String str = "";
		
		for (int i = 0; i < arrayListSize; i++) {
			Map<Integer, String> map = new HashMap<>();
			
			int dataNum = Integer.parseInt(br_f.readLine());
			
			System.out.println("----------------");
			String childList = "";
			for (int j = 0; j < dataNum-1; j++) {
				
				String[] strArr = br_f.readLine().split(" "); // 3 5
				Integer parent = Integer.valueOf(strArr[0]);
				String child = (strArr[1]);
				
//				System.out.println("parent : " + parent + ", child : " + child);
				
				if (map.get(parent) == null) {
					map.put(parent, child);
				} else {
					String newChildList = map.get(parent);
					newChildList += "," + child;
					
//					System.out.println("childLIst : " + childList);
					map.put(parent, newChildList);
				}
				
				childList += child + ",";
			}
//			System.out.println("childList : " + childList);
			
			Integer root = 0;
			Iterator<Integer> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				Integer key = iter.next(); 
//				String key = Integer.toString(iter.next());

//				System.out.println("key : " + key + " / value : " + map.get(key));
				
				String[] strArr = childList.split(",");
				
				boolean isRoot = true;
				
				for (int j = 0; j < strArr.length; j++) {
					int nowKey = Integer.parseInt(strArr[j]);
					
//					System.out.println("key : " + key + " , nowKey : " + nowKey);
					if (key == nowKey) {
						isRoot = false;
						break;
					}
				}
				
				if (isRoot) {
					root = key;
				}
			}
//			System.out.println("root : " + root);
			
			String keyList = Integer.toString(root) + ",";
			
			int depth = 0;
			while(true) {
//				System.out.println("keyList : " + keyList);
				String[] keyListArr = keyList.split(",");
				String childArray = "";
				
				for (int t = 0; t < keyListArr.length ; t++) {
					int key = Integer.parseInt(keyListArr[t]);
					String nowChildList = map.get(key);
					
//					System.out.println("key : " + key + " / nowChildList : " + nowChildList);
					if (nowChildList != null) {
						
						String[] nowChildListArr = nowChildList.split(",");
						
						for (int k = 0; k < nowChildListArr.length; k++) {
							childArray += nowChildListArr[k] + ",";
						}
					}
				}
				keyList = childArray;
				
				depth++;
				if (keyList == "") break;
			}
			System.out.println("depth : " + depth);
			str += depth + "\n";
					
			// search Tree
		}
		bs.write(str.getBytes());
//
//		
		br_f.close();
		bs.close();
	}
	
}