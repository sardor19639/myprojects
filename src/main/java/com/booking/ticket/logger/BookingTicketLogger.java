package com.booking.ticket.logger;

import com.booking.ticket.exceptions.BookingTicketGlobalException;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

public final class BookingTicketLogger {
     @Bean
    public ServletContextInitializer servletContextInitializer() {
        return (ServletContext servletContext) -> {
            final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
            characterEncodingFilter.setEncoding("UTF-8");
            characterEncodingFilter.setForceEncoding(false);
           
        };
    }
    private static Logger log = LoggerFactory.getLogger(BookingTicketLogger.class.getName());
    private static final String PROJECT = "GMoika";

    private BookingTicketLogger() {
    }

    public static class SingletonHolder {

        public static final BookingTicketLogger INSTANCE = new BookingTicketLogger();
    }

    public static BookingTicketLogger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void errorLog(BookingTicketGlobalException exc) {
        log.error("\nReason: ERROR" + "\n , "
                + "HTTPRequest: " + exc.message().getRequest() + "\n , "
                + "Error code: " + exc.message().getErrorCode()+ "\n , "
                + "Class: " + exc.message().getClasssName() + "\n , "
                + "Method: " + exc.message().getMethodName() + "\n , "
                //+ "user id: " + exc.message().getUserId() + "\n , "
                + "RequestBody: " + (exc.message().getRequestBody() == null ? "empty" : exc.message().getRequestBody().toString()) + "\n , "
                + "CAUSE: " + exc.message().getCause() + "\n\n ");
    }
     public void log(LogMessage message) {
        log.info("\nReason: ERROR" + "\n"
                + "HTTPRequest: " + message.getRequest() + "\n"
                + "Error code: " + message.getErrorCode()+ "\n"
                + "Class: " + message.getClasssName() + "\n"
                + "Method: " + message.getMethodName() + "\n"
               
                //+ "user id: " + message.getUserId() + "\n"
                + "RequestBody: " + (message.getRequestBody() == null ? "empty" : message.getRequestBody().toString()) + "\n"
                + "CAUSE: " + message.getCause() + "\n\n");
    }

    public void onSuccessLog(LogMessage message) {
        log.info("\n" + "Reason : SUCCESS" + "\n , "
                + "HttpRequest: " + message.getRequest() + "\n , "
                + "Method name: " + message.getMethodName() + "\n , "
                + "RequestBody: " + (message.getRequestBody() == null ? "empty" : message.getRequestBody().toString()) + "\n\n  ");
    }

    public void simpleLog(String meesage) {
        log.error("\nError\n"
                + "Reason: " + meesage);
    }

    public void unknownExceptionLog(LogMessage message, String... args) {
        StringBuilder _args = new StringBuilder("");
        for (String str : args) {
            _args.append(str + "\n");
        }
        log.info("\n\t\t\t Project: " + PROJECT + "\n\t\t\t "
                + "Method: "
                + message.getMethodName() + "\n\t\t\t Params: "
                + _args.toString()
                + "Reason: " + message.getCause() + "\n\n");
    }

}
