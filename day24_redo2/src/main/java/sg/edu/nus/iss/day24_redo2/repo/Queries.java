package sg.edu.nus.iss.day24_redo2.repo;

public class Queries {
    
    public static final String SQL_INSERTORDERS = """
        insert into Orders (order_date, customer_name, ship_address, notes, tax)     
        values ( ?, ?, ?, ?, ?);
        """;

    public static final String SQL_INSERTORDERDETAILS = """
        insert into Order_details (order_id, product, unit_price, discount, quantity) 
        values (?, ?, ?, ?, ?);
        """;

    
}
