package com.basic.framework.common.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSAUtils {

	public static final String KEY_ALGORITHM = "RSA";

	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";

	private static final String PRIVATE_KEY = "RSAPrivateKey";

	private static final int MAX_ENCRYPT_BLOCK = 117;

	private static final int MAX_DECRYPT_BLOCK = 128;

	public static void main( String[] args ) throws Exception {
		Map<String, Object> keyMap = RSAUtils.genKeyPair();
		String publicKey = RSAUtils.getPublicKey( keyMap );
		String privateKey = RSAUtils.getPrivateKey( keyMap );
		System.out.println( "公钥:" + publicKey + "公钥长度：" + publicKey.length() );
		System.out.println( "私钥:" + privateKey + "私钥长度：" + privateKey.length() );

		System.out.println( "私钥加密——公钥解密" );
		String source = "lihui";
		System.out.println( "加密前文字：" + source );
		byte[] encodedData = RSAUtils.encryptByPrivateKey( source.getBytes(), privateKey );
		System.out.println( "加密后文字：" + Base64.encodeBase64String( encodedData ) );
		byte[] decodedData = RSAUtils.decryptByPublicKey( encodedData, publicKey );
		String target = new String( decodedData );
		System.out.println( "解密后文字: " + target );
		String signData = RSAUtils.sign( source.getBytes( "UTF-8" ), privateKey );
		System.out.println( "签名后数据：" + signData );
		System.out.println( "验签结果：" + RSAUtils.verify( source.getBytes( "UTF-8" ), publicKey, signData ) );
	}

	/**
	* <p>
	* 生成密钥对(公钥和私钥)
	* </p>
	* 
	* @return
	* @throws Exception
	*/
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance( KEY_ALGORITHM );
		keyPairGen.initialize( 1024 );
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = ( RSAPublicKey )keyPair.getPublic();
		RSAPrivateKey privateKey = ( RSAPrivateKey )keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>( 2 );
		keyMap.put( PUBLIC_KEY, publicKey );
		keyMap.put( PRIVATE_KEY, privateKey );
		return keyMap;
	}

	/**
	* <p>
	* 用私钥对信息生成数字签名
	* </p>
	* 
	* @param data
	*            已加密数据
	* @param privateKey
	*            私钥(BASE64编码)
	* 
	* @return
	* @throws Exception
	*/
	public static String sign( byte[] data, String privateKey ) throws Exception {
		byte[] keyBytes = Base64.decodeBase64( privateKey );
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( keyBytes );
		KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM );
		PrivateKey privateK = keyFactory.generatePrivate( pkcs8KeySpec );
		Signature signature = Signature.getInstance( SIGNATURE_ALGORITHM );
		signature.initSign( privateK );
		signature.update( data );
		return Base64.encodeBase64String( signature.sign() );
	}

	/**
	* <p>
	* 校验数字签名
	* </p>
	* 
	* @param data
	*            已加密数据
	* @param publicKey
	*            公钥(BASE64编码)
	* @param sign
	*            数字签名
	* 
	* @return
	* @throws Exception
	* 
	*/
	public static boolean verify( byte[] data, String publicKey, String sign ) throws Exception {
		byte[] keyBytes = Base64.decodeBase64( publicKey );
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec( keyBytes );
		KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM );
		PublicKey publicK = keyFactory.generatePublic( keySpec );
		Signature signature = Signature.getInstance( SIGNATURE_ALGORITHM );
		signature.initVerify( publicK );
		signature.update( data );
		return signature.verify( Base64.decodeBase64( sign ) );
	}

	/**
	* <P>
	* 私钥解密
	* </p>
	* 
	* @param encryptedData
	*            已加密数据
	* @param privateKey
	*            私钥(BASE64编码)
	* @return
	* @throws Exception
	*/
	public static byte[] decryptByPrivateKey( byte[] encryptedData, String privateKey ) throws Exception {
		byte[] keyBytes = Base64.decodeBase64( privateKey );
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( keyBytes );
		KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM );
		Key privateK = keyFactory.generatePrivate( pkcs8KeySpec );
		Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
		cipher.init( Cipher.DECRYPT_MODE, privateK );
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while( inputLen - offSet > 0 ) {
			if( inputLen - offSet > MAX_DECRYPT_BLOCK ) {
				cache = cipher.doFinal( encryptedData, offSet, MAX_DECRYPT_BLOCK );
			} else {
				cache = cipher.doFinal( encryptedData, offSet, inputLen - offSet );
			}
			out.write( cache, 0, cache.length );
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	* <p>
	* 公钥解密
	* </p>
	* 
	* @param encryptedData
	*            已加密数据
	* @param publicKey
	*            公钥(BASE64编码)
	* @return
	* @throws Exception
	*/
	public static byte[] decryptByPublicKey( byte[] encryptedData, String publicKey ) throws Exception {
		byte[] keyBytes = Base64.decodeBase64( publicKey );
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( keyBytes );
		KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM );
		Key publicK = keyFactory.generatePublic( x509KeySpec );
		Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
		cipher.init( Cipher.DECRYPT_MODE, publicK );
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while( inputLen - offSet > 0 ) {
			if( inputLen - offSet > MAX_DECRYPT_BLOCK ) {
				cache = cipher.doFinal( encryptedData, offSet, MAX_DECRYPT_BLOCK );
			} else {
				cache = cipher.doFinal( encryptedData, offSet, inputLen - offSet );
			}
			out.write( cache, 0, cache.length );
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	* <p>
	* 公钥加密
	* </p>
	* 
	* @param data
	*            源数据
	* @param publicKey
	*            公钥(BASE64编码)
	* @return
	* @throws Exception
	*/
	public static byte[] encryptByPublicKey( byte[] data, String publicKey ) throws Exception {
		byte[] keyBytes = Base64.decodeBase64( publicKey );
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec( keyBytes );
		KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM );
		Key publicK = keyFactory.generatePublic( x509KeySpec );
		// 对数据加密
		Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
		cipher.init( Cipher.ENCRYPT_MODE, publicK );
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while( inputLen - offSet > 0 ) {
			if( inputLen - offSet > MAX_ENCRYPT_BLOCK ) {
				cache = cipher.doFinal( data, offSet, MAX_ENCRYPT_BLOCK );
			} else {
				cache = cipher.doFinal( data, offSet, inputLen - offSet );
			}
			out.write( cache, 0, cache.length );
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	* <p>
	* 私钥加密
	* </p>
	* 
	* @param data
	*            源数据
	* @param privateKey
	*            私钥(BASE64编码)
	* @return
	* @throws Exception
	*/
	public static byte[] encryptByPrivateKey( byte[] data, String privateKey ) throws Exception {
		byte[] keyBytes = Base64.decodeBase64( privateKey );
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec( keyBytes );
		KeyFactory keyFactory = KeyFactory.getInstance( KEY_ALGORITHM );
		Key privateK = keyFactory.generatePrivate( pkcs8KeySpec );
		Cipher cipher = Cipher.getInstance( keyFactory.getAlgorithm() );
		cipher.init( Cipher.ENCRYPT_MODE, privateK );
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while( inputLen - offSet > 0 ) {
			if( inputLen - offSet > MAX_ENCRYPT_BLOCK ) {
				cache = cipher.doFinal( data, offSet, MAX_ENCRYPT_BLOCK );
			} else {
				cache = cipher.doFinal( data, offSet, inputLen - offSet );
			}
			out.write( cache, 0, cache.length );
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	* <p>
	* 获取私钥
	* </p>
	* 
	* @param keyMap
	*            密钥对
	* @return
	* @throws Exception
	*/
	public static String getPrivateKey( Map<String, Object> keyMap ) throws Exception {
		Key key = ( Key )keyMap.get( PRIVATE_KEY );
		return Base64.encodeBase64String( key.getEncoded() );
	}

	/**
	* <p>
	* 获取公钥
	* </p>
	* 
	* @param keyMap
	*            密钥对
	* @return
	* @throws Exception
	*/
	public static String getPublicKey( Map<String, Object> keyMap ) throws Exception {
		Key key = ( Key )keyMap.get( PUBLIC_KEY );
		return Base64.encodeBase64String( key.getEncoded() );
	}

}