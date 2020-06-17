import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", basePayment=" + basePayment +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience &&
                Objects.equals(name, employee.name) &&
                Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }
}
class Manager extends  Employee{
    private Double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        Double d1 = super.getPayment().doubleValue();
        BigDecimal t = new BigDecimal(coefficient*d1) ;
        return t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Double.compare(manager.coefficient, coefficient) == 0 &&
                super.equals(o);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "coefficient=" + coefficient +
                "name='" + getName() + '\'' +
                ", experience=" + getExperience() +
                ", basePayment=" + getPayment() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coefficient);
    }
}
public class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        if(workers==null ){
            return null;
        }
        if(workers.size()==0){
            return workers;
        }
        if(workers.size()==1){
            return workers;
        }
        HashSet<Employee> set = new HashSet<>();
        BigDecimal maxPayment = workers.get(0).getPayment();
        int maxExperience = workers.get(0).getExperience();
        HashMap<Number,List<Employee>> map = new HashMap<>();

        map.put(maxExperience,new ArrayList<Employee>(Arrays.asList(workers.get(0))));

        map.put(maxPayment,new ArrayList<Employee>(Arrays.asList(workers.get(0))));
        for(Employee e : workers){
            if(e!=null && !set.contains(e) ){
                set.add(e);
                if(e.getPayment().doubleValue()>maxPayment.doubleValue()
                    || e.getPayment().compareTo(maxPayment)==0){
                    maxPayment=e.getPayment();
                    if(map.containsKey(e.getPayment())){
                        map.get(e.getPayment()).add(e);
                    }else{
                        map.put(e.getPayment(),new ArrayList<Employee>(Arrays.asList(e)));
                    }
                }else{
                    if(e.getExperience()>=maxExperience){
                        maxExperience= e.getExperience();
                        if(map.containsKey(e.getExperience())){
                            map.get(e.getExperience()).add(e);
                        }else{
                            map.put(e.getExperience(),new ArrayList<Employee>(Arrays.asList(e)));
                        }
                    }
                }
            }
        }
        map.get(maxExperience).addAll(map.get(maxPayment));

        return map.get(maxExperience);
    }
}