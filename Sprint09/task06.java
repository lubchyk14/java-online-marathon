import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyUtils {

    private void set(Map<String,ArrayList<String>> map,String key){
        String value="";
        if(key.length()==10){
            value=key.substring(3);
            key=key.substring(0,3);
        }else{
            if(key.length()==7){
                value = key;
                key = "loc";
            }else{
                value=key;
                key="err";
            }
        }

        if(map.containsKey(key)){
            map.get(key).add(value);
        }else{
            map.put(key,new ArrayList<>(Arrays.asList(value)));
        }

    }
    @SuppressWarnings("unchecked")
    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        Map<String, ArrayList<String>> finalMap = new HashMap<>();
        Map<String,Stream<String>> map = new HashMap<>();

        for(Stream<String> curr : list){
            if(curr !=null) {
                curr.filter(s -> s != null && !s.isEmpty() && !s.matches("\\s"))
                        .map(s -> s.replaceAll("[()\\s-]+", ""))
                        .forEach(s-> set(finalMap,s));
            }
        }

        for (Map.Entry entry : finalMap.entrySet()){
            map.put((String)entry.getKey(),((ArrayList<String>)entry.getValue()).stream().distinct().sorted());
        }
        return map;
    }
}