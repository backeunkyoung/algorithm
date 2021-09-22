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
			for (int j = 0; j < dataNum-1; j++) {
				
				String[] strArr = br_f.readLine().split(" ");
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
			}

			Integer root = 0;
			Iterator<Integer> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				Integer key = iter.next(); 
				if (!childList.contains(key)) {
					root = key;
					break;
				}
			}
			ArrayList<Integer> keyList = new ArrayList(Arrays.asList(root));
			
			int depth = 0;
			while(true) {
				ArrayList<Integer> childArray = new ArrayList<>();
				for (int t=0; t< keyList.size() ; t++) {
					ArrayList<Integer> nowChildList = map.get(keyList.get(t));

					if (nowChildList != null) {
						for (int k=0; k<nowChildList.size(); k++) {
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