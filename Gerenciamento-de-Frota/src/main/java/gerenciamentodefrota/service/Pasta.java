package gerenciamentodefrota.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.google.common.io.ByteStreams;

public class Pasta {
	
	private File diretorio;
	
	public Pasta(String caminho) {
		diretorio = new File(caminho);
	}
	
	public List<File> get() {
		return Arrays.asList(diretorio.listFiles());
	}
	
	public byte[] toByteArray(String fileName) throws FileNotFoundException, IOException {
		return toByteArray(get(fileName));
	}
	
	public byte[] toByteArray(File file) throws FileNotFoundException, IOException {
		return ByteStreams.toByteArray(new FileInputStream(file));
	}
	
	public File get(String fileName) {
		return new File(fullPath(fileName));
	}
	
	private String fullPath(String fileName) {
		return diretorio.getPath() + "\\" + fileName;
	}
	
	public void save(UploadedFile upload, String fileName) throws IOException {
		InputStream file = upload.getFile();
		OutputStream fileNew = new FileOutputStream(fullPath(fileName));
		
		byte[] buffer = new byte[(int) upload.getSize()];
		int bytesRead;
		
		while ((bytesRead = file.read(buffer)) != -1) {
			fileNew.write(buffer, 0, bytesRead);
		}
		
		file.close();
		fileNew.flush();
		fileNew.close();
	}
	
	public boolean save(File file) {
		// TODO Implementar método para salva um arquivo na pasta
		return false;
	}
	
	public boolean delete(File file) {
		// TODO Implementar método para apagar um arquivo da pasta
		return false;
	}
	
}
