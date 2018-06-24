package entities;

public interface IUser {
    public String getName();
    public boolean isAdmin();
    public boolean hasWishlist();
    public boolean wishlistContains(Product product);
    public void addToWishlist(Product product);
    public void removeFromWishlist(Product product);
    public void displayWishlist();
}
