//ShoppingCart.java

import java.util.Scanner;

public class ShoppingCart {
    ProductList Wishlist;
    public ShoppingCart() {
        Wishlist = new ProductList();
    }
    public void addToWishlist(int i,int quantity,ProductList inv) {
        if (inv.productList.isEmpty()){
            System.out.println("Inventory is empty");
        }else if (i<inv.productList.size()) {
            Product P=inv.productList.get(i);
            if (!Wishlist.productList.contains(P)) {
                if(P.getQuantity()<quantity) {
                    System.out.println("Not enough items of this product in inventory");
                } else {
                    if (P instanceof Electronics) {
                        Electronics P1=new Electronics((Electronics)P);
                        P1.updateQuantity(quantity);
                        Wishlist.addProduct(P1);
                    } else if (P instanceof Book) {
                        Book P1 = new Book((Book)P);
                        P1.updateQuantity(quantity);
                        Wishlist.addProduct(P1);
                    } else {
                        Clothing P1 = new Clothing((Clothing)P);
                        P1.updateQuantity(quantity);
                        Wishlist.addProduct(P1);
                    }
                }
            } else {
                System.out.println("This item is already in your wishlist");
            }
        } else {
            System.out.println("Inventory only has "+inv.productList.size()+" items");
        }

    }
    public void removeFromWishlist(int i) {
        if (i<Wishlist.productList.size()) {
            Wishlist.removeProduct(Wishlist.productList.get(i));
        } else {
            System.out.println("Product is not in your wishlist");
        }
    }
    public void updateQuantity(int i, int Q) {
        Product P=Wishlist.productList.get(i);
        if (Wishlist.productList.contains(P)) {
            if (Q>0){
                P.updateQuantity(Q);
            } else {
                System.out.println("Invalid quantity input");
            }
        }
    }
    public void listDisplay(ProductList inv) {
        if (Wishlist.productList.isEmpty()) {
            System.out.println("Your Shopping cart is empty");
        } else {
            System.out.println("List of products in your shopping cart: ");
            for (int i=0;i<Wishlist.productList.size();i++) {
                Product P = Wishlist.productList.get(i);
                System.out.println(i+" - Name: "+P.getName()+" Quantity: "+P.getQuantity()+" Total Price: "+P.getQuantity()*P.getPrice());
                if (!inv.idSearch(P.getId())) {
                    System.out.println("This product has been removed from inventory, it will be immediately removed from your shopping cart");
                    this.removeFromWishlist(i);
                }
            }
        }
    }
    public double order(int i,ProductList inv,Order orderList, Scanner myObj) {
        if (Wishlist.productList.isEmpty()) {
            System.out.println("Your Shopping cart is empty");
            return 0;
        } else if (i<Wishlist.productList.size()) {
            Product P = Wishlist.productList.get(i);
            System.out.println("Ordered Product : "+P.getName());
            if (inv.idSearch(P.getId())) {
                Product Pi=inv.getProductById(P.getId());
                if (Pi.getQuantity()<P.getQuantity()) {
                    System.out.println("This product was placed in your cart with a quantity of "+P.getQuantity());
                    System.out.println("It appears that the inventory currently only has "+Pi.getQuantity()+" items of this product");
                    System.out.println("Enter the new quantity you want to order: ");
                    int c;
                    do {
                        System.out.println("Choose new quantity");
                        c= myObj.nextInt();
                        myObj.nextLine();
                    } while (c>Pi.getQuantity());
                    updateQuantity(i, c);
                    P.updateStockState();
                }
                Pi.updateQuantity(Pi.getQuantity()-P.getQuantity());
                Pi.updateStockState();
                orderList.addTransaction(P);
                Wishlist.removeProduct(P);
                if (P.getQuantity()>5) {
                    System.out.println("Since you bought more than 5 items of this product you'll get a 20% reduction");
                    return(0.8*P.getPrice()*P.getQuantity());
                } else {
                    return(P.getPrice()*P.getQuantity());
                }
                
            } else {
                System.out.println("This product has been removed from inventory, it will be immediately removed from your shopping cart");
                this.removeFromWishlist(i);
                return(0);
            }
        } else {
            System.out.println("Invalid product number.");
            return(0);
        }
    }


}
