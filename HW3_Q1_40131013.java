import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
public class HW3_Q1_40131013 {

    abstract public class User {
        private String ID;
        private String password;
        private String firstName;
        private String lastName;
        private String nationalCode;
        private String birthYear;
        private String address;
        public User(String ID, String password, String firstName, String lastName, String nationalCode, String birthYear, String address) {
            this.ID = ID;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.nationalCode = nationalCode;
            this.birthYear = birthYear;
            this.address = address;
        }
        public String getID() {
            return ID;
        }
        public String getPassword() {
            return password;
        }
    }
    public class Student extends User{
        public Student(String ID, String password, String firstName, String lastName, String nationalCode, String birthYear, String address) {
            super(ID, password, firstName, lastName, nationalCode, birthYear, address);
        }
    }
    public class Staff extends User{
        public Staff(String ID, String password, String firstName, String lastName, String nationalCode, String birthYear, String address) {
            super(ID, password, firstName, lastName, nationalCode, birthYear, address);
        }
    }
    public class Professor extends User {
        public Professor(String ID, String password, String firstName, String lastName, String nationalCode, String birthYear, String address) {
            super(ID, password, firstName, lastName, nationalCode, birthYear, address);
        }
    }
    public class Manager extends User {
        private String libraryID;
        public Manager(String ID, String password, String firstName, String lastName, String nationalCode, String birthYear, String address, String libID) {
            super(ID, password, firstName, lastName, nationalCode, birthYear, address);
            this.libraryID = libID;
        }
        public String getLibraryID() {
            return libraryID;
        }
        public void addBook(String ID, String name, String author, String publishing, String year, String count, String categoryID, String libraryID,
                            ArrayList<Source> sources,ArrayList<Category> categories, ArrayList<Library> libraries) {
            Library tempLib = findLibrary(libraryID, libraries);
            if (tempLib == null) {
                System.out.println("not-found");
                return;
            }
            Book tempBook = (Book) findSource(ID, tempLib.sources);
            if (tempBook != null) {
                System.out.println("duplicate-id");
                return;
            }
            Category tempCat = findCategory(categoryID, categories);
            if (tempCat == null) {
                System.out.println("not-found");
                return;
            }
            Book temp = new Book(ID, name, author, publishing, year, count, categoryID, libraryID);
            sources.add(temp);
            tempLib.sources.add(temp);
            System.out.println("success");
        }
        public void addThesis(String ID, String name, String author, String supervisor, String year,
                              String categoryID, String libraryID, ArrayList<Source> sources,
                              ArrayList<Category> categories, ArrayList<Library> libraries){
            Library tempLib = findLibrary(libraryID, libraries);
            if (tempLib == null) {
                System.out.println("not-found");
                return;
            }
            Thesis tempThesis = (Thesis) findSource(ID, tempLib.sources);
            if (tempThesis != null) {
                System.out.println("duplicate-id");
                return;
            }
            Category tempCat = findCategory(categoryID, categories);
            if (tempCat == null) {
                System.out.println("not-found");
                return;
            }
            Thesis temp = new Thesis(ID, name, author, supervisor, year, categoryID, libraryID);
            sources.add(new Thesis(ID, name, author, supervisor, year, categoryID, libraryID));
            tempLib.sources.add(temp);
            System.out.println("success");
        }
        public void addGanjinehBook(String ID, String name, String author, String publishing, String year, String Donator,
            String categoryID, String libraryID, ArrayList<Source> sources, ArrayList<Category> categories, ArrayList<Library> libraries){
            Library tempLib = findLibrary(libraryID, libraries);
            if (tempLib == null) {
                System.out.println("not-found");
                return;
            }
            GanjinehBook tempGanjinehBook = (GanjinehBook) findSource(ID, tempLib.sources);
            if (tempGanjinehBook != null) {
                System.out.println("duplicate-id");
                return;
            }
            Category tempCat = findCategory(categoryID, categories);
            if (tempCat == null) {
                System.out.println("not-found");
                return;
            }
            GanjinehBook temp = new GanjinehBook(ID, name, author, publishing, year, Donator, categoryID, libraryID);
            sources.add(temp);
            tempLib.sources.add(temp);
        }
        public void addSellingBook(String ID, String name, String author, String publishing, String year, String count,
            String price, String discount, String categoryID, String libraryID, ArrayList<Source> sources,
            ArrayList<Category> categories, ArrayList<Library> libraries){
            Library tempLib = findLibrary(libraryID, libraries);
            if (tempLib == null) {
                System.out.println("not-found");
                return;        
            }
            SellingBook tempSellingBook = (SellingBook) findSource(ID, tempLib.sources);
            if (tempSellingBook != null) {
                System.out.println("duplicate-id");
                return;
            }
            Category tempCat = findCategory(categoryID, categories);
            if (tempCat == null) {
                System.out.println("not-found");
                return;
            }
            SellingBook temp = new SellingBook(ID, name, author, publishing, year, count, price, discount, categoryID, libraryID);
            sources.add(temp);
            tempLib.sources.add(temp);
    }
    public class Admin extends User implements studentAdder, libraryAdder, categoryAdder, staffAdder, userRemover {
        public Admin(String ID, String password, String firstName, String lastName, String nationalCode, String birthYear, String address) {
            super(ID, password, firstName, lastName, nationalCode, birthYear, address);
        }
       /*  @Override
        public void removeUser(String ID, ArrayList<User> users) {
            for (User user : users) {
                if (user.getID().equals(ID)) {
                    users.remove(user);
                    System.out.println("success");
                    return;
                }
            }
            System.out.println("not-found");
        } */
        @Override
        public void addStudent(String ID, String pass, String fName, String lName, String nCode,
                               String bYear, String address, ArrayList<User> users) {
            if (findUserWithoutPass(ID, users) != null) {
                System.out.println("duplicate-id");
                return;
            }
            users.add(new Student(ID, pass, fName, lName, nCode, bYear, address));
            System.out.println("success");
            return;
        }
        @Override
        public void addLibrary(String ID, String libName, String libYear, String libCapacity,
                      String libAddress, ArrayList<Library> libraries) {
        Library tempLib = findLibrary(ID, libraries);
        if (tempLib != null) {
            System.out.println("duplicate-id");
            return;
        }
        libraries.add(new Library(ID, libName, libYear, libCapacity, libAddress));
        System.out.println("success");
        return;
        }
        public void addCategory(String ID, String catName, String parentCatID, ArrayList<Category> categories){
            if (findCategory(ID, categories) != null){
                System.out.println("duplicate-id");
                return;
            }
            if (findCategory(parentCatID, categories) == null) {
                System.out.println("not-found");
                return;
            }
            int parentFlag = 0;
            for (Category category : categories) {
                if (category.getID().equals(parentCatID)) {
                    parentFlag = 1;
                }
                if (category.getID().equals(ID)) {
                    System.out.println("duplicate-id");
                    return;
                }
            }
            categories.add(new Category(catName, ID));
            System.out.println("success");
            return;
        }
        public void addStaff(String ID, String pass, String fName, String lName, String nCode,
                             String bYear, String address, String type, ArrayList<User> users) {
            if (findUserWithoutPass(ID, users) != null) {
                System.out.println("duplicate-id");
                return;
            }
            switch (type) {
                case "staff":
                    users.add(new Staff(ID, pass, fName, lName, nCode, bYear, address));
                    break;
                case "professor":
                    users.add(new Professor(ID, pass, fName, lName, nCode, bYear, address));
                    break;
            }
            System.out.println("success");
        }
        public void addManager(String ID, String pass, String fName, String lName, String nCode,
                               String bYear, String address, String libID, ArrayList<User> users, ArrayList<Library> libraries) {
            if (findUserWithoutPass(ID, users) != null) {
                System.out.println("duplicate-id");
                return;
            }
            if (findLibrary(libID, libraries) == null) {
                System.out.println("not-found");
                return;
            }
            users.add(new Manager(ID, pass, fName, lName, nCode, bYear, address, libID));
            System.out.println("success");
        }
        public void removeUser(String ID, ArrayList<User> users) {
            User tempUser = findUserWithoutPass(ID, users);
            if (tempUser == null) {
                System.out.println("not-found");
                return;
            }
            // age ketab ya payanname gharz gerefte bashe ya mablaghi bedehkar bashe nemitoone remove beshe
            //System.out.println("not-allowed");
            users.remove(tempUser);
            System.out.println("success");
            return;
            

        }

    }
abstract public class Source {
    private String ID;
    private String name;
    private String author;
    private String year;
    private String category;
    private String libraryID;
    public Source(String ID, String name, String author, String year, String category, String libraryID) {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.year = year;
        this.category = category;
        this.libraryID = libraryID;
    }
    public String getID() {
        return ID;
    }
    // getters and setters ?
}
public class Book extends Source {
    private String publishing;
    private String count;
    public Book (String ID, String name, String author, String publishing,  String year, String count, String category, String libraryID) {
        super(ID, name, author, year, category, libraryID);
        this.publishing = publishing;
        this.count = count;
    }
    // getter and setter ?
}
public class Thesis extends Source {
    private String supervisor;
    public Thesis (String ID, String name, String author, String supervisor, String year, String category, String libraryID) {
        super(ID, name, author, year, category, libraryID);
        this.supervisor = supervisor;
    }
    // getter and setter ?
}
public class GanjinehBook extends Book {
    private String donator;
    //private String publishing;
    public GanjinehBook(String ID, String name, String author, String publishing, String year, String donator, String category, String libraryID) {
        super(ID, name, author, publishing, year, "1", category, libraryID);
        this.donator = donator;
        //this.publishing = publishing;
    }
}
public class SellingBook extends Book {
    private String price;
    private String discount;
    public SellingBook(String ID, String name, String author, String publishing, String year, String count, String price, String discount, String category, String libraryID){
        super(ID, name, author, publishing, year, count, category, libraryID);
        this.price = price;
        this.discount = discount;
    }
}
public class Category {    
    private String name;
    private String ID;
    public Category(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }
    public String getID() {return ID;}
    public String getName() {return name;}
    public void setID(String iD) {ID = iD;}
    public void setName(String name) {this.name = name;}
}

public interface studentAdder {
    public void addStudent(String ID, String pass, String fName, String lName, String nCode,
                           String bYear, String address, ArrayList<User> users);
}
public interface libraryAdder {
    public void addLibrary(String ID, String libName, String libYear, String libCapacity,
                           String libAddress, ArrayList<Library> libraries);
}
public interface categoryAdder {
    public void addCategory(String ID, String catName, String parentCatID, ArrayList<Category> categories);
}
public interface staffAdder {
    public void addStaff(String ID, String pass, String fName, String lName, String nCode,
                         String bYear, String address, String type, ArrayList<User> users);
}
public interface userRemover {
    public void removeUser(String ID, ArrayList<User> users);
}
public interface managerAdder {
    public void addManager(String ID, String pass, String fName, String lName, String nCode,
                           String bYear, String address, String libID, ArrayList<User> users, ArrayList<Library> libraries);
}
public interface bookAdder {
    public void addBook(String ID, String name, String author, String publishing, String year, String count, String categoryID, String libraryID,
                        ArrayList<Source> sources, ArrayList<Category> categories, ArrayList<Library> libraries);
}
public interface thesisAdder {
    public void addThesis(String ID, String name, String author, String supervisor, String year, String categoryID,
                          String libraryID, ArrayList<Source> sources, ArrayList<Category> categories, ArrayList<Library> libraries);
}
public interface ganjinehBookAdder {
    public void addGanjinehBook(String ID, String name, String author, String publishing, String year, String donator, String categoryID,
                                String libraryID, ArrayList<Source> sources, ArrayList<Category> categories, ArrayList<Library> libraries);
}
public interface sellingBookAdder {
    public void addSellingBook(String ID, String name, String author, String publishing, String year, String count, String price, String discount, String categoryID,
                               String libraryID, ArrayList<Source> sources, ArrayList<Category> categories, ArrayList<Library> libraries);
}
public class Library {
    private final String ID;
    private final String name;
    private final String stablishedYear;
    private final String tableCapacity;
    private final String address;
    private ArrayList<Source> sources;
    public Library(String ID, String name, String stablishedYear, String tableCapacity, String address) {
        this.ID = ID;
        this.name = name;
        this.stablishedYear = stablishedYear;
        this.tableCapacity = tableCapacity;
        this.address = address;
        sources = new ArrayList<>();
    }
    public String getID() {return this.ID;}
    public String getTableCapacity() {return tableCapacity;}
}
public User findUser(String ID, String pass, ArrayList<User> users) {
    int flag = 0;
    User temp = null;
    for (User user : users) {
        if (user.getID().equals(ID)) {
            flag = 1;
            temp = user;
        }
    }
    if (flag == 0) {
        System.out.println("not-found");
        return null;
    }
    if (temp.getPassword().equals(pass)) {
        return temp;
    }
    System.out.println("invalid-pass");
    return null;
}
public Admin findAdmin(String ID, String pass, ArrayList<Admin> admins, ArrayList<User> users){
    User temp = findUser(ID, pass, users);
    if (temp == null) {
        return null;
    }
    if (!(temp instanceof Admin)) {
        System.out.println("permission-denied");
        return null;
    }
    Admin admin = (Admin) temp;
    return admin;
}
public Library findLibrary(String ID, ArrayList<Library> libraries) {
    for (Library library : libraries) {
        if (library.getID().equals(ID)) {
            return library;
        }
    }
    return null;
}
public Category findCategory(String catID, ArrayList<Category> categories){
    for (Category cat : categories) {
        if (catID.equals(cat.getID())) {
            return cat;
        }
    }
    return null;
}
public User findUserWithoutPass(String ID, ArrayList<User> users) {
    for (User user : users) {
        if (user.getID().equals(ID)) {
            return user;
        }
    }
    return null;
}
public Source findSource(String ID, ArrayList<Source> sources) {
    for (Source source : sources) {
        if (source.getID().equals(ID)) {
            return source;
        }
    }
    return null;
}
public Source findSourceInLibrary(String ID, String lidID, ArrayList<Library> libraries) {
    Library library = findLibrary(lidID, libraries);
    for (Source source : library.sources) {
        if (source.getID().equals(ID)) {
            return source;
        }
    }
    return null;
}



public static void main(String[] args) throws IOException {
    PrintStream out = new PrintStream(new FileOutputStream("kh.txt"));
    System.setOut(out);
    HW3_Q1_40131013 main = new HW3_Q1_40131013();
    ArrayList<Library> libraries = new ArrayList<Library>();
    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Source> sources = new ArrayList<>();
    Admin admin = main.new Admin("admin", "AdminPass", "admini", "adminZade", "admin1234", "farvardin", "adminAbad");
    users.add(admin);
    categories.add(main.new Category("null", "null"));
    String command;
    Scanner scanner = new Scanner(System.in);
    while(true){
        command = scanner.nextLine();
        if (command.equals("finish")) {
            break;
        }
        main.manageCommands(command, libraries, users, categories, sources);
    }
    scanner.close();
}
public void manageCommands(String command, ArrayList<Library> libraries, ArrayList<User> users, ArrayList<Category> categories, ArrayList<Source> sources){
    int firstLineIndex = command.indexOf("|");
    String[] commandTemp = command.split("#");
    String commandStart = commandTemp[0];
    int sharpIndex = command.indexOf('#');
    String ID;
    String[] args;
    args = command.split("\\|");
    if (commandStart.contains("search")) {
        switch (commandStart) {
            case "search":
                String search = command.replace("search#", "");
                //search(search, libraries);
                break;
            case "search-user":
                String searchPass = args[1], userSearch = args[2];
                //searchUser()
                break;
        }
    }
    else {
        ID = command.substring(sharpIndex + 1, firstLineIndex); // باگ داره
        User user = findUserWithoutPass(ID, users);
        if (user == null) {
            System.out.println("not-found");
            return;
        }
        if (!(user.getPassword().equals(args[1]))) {
            System.out.println("invalid-pass");
            return;
        }
        if (commandStart.contains("thesis") || commandStart.contains("book") || commandStart.contains("resource")) {
            if(findLibrary(args[8], libraries) == null){
                System.out.println("not-found"); // ??
                return;
            }
            if (!(user instanceof Manager)) {
                System.out.println("permission-denied");
                return;
            }
            Manager manager = (Manager) user;
            if (!(manager.getLibraryID().equals(args[8]))){
                System.out.println("permission-denied");
                return;
            }
            // check admin and his premissions
            switch (commandStart) {
                case "add-book":
                    manager.addBook(args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                                    args[9], sources, categories, libraries);
                    break;
                case "add-thesis":
                    manager.addThesis(args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                                      sources, categories, libraries);
                    break;
                case "add-ganjineh-book":

                    break;
                case "add-selling-book":
                    
                    break;
            }
        } else {
            if (!(user instanceof Admin)) {
                System.out.println("permission-denied");
                return;
            }
            Admin admin = (Admin) user;
            switch (commandStart) {
                case "add-library":
                    admin.addLibrary(args[2], args[3], args[4], args[5], args[6], libraries);
                    break;
                case "add-category":
                    admin.addCategory(args[2], args[3], args[4], categories);
                    break;
                case "add-student":
                    admin.addStudent(args[2], args[3], args[4], args[5], args[6], args[7], args[8], users);
                    break;
                case "add-staff":
                    admin.addStaff(args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], users);
                    break;
                case "add-manager":
                    admin.addManager(args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], users, libraries);
                    break;
                case "remove-user":
                    admin.removeUser(args[2], users);
                    break;
                
            }
        }
    }
}

}