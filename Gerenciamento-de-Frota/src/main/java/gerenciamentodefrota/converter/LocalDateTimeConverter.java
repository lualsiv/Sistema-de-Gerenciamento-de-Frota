package gerenciamentodefrota.converter;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(LocalDateTime.class)
@ApplicationScoped
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

	@SuppressWarnings("static-access")
	public LocalDateTime convert(String value, Class<? extends LocalDateTime> type, ResourceBundle bundle) {
		if (value == null || value.equals("")) {
			return null;
		}
		try {
			System.out.println("LocalDateTimeConverter" + value);
			
			return new LocalDateTime().now();
		} catch (NumberFormatException e) {
			throw new ConversionError(MessageFormat.format(bundle.getString("is_not_a_valid_integer"), value));
		}
	}

}
