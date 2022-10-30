package de.god.workorder.exception;


/**
 * @author belbaghazaoui
 *
 */
public class WorkOrderValidationException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkOrderValidationException(String message){
        super(message);
    }
}
