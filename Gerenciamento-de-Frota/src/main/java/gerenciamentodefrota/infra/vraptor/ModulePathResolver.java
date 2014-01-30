package gerenciamentodefrota.infra.vraptor;

import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.DefaultPathResolver;

@Component
public class ModulePathResolver extends DefaultPathResolver {
	
	private FormatResolver resolver2;

	public ModulePathResolver(FormatResolver resolver) {
		super(resolver);
		resolver2 = resolver;
	}
	
	@Override
	public String pathFor(ResourceMethod method) {
		String name = method.getResource().getType().getSimpleName();
		String folderName = extractControllerFromName(name);
		return getPrefix() + getModuleName(method) + folderName + "/" + getViewName(method) + getSuffix() + "." + getExtension();
	}

	private String getSuffix() {
		String format = resolver2.getAcceptFormat();
		
		String suffix = "";
		if (format != null && !format.equals("html")) {
			suffix = "." + format;
		}
		return suffix;
	}
	
	private String getViewName(ResourceMethod method) {
		if(method.getMethod().isAnnotationPresent(View.class)) {
			View view = method.getMethod().getAnnotation(View.class);
			return view.value();
		}
		return method.getMethod().getName();
	}
	
	private String getModuleName(ResourceMethod method) {
		try {
			Module module = method.getResource().getType().getAnnotation(Module.class);
			return module.value().toLowerCase() + "/";
		} catch (Exception e) {
			return "";
		}
	}
	
}
