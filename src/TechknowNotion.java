import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

// class for displaying
class Preview {
    public List<Object> lst1;

    public Preview(List<Object> lst1) {
        this.lst1 = lst1;
    }

    void set_view(ArrayList<Object> lst1) {
        this.lst1 = lst1;
    }

    //displays the list
    void view2() {
        System.out.println(this.lst1.get(0) + "\t       " + this.lst1.get(1) +
                "\t\t\t   " + this.lst1.get(2) + "\t\t\t\t  " + this.lst1.get(3));
    }
}

class FileHandling {
    static List<String[]> csvReading(String filename) throws IOException {
        CSVReader cr = new CSVReader(new FileReader(filename));
        ArrayList<String[]> first_list = new ArrayList<>();
        String[] second_list;
        while ((second_list = cr.readNext()) != null) {
            first_list.add(second_list);
        }
        cr.close();
        return first_list;
    }

    static void csvWriting(String filename, List<String[]> queue_list, String[] arr, List<Tasks> sort_list) throws IOException {
        CSVWriter cw = new CSVWriter(new FileWriter(filename));
        queue_list.add(arr);
        for (int i = 0; i < sort_list.size(); i++) {
            queue_list.add(new String[]{sort_list.get(i).module_name, sort_list.get(i).type,
                    String.valueOf(sort_list.get(i).no_task), String.valueOf(sort_list.get(i).priority)});
        }
        cw.writeAll(queue_list);
        cw.close();
    }

    static void csvWriting(String filename, List<String[]> first_list, String[] arr) throws IOException {
        CSVWriter cw = new CSVWriter(new FileWriter(filename));
        first_list.add(arr);
        cw.writeAll(first_list);
        cw.close();
    }

    static void csvWriting(String filename, List<String[]> collect_list) throws IOException {
        CSVWriter cw = new CSVWriter(new FileWriter(filename));
        cw.writeAll(collect_list);
        cw.close();
    }
}

class OtherFunctions {

    // iterates through the list
    void iteration(List<String[]> ln) {
        System.out.println("Modules\t\t\tType\t\t\tNo. of Tasks\t\t\tPriority");
        for (int x = 0; x < ln.size(); x++) { // iterate per row
            List<Object> list4;
            if (x == 0) {
                continue; // skip header
            }
            else {
                list4 = Arrays.asList(ln.get(x));
                Preview i = new Preview(list4);
                i.view2();
            }
        }
    }

    // checks if .csv file is empty
    Boolean is_empty(String x) throws IOException {
        CSVReader cr = new CSVReader(new FileReader(x));
        ArrayList<String[]> first_list = new ArrayList<>();
        String[] second_list;
        while ((second_list = cr.readNext()) != null) {
            first_list.add(second_list);
        }

        return first_list.size() != 1;
    }

    // asks if user wants to go back to menu of the current location
    void try_again(String ques, String msg, int loc){
        while (true){
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.printf("%s YES(1) or NO(0): ", ques);
                int choice = Integer.parseInt(br.readLine());
                if (choice == 0){
                    restart();
                }
                else if (choice == 1){
                    if (loc == 1){
                        TechknowNotion.input_module_details();
                    } else if (loc == 2){
                        TechknowNotion.view_module();
                    } else if (loc == 3){
                        TechknowNotion.schedule_module();
                    } else if (loc == 4){
                        TechknowNotion.get_a_module();
                    }
                    else {
                        System.out.println("Invalid input.");
                    }
                }
                else {
                    System.out.println("Invalid input.");
                }
            } catch (IOException | InterruptedException | NumberFormatException e ) {
                System.out.println(msg);
            }
        }
    }

    // asks if user want to go back to main menu
    void restart() {
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("\nGo back to main menu? YES(1) or NO(0): ");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 0) {
                    System.exit(0);
                }
                else if (choice == 1) {
                    TechknowNotion.main2();
                }
                else {
                    System.out.println("Invalid input.");
                }
            }
            catch (IOException | InterruptedException | NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    // checks if input is a positive integer
    int input_validation(String msg){
        while (true){
            try {
                System.out.print(msg);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int i = Integer.parseInt(br.readLine());
                if (i < 1){
                    System.out.println("Invalid input.\n");
                }
                else {
                    return  i;
                }

            } catch (InputMismatchException | IOException | NumberFormatException e){
                System.out.println("Invalid input.\n");
            }
        }
    }
}

// a tasks class that shows its attributes needed for comparator comparing
class Tasks {
    String module_name;
    String type;
    int no_task;
    int priority;

    public Tasks(String module_name, String type, Integer no_task, Integer priority) {
        super();
        this.module_name = module_name;
        this.type = type;
        this.no_task = no_task;
        this.priority = priority;
    }

