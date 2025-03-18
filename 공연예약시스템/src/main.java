import java.util.*;

class Seat{ // 좌석 한 개의 상태와 행동
    private String name;
    private String type;
    private int number;
    private boolean reserved;

    public Seat(String type, int number){ // 생성자 : 변수 기본값 초기화
        this.name = "---";
        this.type = type;
        this.number = number;
        this.reserved = false;
    }

    public boolean reserve(String name){
        if(!reserved){
            this.reserved = true;
            this.name = name;
            return true;
        }
        return false;
    }

    public boolean cancel(){
        if(reserved){
            this.reserved = false;
            this.name = "---";
            return true;
        }
        return false;
    }

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public int getNumber(){
        return number;
    }
    public boolean isReserved(){
        return reserved;
    }
}

class ReservationDB{// 좌석 타입 구분해서 배열 정의, 한 좌석을 나타내는 클래스 Seat의 객체들을 배열로 구성
    private Seat[] sSeats;
    private Seat[] aSeats;
    private Seat[] bSeats;

    public ReservationDB(){  // 생성자 : 배열 기본값 초기화
        sSeats = new Seat[10];
        for (int i = 0; i < sSeats.length; i++){
            sSeats[i] = new Seat("---", i+1);
        }

        aSeats = new Seat[15];
        for (int i = 0; i < aSeats.length; i++){
            aSeats[i] = new Seat("---", i+1);
        }

        bSeats = new Seat[20];
        for (int i = 0; i < bSeats.length; i++){
            bSeats[i] = new Seat("---", i+1);
        }
    }

    public Seat[] getSSeats() {
        return sSeats;
    }

    public Seat[] getASeats() {
        return aSeats;
    }
    public Seat[] getBSeats() {
        return bSeats;
    }
}


class ReservationService { // 비즈니스 로직 실행
    private ReservationDB db; // db 클래스 객체 선언 -> 좌석 타입 구분을 많이 해야하니까

    public ReservationService() {
        db = new ReservationDB();
    }

    public boolean reserveSeats(String name, String type, int number) {

        Seat[] seats;  // Seat classfication 을
        if (type.equals("S")) {
            seats = db.getSSeats();
        }
        else if (type.equals("A")) {
            seats = db.getASeats();
        }
        else if (type.equals("B")) {
            seats = db.getBSeats();
        }
        else {
            System.out.println("잘못된 좌석 타입입니다.");
            return false;
        }

        if (number < 0 || number > seats.length) {
            System.out.println("잘못된 좌석 번호입니다.");
            return false;
        }

        if (seats[number-1].reserve(name)) { // 배열의 한 객체 == Seat 클래스 의 reserve 메소드 이용
            return true;
        }
        else {
            System.out.println("이미 예약된 좌석입니다.");
            return false;
        }
    }

    public String searchSeats() { // 전체 조회 : 형식대로 문자열 출력시키기
        StringBuilder total = new StringBuilder();

        Seat[] sSeat = db.getSSeats();
        total.append("S>> ");
        for (int i = 0; i < sSeat.length; i++) {
            total.append(sSeat[i].getName()).append(" ");
        }
        total.append("\n");

        Seat[] aSeat = db.getASeats();
        total.append("A>> ");
        for (int i = 0; i < aSeat.length; i++) {
            total.append(aSeat[i].getName()).append(" ");
        }
        total.append("\n");

        Seat[] bSeat = db.getBSeats();
        total.append("B>> ");
        for (int i = 0; i < bSeat.length; i++) {
            total.append(bSeat[i].getName()).append(" ");
        }
        total.append("\n");

        total.append("<<<You have completed your search>>>");

        return total.toString();
    }

    public String searchSeat(String type) {
        StringBuilder sb = new StringBuilder();
        Seat[] seat ;
        if (type.equals("S")) {
            seat = db.getSSeats();
            sb.append("S>> ");
        }
        else if (type.equals("A")) {
            seat = db.getASeats();
            sb.append("A>> ");
        }
        else if (type.equals("B")) {
            seat = db.getBSeats();
            sb.append("B>> ");
        }
        else {
            return "잘못된 좌석 타입입니다.";
        }

        for(int i=0;i<seat.length;i++){
            sb.append(seat[i].getName()).append(" ");
        }
        return sb.toString();
    }

    public boolean cancelReservation(String name, String type) {
        Seat[] seats;
        if (type.equals("S")) {
            seats = db.getSSeats();
        }
        else if (type.equals("A")) {
            seats = db.getASeats();
        }
        else if (type.equals("B")) {
            seats = db.getBSeats();
        }
        else {
            System.out.println("잘못된 좌석 타입입니다.");
            return false;
        } //Seat classification

        for (int i = 0; i < seats.length; i++) {
            if (seats[i].getName().equals(name)) {
                seats[i].cancel();
                return true;
            }
        }
        return false;
    }
}

    public class main {
        public static void main(String[] args) {

            ReservationService service = new ReservationService();
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("예약(1), 조회(2), 취소(3), 종료(4)>>>>");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: //예약
                        System.out.println("Seat classification S(1), A(2), B(3)>>");
                        String reserveChoice = scanner.next();
                        String type;
                        if (reserveChoice.equals("1")) {
                            type = "S";
                        }
                        else if (reserveChoice.equals("2")) {
                            type = "A";
                        }
                        else if (reserveChoice.equals("3")) {
                            type = "B";
                        }
                        else {
                            System.out.println("잘못된 선택입니다.");
                            break;
                        }
                        System.out.println(service.searchSeat(type));

                        System.out.println("name>>");
                        String name = scanner.next();
                        System.out.println("number>>");
                        int number = scanner.nextInt();

                        if(service.reserveSeats(name, type, number))
                            break;
                        else{
                            System.out.println("잘못된 선택입니다.");
                            break;
                        }

                    case 2: //조회
                        System.out.println(service.searchSeats());
                        break;

                    case 3: //취소
                        System.out.println("Seat classification S(1), A(2), B(3)>>");
                        String cancelChoice = scanner.next();
                        String cancelType;
                        if (cancelChoice.equals("1")) {
                            cancelType = "S";
                        }
                        else if (cancelChoice.equals("2")) {
                            cancelType = "A";
                        }
                        else if (cancelChoice.equals("3")) {
                            cancelType= "B";
                        }
                        else {
                            System.out.println("잘못된 선택입니다.");
                            break;
                        }
                        System.out.println(service.searchSeat(cancelType));

                        System.out.println("name>>");
                        String cancelName = scanner.next();
                        if(service.cancelReservation(cancelName, cancelType))
                            break;
                        else {
                            System.out.println("잘못된 선택입니다.");
                            break;
                        }


                    case 4:
                        scanner.close();
                        return;
                }
            }
        }

    }


