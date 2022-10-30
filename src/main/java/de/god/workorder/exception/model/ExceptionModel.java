package de.god.workorder.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author belbaghazaoui
 *
 */
@AllArgsConstructor
@Getter
public class ExceptionModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;
}
