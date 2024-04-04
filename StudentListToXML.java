import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StudentListToXML {
    public static void main(String[] args) {
        // Tạo danh sách sinh viên
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 20, 3.8));
        students.add(new Student("Bob", 21, 3.5));
        students.add(new Student("Charlie", 19, 3.9));

        // Xuất danh sách sinh viên dưới dạng XML
        try {
            writeStudentsToXML(students, "students.xml");
            System.out.println("Xuất danh sách sinh viên thành công.");
        } catch (IOException | XMLStreamException e) {
            System.out.println("Đã xảy ra lỗi khi xuất danh sách sinh viên.");
            e.printStackTrace();
        }
    }

    // Phương thức để xuất danh sách sinh viên dưới dạng XML
    private static void writeStudentsToXML(List<Student> students, String filename)
            throws IOException, XMLStreamException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter(filename));

        writer.writeStartDocument("1.0");
        writer.writeStartElement("students");

        for (Student student : students) {
            writer.writeStartElement("student");

            writer.writeStartElement("name");
            writer.writeCharacters(student.getName());
            writer.writeEndElement();

            writer.writeStartElement("age");
            writer.writeCharacters(String.valueOf(student.getAge()));
            writer.writeEndElement();

            writer.writeStartElement("gpa");
            writer.writeCharacters(String.valueOf(student.getGpa()));
            writer.writeEndElement();

            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
}

class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}