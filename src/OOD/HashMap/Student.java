package OOD.HashMap;

public class Student {
    private String id;
    private String firstName, lastName;

    public Student(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        int i = 0;
        for (char ch : id.toCharArray()) {
            hashCode += ch * Math.pow(31, i++);
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }

        Student other = (Student) o;
        return this.getId().equals(other.getId());
    }



    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
