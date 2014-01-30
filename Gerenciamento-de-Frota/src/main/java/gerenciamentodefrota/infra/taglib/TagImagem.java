package gerenciamentodefrota.infra.taglib;

import gerenciamentodefrota.infra.WebConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.xml.bind.DatatypeConverter;

import com.google.common.io.ByteStreams;
import com.hp.gagawa.java.elements.Img;

public class TagImagem extends TagSupport {

	private static final long serialVersionUID = 4930458327111815060L;
	private WebConfig config = new WebConfig();

	private String arquivo;
	private String width;
	private String height;
	
	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = config.get("pasta_fotos") + arquivo;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			String imageString = imageToString();
			
			Img img = new Img("descrição", imageString)
							.setWidth(width)
							.setHeight(height);
			
			print(img.write());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

	private String imageToString() throws FileNotFoundException, IOException {
		InputStream input = new FileInputStream(arquivo);
		byte[] baos = ByteStreams.toByteArray(input);
		String imageString = "data:image/png;base64,"
				+ DatatypeConverter.printBase64Binary(baos);
		return imageString;
	}

	private void print(String string) {
		try {
			pageContext.getOut().print(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
