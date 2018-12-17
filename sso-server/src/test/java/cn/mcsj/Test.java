package cn.mcsj;

import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;

import cn.mcsj.sso.constant.GlobalConstant;

public class Test {

	public static void main(String[] args) {
		String username = "jrzg";
		String password="1234qwer";
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		Object obj = new SimpleHash(GlobalConstant.HASH_ALGORITHM_NAME, password, username+salt, GlobalConstant.HASH_ITERATIONS);
		System.out.println("SALT:"+salt);
		System.out.println("encode pwd:"+obj);
	}
}
