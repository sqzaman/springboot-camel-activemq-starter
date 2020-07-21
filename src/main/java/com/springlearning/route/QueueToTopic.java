package com.springlearning.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class QueueToTopic extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		  from("{{routes.queuetotopic.from}}")
           .log(LoggingLevel.INFO, this.getClass().getName(), "incoming employee: ${body}")  
          .multicast()
          .to("{{routes.queuetotopic.to}}")
		  .to("{{routes.queuetotopic.to2}}")
		  .end();
		
	}

}
