
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUtils {
    // Code
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        if(phones==null || phones.isEmpty()){
            return new HashMap<String, List<String>>();
        }
        Map<String,List<String>> map = new HashMap<>();
        for(Map.Entry s : phones.entrySet()){
            String name = (String)s.getValue();
            String number = (String)s.getKey();
            if(map.containsKey(name)){
                 map.get(name).add(number);
            }else{
                List <String > l = new ArrayList<>();
                l.add(number);
                map.put(name,l);
            }

        }
        return map;
    }
    
}
