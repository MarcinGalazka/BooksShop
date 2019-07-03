import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Library library =new Library();

        library.createTable();
//
        library.deleteBooks();
//        System.out.println("Dodawanie");
        library.addBook("Nóż","Jo Nesbo", (float) 24.76,5);
        library.addBook("365 dni","Lipińska Blanka", (float) 27.93,4);
        library.addBook("Nieodgadniona","Remigiusz Mróz", (float) 31.99,2);
        library.addBook("Zranić marionetkę","Katarzyna Grochola", (float) 30.00,6);
        library.addBook("Pacjentka","Michaelides Alex", (float) 29.99,3);
        library.addBook("Karpie bijem","Andrzej Pilipiuk", (float) 24.49,5);
        library.addBook("Ja, inkwizytor. Przeklęte krainy","Jacek Piekara", (float) 27.99,8);
//

//        library.showBooks();
////
        library.setPrice("Nóż", BigDecimal.valueOf(22.15));
        library.setDiscount("365 dni",20);
        library.changeBooksNumber("Nieodgadniona",-2);
        library.changeBooksNumber("Pacjentka",+10);
//        library.showBooks();
//
        System.out.println("znaleziono po tytule");
        library.findByTitle("odga");
//
//        System.out.println(" ");
        System.out.println("znaleziono po autorze");
        library.findByAuthor("Pil");
//        library.showBooks();
    }
}
