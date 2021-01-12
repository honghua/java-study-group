> Effective Java - Third Edition
> -- <cite> Joshua Bloch </cite>
> -- <cite> Read by Yang Li </cite>


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [Creating and Destroying Objects](#creating-and-destroying-objects)
  - [Item 1: Consider static factory methods instead of constructors](#item-1-consider-static-factory-methods-instead-of-constructors)
  - [Item 2: Consider a builder when faced with many constructor parameters](#item-2-consider-a-builder-when-faced-with-many-constructor-parameters)
  - [Item 3: Enforce the singleton property with a private constructor or an enum type](#item-3-enforce-the-singleton-property-with-a-private-constructor-or-an-enum-type)
  - [Item 4: Enforce non-instantiability with a private constructor](#item-4-enforce-non-instantiability-with-a-private-constructor)
  - [Item 5: Prefer dependency injection to hardwiring resources](#item-5-prefer-dependency-injection-to-hardwiring-resources)
  - [Item 6: Avoid creating unnecessary objects](#item-6-avoid-creating-unnecessary-objects)
    - [Java Heap Space vs. Stack Memory](#java-heap-space-vs-stack-memoryhttpswwwbaeldungcomjava-stack-heap)
    - [Reuse a single object is better than creating a new functionally equivalent object each time it is needed.](#reuse-a-single-object-is-better-than-creating-a-new-functionally-equivalent-object-each-time-it-is-needed)
    - [How to avoid unnecessary object creation?](#how-to-avoid-unnecessary-object-creation)
      - [Use Static factory method](#use-static-factory-method)
      - [Consider *adapters* as known as *views*](#consider-adapters-as-known-as-views)
      - [Prefer primitives to boxed primitives](#prefer-primitives-to-boxed-primitives)
      - [Use Object Pooling unless the pool are extremely heavyweight](#use-object-pooling-unless-the-pool-are-extremely-heavyweight)
  - [Item 7: Eliminate obsolete object references](#item-7-eliminate-obsolete-object-references)
  - [Item 8: Avoid finalizers and cleaners](#item-8-avoid-finalizers-and-cleaners)
  - [Item 9: Prefer try-with-resources to try-finally](#item-9-prefer-try-with-resources-to-try-finally)
- [Methods Common to All Objects](#methods-common-to-all-objects)
  - [Item 10: Obey the general contract when overriding equals](#item-10-obey-the-general-contract-when-overriding-equals)
    - [Conditions that not override `equals` method](#conditions-that-not-override-equals-method)
    - [Appropriate time to override equals method](#appropriate-time-to-override-equals-method)
    - [Contract for the `equals()` method?](#contract-for-the-equals-method)

<!-- /code_chunk_output -->


# Creating and Destroying Objects

## Item 1: Consider static factory methods instead of constructors
Basically, the way to obtain an instance is to provide a public constructor. There is another technique that provide a <u>*public static factory method* </u> which is a static method that returns an instance of the class.

```java
public class PhoneNumber {
    private static final PhoneNumber COMMON = new PhoneNumber(123, 1234);

    private final int areaCode;
    private final int number;

    private PhoneNumber(int areaCode, int number) {
        checkArgument(areaCode > 100); // Guava (concerns: version incompatible)
        this.areaCode = areaCode;
        this.number = number;
    }

    public static PhoneNumber of(int areaCode, int number) {
        if (areaCode == 123 && number == 1234) {
            return COMMON;
        }
        return new PhoneNumber(areaCode, number);
    }

    public static void main(String[] args) {
        PhoneNumber common1 = PhoneNumber.of(123, 1234);
        PhoneNumber common2 = PhoneNumber.of(123, 1234);
        System.out.println(common1 == common2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("areaCode: ", areaCode)
                .add("number: ", number)
                .toString();
    }
}
```

```java
package com.company;

public class Person {
    private String title;
    private final String name;
    private final String surname;
    private String prefix;

    private Person(String title, String name, String surname, String prefix) {
        this.title = title;
        this.name = name;
        this.surname = surname;
        this.prefix = prefix;
    }

    public static class Builder {
        private String title;
        private final String name;
        private final String surname;
        private String prefix;


        private Builder(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public static Builder of(String name, String surname) {
            return new Builder(name, surname);
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public Person build() {
            // check statement
            return new Person(title, name, surname, prefix);
        }
    }
}
```

**Advantages:**
* Static factory method has names.
  * With descriptive names, customer can better understand it.
  * A class can have only a single constructor with a given signature. By providing different lists of parameters, customer will use different constructor. But it's hard for customers to remember details. Because static factory method has names, so they do not have previous restriction.
* Static factory method are not required to create a new object each time when they're revoked.
  ```java
    // if this number is very common, will return common and not create a new instance every time
    public static PhoneNumber of(int areaCode, int number) {
        if (areaCode == 123 && number == 1234) {
            return COMMON;
        }
        return new PhoneNumber(areaCode, number);
    }

    public static void main(String[] args) {
        PhoneNumber common = PhoneNumber.of(123, 1234);
        PhoneNumber common2 = PhoneNumber.of(123, 1234);

        // compare them using == instead of equals.
        System.out.println(common == common2);
    }
  ```
* Static factory method can return an object of any subtype of their return type.
  * Hiding from the client (better encapsulation) of object creation
    ```java
    public class UserFactory {

        public static User newUser(UserEnum type){
            switch (type){
                case ADMIN: return new Admin();
                case STAFF: return new StaffMember();
                case CLIENT: return new Client();
                default:
                    throw new IllegalArgumentException("Unsupported user. You input: " + type);
            } 
        }

        public static void main(String[] args) {
            // client code - give me an admin object, 
            // don't care about the inner details of how it gets constructed
            User admin = UserFactory.newUser(ADMIN); 
        }
    }
    ```
  * Flexibility to swap out implementations without breaking client code
    ```java
    // static factory method
    public static List<String> getMyList(){
        return new ArrayList<>(); 
    }

    public class MyMuchBetterList<E> extends AbstractList<E> implements List<E> {
    // implementation
    }

    public static List<String> getMyList(){
        return new MyMuchBetterList<>(); // compiles and works, subtype of list
    }
    ```
* The class of returned object can vary from call to call as a function of the <u> input parameters </u>. Example: EnumSet. 
    ```java
    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
        Enum<?>[] universe = getUniverse(elementType);
        if (universe == null)
            throw new ClassCastException(elementType + " not an enum");

        if (universe.length <= 64)
            return new RegularEnumSet<>(elementType, universe);
        else
            return new JumboEnumSet<>(elementType, universe);
    }
    ```
* The class of returned object need not exit when the class containing the method is written. This one is essential components in a service provider framework.
  * Service Interface
  * Provider Registration API
  * Service access API
  * Service provider interface

 **Disadvantages:**
 * Classes without public or protected constructors cannot be subclassed.
 * They are hard for programmers to find.
   * from, of, valueOf, create or newInstance, getType, newType and type

## Item 2: Consider a builder when faced with many constructor parameters
Static factories and constructors share a limitation: they do not scale well to large number of optional parameters.

Traditionally, programmers have used the <u> *telescoping constructor*</u> pattern. This constructor will force customer to pass required value. **Telescoping constructor works, but it is hard to write client code when there are many parameters, and harder still to read it.** And if customer accidentally reverses two such parameters, the compiler will not complain, but the program will misbehave at runtime.
```java
public class NutritionFacts {
    private final int servingSize; // ml
    private final int servings; // per container
    private final int calories; // per serving
    private final int fat; // g/serving
    private final int sodium; // mg/serving
    private final int carbohydrate; // g/serving

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
```

A second alternative when facing with many optional parameters is a constructor is <u> *JavaBeans* </u> pattern, in which you call a parameterless constructor to create the object and then call setter methods to set each required parameter and each optional parameter of interest. This patter has no disadvantage of the telescoping constructor pattern. But it is wordy.
JavaBeans has its own disadvantages. 
* Because construction is split across multiple calls, a JavaBean may be in an inconsistent state partway through its construction.
* JavaBeans pattern precludes the possibility of making a class immutable.
```java
public class NutritionFacts {
    // parameters initialized to default values
    private int servingSize; 
    private int servings; 
    private int calories; 
    private int fat; 
    private int sodium; 
    private int carbohydrate;

    public NutritionFacts(){}

    // settings
    public void setServingSize(int val) {
        servingSize = val;
    }

    public void setServings(int val) {
        servings = val;
    }

    public void setCalories(int val) {
        calories = val;
    }

    // ... ...
}
```

The third one is <u> *Builder* </u> pattern. The client calls a constructor with all of thr required parameters and gets a <u> builder object </u> .
```java
public class NutritionFactsBuilder {
    private int servingSize;
    private int servings;
    // ... ...

    public NutritionFactsBuilder withServingSize(int servingSize) {
        this.servingSize = servingSize;
        // What is returned is the instantiated object that actually calls this method 
        return this; 
    }

    public NutritionFactsBuilder withServings(int servings) {
        this.servings = servings;
        return this;
    }

    public NutritionFactsBuilder build() {
        return new NutritionFactsBuilder();
    }
}

class Test {
    public static void main(String[] args) {
        NutritionFactsBuilder nutritionFactsBuilder = new NutritionFactsBuilder().withServings(1).withServingSize(1).build();
        System.out.println(nutritionFactsBuilder);
    }
}
```

```java
public class NutritionFactsBuilder2 {
    private final int servingSize;
    private final int servings;
    
    public static class Builder{
        private final int servingSize;
        private int servings;
        
        public Builder(int servingSize) {
            this.servingSize = servingSize;
        }
        
        public Builder withServings(int servings) {
            this.servings = servings;
            return this;
        }
        
        public NutritionFactsBuilder2 build() {
            return new NutritionFactsBuilder2(this);
        }
    }
    
    private NutritionFactsBuilder2(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
    }
}

class Test2 {
    public static void main(String[] args) {
        NutritionFactsBuilder2 builder2 = new NutritionFactsBuilder2.Builder(240).build();
    }
}

```

```java
public class NutritionFactsBuilder2 {
    private final int servingSize;
    private final int servings;

    public static class Builder{
        private int servingSize;
        private int servings;

        public Builder() {

        }

        public Builder withServings(int servings) {
            this.servings = servings;
            return this;
        }

        public NutritionFactsBuilder2 build() {
            return new NutritionFactsBuilder2(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private NutritionFactsBuilder2(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
    }
}

class Test2 {
    public static void main(String[] args) {
        NutritionFactsBuilder2 builder21 = NutritionFactsBuilder2.builder().build();
    }
}

```

Problem: lack of compile-time guarantee. `builder2.getServings()` will throw NPE.
Solution: use @NonNull. When running it, it will check the parameter.

**Builder with hierarchies**
```java
public class People {
    private String name;
    private int age;

    protected People(Builder<?> builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "name: " + this.name + ", age: " + this.age;
    }

    public static Builder builder() {
        return new Builder() {
            @Override
            public Builder getThis() {
                return this;
            }
        };
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String name;
        private int age;

        public abstract T getThis();

        public T name(String name) {
            this.name = name;
            return this.getThis();
        }

        public T age(int age) {
            this.age = age;
            return this.getThis();
        }

        public People build() {
            return new People(this);
        }
    }

}

class Student extends People {

    private String school;

    public Student(Builder builder) {
        super(builder);
        this.school = builder.school;
    }

    @Override
    public String toString() {
        return super.toString() + ", school: " + this.school;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends People.Builder<Builder> {
        private String school;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder school(String school) {
            this.school = school;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}

class Test3{
    public static void main(String[] args) {
        Student student= Student.builder()
                .name("student")
                .age(18)
                .school("ABC")
                .build();
        System.out.println(student.toString());

        People people = People.builder()
                .name("teacher")
                .age(20)
                .build();
        System.out.println(people.toString());
    }
}
```

When we implement `Student.class`, all properties of `Student.class` must be implemented before calling parents field. If we use the wrong order, IDE will warning some errors.

**Annotation**
* @Builder

```java
// need to install plugins "Lombok"
@Builder
public class People {
    private String name;
    private int age;
}

@Builder
class Student extends People{
    private String tag;
    Student(String name, int age, String tag) {
        super(name, age);
        this.tag = tag;
    }
}

class Test {
    public static void main(String[] args) {
        People people = People.builder().age(18).name("test").build();
    }
}
```

* @SuperBuilder: works for all field, solve the problem in @Builder
```java
@SuperBuilder
public class People {
    private String name;
    private int age;
}

@SuperBuilder
class Student extends People{
    private String tag;
}
```
  
## Item 3: Enforce the singleton property with a private constructor or an enum type
A singleton is simply a class that is instantiated exactly once. Three properties:
* A private constructor
* A static field containing its only instance
* A static factory method for obtaining the instance
```java
public class Singleton {
     private static final Singleton singleton = null ;
     private Singleton() {  }
     public static Singleton getInstance() {
         if (singleton== null ) {
             singleton= new Singleton();
         }
         return singleton;
     }
}
```
While this is a common approach, it's important to note that it can be **problematic in multithreading scenarios**, which is the main reason for using Singletons. Simply put, it can result in more than one instance, breaking the pattern's core principle. Singleton may have multiple processes pass the condition check of (singleton== null) at the same time, so multiple instances are created, and memory leaks are likely.
->  mutually exclusive or synchronized.
```java
// version 1.1
public class Singleton
{
     private static Singleton singleton = null ;
     private Singleton() {  }
     public static Singleton getInstance() {
         if (singleton== null ) {
             synchronized (Singleton. class ) {
                 singleton= new Singleton();
             }
         }
         return singleton;
     }
}
```
The synchronized method will help us synchronize all threads. But we still initialize multiple instances.

```java
// version 1.2
public class Singleton
{
     private static Singleton singleton = null ;
     private Singleton()  {  }
     public static Singleton getInstance()  {
         synchronized (Singleton. class ) {
             if (singleton== null ) {
                singleton= new Singleton();
             }
          }
         return singleton;
     }
}
```
In this version, we sync all thread, and make sure the new operation will be triggered once. However, we sync reading as well (which is not needed).

```java
// version 1.4
public class Singleton
{
     private volatile static Singleton singleton = null ;
     private Singleton()  {    }
     public static Singleton getInstance()   {
         if (singleton== null )  {
             synchronized (Singleton. class ) {
                 if (singleton== null )  {
                     singleton= new Singleton();
                 }
             }
         }
         return singleton;
     }
}
```
```java
public enum Singleton{
    INSTANCE;
}
```

## Item 4: Enforce non-instantiability with a private constructor
You will want to write a class that is just a grouping of static methods and static fields. Such class do not need any constructor. Example: Arrays, Collections. This kind of utility classes were not designed to be initialized. 
* Abstract the class - > not working
Because sub-class can be initialized.
* make constructor private. 
 * @NoArgConstructor: creates a public Constructor with no parameter.
 * @NoArgsConstructor(access = AccessLevel.PRIVATE): private access.

## Item 5: Prefer dependency injection to hardwiring resources
Many classes depends on one or more underlying resources. The approach below is satisfactory. **Static utility and singletons are inappropriate for classes whose behavior is parameterized by an underlying resources.** We want our class can handle multiple instances of a class.Each of the resources are designed by client. <u> *Dependency injection* </u> is the concept in which objects get other required objects from outside.
```java
public class SpellChecker{
    // The Lexicon class is a data structure of maintaining all unique words that appears in the given data set.
    private static final Lexicon dictionary = ...;
    private SpellChecker(){}
}
```
```java
public class SpellChecker{
    private static final Lexicon dictionary;
    private SpellChecker(Lexicon dictionary){}
}
```

Ideally Java classes should be as independent as possible from other Java classes. This increases the possibility of reusing these classes and to be able to test them independently from other classes. If the Java class creates an instance of another class via the new operator, it cannot be used (and tested) independently from this class and this is called a <u> *hard dependency* </u>. The following example shows a class which has no hard dependencies.
```java
import java.util.logging.Logger;

public class MyClass {

    private Logger logger;

    public MyClass(Logger logger) {
        this.logger = logger;
        // write an info log message
        logger.info("This is a log message.")
    }
}
```

We can use annotation to describe class dependencies. `@Inject, @Name`. The dependencies of injection is: 1) the constructor of the class (construction injection), 2) a field (field injection) and 3) the parameters of a method (method injection). <u> *Injection* </u> passes one object to another through a method or constructor.

**Tips:**
1. Do not use a singleton or static utility class to implement a class that depends on one or more underlying resources whose behavior affects that of the class.
2. Do not have the class create these resources directly. Instead, pass the resources or factories to create them into a constructor (or static factory or builder).

## Item 6: Avoid creating unnecessary objects
* What is unnecessary objects?
Ans:
* Why we need to avoid it?
Ans: 
* Which kind of data can be reused?
Ans: 

### [Java Heap Space vs. Stack Memory](https://www.baeldung.com/java-stack-heap)
**Heap:** object. GC runs here to release unused objects.\
**Stack:** static memory allocation. LIFO
```java
public class Memory {
	public static void main(String[] args) { // Line 1
		int i=1; // Line 2
		Object obj = new Object(); // Line 3
		Memory mem = new Memory(); // Line 4
		mem.foo(obj); // Line 5
	} // Line 9
 
	private void foo(Object param) { // Line 6
		String str = param.toString(); // Line 7
		System.out.println(str);
	} // Line 8
```
![Java Runtime Memory](https://cdn.journaldev.com/wp-content/uploads/2014/08/Java-Heap-Stack-Memory.png)

**Java class loading:**
* compile: javac file -> .class.
* run: JVM.

### Reuse a single object is better than creating a new functionally equivalent object each time it is needed. 
An object can always be reused if it is immutable. Consider this statement: `String s = new String("test");` This statement creates a new String instance each time it is executed and none of the creation is necessary. The improved version is `String s = "test`. This one can be reused by any other code running in the same virtual machine that happens to contain the same string literal. 

Some object creations are much more expensive than others. If you’re going to need such an “expensive object” repeatedly, it may be advisable to cache it for reuse. The problem with this implementation is that it relies on the String.matches method. **While String.matches is the easiest way to check if a string matches a regular expression, it’s not suitable for repeated use in performance-critical situations.** The problem is that it internally creates a Pattern instance for the regular expression and uses it only once, after which it becomes eligible for garbage collection. Creating a Pattern instance is expensive because it requires compiling the regular expression into a finite state machine.
```java
// Performance can be greatly improved!
static boolean isRomanNumeral(String s) {
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}
```
The way to improve it is: compile the regular expression into a Pattern instance (which is immutable) as part of class initialization, cache it,and reuse the same instance for every invocation of the isRomanNumeral method.
```java
// Reusing expensive object for improved performance
public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```
There is one situation that `isRomanNumeral` is initialized but never invoked. The field `ROMAN` is initialized needlessly. It would be possible to eliminate the initialization by *lazy initialization.*

### How to avoid unnecessary object creation?

#### Use Static factory method
```java
public static Boolean valueOf(boolean b) {
    return (b ? TRUE : FALSE);
}
```
#### Consider *adapters* as known as *views*
is an object that delegates to a backing object, providing an alternative interface.
```java
public Set<K> keySet() {
    Set<K> ks = keySet;
    if (ks == null) {
        ks = new KeySet();
        keySet = ks;
    }
    return ks;
}
```

#### Prefer primitives to boxed primitives
Another way to create an unnecessary object is *autoboxing* which allows the programmer to mix primitive and boxed primitive types, boxing and unnboxing automatically as needed. **Autobixing blurs but does not erase the distinct between primitive and boxed primitive types.**
* Autoboxing: primitive -> object. boolean -> Boolean
* Unboxing: reverse
```java
private static long sum() {
    Long sum = 0L;
    for (long i = 0; i <Integer.MAX_VALUE; i++) {
        sum+=i;
    }

    return sum;
}
```

#### Use Object Pooling unless the pool are extremely heavyweight
An *object pool* is a collection of a particular object that an application will create and keep on hand for those situations where creating each instance is expensive. A good example would be a database connection or a worker thread. The pool checks instances in and out for users like books out of a library.

## Item 7: Eliminate obsolete object references
Manual memory management: `C, C++`. Garbage-collected language: `Java`.
```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        element = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {...}

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        return elements[--size];
    }
}
```

Consider the code above, there is nothing wrong with the code itself. However, the program has a "memory leak". And will cause `OutOfMemoryError`, but it's rare. 
*Memory Leak*: if a stack grows and then shrinks, the objects that popped off the stack will not be garbage collected, even if the program using the stack has no more references to them. This is because stack maintain <u> *Obsolete references* </u> to those objects.
```java
// fixed version
public Object pop() {
    if (size == 0) {
        throw new EmptyStackException();
    }
    Object result = elements[--size];
    elements[size] = null;
    return result;
}
```

**Nulling out object references should be exception rather than the norm. Whenever a class manages its own memory, the programmer should be alert for memory alert. Another common source of memory leaks is caches.**

Then, through the below method, is the memory space used to create `str` be reclaimed? In fact, no, the list still holds a reference to `str`, so the memory space when `str` is created will not be reclaimed. For this kind of reference, we call it <u> *unconscious memory reference* </u>.
```java
// whether str is recycled?
List<String> list = new ArrayList<>();
String str = "java";
list.add(str);
str  = null;
```

## Item 8: Avoid finalizers and cleaners

**Finalizers**

*Finalizers* is finalize() function that to release resources used by objects before they're removed from the memory. 
```java
// Called by the garbage collector on an object when garbage collection
// determines that there are no more references to the object.
public class Object{
    @Deprecated(since="9")
    protected void finalize() throws Throwable { }
}
```
对象可由两种状态，涉及到两类状态空间，一是终结状态空间 F = {unfinalized, finalizable, finalized}；二是可达状态空间 R = {reachable, finalizer-reachable, unreachable}。各状态含义如下：
*unfinalized: 新建对象会先进入此状态，GC并未准备执行其finalize方法，因为该对象是可达的
* finalizable: 表示GC可对该对象执行finalize方法，GC已检测到该对象不可达。正如前面所述，GC通过F-Queue队列和一专用线程完成finalize的执行
* finalized: 表示GC已经对该对象执行过finalize方法
* reachable: 表示GC Roots引用可达
* finalizer-reachable(f-reachable)：表示不是reachable，但可通过某个finalizable对象可达
* unreachable：对象不可通过上面两种途径可达
```java
public class GC {

    public static GC SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        // 新建对象，因为SAVE_HOOK指向这个对象，对象此时的状态是(reachable,unfinalized)
        SAVE_HOOK = new GC();
        //将SAVE_HOOK设置成null，此时刚才创建的对象就不可达了，因为没有句柄再指向它了，对象此时状态是(unreachable，unfinalized)
        SAVE_HOOK = null;
        //强制系统执行垃圾回收，系统发现刚才创建的对象处于unreachable状态，并检测到这个对象的类覆盖了finalize方法，因此把这个对象放入F-Queue队列，由低优先级线程执行它的finalize方法，此时对象的状态变成(unreachable, finalizable)或者是(finalizer-reachable,finalizable)
        System.gc();
        // sleep，目的是给低优先级线程从F-Queue队列取出对象并执行其finalize方法提供机会。在执行完对象的finalize方法中的super.finalize()时，对象的状态变成(unreachable,finalized)状态，但接下来在finalize方法中又执行了SAVE_HOOK = this;这句话，又有句柄指向这个对象了，对象又可达了。因此对象的状态又变成了(reachable, finalized)状态。
        Thread.sleep(500);
        // 对象处于(reachable,finalized)状态应该是合理的。对象的finalized方法被执行了，因此是finalized状态。又因为在finalize方法是执行了SAVE_HOOK=this这句话，本来是unreachable的对象，又变成reachable了。
        if (null != SAVE_HOOK) { //此时对象应该处于(reachable, finalized)状态 
            // 这句话会输出，注意对象由unreachable，经过finalize复活了。
            System.out.println("Yes , I am still alive");
        } else {
            System.out.println("No , I am dead");
        }
        // 再一次将SAVE_HOOK放空，此时刚才复活的对象，状态变成(unreachable,finalized)
        SAVE_HOOK = null;
        // 再一次强制系统回收垃圾，此时系统发现对象不可达，虽然覆盖了finalize方法，但已经执行过了，因此直接回收。
        System.gc();
        // 为系统回收垃圾提供机会
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            // 这句话不会输出，因为对象已经彻底消失了。
            System.out.println("Yes , I am still alive");
        } else {
            System.out.println("No , I am dead");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("execute method finalize()");
        // 这句话让对象的状态由unreachable变成reachable，就是对象复活
        SAVE_HOOK = this;
    }
}
```

***

**Finalizers are unpredictable, often dangerous and generally unnecessary.** The Java 9 replacement for finalizers is <u> *Cleaners* </u>. **Cleaners are less dangerous than finalizers, but still unpredictable, slow, and generally unnecessary.**

In C++, destructors are the normal way to reclaim the resources associated with an object, a necessary counterpart to constructors. In Java, the garbage collector reclaims the storage associated with an object when it becomes unreachable, requiring no special effort on the part of the programmer.

**Disadvantage(Finalizers)**: 
* no guarantee they will be executed promptly(迅速的). It can take arbitrarily long between the time that an object becomes unreachable and the time its dinalizer or cleaner run.
* no guarantee that they will run at all. **Should not depend on a finalizer or cleaner to update persistent state.**
* uncaught exception thrown during finalization is ignored.
* server performance: increase running time. (cleaner is better.)
* finalizers open your class up to finalizer attacks.

**TODO: rest tips for not using finalizers and cleaners.**

## Item 9: Prefer try-with-resources to try-finally
The Java libraries include many resources that must be closed manually by invoking a `close` method. Historically, `try-finally` is a good way to close the resources. However, when we open multiple resources, the code would be ugly. And also, if exceptions are thrown by two resources, we can only get the second one. It become harder to catch the first exception.
```java
try {
    // open resource
} finally {
    // close it
}
```

```java
try {
    // open resource 1
    try {
        // open resource 2
    } finally{
        // close resource 2
    }
} finally {
    // close resource 1
}
```

Use `try-with-resources` will solve the problem. If you write a class that represents a resource that must be closed, should implement `AutoCloseable`.
```java
try (Scanner scanner = new Scanner(new File("testRead.txt"));
    PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
    while (scanner.hasNext()) {
	writer.print(scanner.nextLine());
    }
}
```

# Methods Common to All Objects
Although `Object` is concrete class, it is designed primarily for extension. All of its nonfinal methods have explicit *general contracts* because they are designed to be overridden. This chapter is to tell you when and how to override the nonfinal `Object` methods.

## Item 10: Obey the general contract when overriding equals

### Conditions that not override `equals` method
* Each instance of the class is inherently unique. \
Thread class doesn't need to implement as `equals()` method provided by Object has the right behavior.
* There is no need for the class to provide a "logical equality". \
In `java.util.Random` or `java.util.regex.Pattern` we don't care about two random numbers being equal, even if we need one, the equals implementation inherited from Object is adequate.
* A superclass has already overridden equals, and the superclass behavior is appropriate for this class. \
`List` -> `ArrayList`
* The class is private or package-private, and you are certain that its equals method will never be invoked. \
```java
// ensure it is never be invoked
@Override
public boolean equals(Object o) {
    throw new AssertionError();
}
```
### Appropriate time to override equals method
* a class has a notion of logical equality that differs from mere object identity.
* a superclass has not already overridden equals to implement the desired behavior. \
Those are generally the case for *value classes*. A value class is simply a class that represents a value, such as Integer or String. `equals()` is to compare whether they are logically equivalent not whether they refer to the same object.
One kind of value class that does not require the `equals` method is a class that uses instance control (item 1) to ensure that at most one Object exists with each value. Enum type (item 34) fall into this category. **Use `Object`'s equals method is enough.**

### Contract for the `equals()` method?
* Reflexive: An object must be equal to itself. \
For any non-null reference value `x`, `x.equals(x)` should return true.
* Symmetric: Two objects must agree on whether they are equal.\
For any non-null reference values `x` and `y`, `x.equals(y)` should return true if and only if `y.equals(x)` returns true.
* Transitive: If A = B & B = C; than A = C.
* Consistent: If two objects are equal, they must remain equal for all times unless one or both of them are modified.
* Non-nullity: All objects must not be equal to null. For any non-null reference value `x`, `x.equals(null)` should return false.

