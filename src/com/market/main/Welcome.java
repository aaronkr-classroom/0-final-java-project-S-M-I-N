package com.market.main;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.market.exception.CartException;

import com.market.bookitem.Book;
import com.market.cart.Cart;
import com.market.member.Admin;
import com.market.member.User;

public class Welcome {
	
	static final int NUM_BOOK = 3;
	static final int NUM_ITEM = 7;
//	static CartItem[] mCartItem = new CartItem[NUM_BOOK];
//	static int mCartCount = 0;
	static Cart mCart = new Cart();
	static User mUser;


	public static void main(String[] args) {
		
//		String[][] mBook = new String[NUM_BOOK][NUM_ITEM];
		Book[] mBookList = new Book[NUM_BOOK];
		
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.print("당신의 이름을 입력하세요: ");
		String userName = input.next();
		
		System.out.print("연락처를 입력하세요: ");
		int userPhone = input.nextInt(); // 숫자만 입력
		
		mUser = new User(userName, userPhone);
		
	String greeting = "Welcome to Shopping Mall!";
	String tagline = "Welcome to Book Market!!";
	
	boolean quit = false; //true 안되면 계속 반복
	
	while(!quit) {
		
	
	System.out.println("************************************************");
	System.out.println("\t" + greeting);
	System.out.println("\t" + tagline);
	
	menuIntro();
	
	/*
	 * System.out.println("************************************************");
	 * System.out.println("1. 고객 정보 확인하기 \t5. 바구니에 항목 추가하기");
	 * System.out.println("2. 장바구니 상품 목록 보기 \t6. 장바구니의 항목 수량 줄이기");
	 * System.out.println("3. 장바구니 비우기 \t \t7. 장바구니의 항목 삭제하기");
	 * System.out.println("4. 영수증 표시하기 \t \t8. 종료");
	 * System.out.println("************************************************");
	 */
	try {
	System.out.println("메뉴 번호를 선택하세요: ");
	int choice = input.nextInt(); // 숫자만 입력
	
		if(choice < 1 || choice > 9) {
			System.out.println("1부터 9까지의 숫자를 입력하세요.");
		} else {
			switch(choice) {
			case 1 : menuGuestInfo(userName, userPhone);
				break;
			case 2:
				menuCartItemList();
				break;
			case 3:
				menuCartClear();
				break;
			case 4:
				menuCartBill();
				break;
			case 5:
//				menuCartAddItem(mBook);
				menuCartAddItem(mBookList);
				break;
			case 6:
				menuCartRemoveCount();
				break;
			case 7:
				menuCartRemoveItem();
				break;
			case 8:
				menuCartExit();
				quit = true;
				break;
			case 9 :
				menuAdminLogin();
				break;
				} //switch 문 끝
			} //if-else 문 끝
		} //while 문 끝
	catch(CartException e) {
		System.out.println(e.getMessage());
		quit = true;
	}
	catch(Exception e) {
		System.out.println("올바르지 않은 메뉴 선택으로 종료합니다.");
		quit = true;
		}
	} //main 함수 끝
}
	
	
	/**
	 *  설명 : Print Menu
	 *  매개변수 : x
	 *  반환값 : x
	 */
	public static void menuIntro() {
		System.out.println("************************************************");
		System.out.println("1. 고객 정보 확인하기 \t\t5. 바구니에 항목 추가하기");
		System.out.println("2. 장바구니 상품 목록 보기 \t6. 장바구니의 항목 수량 줄이기");
		System.out.println("3. 장바구니 비우기 \t \t7. 장바구니의 항목 삭제하기");
		System.out.println("4. 영수증 표시하기 \t \t8. 종료");
		System.out.println("9. 관리자 로그인");
		System.out.println("************************************************");
	}
	
	/**
	 *  설명 : 고객 정보
	 *  매개변수 :
	 *  	-String name	고객 이름
	 *  	-int phone		휴대폰 번호
	 *  반환값 : x
	 */
	public static void menuGuestInfo(String name, int phone) {
		System.out.println("1. 현재 고객 정보: ");
//		System.out.println("이름: " + name + "\n연락처: " + phone);
//		Person person = new Person(name, phone);
//		System.out.println("이름 : " + person.getName() + " 연락처 : " + person.getPhone() );
		System.out.println("이름 " + mUser.getName() + "   연락처 " + mUser.getPhone());
	}
	
