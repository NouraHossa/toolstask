package ejbs;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Stateless
@Entity
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number1;
    private int number2;
    private String operation;
    private int result;
    
    public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	Calculation() {}
    
    Calculation(int number1, int number2, String operation, int result) 
    {
    	this.number1 = number1;
    	this.number2 = number2;
    	this.operation = operation;
    	this.result = result;
    }
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public int performCalculation(int number1, int number2, String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    throw new IllegalArgumentException("Cannot divide by zero.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
      return result;
    }


}
