쇼핑몰 주문 시스템

- 쇼핑몰에서는 사용자가 상품을 장바구니에 담고 주문을 완료할 수 있다.
- 상품은 이름, 가격, 재고 수량을 가진다.
- 시스템 메뉴는 "상품 추가", "장바구니 조회", "주문 완료", "종료" 가 있다.
- 주문 시 재고가 부족하면 주문할 수 없다.
- 주문 완료 시 재고가 감소한다.


실행 예시
```java
run:
Add to Cart(1), View Cart(2), Complete Order(3), Exit(4) >>>> 1
Available Products:
1. Laptop - $1000 (Stock: 5)
2. Phone - $800 (Stock: 10)
Select product number >> 1
Enter quantity >> 2
<< 2 Laptop(s) added to cart! >>

Add to Cart(1), View Cart(2), Complete Order(3), Exit(4) >>>> 2
Current Cart:
- Laptop x2 ($2000)

Add to Cart(1), View Cart(2), Complete Order(3), Exit(4) >>>> 3
<< Order completed successfully! >>
Stock Update: Laptop - Remaining 3

Add to Cart(1), View Cart(2), Complete Order(3), Exit(4) >>>> 4

```