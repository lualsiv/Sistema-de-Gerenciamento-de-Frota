package gerenciamentodefrota.interceptor;

import gerenciamentodefrota.controller.IndexController;

import org.apache.log4j.Logger;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.interceptor.ResourceLookupInterceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

//@Intercepts(before = ResourceLookupInterceptor.class, after = {})
public class ExceptionInterceptor implements Interceptor {

	private Logger log = Logger.getRootLogger();
	private Result result;

	public ExceptionInterceptor(Result result) {
		this.result = result;
	}
	
	public boolean accepts(final ResourceMethod method) {
		return true;
	}
	
	public void intercept(InterceptorStack stack, ResourceMethod method, Object controller) throws InterceptionException {
		try {
			stack.next(method, controller);
		} catch (final InterceptionException exception) {
			Throwable rootCause = exception.getCause();
			log.error(rootCause.getMessage(), rootCause);
			result.include("exception", rootCause);
			result.redirectTo(IndexController.class).erro();
		}

	}
}