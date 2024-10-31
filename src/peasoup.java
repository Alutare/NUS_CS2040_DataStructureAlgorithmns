import java.util.Scanner;

class Main {
  public static void main(String[] args) {

    // creates an object of Scanner
    Scanner input = new Scanner(System.in);
    int num_restaurant = input.nextInt(); 
    String result = "Anywhere is fine I guess";

    for (int i = 0; i < num_restaurant; i++) {
      int items_restaurant = input.nextInt();
      input.nextLine();
      String restaurantName = input.nextLine();

      boolean haspeasoup = false;
      boolean haspancakes = false;

      for(int j=0; j<items_restaurant; j++) {
        String menu_Item = input.nextLine();

        if (menu_Item.equals("pea soup")) {
          haspeasoup = true;
        }
        if (menu_Item.equals("pancakes")){
          haspancakes = true;
        }

      }
      if (haspeasoup == true && haspancakes == true){
        result = restaurantName;
        break;
      } 
    }
    
    System.out.println(result);
    input.close();


  }
}


