import java.util.Arrays;
import java.util.Comparator;
public class Utility  {
    public static <T extends Person> void  sortPeople(T [] arr,Comparator<? super T> comparator){

        Arrays.sort(arr,comparator);
    }
}
class PersonComparator implements Comparator<Person>{
    @Override

    public int compare(Person o1, Person o2) {
        int c = o1.getName().compareTo(o2.getName());
        if(c==0){
            c = Integer.compare(o1.getAge(),o2.getAge());
        }
        return c;
    }
}
class EmployeeComparator implements Comparator<Employee>{
    @Override

    public int compare(Employee o1, Employee o2) {
        int c = new PersonComparator().compare(o1,o2);
        if (c==0){
            c= Double.compare(o1.getSalary(),o2.getSalary());

        }
        return c;
    }
}
class DeveloperComparator implements Comparator<Developer>{
    @Override

    public int compare(Developer o1, Developer o2) {
        int c = new PersonComparator().compare(o1,o2);
        if(c==0){

            c= o1.getLevel().compareTo(o2.getLevel());
        }
        return c;
    }
}
