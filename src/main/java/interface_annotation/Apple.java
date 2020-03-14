package interface_annotation;


public class Apple {

    @FruitName("Apple")
     public String appleName;

//    @FruitColor(fruitColor= FruitColor.Color.RED)
//     String appleColor;




//    public void setAppleColor(String appleColor) {
//        this.appleColor = appleColor;
//    }
//    public String getAppleColor() {
//        return appleColor;
//    }


    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }
    public String getAppleName() {
        return appleName;
    }

    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }
}
