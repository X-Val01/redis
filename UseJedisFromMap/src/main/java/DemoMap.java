import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DemoMap  implements Map<String, String> {
	

	DemoJedis rediska = new DemoJedis();
	Map<String, String> map = new HashMap<String, String>();
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void clear() {
		String nameHashDB= "hash1";
		rediska.clearJedis(nameHashDB);
		
	}
	public void disconnect() {
		rediska.close();
	}

	public boolean containsKey(Object arg0) {
		
		return rediska.keySearch((String)arg0);
	}

	public boolean containsValue(Object arg0) {
		
		return rediska.valueSearch((String)arg0);
	}

	public Set<Entry<String, String>> entrySet() {
		Map<String, String> map = rediska.getSetDB();
		Set<Entry<String, String>> set = map.entrySet();
		
		return set;
	}

	public String get(Object arg0)  {

		
		return rediska.getJedis((String)arg0);
		
			
		}

	public boolean isEmpty() {
		
		return rediska.DBsize();
	}

	public Set<String> keySet() {
		Set<String> newset = rediska.allKeyInJedis();
		
		return newset;
	}

	public String put(String arg0, String arg1) {
		System.out.println("1");
		if(rediska.getJedis(arg0)==null) {
			rediska.setJedis(arg0, arg1);
			
			return "0";
			
		}
		else {
			String arg3 = rediska.getJedis(arg0);
			rediska.setJedis(arg0, arg1);
			
			return arg3;
		}
	}
	public String test() {
		return rediska.test();
	}

	

	public String remove(Object arg0) {
		rediska.deleteByKey((String)arg0);
		return null;
	}

	public int size() {
		
		int size = (int)rediska.sizeDB();
		return size;
	}

	public Collection<String> values() {		
		List<String> list = rediska.allValueInJedis();
		
		return list;
	}
	
	public void putAll(Map<? extends String, ? extends String> arg0) {
		rediska.swap(arg0);
		
	}

}
