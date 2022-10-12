import java.util.*;
public class SalePerson implements Comparable<SalePerson>{
    private String firstName;
    private String lastName;
    private Integer totalSales;

    public SalePerson(String firstName, String lastName, Integer totalSales){
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSales = totalSales;
    }

    public String toString(){
        return (firstName + ", " + lastName + " : " + totalSales);
    }

    public boolean equals(SalePerson person){
        if (this.firstName == person.firstName && this.lastName == person.lastName){
            return true;
        }
        else{
            return false;
        }
    }

    public int compareTo(SalePerson person){
        if (this.totalSales<person.totalSales){
            return -1;
        }
        else if(this.totalSales>person.totalSales){
            return 1;
        }
        else{
            return -(this.lastName.compareTo(person.lastName));
        }
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public int getTotalSales(){
        return this.totalSales;
    }
}
