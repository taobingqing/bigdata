package taobq.spring.bean;

public class Movie {

    @Override
    public String toString() {
        return "Movie [name=" + name + ", director=" + director + "]";
    }
    private String name;
    private String director;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

}
