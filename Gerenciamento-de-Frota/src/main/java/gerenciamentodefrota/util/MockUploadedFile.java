package gerenciamentodefrota.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.io.ByteStreams;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class MockUploadedFile implements UploadedFile {

	// TODO - WTF! Funciona no jetty e no tomcat 7 não
	private MimeTypesFileTypeDetector mimeTypeDetector = new MimeTypesFileTypeDetector();
	
	private String contentType;
	private InputStream file;
	private String filename;
	private Long size;
	
	public MockUploadedFile(String caminho, String nome) throws IOException {
		Path path = getPath(caminho, nome);
		
		this.contentType = mimeTypeDetector.probeContentType(path);
		
		this.file = new FileInputStream(path.toFile());
		this.filename = nome;
		this.size = (long) ByteStreams.toByteArray(this.file).length;
	}

	private Path getPath(String caminho, String nome) {
		Path path = Paths.get(caminho);
		return path.resolve(nome);
	}
	
	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public InputStream getFile() {
		return file;
	}

	@Override
	public String getFileName() {
		return filename;
	}

	@Override
	public long getSize() {
		return size;
	}

}
