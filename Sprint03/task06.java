import javax.swing.*;
import java.lang.reflect.Array;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class AddressBook implements Iterable{
    private NameAddressPair[] addressBook;
    private int counter=0;
    private HashMap<NameAddressPair.Person,Integer> map;

    public AddressBook(int capacity) {
        addressBook = new NameAddressPair[capacity];
        map= new HashMap<>();

    }


    public boolean create(String firstName,String lastName,String address){
        NameAddressPair.Person per = new NameAddressPair.Person(firstName,lastName);
        NameAddressPair pair = new NameAddressPair(per,address);
        if(map.containsKey(per)){
            return false;
        }
        try{
            addressBook[counter] = pair;
            map.put(per,counter);
            counter+=1;
            return true;
        }catch(ArrayIndexOutOfBoundsException e ){
            NameAddressPair[] curr = new NameAddressPair[counter*2];
            System.arraycopy(addressBook,0,curr,0,addressBook.length);
            addressBook=curr;
            addressBook[counter] = pair;
            map.put(per,counter);
            counter+=1;
            return true;
        }

    }
    public String read(String firstName,String lastName){
        if (firstName==null || lastName==null){
            throw  new NullPointerException();
        }
        NameAddressPair.Person pr = new NameAddressPair.Person(firstName,lastName);
        if(map.containsKey(pr)){
            int curr_Pos = map.get(pr);
            NameAddressPair curr_User = addressBook[curr_Pos];
            return curr_User.getAddress();
        }else{
            return null;
        }

    }
    public boolean update(String firstName,String lastName,String address){
        if(firstName==null || lastName==null || address==null){
            return false;
        }
        NameAddressPair.Person pr = new NameAddressPair.Person(firstName,lastName);
        if(map.containsKey(pr)){
            int curr_Pos = map.get(pr);
            NameAddressPair curr_User = addressBook[curr_Pos];
            curr_User.address=address;
            return true;
        }else{
            return false;
        }
    }
    public boolean delete(String firstName,String lastName){
        if(firstName==null || lastName==null ){
            return false;
        }
        NameAddressPair.Person pr = new NameAddressPair.Person(firstName, lastName);
        if(map.containsKey(pr)){
            int curr_Pos = map.get(pr);
            Predicate<NameAddressPair> a =(NameAddressPair w)  ->{
                if(w!=null){
                    return !w.getPerson().equals(pr);}
                else {return false;}
            };
            NameAddressPair[] new_Array = Arrays.stream(addressBook)
                    .filter(a)
                    .toArray(size -> new NameAddressPair[size]);
            counter-=1;
            map.remove(pr);
            addressBook=new_Array;
            return true;
        }else{
            return  false;
        }
    }
    public int size(){
        return counter;
    }
    public void sortedBy(SortOrder order){
        if(order.equals(SortOrder.ASC)){
            Arrays.sort(addressBook, new Comparator<NameAddressPair>() {
                @Override
                public int compare(NameAddressPair o1, NameAddressPair o2) {
                    return o1.getPerson().getFirstName().compareTo(o2.getPerson().getFirstName());
                }
            }.thenComparing(new Comparator<NameAddressPair>() {
                @Override
                public int compare(NameAddressPair o1, NameAddressPair o2) {
                    return o1.getPerson().getLastName().compareTo(o2.getPerson().getLastName());
                }
            }));

        }else{
            Arrays.sort(addressBook, new Comparator<NameAddressPair>() {
                @Override
                public int compare(NameAddressPair o1, NameAddressPair o2) {
                    return o2.getPerson().getFirstName().compareTo(o1.getPerson().getFirstName());
                }
            }.thenComparing(new Comparator<NameAddressPair>() {
                @Override
                public int compare(NameAddressPair o1, NameAddressPair o2) {
                    return o2.getPerson().getLastName().compareTo(o1.getPerson().getLastName());
                }
            }));

        }
        HashMap<NameAddressPair.Person,Integer> map1 = new HashMap<>();
        for (int i =0;i<addressBook.length;i++){
            map1.put(addressBook[i].getPerson(),i);
        }
        map= map1;
    }

    @Override
    public Iterator iterator() {
        return new AddressBookIterator(this);
    }

    private class AddressBookIterator implements Iterator {
        private int counter  =0;
        AddressBook ab ;

        public AddressBookIterator(AddressBook ab) {

            this.ab=ab;

        }
        @Override
        public boolean hasNext() {
            return counter<ab.counter;
        }

        @Override
        public String next() {
            NameAddressPair.Person person = ab.addressBook[counter].getPerson();
            String first = person.getFirstName();
            String last = person.getLastName();
            String ad = ab.addressBook[counter].getAddress();
            counter+=1;
            return String.format("First name: %s, Last name: %s, Address: %s",first,last,ad);
        }
    }
    private static class NameAddressPair{
        private final Person person;
        private String address;

        private NameAddressPair(Person person,String address){
            this.person=person;
            this.address=address;
        }

        public Person getPerson() {
            return person;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return String.format("First name: %s, Last name: %s, Address: %s",person.getFirstName(),
                    person.getLastName(),address);

        }

        private static class Person{
            private final String firstName;
            private final String lastName;

            private Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Person)) return false;

                Person person = (Person) o;

                if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
                return lastName != null ? lastName.equals(person.lastName) : person.lastName == null;
            }

            @Override
            public int hashCode() {
                int result = firstName != null ? firstName.hashCode() : 0;
                result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
                return result;
            }
        }


    }

}
enum SortOrder{
    ASC,DESC;
}
