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
- [1 Entry Point](#1-entry-point) <br/>
- [2 Repository](#2-repository) <br/>
- [3 Using Config Manager](#3-using-config-manager) <br/>
- [4 Logging](#4-logging) <br/>
- [5 Testing And Incoming Outgoing JSON Samples](#5-testing-and-incoming-outgoing-json-samples) <br/>
  * [5-a- Test Web Service](#5-a-test-web-service) <br/>
  * [5-b- Retrieve All Customer Collection](#5-b-retrieve-all-customer-collection) <br/>
  * [5-c- Retrieve Customer](#5-c-retrieve-customer) <br/>
  * [5-d- Create Customer](#5-d-create-customer) <br/>
  * [5-e- Update Customer](#5-e-update-customer) <br/>
  * [5-f- Delete Customer](#5-f-delete-customer) <br/>

1 Entry Point
--------------
Starting grizzly standalone web server is quite easy. First you have to create a simple instance of
Server that which you configure the controller packages and the URI of your server. You can check
out the [Server](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/Server.java) class and the [EntryPoint](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/EntryPoint.java), it's pretty straight forward.

Just in case, note that I've used [ConfigManager](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/configuration/ConfigManager.java) class in the [Server](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/Server.java) class so that instead of hardcoding
the Endpoint URI, I grab the endpoint from the properties file via the [ConfigManager class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/configuration/ConfigManager.java).

There are two main straight-forward classes that you have to understand how an HTTP Server initialized;

1. [Server class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/Server.java)
2. [EntryPoint class](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/src/main/java/com/levent/webservice/main/EntryPoint.java)

[Go back to TOC](#toc)

2 Repository
-------------
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

3 Using Config Manager
-----------------------
The configuration must always abstracted via a properties file. But in order to do so, the property file(s)
must be read via a configuration manager. I've created my own configuration manager and actually it is a
simple example of [Gang Of Four Singleton Design Pattern](https://en.wikipedia.org/wiki/Singleton_pattern).

If you want to understand the Config Manager in detail, you can check out my [Config Manager Repository](https://github.com/bzdgn/singleton-configuration-manager-example).

Config Manager is easy to use. It assumes either you are using an environmental variable to store the properties
file including log4j properties file, or config files are located on the working directory. It's always
a good practice to define an environmental variable so that the portability of the configuration will
become easier.

To use ConfigManager with an environmental variable

1. Set up an environmental variable and point it to a directory
2. Put your properties file and your log4j properties file on the defined directory
3. Define the configuration parameter in your ConfigManager class

That's all. Then you can get your configuration parameters easily from any class file.

To set up the environmental variable in windows, simply follow these steps;

1. Follow the following steps and define your enrivonmental variable;
![environmental-variable-setup-up-in-windows](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/ENV_VAR_01.PNG)

2. Put your properties file as below in the designated folder in the environmental variable;
![properties-files](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/ENV_VAR_02.PNG)

And how do we define the name of the environmental variable and name of the properties file. We can not define
it in another properties file and it will be nonsense. That's why I've hardcoded the name of the properties file
in the ConfigManager. Environmental Variable is set to "CUSTOMER_API_CONFIG" by default, and also the properties
file set to "customer_api.properties" by default. 

If you want to change these two hardcoded names, you can set them in the static inner Config class which
is in the ConfigManager. The following code part is where the two hardcoded names are set;

```
private static class Config {
	
	private static final String 			ENVIRONMENT_VAR_NAME	= "CUSTOMER_API_CONFIG";
	public static final String 				CONFIG_FILE_NAME 		= "customer_api.properties";
	private static final String 			RESOURCE_DIR;
	private Properties
```

Lastly, I've put a sample customer_api.properties file on the top level folder. The location of the rolling log file
is defined there. You can check out the config snippet down below;

```
endpoint_uri=http://0.0.0.0:8080
```

[Go back to TOC](#toc)

4 Logging
----------
The project uses Apache Log4J. If you want to change the version, simply you can modify it on the [POM file](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/pom.xml).
Log4J also needs to find it's properties file, which is also defined inside the ConfigManager.

For the simplicity, I've set it to the same folder we use for the config files. Check out the
code snippet taken from ConfigManager;

```
	private ConfigManager() {
		Config config = new Config(Config.CONFIG_FILE_NAME);
		
		// RESOURCE AND LOG
		RESOURCE_DIRECTORY			= Config.RESOURCE_DIR;
		LOG4J_PROPERTIES			= RESOURCE_DIRECTORY + "/log4j.properties";
		
		ENDPOINT_URI				= config.getProperty("endpoint_uri");
	}
```

But if you want to define it configurable, simply create a new variable in the ConfigManager class
so that you can configure it via the configuration file.

I've put a sample logj4.properties file on the top level folder. The location of the rolling log file
is defined there. You can check out the config snippet down below;

```
#Set your target folder configuration in here
log4j.appender.rollingfile.File=D:/CONFIG_BASE/application.log
```


[Go back to TOC](#toc)

5 Testing And Incoming Outgoing JSON Samples
---------------------------------------------

5-a Test Web Service
--------------------

Method:          GET <br/>
Tool:            Web Browser <br/>
Link:            http://localhost:8080/customer-api/test <br/>
Response Format: "text/plain" <br/>
Response Output;
```
customer-api is working.
```

Sample Capture;

![capture-for-test](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/01_TEST.PNG)

[Go back to Section 5](#5-testing-and-incoming-outgoing-json-samples) <br/>
[Go back to TOC](#toc)



5-b Retrieve All Customer Collection
------------------------------------

Method:          GET <br/>
Tool:            Web Browser <br/>
Link:            http://localhost:8080/customer-api/customers <br/>
Response Format: "application/json" <br/>
Response Output;
```
{
	"data": {
		"customers": [{
			"age": 31,
			"firstName": "Levent",
			"id": 1,
			"lastName": "Divilioglu",
			"regular": true
		},
		{
			"age": 44,
			"firstName": "John",
			"id": 2,
			"lastName": "Doe",
			"regular": false
		},
		{
			"age": 24,
			"firstName": "Jane",
			"id": 3,
			"lastName": "Doe",
			"regular": true
		},
		{
			"age": 66,
			"firstName": "Fawn",
			"id": 4,
			"lastName": "Smith",
			"regular": false
		}]
	},
	"meta": {
		"successful": true
	}
}
```

Sample Capture;

![capture-for-retrieve-all](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/02_RETRIEVE_ALL_CUSTOMERS.PNG)

[Go back to Section 5](#5-testing-and-incoming-outgoing-json-samples) <br/>
[Go back to TOC](#toc) <br/>



5-c Retrieve Customer
---------------------

Method:          GET <br/>
Tool:            Web Browser <br/>
Link:            http://localhost:8080/customer-api/customers/1 <br/>
Response Format: "application/json" <br/>
Response Output;

```
{
	"data": {
		"customers": [{
			"age": 31,
			"firstName": "Levent",
			"id": 1,
			"lastName": "Divilioglu",
			"regular": true
		}]
	},
	"meta": {
		"successful": true
	}
}
```

Sample Capture;

![capture-for-retrieve-single-customer](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/03_RETRIEVE_SINGLE_CUSTOMER.PNG)

[Go back to Section 5](#5-testing-and-incoming-outgoing-json-samples) <br/>
[Go back to TOC](#toc) <br/>



5-d Create Customer
-------------------

Method:          POST <br/>
Tool:            Postman <br/>
Link:            http://localhost:8080/customer-api/customers <br/>
Consume  Format: "application/json" <br/>
Response Format: "application/json" <br/>
Request Input;
```
{
    "age": 99,
    "firstName": "Bob",
    "id": 12345,
    "lastName": "Immortal",
    "regular": true
}
```

Response Output;
```
{
    "data": {
        "customers": [
            {
                "age": 99,
                "firstName": "Bob",
                "id": 12345,
                "lastName": "Immortal",
                "regular": true
            }
        ]
    },
    "meta": {
        "successful": true
    }
}
```

Final Collection;
```
{
	"data": {
		"customers": [{
			"age": 31,
			"firstName": "Levent",
			"id": 1,
			"lastName": "Divilioglu",
			"regular": true
		},
		{
			"age": 44,
			"firstName": "John",
			"id": 2,
			"lastName": "Doe",
			"regular": false
		},
		{
			"age": 24,
			"firstName": "Jane",
			"id": 3,
			"lastName": "Doe",
			"regular": true
		},
		{
			"age": 66,
			"firstName": "Fawn",
			"id": 4,
			"lastName": "Smith",
			"regular": false
		},
		{
			"age": 99,
			"firstName": "Bob",
			"id": 5,
			"lastName": "Immortal",
			"regular": true
		}]
	},
	"meta": {
		"successful": true
	}
}
```

Sample Capture;

![capture-for-create-customer-a](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/04_A_CREATE_CUSTOMER.PNG)
![capture-for-create-customer-b](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/04_B_CREATE_CUSTOMER.PNG)

[Go back to Section 5](#5-testing-and-incoming-outgoing-json-samples) <br/>
[Go back to TOC](#toc) <br/>



5-e Update Customer
-------------------

Method:          PUT <br/>
Tool:            Postman <br/>
Link:            http://localhost:8080/customer-api/customers <br/>
Consume  Format: "application/json" <br/>
Response Format: "application/json" <br/>
Request Input;
```
{
	"age": 66,
	"firstName": "Johnny B.",
	"id": 345345,
	"lastName": "Goode",
	"regular": true
}
```

Response Output;
```
{
	"data": {
		"customers": [{
			"age": 66,
			"firstName": "Johnny B.",
			"id": 2,
			"lastName": "Goode",
			"regular": true
		}]
	},
	"meta": {
		"successful": true
	}
}
```

Final Collection;
```
{
	"data": {
		"customers": [{
			"age": 31,
			"firstName": "Levent",
			"id": 1,
			"lastName": "Divilioglu",
			"regular": true
		},
		{
			"age": 66,
			"firstName": "Johnny B.",
			"id": 2,
			"lastName": "Goode",
			"regular": true
		},
		{
			"age": 24,
			"firstName": "Jane",
			"id": 3,
			"lastName": "Doe",
			"regular": true
		},
		{
			"age": 66,
			"firstName": "Fawn",
			"id": 4,
			"lastName": "Smith",
			"regular": false
		},
		{
			"age": 99,
			"firstName": "Bob",
			"id": 5,
			"lastName": "Immortal",
			"regular": true
		}]
	},
	"meta": {
		"successful": true
	}
}
```

Sample Capture;

![capture-for-update-customer-a](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/05_A_UPDATE_CUSTOMER.PNG)
![capture-for-update-customer-b](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/05_B_UPDATE_CUSTOMER.PNG)

[Go back to Section 5](#5-testing-and-incoming-outgoing-json-samples) <br/>
[Go back to TOC](#toc) <br/>



5-f Delete Customer
-------------------

Method:          DELETE <br/>
Tool:            Postman <br/>
Link:            http://localhost:8080/customer-api/customers/4 <br/>
Response Format: "application/json" <br/>

Response Output;
```
{
	"meta": {
		"successful": true
	}
}
```

Final Collection;
```
{
	"data": {
		"customers": [{
			"age": 31,
			"firstName": "Levent",
			"id": 1,
			"lastName": "Divilioglu",
			"regular": true
		},
		{
			"age": 66,
			"firstName": "Johnny B.",
			"id": 2,
			"lastName": "Goode",
			"regular": true
		},
		{
			"age": 24,
			"firstName": "Jane",
			"id": 3,
			"lastName": "Doe",
			"regular": true
		},
		{
			"age": 99,
			"firstName": "Bob",
			"id": 5,
			"lastName": "Immortal",
			"regular": true
		}]
	},
	"meta": {
		"successful": true
	}
}
```

Sample Capture;

![capture-for-delete-customer-a](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/06_A_DELETE_CUSTOMER.PNG)
![capture-for-delete-customer-b](https://github.com/bzdgn/simple-grizzly-standalone-restful-webservice-example/blob/master/ScreenShots/06_B_DELETE_CUSTOMER.PNG)

[Go back to Section 5](#5-testing-and-incoming-outgoing-json-samples) <br/>
[Go back to TOC](#toc) <br/>