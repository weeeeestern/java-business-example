도서관 도서 대출 관리 시스템

- 도서관에는 여러 권의 책이 있으며, 사용자는 책을 대출하거나 반납할 수 있다.
책은 제목, 저자, 대출 여부(boolean) 속성을 가진다.
- 시스템 메뉴는 "대출", "반납", "검색", "종료" 가 있다.
- 대출 시 이미 대출된 책은 대출할 수 없도록 해야 한다.
- 반납 시 없는 책을 반납하려 하면 오류 메시지를 출력한다.
검색은 현재 도서 목록을 출력한다.


실행 예시
```java
run: 
Loan(1), Return(2), Search(3), Exit(4) >>>> 1
Enter book title: Clean Code
<< Book 'Clean Code' is successfully loaned out! >>

Loan(1), Return(2), Search(3), Exit(4) >>>> 3
Available books:
- The Pragmatic Programmer
- Refactoring
- Domain-Driven Design

Loan(1), Return(2), Search(3), Exit(4) >>>> 2
Enter book title: Clean Code
<< Book 'Clean Code' has been returned. >>

Loan(1), Return(2), Search(3), Exit(4) >>>> 4

```
