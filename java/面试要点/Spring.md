Spring的经典面试题

#什么是Spring框架
  Spring是一种轻量级框架，旨在提高开发人员的开发效率以及系统的可维护性
  我们一般说的Spring框架就是Spring Framework，它是很多模块的集合，使用这些模块可以很方便地协助我们进行开发。这些模块是核心容器、数据访问/集成、Web、Aop(面向切面编程)、工具、消息和测试模块。比如Core Container 中的Core组件是Spring所有组件的核心，Beans组件和Context组件是实现IOC和DI的基础，AOP组件用来实现面向切面编程。
  Spring官网（https://spring.io/）列出的Spring的6个特征：

  核心技术：依赖注入（DI），AOP，事件（Events），资源，i18n，验证，数据绑定，类型转换，SpEL。

  测试：模拟对象，TestContext框架，Spring MVC测试，WebTestClient。

  数据访问：事务，DAO支持，JDBC，ORM，编组XML。

  Web支持：Spring MVC和Spring WebFlux Web框架。

  集成：远程处理，JMS，JCA，JMX，电子邮件，任务，调度，缓存。

  语言：Kotlin，Groovy，动态语言。


#列举一些重要的Spring模块？
  Spring Core：基础，可以说Spring其他所有的功能都依赖于该类库。主要提供IOC和DI功能。

  Spring Aspects：该模块为与AspectJ的集成提供支持。

  Spring AOP：提供面向方面的编程实现。

  Spring JDBC：Java数据库连接。

  Spring JMS：Java消息服务。

  Spring ORM：用于支持Hibernate等ORM工具。

  Spring Web：为创建Web应用程序提供支持。

  Spring Test：提供了对JUnit和TestNG测试的支持。


#IOC

	IOC（Inversion Of Controll，控制反转）是一种设计思想，就是将原本在程序中手动创建对象的控制权，交由给Spring框架来管理。IOC在其他语言中也有应用，并非Spring特有。IOC容器是Spring用来实现IOC的载体，IOC容器实际上就是一个Map(key, value)，Map中存放的是各种对象。

	将对象之间的相互依赖关系交给IOC容器来管理，并由IOC容器完成对象的注入。这样可以很大程度上简化应用的开发，把应用从复杂的依赖关系中解放出来。IOC容器就像是一个工厂一样，当我们需要创建一个对象的时候，只需要配置好配置文件/注解即可，完全不用考虑对象是如何被创建出来的。在实际项目中一个Service类可能由几百甚至上千个类作为它的底层，假如我们需要实例化这个Service，可能要每次都搞清楚这个Service所有底层类的构造函数，这可能会把人逼疯。如果利用IOC的话，你只需要配置好，然后在需要的地方引用就行了，大大增加了项目的可维护性且降低了开发难度。

	Spring时代我们一般通过XML文件来配置Bean，后来开发人员觉得用XML文件来配置不太好，于是Sprng Boot注解配置就慢慢开始流行起来。
     XML ------->(读取) Resource -------> （解析）BeanDefinition ------>（注册）BeanFactory


#AOP

	AOP（Aspect-Oriented Programming，面向切面编程）能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可扩展性和可维护性。

	Spring AOP是基于动态代理的，如果要代理的对象实现了某个接口，那么Spring AOP就会使用JDK动态代理去创建代理对象；而对于没有实现接口的对象，就无法使用JDK动态代理，转而使用CGlib动态代理生成一个被代理对象的子类来作为代理。

