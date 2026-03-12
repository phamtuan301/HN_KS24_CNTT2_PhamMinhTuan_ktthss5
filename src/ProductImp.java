import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductImp implements IProduct {
    protected List<Product> products = new ArrayList<>();
    @Override
    public void createProduct(Product product) throws InvalidProductException {
        boolean productExists =
                products.stream()
                        .anyMatch(p -> p.getId() == product.getId());
        if (productExists) {
            throw new InvalidProductException(
                    "ID " + product.getId() + " da ton tai");
        }
        products.add(product);
        System.out.println("Them san pham thanh cong");
    }
    @Override
    public Product getProduct(int id) throws InvalidProductException {
        Optional<Product> optionalProduct =
                products.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst();
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            System.out.println("----------");
            System.out.println("|ID: " + product.getId());
            System.out.println("|Name: " + product.getName());
            System.out.println("|Price: " + product.getPrice());
            System.out.println("|Quantity: " + product.getQuantity());
            System.out.println("|Category: " + product.getCategory());
            return product;
        } else {
            throw new InvalidProductException("Khong tim thay san pham");
        }
    }
    @Override
    public void updateProduct(Product product) throws InvalidProductException {
        Optional<Product> optionalProduct =
                products.stream()
                        .filter(p -> p.getId() == product.getId())
                        .findFirst();
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setQuantity(product.getQuantity());
            System.out.println("Cap nhat thanh cong");
        } else {
            throw new InvalidProductException(
                    "ID " + product.getId() + " khong ton tai");
        }
    }
    @Override
    public void deleteProduct(int id) throws InvalidProductException {
        boolean removed =
                products.removeIf(p -> p.getId() == id);
        if (!removed) {
            throw new InvalidProductException(
                    "Khong tim thay san pham de xoa");
        }

        System.out.println("Xoa san pham thanh cong");
    }
    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sach rong");
            return;
        }
        products.forEach(p -> {
            System.out.println(p);
        });
    }

    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Them san pham");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Cap nhat so luong");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Thoat");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Nhap ID:");
                    int id = scanner.nextInt();

                    System.out.println("Nhap name:");
                    String name = scanner.next();

                    System.out.println("Nhap price:");
                    double price = scanner.nextDouble();

                    System.out.println("Nhap quantity:");
                    int quantity = scanner.nextInt();

                    System.out.println("Nhap category:");
                    String category = scanner.next();

                    try {
                        createProduct(
                                new Product(id, name, price, quantity, category)
                        );
                    } catch (InvalidProductException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    printAllProducts();
                    break;
                case 3:
                    System.out.println("Nhap ID can update:");
                    int updateId = scanner.nextInt();

                    System.out.println("Nhap quantity moi:");
                    int newQuantity = scanner.nextInt();

                    try {
                        updateProduct(
                                new Product(updateId, "", 0, newQuantity, "")
                        );
                    } catch (InvalidProductException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID can xoa:");
                    int deleteId = scanner.nextInt();

                    try {
                        deleteProduct(deleteId);
                    } catch (InvalidProductException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

            }

        } while (choice != 5);
    }
}