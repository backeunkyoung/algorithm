import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class family {

	public static void main(String[] args) throws IOException {
		String fname = "family.inp";
		String outfname = "family.out";
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

			String childList = "";
			for (int j = 0; j < dataNum-1; j++) {
				
				String[] strArr = br_f.readLine().split(" "); // 3 5
				Integer parent = Integer.valueOf(strArr[0]);
				String child = (strArr[1]);
				
				if (map.get(parent) == null) {
					map.put(parent, child);
				} else {
					String newChildList = map.get(parent);
					newChildList += "," + child;
					
					map.put(parent, newChildList);
				}
				
				childList += child + ",";
			}
			
			Integer root = 0;
			Iterator<Integer> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				Integer key = iter.next(); 
				
				String[] strArr = childList.split(",");
				
				boolean isRoot = true;
				
				for (int j = 0; j < strArr.length; j++) {
					int nowKey = Integer.parseInt(strArr[j]);
					
					if (key == nowKey) {
						isRoot = false;
						break;
					}
				}
				
				if (isRoot) {
					root = key;
				}
			}
			
			String keyList = Integer.toString(root) + ",";
			
			int depth = 0;
			while(true) {
				String[] keyListArr = keyList.split(",");
				String childArray = "";
				
				for (int t = 0; t < keyListArr.length ; t++) {
					int key = Integer.parseInt(keyListArr[t]);
					String nowChildList = map.get(key);
					
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
			str += depth + "\n";

		}
		bs.write(str.getBytes());
	
		br_f.close();
		bs.close();
	}
	
}