package to;

public class Member {
    private String id;
    private String name;
    private String address;
    private String contatc;

    public Member() {
    }

    public Member(String id, String name, String address, String contatc) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contatc = contatc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContatc() {
        return contatc;
    }

    public void setContatc(String contatc) {
        this.contatc = contatc;
    }
}
