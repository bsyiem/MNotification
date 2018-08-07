package messageNotification.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


/**
 * Created by brandon on 3/01/18.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"gem"})
public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    
    
    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     *
     * defaults lookup for static content are:
     * /META-INF/resources/ 
     * /resources/
     * /static/
     * /public/
     */
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        registry
          .addResourceHandler("/html/**")
          .addResourceLocations("classpath:/static/html/"); 
        registry
          .addResourceHandler("/css/**")
          .addResourceLocations("classpath:/static/css/");
        registry
          .addResourceHandler("/js/**")
          .addResourceLocations("classpath:/static/js/");
        registry
          .addResourceHandler("/images/**")
          .addResourceLocations("classpath:/static/images/");
    }
}
