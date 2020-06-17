
import java.util.*;
import java.util.stream.Stream;

class task4 {
}
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }
    public Integer getNumber(){
        if(this instanceof Student){
            return ((Student) this).getStudyYears();
        }
        if (this instanceof  Worker){
            return ((Worker) this).getExperienceYears();
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
class Student extends Person {
    private String studyPlace;
    private int studyYears;

    public Student(String name ,String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studyYears == student.studyYears &&
                this.getStudyPlace().compareTo(student.getStudyPlace())==0 &&
                this.getName().compareTo(student.getName())==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyPlace, studyYears,getName());
    }
}
class Worker extends Person {
    private String workPosition;
    private int experienceYears;

    public Worker(String name ,String workPosition,int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return experienceYears == worker.experienceYears &&
                this.workPosition.compareTo(worker.getWorkPosition())==0 && 
                this.getName().compareTo(worker.getName())==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(workPosition, experienceYears,getName());
    }
}

public class MyUtils {
    public List<Person> maxDuration(List<Person> persons) {
        int maxStudent=0;
        int maxWorker=0;
        HashSet<String> set = new HashSet<>();
        for (Person per : persons){
            if(per instanceof  Student && ((Student) per).getStudyYears()>maxStudent){
                maxStudent = ((Student) per).getStudyYears();
            }else{
                if(per instanceof Worker && ((Worker) per).getExperienceYears()>maxWorker){
                    maxWorker = ((Worker) per).getExperienceYears();
                }
            }

        }
        List<Person> fin = new ArrayList<>();
        for (Person per : persons){
            if(per instanceof  Student && ((Student) per).getStudyYears()==maxStudent
                    && !set.contains(per.getName())){
                fin.add(per);
                set.add(per.getName());
            }else{
                if(per instanceof Worker && ((Worker) per).getExperienceYears()==maxWorker
                        && !set.contains(per.getName())){
                    fin.add(per);
                    set.add(per.getName());
                }
            }

        }
        return  fin;
        // Code
    }
}

