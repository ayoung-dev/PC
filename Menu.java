package pc;

public class Menu {
	private String type;		//종류 필드
	private String product;		//품명 필드
	private String price; 		//가격 필드
	private String count;		//재고량 필드
	private String date;		//입고일 필드
	private String remarks;		//비고 필드
	
	//"종류", "품명", "가격", "재고량", "입고일", "비고" 
	public Menu(String type, String product, String price, String count, String date, String remarks){ 
		this.type=type;
		this.product=product;
		this.price=price;
		this.count=count;
		this.date=date;
		this.remarks=remarks;
	}

	public String getType() {
		return type;
	}
	public String getProduct() {
		return product;
	}
	public String getPrice() {
		return price;
	}
	public String getCount() {
		return count;
	}
	public String getDate(){ 			
		return date;
	}
	public String getRemarks(){ 			
		return remarks;
	}
}
