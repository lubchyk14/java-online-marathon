

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyUtils {
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        if(list==null || map == null ){
            return false;
        }
        if(list.size() == 0 && map.size()!=0 ||  
                list.size() != 0 && map.size()==0){
            return false;
        }
        
        Set<String> set = new HashSet<>();
        Set<String > compareSet = new HashSet<>();
        for (String l: list){
            set.add(l);
        }
        for(String s : map.values()){
            if(!set.contains(s)){
                return false;
            }else{
                compareSet.add(s);
            }
        }
        return compareSet.size()==set.size();
    }
}
