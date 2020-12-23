# Experimental DI and Application Execution Framework

## Motivation

## Basic usage

### Context creation

#### Code-based
```java
Context context = new GenericContext.Builder()
                    .withScanner(new ClassPathScanner("com.package"))
                    .withScanner(new PropertiesPathScanner("classpath:some-source.yaml"))
                    .build();
```

#### Annotation-based
```java
@ContextScopedApplication(
        classPathScanner = { "com.package" },
        propertiesScanner = { "classpath:some-source.yaml" }
)
public class Application {
} 
```

### Create component to be registered in context

#### Code-based
```java
context.registerComponent(
        (sections, registry) -> ComponentClass.class,
        registry -> new ComponentConfiguration()
                    .withScope(Scope.SINGLETON)
);
```

#### Annotation-based
```java
@ContextComponent(Scope.SINGLETON)
public class ComponentClass {
    public void someAction() {
    }
}
```

### Add pre and post-processors

#### Code-based
```java
context.registerComponent(
        (sections, registry) -> ComponentClass.class,
        registry -> new ComponentConfiguration()
                        .pre(component -> component.core().initFirst())
                        .pre(component -> component.core().initSecond())
                        .post(component -> component.proxy().add(
                                template -> template.someAction().doBefore(() -> System.out.println("Hey"))
                        ))
);
```

### Inject component

#### Annotation-based
```java
@ContextComponent(Scope.SINGLETON)
public class ComponentClass {
    private final AnotherComponentClass anotherComponentClass;
    
    @InjectionTarget
    public ComponentClass(AnotherComponentClass anotherComponentClass) {
        this.anotherComponentClass = anotherComponentClass;    
    }
}
```

### 

## Release notes

## Links

