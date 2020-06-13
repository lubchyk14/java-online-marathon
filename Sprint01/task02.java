// Create an instances of Employee class and use they here
Employee emp1 = new Employee();
Employee emp2 = new Employee();
emp1.fullName = "Petrov";
emp1.salary = 15.15f;
emp2.fullName = "Ivanov";
emp2.salary = 20.20f;
Employee [] employees = new Employee[]{emp1,emp2};
String employeesInfo = "[";
int i =0;
for (Employee emp : employees){
    if(i!=0){
        employeesInfo+=", ";
    }
    employeesInfo += "{fullName: \""+emp.fullName+"\", salary: "+emp.salary+"}";
    
    i++;                
}
employeesInfo+="]";
