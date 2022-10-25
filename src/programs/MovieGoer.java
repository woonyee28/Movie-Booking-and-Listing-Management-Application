import java.io.Serializable;

public class MovieGoer implements Serializable{
    int id;
	String name;
    String email;
    int age;
    String passwordHashed;
    String mobileNumber;
    String TID;

    String membership;

	public MovieGoer(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setContact(String mobileNumber)
    {
        this.mobileNumber  = mobileNumber;
    }

    // set membership status - Student/senior/etc
    public void setMembership(String membership)
    {
        this.membership = membership;
    }
    


}
