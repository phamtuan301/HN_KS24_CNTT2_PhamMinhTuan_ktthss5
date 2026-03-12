public interface IProduct {
    void createProduct(Product product) throws InvalidProductException;
    Product getProduct(int id) throws InvalidProductException;
    void updateProduct(Product product) throws InvalidProductException;
    void deleteProduct(int id) throws InvalidProductException;
}