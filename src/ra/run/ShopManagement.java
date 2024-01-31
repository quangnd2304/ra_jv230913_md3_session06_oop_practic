package ra.run;

import ra.entity.Category;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    Category[] arrCategoris = new Category[100];
    Product[] arrProduct = new Product[100];
    int indexCatalog = 0;
    int indexProduct = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShopManagement shopManagement = new ShopManagement();
        //In menu
        do {
            System.out.println("**************SHOP MENU****************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.displayCategoryMenu(scanner, shopManagement);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-3");
            }
        } while (true);
    }

    public void displayCategoryMenu(Scanner scanner, ShopManagement shopManagement) {
        boolean isExit = true;
        do {
            System.out.println("*************CATEGORY MENU********************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    shopManagement.inputListCategories(scanner);
                    break;
                case 2:
                    shopManagement.displayListCategories();
                    break;
                case 3:
                    shopManagement.updateCatalog(scanner);
                    break;
                case 4:
                    shopManagement.deleteCatalog(scanner);
                    break;
                case 5:
                    shopManagement.updateCatalogStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public void inputListCategories(Scanner scanner) {
        System.out.println("Nhập số danh mục cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            //1. Khởi tạo phần tử index của mảng arrCategorie là 1 đối tượng Category để nhập dữ liệu
            arrCategoris[indexCatalog] = new Category();
            //2. Gọi inputData để nhập dữ liệu cho phần tử indexCatalog
            arrCategoris[indexCatalog].inputData(scanner,arrCategoris, indexCatalog);
            //3. Tăng indexCatalog
            indexCatalog++;
        }
    }

    public void displayListCategories() {
        for (int i = 0; i < indexCatalog; i++) {
            arrCategoris[i].displayData();
        }
    }

    public void updateCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = findIndexCatlogById(catalogId);
        if (indexUpdate >= 0) {
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên danh mục");
                System.out.println("2. Cập nhật mô tả danh mục");
                System.out.println("3. Cập nhật trạng thái danh mục");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên danh mục cần cập nhật:");
                        arrCategoris[indexUpdate].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào mô tả danh mục cần cập nhật:");
                        arrCategoris[indexUpdate].setDescription(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Nhập vào trạng thái danh mục cần cập nhật:");
                        arrCategoris[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    default:
                        isExit = false;

                }
            } while (isExit);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public int findIndexCatlogById(int catalogId) {
        for (int i = 0; i < indexCatalog; i++) {
            if (arrCategoris[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public void deleteCatalog(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexDelete = findIndexCatlogById(catalogId);
        if (indexDelete >= 0) {
            for (int i = 0; i < indexCatalog; i++) {
                if (i >= indexDelete) {
                    arrCategoris[i] = arrCategoris[i + 1];
                }
            }
            indexCatalog--;
        } else {
            System.err.println("Mã danh muc không tồn tại");
        }
    }

    public void updateCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        int indexUpdateStatus = findIndexCatlogById(catalogId);
        if (indexUpdateStatus >= 0) {
            arrCategoris[indexUpdateStatus].setCatalogStatus(!arrCategoris[indexUpdateStatus].isCatalogStatus());
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }
}
