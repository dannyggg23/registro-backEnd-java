package io.danny.back_end.exception;

import javax.servlet.http.HttpServletRequest;

import io.danny.back_end.utils.EcReturn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandling {

    

    public ExceptionHandling() {

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    @ResponseBody
    public static EcReturn notFoundRequest(HttpServletRequest request,Exception ae_exception)
    {
        EcReturn vl_return = new EcReturn();
        if(ae_exception != null)
        {
            vl_return.setCorrectProcess(false);

            String ls_message = "EC: "+ae_exception.getMessage();

            vl_return.setMessage(ls_message);
            vl_return.setData(ls_message);
        }
        else
        {
            vl_return.setCorrectProcess(false);
            vl_return.setMessage("S/D");
            vl_return.setData(ae_exception);
        }
        return vl_return;
    }

   
 
    public static EcReturn get(Exception ae_exception)
    {
        EcReturn vl_return = new EcReturn();
        if(ae_exception != null)
        {
            vl_return.setCorrectProcess(false);

            String ls_message = "EC: "+ae_exception.getMessage();

            vl_return.setMessage(ls_message);
            vl_return.setData(ls_message);
        }
        else
        {
            vl_return.setCorrectProcess(false);
            vl_return.setMessage("S/D");
            vl_return.setData(ae_exception);
        }
        return vl_return;
    }
    
}
