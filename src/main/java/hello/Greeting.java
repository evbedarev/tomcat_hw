package hello;

public class Greeting {
    private long id;
    private String content;
    private GreetingPerson person;

    public Greeting(long id, String content, GreetingPerson person) {
        this.id = id;
        this.content = content;
        this.person = person;
    }

    public GreetingPerson getPerson() {
        return person;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPerson(GreetingPerson person) {
        this.person = person;
    }
}
