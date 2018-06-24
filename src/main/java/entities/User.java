package entities;

import java.util.ArrayList;
import ui.WishlistDisplay;

public class User implements IUser{
    protected final String name;
    protected ArrayList<Product> wishlist; 
    
    public User(String name) {
        this.name = name;
        this.wishlist = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }
    
    @Override
    public boolean hasWishlist() {
        return (wishlist != null);
    }
 
    @Override
    public boolean wishlistContains(Product product) {
        return wishlist.contains(product);
    }    
    
    @Override
    public void addToWishlist(Product product) {
        if (wishlist == null) {
            wishlist = new ArrayList<>();
        } 
        
        if (!wishlist.contains(product)){
            wishlist.add(product);
        }
    }
    
    @Override
    public void removeFromWishlist(Product product) {
        wishlist.remove(product);
    }
    
    @Override
    public void displayWishlist() {
        if (wishlist != null && !wishlist.isEmpty()) {
           java.awt.EventQueue.invokeLater(() -> 
                   new WishlistDisplay(wishlist).setVisible(true));
        }
    }   
}
