
class Person{
    int age;
    String name;
    String healthInfo;
    

    
    public Person(int age,String name,String healthInfo){
        this.age=age;
        this.name=name;
        this.healthInfo=healthInfo;

    }
    public String getHealthStatus(){ return this.name +" " + this.healthInfo; }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the healthInfo
     */
    public String getHealthInfo() {
        return healthInfo;
    }

    /**
     * @param healthInfo the healthInfo to set
     */
    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }

}
class Child extends Person{
    private String childIDNumber;

    public Child(int age,String name,String healthInfo,String childINumber){
        super(age,name,healthInfo);
        this.childIDNumber=childINumber;
    }
    

    /**
     * @return String return the childIDNumber
     */
    public String getChildIDNumber() {
        return childIDNumber;
    }

    /**
     * @param childIDNumber the childIDNumber to set
     */
    public void setChildIDNumber(String childIDNumber) {
        this.childIDNumber = childIDNumber;
    }

}
class Adult extends Person{
    private String passportNumber;
    
    public Adult(int age, String name, String healthInfo, String passportNumber) {
        super(age,name,healthInfo);
        this.passportNumber= passportNumber;

    }

    /**
     * @return String return the passportNumber
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * @param passportNumber the passportNumber to set
     */
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

}