    public int getNo_task() {
        return no_task;
    }

    public int getPriority() {
        return priority;
    }
}


// main class
public class TechknowNotion {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // clear the console screen
    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    //main menu
    public static void main2() throws IOException, InterruptedException {
        clear();
        System.out.println("\tTECHKNOW'S NOTION\n");
        System.out.println("1. Input Module Details\n2. View Modules\n3. Schedule Modules\n4. Get a Module\n5. Exit\n");
        while (true) {
            try {
                System.out.print("Please enter a number: ");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 1) {
                    input_module_details();
                }
                else if (choice == 2) {
                    view_module();
                }
                else if (choice == 3) {
                    schedule_module();
                }
                else if (choice == 4) {
                    get_a_module();
                }
                else if (choice == 5) {
                    System.exit(0);
                }
                else {
                    System.out.println("Invalid input.");
                }
            }
            catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    // Menu 1
    static void input_module_details() throws IOException, InterruptedException {
        clear();
        System.out.println("\tINPUT MODULE DETAILS\n");
        while (true) {
            OtherFunctions p = new OtherFunctions();
            System.out.print("Modules: ");

            String module_name = br.readLine();
            System.out.print("Type: ");
            String type = br.readLine();
            int tasks = p.input_validation("No. of Tasks: ");
            int priority = p.input_validation("Priority: ");

            //read file all_modules then write the updated to same file
            FileHandling.csvWriting("all_modules.csv",FileHandling.csvReading("all_modules.csv"),
                    new String[]{module_name,type,String.valueOf(tasks), String.valueOf(priority)});

            //read file queue then write the updated to same file
            FileHandling.csvWriting("queue.csv",FileHandling.csvReading("queue.csv"),
                    new String[]{module_name,type,String.valueOf(tasks), String.valueOf(priority)});

            System.out.println("---Module Details Saved---");
            p.try_again("Make new module details?", "Invalid input.", 1);
        }
    }

    // Menu 2
    static void view_module() throws IOException, InterruptedException{
        clear();
        System.out.println("\tVIEW MODULES\n");
        System.out.println("1. Completed Modules\n2. All Modules\n3. Go Back\n");
        while (true) {
            try {
                System.out.print("Please enter a number: ");
                int choice = Integer.parseInt(br.readLine());
                if (choice < 1 && choice > 3) {
                    System.out.println("Invalid input.");
                } else {
                    OtherFunctions p = new OtherFunctions();

                    if (choice == 1) {
                        clear();
                        System.out.println("\t\t\t\t\tVIEW MODULES");
                        System.out.println("\t\t\t\tLIST OF ALL COMPLETED MODULES\n");

                        if (p.is_empty("completed_modules.csv")) {
                            // reads completed_modules and adds to a list
                            p.iteration(FileHandling.csvReading("completed_modules.csv"));
                            p.try_again("View module again?", "Invalid input.", 2);
                        } else {
                            System.out.println("\t\t\t\t   No completed module yet");
                            p.try_again("View module again?", "Invalid input.", 2);
                        }
                    }

                    else if (choice == 2){
                        clear();
                        System.out.println("\t\t\t\t\tVIEW MODULES");
                        System.out.println("\t\t\t\t\tALL MODULES\n");
                        if (p.is_empty("all_modules.csv")) {
                            // reads all_modules and adds to a list
                            p.iteration(FileHandling.csvReading("all_modules.csv"));
                            p.try_again("View module again?", "Invalid input.", 2);
                        } else {
                            System.out.println("\t\t\t\t\tNo module yet");
                            p.try_again("View module again?", "Invalid input.", 2);
                        }
                    }

                    else if (choice == 3){
                        main2();
                        p.restart();
                    }
                    else {
                        System.out.println("Invalid input.");
                    }
                }
            }
            catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

    static void schedule_module() throws IOException, InterruptedException {
        // Goes to menu 3
        clear();
        OtherFunctions p = new OtherFunctions();
        System.out.println("\tSCHEDULE MODULES\n");
        System.out.println("1. View Updated Schedule of Techknow\n2. Go Back\n");
        while (true) {
            try {
                System.out.print("Please enter a number: ");
                int choice = Integer.parseInt(br.readLine());
                //error
                if ((choice  <1) && (choice > 2)) {
                    System.out.println("Invalid input.");
                } else {
                    if (choice == 1) {
                        clear();
                        System.out.println("\t\t\t\t\tSCHEDULE MODULES");
                        System.out.println("\t\t\t\t   CREATE SCHEDULE OF MODULES\n");
                        if (p.is_empty("queue.csv")) { // may error
                            List<String[]> first_list = FileHandling.csvReading("queue.csv");
                            List<Tasks> sort_list = new ArrayList<>();
                            List<String> indiv_list;
                            List<List<String>> collect_list = new ArrayList<>();
                            List<String[]> queue_list = new ArrayList<>();

                            String[] split1,split2,split3,split4;

                            for (int i = 0; i < first_list.size(); i++) {
                                split1 = first_list.get(i)[0].split("\\t", 0);
                                split2 = first_list.get(i)[1].split("\\t", 0);
                                split3 = first_list.get(i)[2].split("\\t", 0);
                                split4 = first_list.get(i)[3].split("\\t", 0);
                                indiv_list = Arrays.asList(split1[0],split2[0],split3[0],split4[0]);
                                collect_list.add(indiv_list);
                            }

                            for (int i = 1; i < collect_list.size(); i++) {
                                sort_list.add(new Tasks(collect_list.get(i).get(0),
                                        collect_list.get(i).get(1),
                                        Integer.parseInt(collect_list.get(i).get(2)),
                                        Integer.parseInt(collect_list.get(i).get(3))));
                            }
                            //sorts list according to size and priority
                            Comparator<Tasks> comp = Comparator.comparing(Tasks::getNo_task);
                            Collections.sort(sort_list, comp);
                            Comparator<Tasks> comp2 = Comparator.comparing(Tasks::getPriority);
                            Collections.sort(sort_list, comp2);

                            FileHandling.csvWriting("queue.csv",queue_list,
                                    new String[]{"Modules","Type","No. of Tasks", "Priority"},sort_list);

                            p.iteration(FileHandling.csvReading("queue.csv"));
                            p.try_again("Go back to schedule module?", "Invalid input.", 3);
                        }
                    } else if (choice == 2){
                        main2();
                        p.restart();
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
            }
            catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
    }

    static void get_a_module() throws IOException, InterruptedException {
        OtherFunctions p = new OtherFunctions();
        clear();
        System.out.println("\t\t\t\t\tGET A MODULE\n");
        int choice;
        if (p.is_empty("queue.csv")) {
            List<String[]> first_list = FileHandling.csvReading("queue.csv");
            ArrayList<String[]> collect_list = new ArrayList<>();
            ArrayList<String[]> collect_list2 = new ArrayList<>();

            p.iteration(first_list);
            while (true){
                try {
                    System.out.print("Mark as complete the topmost module? YES(1) or NO(0): ");
                    choice = Integer.parseInt(br.readLine());
                    if (choice < 0 && choice > 1) {
                        System.out.println("Invalid input.");
                    } else {
                        if (choice == 1){  //writing to completed modules
                            collect_list2.addAll(FileHandling.csvReading("completed_modules.csv"));  // reads and adds all content of completed_modules in a list
                            collect_list2.add(first_list.get(1)); // adds the topmost module of queue
                            FileHandling.csvWriting("completed_modules.csv",collect_list2); // writes the entire list to completed_modules

                            first_list.remove(0); // removes header
                            first_list.remove(0); // removes topmost

                            CSVWriter cw = new CSVWriter(new FileWriter("queue.csv"));
                            collect_list.add(new String[]{"Modules","Type","No. of Tasks", "Priority"});
                            collect_list.addAll(first_list);
                            cw.writeAll(collect_list);
                            cw.close();
                            p.try_again("Get a module again?", "Invalid input.", 4);
                        } else if (choice == 0) {
                            p.restart();
                        } else {
                            System.out.println("Invalid input.");
                        }
                    }
                } catch (IOException | NumberFormatException | InputMismatchException e) {
                    System.out.println("Invalid Input.");
                }
            }
        }
        else {
            System.out.println("\t\tQueue is empty");
            p.restart();
        }
    }

    static void make_csv_files() throws IOException {
        File f = new File("all_modules.csv");
        if (!f.exists()) {
            CSVWriter cw = new CSVWriter(new FileWriter("all_modules.csv"));
            cw.writeNext(new String[]{"Modules","Type","No. of Tasks", "Priority"});
            cw.close();
        }

        File f2 = new File("completed_modules.csv");
        if (!f2.exists()) {
            CSVWriter cw3 = new CSVWriter(new FileWriter("completed_modules.csv"));
            cw3.writeNext(new String[]{"Modules","Type","No. of Tasks", "Priority"});
            cw3.close();
        }

        File f3 = new File("queue.csv");
        if (!f3.exists()) {
            CSVWriter cw2 = new CSVWriter(new FileWriter("queue.csv"));
            cw2.writeNext(new String[]{"Modules","Type","No. of Tasks", "Priority"});
            cw2.close();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        make_csv_files();
        main2();
    }
}