
class Person {
    private String firstName;
    private String lastName;
    private String idCode;
    private String errorMessage = "";
    private static boolean checkName(String s ){
        if(s.matches("[a-zA-Z-\\s]+") && Character.isUpperCase(s.charAt(0))){
            return true;
        }else {
            return false;
        }
    }
    private static  boolean checkDigit(String s){
        if(s.matches("[0-9]+") && s.length()==10){
            return  true;
        }else {
            return false;
        }
    }
    public void setFirstName(String firstName) throws NameException {
        if(checkName(firstName)){
            this.firstName = firstName;
        }else{
            throw  new NameException("Incorrect value "+ firstName+ " for firstName " +
                    "(should start from upper case and contains only alphabetic characters and symbols -, _); ");
        }

    }

    public void setLastName(String lastName) throws NameException{
        if(checkName(lastName)){
            this.lastName = lastName;
        }else{
            throw  new NameException("Incorrect value "+ lastName+ " for lastName " +
                    "(should start from upper case and contains only alphabetic characters and symbols -, _); ");
        }
    }

    public void setIdCode(String idCode) throws CodeException{
        if(checkDigit(idCode)){
            this.idCode = idCode;
        }else{
            throw new CodeException("Incorrect value " +idCode +" for code (should contains exactly 10 digits)");
        }


    }
    public static Person buildPerson(String firstName,String lastName,String idCode){
        Person p = new Person();
        String s = "";
        try{
            p.setFirstName(firstName);

        }catch (NameException e ){
            s = s+e.getMessage();
        }
        try {
            p.setLastName(lastName);
        }catch (NameException e){
            s=s+e.getMessage();
        }
        try{
            p.setIdCode(idCode);
        }catch (CodeException e ){
            s=s+e.getMessage();
        }
        if(!s.isEmpty()){
            throw new IllegalArgumentException(s);
        }
        return p;


    }

    public static void main(String[] args) {
        buildPerson("sad","Adsds","2324");

    }
    @Override
    public String toString() {
        return firstName+" "+lastName+": "+idCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        return idCode != null ? idCode.equals(person.idCode) : person.idCode == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (idCode != null ? idCode.hashCode() : 0);
        return result;
    }
}
class NameException extends RuntimeException{
    public NameException(String message) {
        super(message);
    }
}
class CodeException extends RuntimeException{
    public CodeException(String message) {
        super(message);
    }
}
