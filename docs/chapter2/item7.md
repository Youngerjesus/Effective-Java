## 아이템 7 - 다 쓴 객체 참조를 해제하라

***

C나 C++ 처럼 자바는 직접 메모리 관리를 하지 않아도 된다. 

가비지 컬렉터가 메모리 관리를 해주기 떄문이다. 그렇다고 해서 개발자가 메모리 관리에 더이상 신경쓰지 않아도 되냐고 물으면 그건 아니다. 

다음 메모리 누수가 일어나는 예시를 보자. 

```java
public class Stack {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_STACK_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_STACK_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if(size == elements.length) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    // memory leak method     
    public Object pop() {
        if(size == 0)
            throw new EmptyStackException(); 
        return elements[size--];
    }

}
```

- pop() 을 하더라도 객체의 참조는 끊어진게 아니라 elements 에서 계속 가지고 있으므로 가비지 컬렉터에서 회수하지 못한다. 

가비지 컬렉터는 객체 참조가 하나라도 살아있으면 객체를 회수하지 못한다. 

그러므로 다음과 같이 pop 메소드를 바꿔야한다. 

##### 올바른 pop 메소드 
```java
public Object pop_good(){
    if(size == 0)
        throw new EmptyStackException();

    Object element = elements[--size];
    elements[size] = null; 
    return element; 
}
```

- 그리고 이렇게 null 처리를 하면 좋은 점이 잘못된 참조를 할 경우 NullPointerException 이 발생하게 되는데 이로인해 잘못된 참조로 인해서
작업을 하는게 아니라 조기에 에러를 만날 수 있어서 다른 부작용이 없다.

이렇게 다 쓴 객체에 대해 null 처리의 이점을 알았다고 해서 무조건 그 객체를 다썼다고 해서 Null 처리를 하지는 말자. 

그러면 코드만 지저분해질 뿐이다. null 처리를 할 때는 __메모리를 관리하는 객체일 때만 하자.__

방금 예시에서 본 stack 은 stack 이 자신이 가지고 있는 개체에 대해 메모리 관리를 했다. __그러므로 stack 을 개발한 개발자 말고
가비지 컬렉터는 이 stack 에 있는 객체가 모두 쓰인다고 생각하지 비활성화 되어있는 객체가 있다고 생각하지 않는다.__

그러므로 메모리를 관리하는 객체가 있다면 메모리 누수에 대해서 생각하고 필요하면 null 처리를 해주자.

캐싱을 할 때 역시 메모리 누수에 대해서 신경써야 한다. 캐시에 저장해놓고 역시 까먹고 있으면 메모리 누수가 발생한다.

만약에 키 값이 살아있을 동안에만 캐시 값이 존재하기만 하면 되는 경우라면 일반적인 HashMap 을 쓰는것보다 WeakHashMap 을 쓰는게 훨씬 낫다.

WeakHashMap 은 WeakReference 를 가지는데 대상에 대한 참조가 없어지면 그 대상이 GC 될 때 WeakReference 도 같이 GC 가 되도록 한다. 

그래서 WeakHashMap 을 쓰는 경우라면 키 값이 참조가 끊기면 값도 WeakReference 로 연결되어 있어서 같이 사라진다. 

##### WeakHashMap 예제 

```java
public class Cache {
    WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        Integer k1 = new Integer(5000); // Integer 는 내부적으로 캐싱을 한다.
        Integer k2 = new Integer(5001);
        Integer k3 = new Integer(5002);

        Integer v1 = new Integer(6000);
        Integer v2 = new Integer(6001);
        Integer v3 = new Integer(6002);

        cache.weakHashMap.put(k1, v1);
        cache.weakHashMap.put(k2, v2);
        cache.weakHashMap.put(k3, v3);

        k1 = null;
        k2 = null;
        k3 = null;

        System.gc();
        Thread.sleep(5000);

        System.out.println(cache.weakHashMap.isEmpty()); // true 
        for(Integer i : cache.weakHashMap.values()) {
            System.out.println(i); // 출력 안됨. 
        }
    }
}
```

메모리 누수의 세번째 주범은 콜백과 리스너인데 콜백과 리스너를 추가만하고 명확히 해지하지 않는다면 계속해서 쌓여갈 것이다.



 

  