	/**
	 *  설명 : 장바구니 목록 보기
	 *  매개변수 :
	 *  	-String name	고객 이름
	 *  	-int phone		휴대폰 번호
	 *  반환값 : x
	 */
	public static void menuCartItemList() {
		/*
		 * System.out.println("2. 장바구니 상품 목록 보기: ");
		 * System.out.println("---------------------------------");
		 * System.out.println("   도서ID \t|   수량 \t|   합계"); for (int i=0; i <
		 * mCartCount; i++) { System.out.print("   " + mCartItem[i].getBookID() +
		 * " \t| "); System.out.print("   " + mCartItem[i].getQuantity() + " \t| ");
		 * System.out.print("   " + mCartItem[i].getTotalPrice());
		 * System.out.println("   "); }
		 * System.out.println("---------------------------------");
		 */
		if(mCart.mCartCount >= 0) {
			mCart.printCart();
		}
	}
	
public static void menuCartClear() throws CartException {
//	System.out.println("3. 장바구니 비우기");
	if(mCart.mCartCount == 0)
		throw new CartException("장바구니에 항목이 없습니다.");
//		System.out.println("장바구니에 항목이 없습니다.");
	else {
		System.out.println("장바구니의 모든 항목을 삭제하겠습니까? Y | N ");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		
		if(str.toUpperCase().equals("Y")) {
			System.out.println("장바구니의 모든 항목을 삭제했습니다.");
			mCart.deleteBook();
		}
	}
	}

public static void menuCartBill() throws CartException{
//	System.out.println("4. 영수증 표시하기");
	if(mCart.mCartCount == 0) 
		throw new CartException("장바구니에 항목이 없습니다.");
//		System.out.println("장바구니에 항목이 없습니다.");
	
	else {
		System.out.println("배송받을 분은 고객 정보와 같습니까? Y | N");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
	
	
	if(str.toUpperCase().equals("Y")) {
		System.out.println("배송지를 입력해주세요.");
		String address = input.nextLine();
		printBill(mUser.getName(), String.valueOf(mUser.getPhone()), address);
	}
	else {
		System.out.print("배송받을 고객명을 입력하세요.");
		String name = input.nextLine();
		System.out.print("배송받을 고객의 연락처를 입력하세요.");
		String phone = input.nextLine();
		System.out.print("배송받을 고객의 배송지를 입력하세요.");
		String address = input.nextLine();
		printBill(name,phone,address);
		}
	}
}
public static void printBill(String name, String phone, String address) {
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	String strDate = formatter.format(date);
	System.out.println();
	System.out.println("------------------배송받을 고객 정보------------------");
	System.out.println("고객명 : " + name + "   \t\t연락처 : " + phone);
	System.out.println("배송지 : " + address + "\t\t발송일 : " + strDate);
	
	mCart.printCart();
	
	int sum = 0;
	for(int i =0; i < mCart.mCartCount; i++)
		sum += mCart.mCartItem[i].getTotalPrice();
	
	System.out.println("\t\t\t주문 총금액 : " + sum + "원");
	System.out.println("------------------------------------------------");
	System.out.println();
}

public static void menuCartAddItem(Book[] bookList) {
//	System.out.println("5. 장바구니에 항목 추가하기: ");
	
	BookList(bookList);
	/*
	 * BookList(book); for(int i=0; i < NUM_BOOK; i++) { for(int j=0; j < NUM_ITEM;
	 * j++) { System.out.print(book[i][j] + " | "); System.out.println(" "); } }
	 */
	mCart.printBookList(bookList);
	
	boolean quit = false;
	
	while (!quit) {
		System.out.println("\n장바구니의 추가할 도서의 ID를 입력하세요.");
		
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		
		boolean flag = false;
		int numId = -1;
		
		for(int i=0; i < NUM_BOOK; i++) {
			if(str.equals(bookList[i].getBookId())) {
				numId = i;
				flag = true;
				break;
			}
		}
		while(!quit) {
		if(flag) {
			System.out.println("장바구니에 추가하겠습니까? Y|N");
			str = input.nextLine();
			if(str.toUpperCase().equals("Y")) {
				System.out.println(bookList[numId].getBookId() + " 도서가 장바구니에 추가되었습니다.");
				// 장바구니에 넣기
				if (!isCartInBook(bookList[numId].getBookId())) {
					mCart.insertBook(bookList[numId]);
				}
//					mCartItem[mCartCount++] = new CartItem(book[numId]);
			}
				quit = true;
		} 
		else System.out.println("다시 입력해 주세요.");
		}
	}
}
// 장바구니에 도서 추가하기
public static boolean isCartInBook(String bookId) {
	/*
	 * boolean flag = false; for (int i=0; i < mCartCount; i++) { if(bookId ==
	 * mCartItem[i].getBookID()) {
	 * mCartItem[i].setQuantity(mCartItem[i].getQuantity()+1); flag = true; } }
	 * return flag;
	 */
	return mCart.isCartInBook(bookId);
}

public static void menuCartRemoveCount() {
	System.out.println("6. 장바구니의 항목 수량 줄이기");
}

public static void menuCartRemoveItem() throws CartException {
//	System.out.println("7. 장바구니의 항목 삭제하기");
	if(mCart.mCartCount == 0)
		throw new CartException("장바구니에 항목이 없습니다.");
//		System.out.println("장바구니의 항목이 없습니다.");
	else {
		menuCartItemList();
		boolean quit = false;
		while(!quit) {
			System.out.println("장바구니의 삭제할 도서의 ID를 입력하세요.");
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			boolean flag = false;
			int numId = -1;
			
			for(int i =0; i < mCart.mCartCount; i++) {
				if(str.equals(mCart.mCartItem[i].getBookID())) {
					numId = i;
					flag = true;
					break;
				}
			}
			
			if(flag) {
				System.out.println("장바구니의 항목을 삭제하겠습니까? Y | N");
				str = input.nextLine();
				if(str.toUpperCase().equals("Y")) {
					System.out.println(mCart.mCartItem[numId].getBookID() + "장바구니에서 도서가 삭제되었습니다.");
					mCart.removeCart(numId);
				}
				quit = true;
			}
			else System.out.println("다시 입력해 주세요.");
		}
	}
}

public static void menuCartExit() {
	System.out.println("8. 종료");
	
}

public static void menuAdminLogin() {
	System.out.println("관리자 정보를 입력하세요.");
	
	Scanner input = new Scanner(System.in);
	System.out.println("아이디 : ");
	String adminId = input.next();
	
	System.out.println("비밀번호 : ");
	String adminPw = input.next();
	
	Admin admin = new Admin(mUser.getName(), mUser.getPhone());
	if(adminId.equals(admin.getId()) && adminPw.equals(admin.getPassword())) {
		System.out.println("이름 " + admin.getName() + "   연락처 " + admin.getPhone());
		System.out.println("아이디 " + admin.getId() + "   비밀번호 " + admin.getPassword());
	} else 
		System.out.println("관리자 정보가 일치하지 않습니다.");
}

public static void BookList(Book[] bookList) {
	
	bookList[0] = new Book("ISBN1234", "쉽게 배우는 JSP 웹 프로그래밍", 27000);
	bookList[0].setAuthor("송미영");
	bookList[0].setDescription("단계별로 쇼핑몰을 구현하며 배우는 JSP 웹 프로그래밍"); 
	bookList[0].setCategory("IT전문서");
	bookList[0].setReleaseDate("2018/10/08");
	
	bookList[1] = new Book("ISBN1235", "안드로이드 프로그래밍", 33000);
	bookList[1].setAuthor("우재남");
	bookList[1].setDescription("실습 단계별 명쾌한 멘토링!"); 
	bookList[1].setCategory("IT전문서");
	bookList[1].setReleaseDate("2022/01/22");
	
	bookList[2] = new Book("ISBN1236", "스크래치", 22000);
	bookList[2].setAuthor("고광일");
	bookList[2].setDescription("컴퓨팅 사고력을 키우는 블록 코딩"); 
	bookList[2].setCategory("컴퓨터입문");
	bookList[2].setReleaseDate("2019/06/10");
	
	
}


} //welcome 클래스 끝
