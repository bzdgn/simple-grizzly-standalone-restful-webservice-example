Simple Grizzly Standalone Restful Webservice Example
====================================================
This project is a demonstration and example for a standalone grizzly RESTful webservice application.
It is a customer-api that you can apply the basic CRUD operations. For the 'customer' model, 
a simple POJO with dot-notation is introduced. An interface Repository is used to generalize
and set a contract for any arbitrary Repository Implementation to be used in the project.
A simple static ArrayList is used in the Cache Repository Implementation but you can use
any arbitrary repository implementing the interface contract like Database Implementation,
File Implementation, Socket Implementation and so on.

I've used a Configuration Manager, which that I've previously implemented and also you can check
[github repository](https://github.com/bzdgn/singleton-configuration-manager-example/blob/master/README.md). I've explained the Configuration Manager on the independent repository
in detail.

To demonstrate the Front Controller, I've created a [CustomerController class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/controller/impl/CustomerController.java) and applied basic
HTTP Methods like GET (for single and collection retrieve), POST (for create), PUT (for update) 
and DELETE (for delete).

TOC
---
1- [Entry Point](#entry-point) <br/>
2- [Repository](#repository) <br/>
3- [Using Config Manager](#using-config-manager) <br/>
4- [Logging](#logging) <br/>

Entry Point
-----------
Starting grizzly standalone web server is quite easy. First you have to create a simple instance of
Server that which you configure the controller packages and the URI of your server. You can check
out the [Server](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/Server.java) class and the [EntryPoint](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/EntryPoint.java), it's pretty straight forward.

Just in case, note that I've used [ConfigManager](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/configuration/ConfigManager.java) class in the [Server](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/Server.java) class so that instead of hardcoding
the Endpoint URI, I grab the endpoint from the properties file via the [ConfigManager class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/configuration/ConfigManager.java).

There are two main straight-forward classes that you have to understand how an HTTP Server initialized;

1. [Server class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/Server.java)
2. [EntryPoint class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/EntryPoint.java)

[Go back to TOC](#toc)

Repository
----------
Any repository implementation regardless of the storage (File, Socket, Database) must have the following basic operations;

1. ```T find(long id)```
2. ```List<T> findAll()```
3. ```T create(T t)```
4. ```boolean update(T t)```
5. ```boolean delete(long id)```

Thus, I've created a simple interface named as [BaseRepository](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/repository/BaseRepository.java) which is as follows;

```
public interface BaseRepository<T> {
	
	T find(long id) throws Exception;
	List<T> findAll() throws Exception;
	T create(T t) throws Exception;
	boolean update(T t) throws Exception;
	boolean delete(long id) throws Exception;
	
}
```

The [CustomerRepository](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/repository/CustomerRepository.java) interface, like all arbitrary repositories, extends this interface. Because that the interface
is using generics, any model class can be used for any arbitrary implementation. So In order to create a specific interface
for any arbitrary model;

1. Create the interface which extends the [BaseRepository](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/repository/BaseRepository.java) (eg: [CustomerRepository](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/repository/CustomerRepository.java))
2. Implement the specific interface (eg: [CustomerRepositoryCacheImpl](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/repository/impl/CustomerRepositoryCacheImpl.java))

[Go back to TOC](#toc)

Using Config Manager
--------------------
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder

[Go back to TOC](#toc)

Logging
-------
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder
Placeholder

[Go back to TOC](#toc)