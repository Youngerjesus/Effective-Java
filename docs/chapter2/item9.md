## 아이템 9 - try-finally 보다는 try-with-resources 를 사용하라

***

자바의 여러 라이브러리에는 close 메소드를 호출해서 직접 자원을 닫아줘야 하는 경우가 많다. 

예를들면 InputStream, 이나 OutputStream 이 있고 java.sql.Connection 이 있다. 

하지만 이렇게 직접 호출하는 경우에는 빠트리는 경우가 있어서 자원 누수가 일어날 수 있고 이에 대한 안정책으로 물론
finalizer 가 있긴 하지만 이도 성능상으론 좋지 않다. 

자원을 회수할 땐 try-finally 를 주로 쓰는데 이런 경우에는 문제가 생길 수 있다.

예컨데 BufferReader 기준으로 readLine() 메소드를 호출하고 finally 에서 이 자원을 닫는다고 가정해보자.

기기에 문제가 생기면 readLine() 메소드도 예외가 발생할거고 close() 메소드에서도 예외가 발생할 것이다. 

이 경우 두번째 예외가 첫번째 예외를 모두 집어삼켜서 디버깅 하기 힘든 문제가 생긴다. 

이러한 문제들을 자바 7 에서 들어온 try-with-resources 를 이용하면 모두 해결할 수 있다. 

try-with-resources 는 자원 사용이 끝나면 알아서 close() 메소드를 호출해준다. 

##### try-with-resource 예제

```java
static String firstLineOfFile(String path) {
    try (BufferedReader br = new BufferedReader(new FileReader(path))){ 
        return br.readLine(); 
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    throw new RuntimeException(); 
}
```

- 이 경우에 close() 와 readLine() 에서 둘 다 모두 예외가 발생한다고 했을때 close() 에서 발생한 예외는 숨겨지고 readLine() 에서
발생한 예외만 표시된다. 그렇다고해서 아예 없어지는게 아니라 getSuppressed() 메소드를 호출하면 볼 수 있다. 

- try-with-resources 에서도 catch 문을 넣을 수 있는데 이로인해서 close() 메소드 때문에 생길 수 있는 예외를 대비하기 위해서 
try 문을 중첩해서 넣는데 이를 하지 않아도 된다.   
