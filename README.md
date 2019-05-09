# mockito-feign-demo

Questo repository mostra

* Come realizzare il mock di un client Spring Feign con [Mockito](https://site.mockito.org/)
* Come [iniettare](https://www.baeldung.com/injecting-mocks-in-spring) il mock nella classe oggetto del test
* Come utilizzare [Podam](https://mtedone.github.io/podam/) per generare modelli popolati randomicamente

# Mockito

In pratica, i test provano i due metodi della classe **TestableService**. Questo semplice servizio invoca un client Feign puntato all'indirizzo di fantasia http://localhost:6996. Se avviassimo l'applicazione (non si può, manca lo starter web di Spring Boot, se volete provare di persona basta aggiungerlo) e invocassimo i metodi del servizio, otterremo delle FeignException perchè il client non trova l'indirizzo.

I nostri test invece funzionano correttamente, perchè grazie all'annotation [*@Mockbean*](https://www.baeldung.com/java-spring-mockito-mock-mockbean) di Spring Boot, abbiamo configurato che durante i test il client sarà mockato.

Nei singoli test, configuriamo Mockito con le risposte che vogliamo ritornare con la sintassi

```java
Mockito.when(cli.getFoo()).thenReturn("MOCK");
```
e possiamo verificare che l'indirizzo locale non viene mai invocato realmente, mentre il servizio esegue correttamente senza lamentare l'assenza del client Feign che richiede per istanziarsi come Bean.

## Podam

Dal momento che non abbiamo più la preoccupazione che i nostri modelli rispettino le logiche imposte dai veri servizi esterni, è lecito generare casualmente un oggetto con valori random. Podam, tipicamente usando una factory, è in grado di istanziare oggetti valorizzati in questo modo ed è quindi utile per preparare risposte complesse da dare in pasto a Mockito. La sintassi di base è

```java
PodamFactoryImpl factory = new PodamFactoryImpl();
MyModel mock = factory.manufacturePojo(MyModel.class);
```

Ci sono altre feature interessanti che potete trovare nelle relative documentazioni di Mockito e Podam se avete voglia di approfondire.

## Maven

Di seguito le versioni usate nella demo. Mockito è già incluso nello starter spring-boot-starter-test, basta dichiararlo.
```
		<dependency>
			<groupId>uk.co.jemos.podam</groupId>
			<artifactId>podam</artifactId>
			<version>7.2.3.RELEASE</version>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
		</dependency>
 ```
