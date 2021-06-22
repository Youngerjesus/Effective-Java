## 아이템 3 - private 생성자나 열거 타입으로 싱글턴임을 보장하라

***

싱글턴이란 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다. 

싱글턴으로 만들때 유의해야 할 점은 무상태(Stateless)와 시스템 컴포넌트중 유일해야 한다는 점이다. 이를 염두해두고 싱긑턴으로 사용해야한다. 

싱글턴의 단점 중 하나는 클라이언트쪽에서 테스트하기 어렵다는 점인데 인터페이스를 정의하고 그 구현 객체에다가 싱긑턴으로 만든 경우라면
Mock 객체를 주입하기 쉽지만 일반적으로 이렇게 만들지 않기 때문이다. (인터페이스를 정의해두면 갈아끼우기가 쉬우므로 테스트가 더 쉽다.)

#### 싱글턴으로 만드는 방법

싱글턴으로 만드는 방식은 보통 둘 중 하나다.

두 방식 모두 생성자는 private 으로 감싸서 외부에서 절대 호출하지 못하도록 한다. 그리고 필드에 public static 멤버를 
하나 마련해두고 그 객체만 쓰도록 한다. 

##### 싱글턴 객체 첫번째 방법 예 - Elvis

````java
public class Elvis {
    public static Elvis INSTANCE = new Elvis(); 
    
    private Elvis(){}
    
    public void leaveTheBuilding() {} 
}
````

- private 생성자는 처음 Elvis 객체를 만들 때 딱 한번만 호출된다. 하지만 자바 리플렉션을 이용하면 (AccessibleObject.setAccessible) 이를 호출할 순 있다.
  이를 막기 위해선 생성자가 두번 호출하면 예외를 던지게 해야한다.
  
- Elvis 객체를 참조하기 위해서는 Elvis.INSTANCE 로 멤버에 직접 참조해서 사용이 가능하다. 

- 이 방식의 장점은 코드를 사용할 때 싱글턴임이 잘 들어난다는 점이다. 

##### 싱글턴 객체 두번째 방법 예 - Elvis2

````java
public class Elvis2 {
    private static final Elvis2 INSTANCE = new Elvis2();
    
    public static Elvis2 getInstance(){return INSTANCE;}
    
    private Elvis2(){}

    public void leaveTheBuilding() {}
}
````

- getInstance() 라는 정적 팩토리 메소드를 통해서 매번 같은 참조를 가진 Elvis2 객체를 반환하도록 해서 싱긑턴을 보장할 수 있다. 

- 이 방식은 첫번째 방식보다 좀 더 간결하다. 그리고 요구사항이 바껴서 싱글턴임이 아니게 해야한다면 API 를 바꾸지 않고도 이를 수정하는게 가능하다. 
  그리고 원한다면 제네릭 싱글턴으로 리턴하게 만들 수 있다. 마지막으로 정적 팩토리 메소드를 supplier 로 사용하는게 가능하다는 점이다. 
  
둘 중 하나의 방식으로 싱글턴 클래스를 직렬화 하려면 인스턴스 필드를 일시적(transient) 라고 선언하고 readResolve() 메소드를 제공해야한다. 이렇게 하지 않으면 
직렬화된 인스턴스를 역직렬화할때 매번 새로운 인스턴스가 만들어진다. 

##### 싱글턴 객체 세번째 방법 예 - Elvis3

````java
public enum Elvis3 {
    INSTANCE;
    
    public void leaveTheBuilding() {}
}
````

- 이 방법이 부자연스러워 보일 수 있지만 제2의 인스턴스가 생기는 문제를 완벽히 막아준다. 대부분의 상황에서는 Enum 타입을 사용하는게 가장 완벽하다. 

- Enum 같은 경우는 상속이 안되기 때문에 클래스 상속이 필요한 경우가 아니라면 이를 쓰자.    

 


 