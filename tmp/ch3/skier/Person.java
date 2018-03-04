import javax.xml.bind.annotation.XmlType;

@XmlType       
public class Person  {
            
    // fields
    private String name;
    private int    age;
    private String gender;
        
    // constructors
    public Person() { }
    
    public Person(String name, int age, String gender){
	setName(name);
	setAge(age);
	setGender(gender);
    }

    // properties: name, age, gender
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age;  }
    public void setAge(int age) { this.age = age; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
