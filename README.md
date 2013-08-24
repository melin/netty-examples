netty-examples 
==============

是基于netty4开发的应用实例：

- rpc server和client的实现
- netty4client简化netty client的应用

RPC HelloWorld 实例
-------------------

<b>服务实现</b>

```java
public interface HelloWorld {
	public Result queryUser(User user);
}
```
```java
public class HelloWorldImpl implements HelloWorld {

	@Override
	public Result queryUser(User user) {
		return new Result("hello " + user.getUsername());
	}

}
```

<b>服务端</b>

```java
public class ServerTest {
	public static void main(String[] args) {
		Netty4Server server = new Netty4Server(10);
		server.registerProcessor(HelloWorld.class.getName(), new HelloWorldImpl());

		try {
			server.start(8080);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

<b>客户端</b>

```java
public class ClientTest {
	public static void main(String[] args) {
		try {
			HelloWorld helloWorld = RpcClientFactory.refer(HelloWorld.class, "localhost", 8080);

			Result result = helloWorld.queryUser(new User("melin")); 

			System.out.println(result.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```
