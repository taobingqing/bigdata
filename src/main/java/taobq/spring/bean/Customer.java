package taobq.spring.bean;

public class Customer {

    private String name;
    private Integer age;
    private Movie movie; //此处的movie是一个Movie类型的数据

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", age=" + age + ", movie=" + movie + "]";
    }
}

