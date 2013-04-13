Courses Info Portlet
====================

This project is my seminar work for course MI-LIF at CTU in Prague. It’s very simple Liferay portlet which one can use to search and to view information about our courses.

The main aim of this project is:

1. satisfy project targets with regard to used technologies and features, 
2. provide reference example how to easily retrieve data from RESTful service (specifically [KOSapi](https://kosapi.fit.cvut.cz)) secured with OAuth2 protocol or HTTP Basic,
3. try out new template engine [Thymeleaf](http://www.thymeleaf.org/) in portlet environment.

I didn’t deal with styling and AJAX at all so it doesn’t look cool, but this project is more about code. ;-) Also it may seem weird that I divided such simple functionality into two portlets, but I just wanted to try out Inter Portlet Communication.


Chosen points of work’s targets
-------------------------------

* Spring Portlet MVC
* Inter Portlet Communication
* Portlet Preferencies
* Portlet modes VIEW, HELP, EDIT
* Liferay Friendly URL mapping
* RESTful client (instead of database)


Used frameworks and libraries
-----------------------------

* [Spring Framework](http://www.springframework.org/) (Core, Portlet MVC, Web, OXM, Security OAuth2)
* [Thymeleaf](http://www.thymeleaf.org/) + [liferay-url-addon](https://github.com/jirutka/thymeleaf-liferay) (XML/XHTML/HTML5 template engine) 
* [Hibernate Validator](http://www.hibernate.org/subprojects/validator.html) (JSR-303 Bean Validation)
* [Atom JAXB](https://github.com/jirutka/atom-jaxb) (custom JAXB mapping for Atom Syndication Format)


License
-------

This project is licensed under [MIT license](http://opensource.org/licenses/MIT).
