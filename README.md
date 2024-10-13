# phoneBook
package phoneBook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Contact {
    String name;
    String phoneNumber;

    Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

public class PhoneBook {
    private ArrayList<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phoneNumber) {
        contacts.add(new Contact(name, phoneNumber));
    }

    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println("Name: " + contact.name + ", Phone Number: " + contact.phoneNumber);
        }
    }

    private int binarySearch(String name) {
        int start = 0;
        int end = contacts.size() - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            Contact contact = contacts.get(middle);
            int comparison = contact.name.compareTo(name);
            if (comparison < 0) {
                start = middle + 1;
            } else if (comparison > 0) {
                end = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public void searchContact(String name) {
        sortContacts();
        int index = binarySearch(name);
        if (index != -1) {
            Contact contact = contacts.get(index);
            System.out.println("Found: Name: " + contact.name + ", Phone Number: " + contact.phoneNumber);
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void deleteContact(String name) {
        contacts.removeIf(contact -> contact.name.equals(name));
    }

    public void updateContact(String oldName, String newName, String newPhoneNumber) {
        for (Contact contact : contacts) {
            if (contact.name.equals(oldName)) {
                contact.name = newName;
                contact.phoneNumber = newPhoneNumber;
                break;
            }
        }
    }

    public void sortContacts() {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.name.compareTo(c2.name);
            }
        });
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Delete Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Sort Contacts");
            System.out.println("6. Search Contact");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice == 1) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter phone number: ");
                String phoneNumber = scanner.nextLine();
                phoneBook.addContact(name, phoneNumber);
            } else if (choice == 2) {
                phoneBook.displayContacts();
            } else if (choice == 3) {
                System.out.print("Enter name of contact to delete: ");
                String name = scanner.nextLine();
                phoneBook.deleteContact(name);
            } else if (choice == 4) {
                System.out.print("Enter current name of contact to update: ");
                String oldName = scanner.nextLine();
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new phone number: ");
                String newPhoneNumber = scanner.nextLine();
                phoneBook.updateContact(oldName, newName, newPhoneNumber);
            } else if (choice == 5) {
                phoneBook.sortContacts();
                System.out.println("Contacts sorted.");
            } else if (choice == 6) {
                System.out.print("Enter name to search: ");
                String name = scanner.nextLine();
                phoneBook.searchContact(name);
            } else if (choice == 7) {
                break;
            }
        }
        scanner.close();
    }
}
