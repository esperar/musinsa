# [MUSINSA] Java(Kotlin) Backend Engineer - 과제

### Getting Start

```bash
# git clone
git clone https://github.com/esperar/musinsa.git

# gradle build, + compile querydsl도 해주셔야합니다.
./gradle build
```

<br>

1. 서버를 실행한 후 /h2-console로 접속후 `user: root, password: 1234`로 접속합니다.
2. 과제에 작성되어 있는 data들을 insert 합니다. [insert data query](https://github.com/esperar/musinsa/blob/master/src/main/resources/insert.txt)
3. data insert 이후 api 명세서에 따라 http 요청을 통해 테스트를 진행합니다. (POSTMAN 권장) [api docs](https://github.com/esperar/musinsa/blob/master/docs/apidocs.md)

> h2 memory 저장소를 사용하기 때문에 data.sql를 사용하지 않았습니다. 직접 insert를 해야합니다.

<br>

## Project Architecture


### stack

- Kotlin
- Spring Boot
- H2 DataBase

### architecture layer

- **application(service, service impl)**: 도메인 비즈니스 로직이 작성되어 있습니다.
- **persistence(entity, mapper, repository, adapter, port)**: persistence 영역의 코드와 DB와 연결된 Entity가 작성되어 있습니다. port를 통해서 application 영역과 통신됩니다.
- **presentation(controller)**: client와 API 통신을 위한 Controller가 작성되어 있습니다.
- **domain(domain class**): application 영역에서 조작되는 entity 모델의 도메인 클래스입니다.
 
`controller - service(impl) - port(adpater) - repository`로 연결되는 구조를 가집니다. 

각 서비스는 Command, Query(Write, Read) 기능이 분리되어있습니다.

<br>

> Mocking Test는 Create, Update Clothing 부분의 테스트가 작성되어 있습니다.
