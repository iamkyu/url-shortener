# URL Shortning Service
![uml](https://user-images.githubusercontent.com/13076271/59759114-5b0ae300-92ca-11e9-8167-25473c8254be.jpg)

1. 미리 생성 된 8자리 키들을 데이터베이스에 저장
2. 각 애플리케이션 서버에서는 데이터베이스에서 일부 키를 조회하여 인메모리DB에 적재
    - 인메모리DB에 적재되는 시점에 적재된 키는 사용된 것으로 처리
3. 인메모리DB에서 키를 하나 꺼내와 사용자가 단축 요청한 URL과 함께 NoSQL 에 저장