package gerenciamentodefrota.interceptor;

import gerenciamentodefrota.controller.IndexController;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

//@Intercepts
public class ExceptionInterceptor implements Interceptor {

	// TODO Melhorar este interceptor e gravar log das erros
	
	private Result result;

	public ExceptionInterceptor(Result result) {
		this.result = result;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		return true;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object controller) throws InterceptionException {
		try {
			stack.next(method, controller);
		} catch (Exception e) {
			System.out.println("Error ----->>> " + e.getMessage());
			result.redirectTo(IndexController.class).index();
		}
	}
}
