package ra.entity;

import java.util.Scanner;

public class Category {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Category() {
    }

    public Category(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner, Category[] arrCategories, int index) {
        //Tính mã danh mục sản phẩm thêm mới
        this.catalogId = inputCatalogId(arrCategories, index);
        this.catalogName = inputCatalogName(scanner, arrCategories, index);
        this.description = inputDescription(scanner);
        this.catalogStatus = inputCatalogStatus(scanner);
    }

    public int inputCatalogId(Category[] arrCategories, int index) {
        int max;
        //Tìm catalogId lớn nhất trong mảng arrCategories
        if (index == 0) {
            max = 0;
        } else {
            max = arrCategories[0].getCatalogId();
            for (int i = 1; i < index; i++) {
                if (arrCategories[i].getCatalogId() > max) {
                    max = arrCategories[i].getCatalogId();
                }
            }
        }
        return max + 1;
    }

    public String inputCatalogName(Scanner scanner, Category[] arrCategories, int index) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            //Kiểm tra độ dài lớn nhất là 50
            if (catalogName.length() <= 50) {
                //Kiểm tra trùng lặp
                boolean isExist = false;//chưa tồn tại
                for (int i = 0; i < index; i++) {
                    if (arrCategories[i].getCatalogName().equals(catalogName)) {
                        isExist = true;//đã tồn tại
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogName;
                }
            } else {
                System.err.println("Mã danh mục có độ dài tối đa là 50, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả danh mục:");
        return scanner.nextLine();
    }

    public boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhâp vào trạng thái danh mục:");
        do {
            String catalogStatus = scanner.nextLine();
            if (catalogStatus.equals("true") || catalogStatus.equals("false")) {
                return Boolean.parseBoolean(catalogStatus);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.description, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
