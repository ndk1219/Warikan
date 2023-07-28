import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws IOException {
		String path = "data.csv";
		// ファイルを開いて読み込む準備
		List<String> fileText = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// ファイルの中身を１行ずつ読み込んでListに格納
		String line = null;
		while ((line = br.readLine()) != null) {
			fileText.add(line);
		}
		// 使い終わったファイルを閉じる

		// 画面にテキストの内容表示
		for (String text : fileText) {
			System.out.println(text);
		}

		Map<String, Integer> nameList = new HashMap<String, Integer>();//ac
		Map<String, Integer> costList = new HashMap<String, Integer>();//bc
		for (String text : fileText) {
			String[] datas = text.split(",");
			int value = Integer.parseInt(datas[2]);
			String name = datas[0];
			String item = datas[1];
			if (nameList.containsKey(name)) {
				nameList.put(name, nameList.get(name) + value);
			} else {
				nameList.put(name, value);
			}
			if (costList.containsKey(item)) {
				costList.put(item, costList.get(item) + value);
			} else {
				costList.put(item, value);
			}
		}
		br.close();
		//以下答えコピペして変数だけ書き換え
		System.out.println("キャンプ会計");
		System.out.println("------------------");
		int total = 0;
		for (String key : costList.keySet()) {
			System.out.printf("%s:%d\n", key, costList.get(key));
			total += costList.get(key);
		}
		System.out.println();

		int perPrice = total / nameList.size();
		System.out.printf("個人別会計(１人あたり:%d円)\n", perPrice);
		System.out.println("------------------");
		for (String key : nameList.keySet()) {
			int money = nameList.get(key) - perPrice;
			System.out.printf("%s:%s%d\n", key, money < 0 ? "-" : "+", Math.abs(money));
		}
	}
}

		//		costList.put("ビール", 4600);
		//		costList.put("ガソリン", 5200);
		//		costList.put("駐車場", 2500);
		//		costList.put("食材", 6000);
		//		costList.put("ガソリン", 2000);
		//		costList.put("食材", 2400);
		//		costList.put("キャンプ場", 10000);

		//		for(int i=0;i<nameList.length;i++) {
		//			
		//		}

