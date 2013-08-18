package gerenciamentodefrota.converter;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.converter.ConversionError;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(BigDecimal.class)
@ApplicationScoped
public class BigDecimalConverter implements Converter<BigDecimal> {

	public BigDecimal convert(String value, Class<? extends BigDecimal> type, ResourceBundle bundle) {
		if (value == null || value.equals("")) {
			return null;
		}
		try {
			value = value.replace(',', '.');
			return new BigDecimal(value);
		} catch (NumberFormatException e) {
			throw new ConversionError(MessageFormat.format(bundle.getString("is_not_a_valid_integer"), value));
		}
	}

}
