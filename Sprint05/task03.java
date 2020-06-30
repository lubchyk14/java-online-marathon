public  static Plant tryCreatePlant(String type,String color,String name) {
        
    try{
        return new Plant(type,color,name);

    }catch(TypeException e   ){
        type = "ordinary";
        return tryCreatePlant(type,color,name);
    }catch (ColorException e ){
        color="red";
        return tryCreatePlant(type, color, name);
    }
    


    
}