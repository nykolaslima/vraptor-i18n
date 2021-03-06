package br.com.caelum.vraptor.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import br.com.caelum.vraptor.core.JstlLocalization;

/**
 * ResourceBundle producer to locale configuration 
 * @author Denilson Telaroli
 */
@RequestScoped @Specializes
public class I18nLocalization extends JstlLocalization {

	private final HttpServletRequest request;

	/** 
	 * @deprecated CDI eyes only
	 */
	protected I18nLocalization() {
		this(null);
	}

	@Inject
	public I18nLocalization(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	@Produces
	public ResourceBundle getBundle() {
		setLocaleFromRequestParameter();
		return super.getBundle();
	}
	
	@Override
	@Produces
	public Locale getLocale() {
		return super.getLocale();
	}
	
	private void setLocaleFromRequestParameter() {
		String locale = request.getParameter("_locale");
		
		if(locale != null) {
			Config.set(request.getSession(), Config.FMT_LOCALE, locale);
		}
	}

}