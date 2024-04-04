import java.io.*;
import java.util.Scanner;

public class DirectoryListingToXML {
    public static void main(String[] args) {
        // Nhập đường dẫn của thư mục từ người dùng
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn của thư mục: ");
        String directoryPath = scanner.nextLine();
        scanner.close();

        // Tạo một đối tượng File từ đường dẫn được nhập
        File directory = new File(directoryPath);

        // Kiểm tra xem đường dẫn tồn tại và là một thư mục
        if (directory.exists() && directory.isDirectory()) {
            // Tạo một đối tượng File để lưu trữ thông tin XML
            File xmlFile = new File("directory_tree.xml");

            // Ghi thông tin cây thư mục vào file XML
            try (PrintWriter writer = new PrintWriter(xmlFile)) {
                writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                writer.println("<directory_tree>");
                listFiles(directory, writer);
                writer.println("</directory_tree>");
                System.out.println("Đã tạo file XML thành công: " + xmlFile.getAbsolutePath());
            } catch (FileNotFoundException e) {
                System.out.println("Không thể tạo file XML.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Thư mục không tồn tại.");
        }
    }

    // Phương thức đệ quy để liệt kê các tệp và thư mục trong thư mục
    private static void listFiles(File directory, PrintWriter writer) {
        writer.println("<" + directory.getName() + ">");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file, writer);
                } else {
                    writer.println("\t<file>" + file.getName() + "</file>");
                }
            }
        }
        writer.println("</" + directory.getName() + ">");
    }
}