

import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})

@interface Review{
    String reviewer ();
    String date() default "today";
}
class  Util{
    public  static void  review(String className){

        try {
            Class <?> cl = Class.forName(className);
            Annotation ann =  cl.getAnnotation(Review.class);
            String date1;
            if(ann==null){
                System.out.println("Class "+className+" isn't marked as Reviewed");
            }else{
                if(cl.getAnnotation(Review.class).date().equals("today")){
                    date1=LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString();
                }else{
                    date1 = cl.getAnnotation(Review.class).date();
                }
                System.out.println("Class "+className+" was reviewed " +
                        date1 +
                        " by "+cl.getAnnotation(Review.class).reviewer()+".");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class "+className+" was not found");
        }
    }
}
