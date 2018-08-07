package messageNotification.configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter{
	
	 @Override
	  protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) { 
	    builder
	    //applies the decorator specified in the second parameter (/WEB-INF/sitemesh_decorators/basic.jsp) to all request matching the first parameter (/*)
	    	.addDecoratorPath("/**", "/WEB-INF/sitemesh_decorators/basic.jsp")
	    	.addExcludedPath("/html/**")
	    	.addExcludedPath("/css/**")
	    	.addExcludedPath("/js/**")
	    	.addExcludedPath("/images/**");
	 }

}
