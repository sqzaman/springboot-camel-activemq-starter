package com.springlearning.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springlearning.model.Employee;
import com.springlearning.route.processor.EmployeeProcessor;



@Component
public class QueueToQueueRoute extends RouteBuilder {
	
	
private final EmployeeProcessor processor;
private final JacksonDataFormat jsonDataFormat;

public QueueToQueueRoute(@Qualifier("myObjectMapper") ObjectMapper objectMapper, EmployeeProcessor processor) {
	
	this.processor = processor;
	
	jsonDataFormat = new JacksonDataFormat(Employee.class);
	jsonDataFormat.setObjectMapper(objectMapper);
}


	@Override
	public void configure() throws Exception {
		  from("{{routes.queuetoqueue.from}}")
          .streamCaching()
          .log(LoggingLevel.INFO, this.getClass().getName(), "incoming employee: ${body}")
          .unmarshal(jsonDataFormat)
          .log(LoggingLevel.INFO, this.getClass().getName(), "after unmarshal employee: ${body}")
          .process(processor)
          .log(LoggingLevel.INFO, this.getClass().getName(), "employee, after assign department: ${body}")
          .marshal(jsonDataFormat)
          .log(LoggingLevel.INFO, this.getClass().getName(), "after marshal employee: ${body}")          
          .to("{{routes.queuetoqueue.to}}");
		
	}

}
