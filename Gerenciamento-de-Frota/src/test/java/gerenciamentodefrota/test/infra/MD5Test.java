package gerenciamentodefrota.test.infra;

import gerenciamentodefrota.util.MD5;

import org.junit.Assert;
import org.junit.Test;

public class MD5Test {

	@Test
	public void converterSenhaParaMD5() {
		MD5 md5 = new MD5();
		Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", md5.hash("123456"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveGerarExceptionQuandoPassarValorNulo() {
		MD5 md5 = new MD5();
		Assert.assertEquals("", md5.hash(null));
	}
	
}
