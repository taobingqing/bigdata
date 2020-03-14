package interface_annotation;



import java.lang.reflect.Field;

public class TestMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {

//        Apple apple = new Apple();
//        System.out.println(apple.getAppleName() + "+" + apple.getAppleColor());

//        apple.setAppleColor("红");
//        apple.setAppleName("李子");
//        System.out.println(apple.getAppleName()+"+"+apple.getAppleColor());

        //apple.displayName();

        parseFieldsAnnotation();

    }

    public static void parseFieldsAnnotation() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Field[] fields = Apple.class.getFields();
//        for (Field a : fields){
//            System.out.println(a);
//        }
        Class class1 = Class.forName("interface_annotation.Apple");
        //Object o = class1.newInstance();

        Field[] appleName = class1.getDeclaredFields();
        for (Field field : appleName){


            if (field.isAnnotationPresent(FruitName.class)){

                FruitName annotation = field.getAnnotation(FruitName.class);
                System.out.println("appleName="+annotation.value());
            }

            System.out.println(field.getName());
            String fieldType = field.getType().getSimpleName().toLowerCase();
            Class<?> type = field.getType();//.getSimpleName().toLowerCase();
            System.out.println(type);

        }


        //appleName.setAccessible(true);
       // appleName.set(o, "xigua");
        //System.out.println(appleName.get(o));
    }

//    public static void parseTypeAnnotation() throws ClassNotFoundException {
//        Class clazz = Class.forName("c");
//
//        Annotation[] annotations = clazz.getAnnotations();
//        for (Annotation annotation : annotations) {
//            FruitName fruitName = (FruitName)annotation;
//            System.out.println(fruitName.value());
//        }
//
//        System.out.println("........");
//    }

//    public static void parseMethodAnnotation(){
//        Method[] methods = UserAnnotation.class.getDeclaredMethods();
//        for (Method method : methods) {
//
//            boolean hasAnnotation = method.isAnnotationPresent(TestA.class);
//            if (hasAnnotation) {
//
//                TestA annotation = method.getAnnotation(TestA.class);
//                System.out.println("method = " + method.getName()
//                        + " ; id = " + annotation.id() + " ; description = "
//                        + annotation.name() + "; gid= "+annotation.gid());
//            }
//        }
//    }
//
//    public static void parseConstructAnnotation(){
//        Constructor[] constructors = UserAnnotation.class.getConstructors();
//        for (Constructor constructor : constructors) {
//
//            boolean hasAnnotation = constructor.isAnnotationPresent(TestA.class);
//            if (hasAnnotation) {
//
//                TestA annotation =(TestA) constructor.getAnnotation(TestA.class);
//                System.out.println("constructor = " + constructor.getName()
//                        + " ; id = " + annotation.id() + " ; description = "
//                        + annotation.name() + "; gid= "+annotation.gid());
//            }
//        }
//    }

}
