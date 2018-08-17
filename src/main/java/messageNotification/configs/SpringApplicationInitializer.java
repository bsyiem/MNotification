package messageNotification.configs;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
public class SpringApplicationInitializer extends AbstractSecurityWebApplicationInitializer{
	
	@Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, new MultipartFilter());
    }

}
