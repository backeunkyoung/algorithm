import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
			Map<Integer, ArrayList<Integer>> map = new HashMap<>();
			
			int dataNum = Integer.parseInt(br_f.readLine());

			ArrayList<Integer> childList = new ArrayList<>();
			ArrayList<Integer> rootKeyList = new ArrayList<>();
			for (int j = 0; j < dataNum-1; j++) {
				
				String[] strArr = br_f.readLine().split(" "); // 3 5
				Integer parent = Integer.valueOf(strArr[0]);
				Integer child = Integer.parseInt(strArr[1]);
				
				if (map.get(parent) == null) {
					map.put(parent, new ArrayList<>(Arrays.asList(child)));
				} else {
					ArrayList<Integer> newChildList = new ArrayList<>(map.get(parent));
					newChildList.add(child);
					map.put(parent, newChildList);
				}
				childList.add(child);
				rootKeyList.add(parent);
			}
			rootKeyList.removeAll(childList);
			int root = rootKeyList.get(0).intValue();
			ArrayList<Integer> keyList = new ArrayList(Arrays.asList(root));
					
			int depth = 0;
			while(true) {
				ArrayList<Integer> childArray = new ArrayList<>();
				int keyLen = keyList.size();
				for (int t=0; t< keyLen ; t++) {
					ArrayList<Integer> nowChildList = map.get(keyList.get(t));
					
					if (nowChildList != null) {
						int nowLen = nowChildList.size();
						for (int k=0; k< nowLen ; k++) {
							childArray.add(nowChildList.get(k));
						}
					}
				}
				keyList = childArray;
				
				depth++;
				if (keyList.size() == 0) break;
			}
			str += depth + "\n";

		}
		bs.write(str.getBytes());

		
		br_f.close();
		bs.close();
	}	
}