

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyUtils {
    public static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;

            Student student = (Student) o;

            if (id != student.id) return false;
            return name != null ? name.equals(student.name) : student.name == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Student[" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ']';
        }
    }
    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        Set<Student> studentSet = new HashSet<>();
        Set<Student> finalSet = new HashSet<>();
        if( list1 == null || list2==null){
            return new HashSet<>();
        }
        for (Student s: list1 ) {
            studentSet.add(s);
        }
        for(Student s : list2){
            if(!studentSet.add(s)){
                finalSet.add(s);
            }
        }
        return finalSet;
    }

    
}








