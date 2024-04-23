package app;

import ejbs.Calculation;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("cal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculationResource {

    @PersistenceContext(unitName = "CalculationPU")
    private EntityManager entityManager; 

    @POST
    @Path("calc")
    public String createCalculation(Calculation calculation) {
        int number1 = calculation.getNumber1();
        int number2 = calculation.getNumber2();
        String operation = calculation.getOperation();
        int res = calculation.performCalculation(number1,number2,operation);
        calculation.setResult(res);
        try {
        	entityManager.persist(calculation);
        	return "Result: " +  res;
        } catch (Exception e) {
        	e.printStackTrace();
        	return "Status: 500 ";
        }
    }
    
    @GET
    @Path("test")
    public String test() {
    	return "Test From Calculation";
    }

    @GET
    @Path("calculations")
    public List<Calculation> getAllCalculations() {
    	try {
            TypedQuery<Calculation> query = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class);
            List<Calculation> calculations = query.getResultList();
            return calculations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
