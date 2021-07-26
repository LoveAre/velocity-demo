package com.zp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import com.zp.pojo.User;

public class VelocityTest {
	
	@Test
	public void test1() throws IOException {
		//1.设置velocity的资源加载器
		Properties p = new Properties();
		p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		//2.初始velocity引擎
		Velocity.init(p);
		//3.创建velocity容器
		VelocityContext context = new VelocityContext();
		context.put("name", "hello velocity");
		
		User user01 = new User("唐三藏", "tangsanzang", "@tangsanzang.com");
		User user02 = new User("孙悟空", "sunwukong", null);
		context.put("user01", user01);
		context.put("user02", user02);		
		
		context.put("str", "hello world velocity !");
		context.put("now", new Date());
		
		String[] hobbies = {"吃饭", "睡觉", "打豆豆"};
		context.put("hobbies", hobbies);
		
		User user03 = new User("唐三藏", "tangsanzang", "@tangsanzang", 1);
		User user04 = new User("孙悟空", "sunwukong", "@sunwukong", 2);
		User user05 = new User("猪八戒", "zhubajie", "@zhubajie", 3);
		User user06 = new User("白龙马", "bailongma", "@bailongma", 4);
		User user07 = new User("白骨精", "baigujing", "@baigujing", 5);
		List<User> users = new ArrayList();
		users.add(user03);
		users.add(user04);
		users.add(user05);
		users.add(user06);
		users.add(user07);
		context.put("users", users);
		
		Map<Integer, User> map = new HashMap<Integer, User>();
		for(int i = 0 ; i < users.size(); i++) {
			map.put(i, users.get(i));
		}
		context.put("map2", map);
		
		context.put("code", "#if($name=='over') over  #else  not over #end");
		context.put("code2", "\"#if($name=='over') over  #else  not over #end\"");
		
		
		//4.加载velocity模板文件
		Template template = Velocity.getTemplate("vms/velocityTemplate01.vm", "utf-8");
		//5.合并数据到模板
		FileWriter fw = new FileWriter("E:\\workspace\\myeclipse2019-study\\velocity-demo\\src\\main\\resources\\html\\velocity01.html");
		template.merge(context, fw);
		//6.释放资源
		fw.close();
	}

}
