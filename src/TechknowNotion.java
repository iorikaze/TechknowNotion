import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

//DONE
class Preview {
    public List<Object> lst1;

    public Preview(List<Object> lst1) {
        this.lst1 = lst1;
    }

    void set_view(ArrayList<Object> lst1) {
        this.lst1 = lst1;
    }

    void view2() {
        System.out.println(this.lst1.get(0) + "\t       " + this.lst1.get(1) +
                "\t\t\t   " + this.lst1.get(2) + "\t\t\t\t  " + this.lst1.get(3));
    }
}

class OtherFunctions {

    //DONE
    void iteration(ArrayList<String[]> ln) {
        System.out.println("Modules\t\t\tType\t\t\tNo. of Tasks\t\t\tPriority");
        for (int x = 0; x < ln.size(); x++) { // iterate per row
            List<Object> list4 = new ArrayList<>();
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

    // DONE check if functioning right
    Boolean is_empty(String x) throws IOException {
        CSVReader cr = new CSVReader(new FileReader(x));
        ArrayList<String[]> first_list = new ArrayList<>();
        String[] second_list;
        while ((second_list = cr.readNext()) != null) {
            first_list.add(second_list);
        }

        if (first_list.size() == 1) {
            return false;
        }
        else {
            return true;
        }
    }

    //NOT DONE - unknown
    void try_again(String ques, String msg, int loc){
        while (true){
            try {
                TechknowNotion p = new TechknowNotion();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.printf("%s YES(1) or NO(0):", ques);
                int choice = Integer.parseInt(br.readLine());
                if (choice == 0){
                    restart();
                }
                else if (choice == 1){
                    if (loc == 1){
                        p.input_module_details();
                    } else if (loc == 2){
                        p.view_module();
                    } else if (loc == 3){
                        p.schedule_module();
                    } else if (loc == 4){
                        p.get_a_module();
                    }
                }
            } catch (Exception e ) {
                System.out.println(msg);
            }
        }
    }

    //NOT DONE - unknown
    void restart() {
        while (true) {
            try {
                TechknowNotion p = new TechknowNotion();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\nGo back to main menu? YES(1) or NO(0): ");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 0) {
                    System.exit(0);
                }
                else if (choice == 1) {
                    p.main2();
                }
                else {
                    System.out.println("Invalid Input");
                }
            }
            catch (Exception e) {}
        }
    }

    //DONE
    int input_validation(String msg){
        while (true){
            try {
                System.out.println(msg);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int i = Integer.parseInt(br.readLine());
                if (i < 1){
                    System.out.println("Input must positive integer\n");
                }
                else {
                    return  i;
                }

            } catch (InputMismatchException | IOException | NumberFormatException e){
                System.out.println("Input must positive integer\n");
            }
        }
    }
}

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

    @Override
    public String toString() {
        return "Tasks [module name=" + module_name + ", type=" + type + ", no. of task=" + no_task + ", priority= " + priority + "]";
    }
}



public class TechknowNotion {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void clear() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main2() throws IOException, InterruptedException {
        clear();
        System.out.println("\tTECHKNOW'S NOTION\n");
        System.out.println("1. Input Module Details\n2. View Modules\n3. Schedule Modules\n4. Get a Module\n5. Exit\n");
        while (true) {
            try {
                System.out.print("Please enter a number:");
                int choice = Integer.parseInt(br.readLine());
                if (choice == 1) {
                    input_module_details(); //done with bugs in exceptions
                }
                else if (choice == 2) {
                    view_module(); //file handling
                }
                else if (choice == 3) {  //MARITHE - CURRENT //done in file handling but some errors in some functions
                    schedule_module();   //is_not_empty
                }
                else if (choice == 4) { //file handling
                    get_a_module();
                }
                else if (choice == 5) { //done
                    System.exit(0);
                }
                else {
                    System.out.println("Input must be a positive integer and must be within the range.");
                }
             }
            catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Input must be an integer from 1 to 5");
            }
        }
    }


    static void input_module_details() throws IOException, InterruptedException {
        // Goes to Menu 1
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

            //read file; add to new list;
            CSVReader cr = new CSVReader(new FileReader("all_modules.csv"));
            ArrayList<String[]> first_list = new ArrayList<>();
            String[] second_list;
            while ((second_list = cr.readNext()) != null) {
                first_list.add(second_list);
            }
            cr.close();

            //write
            CSVWriter cw = new CSVWriter(new FileWriter("all_modules.csv"));
            first_list.add(new String[]{module_name,type,String.valueOf(tasks), String.valueOf(priority)});
            cw.writeAll(first_list);
            cw.close();

            CSVReader cr2 = new CSVReader(new FileReader("queue.csv"));
            ArrayList<String[]> first_list2 = new ArrayList<>();
            String[] second_list2;
            while ((second_list2 = cr2.readNext()) != null) {
                first_list2.add(second_list2);
            }
            cr2.close();

            //write
            CSVWriter cw2 = new CSVWriter(new FileWriter("queue.csv"));
            first_list2.add(new String[]{module_name,type,String.valueOf(tasks), String.valueOf(priority)});
            cw2.writeAll(first_list2);
            cw2.close();

            System.out.println("---Module Details Saved---");
            //possible error
            p.try_again("Make new module details?", "Invalid input", 1);
        }
    }


    static void view_module() throws IOException, InterruptedException{
        clear();
        System.out.println("\tVIEW MODULES\n");
        System.out.println("1. Completed Modules\n2. All Modules\n3. Go Back\n");
        while (true) {
            try {
                System.out.println("Please enter a number: ");
                int choice = Integer.parseInt(br.readLine());
                if (choice < 1 && choice > 3) {   //same logic error nung sa schedule_modules
                    System.out.println("Invalid input");
                } else {
                    OtherFunctions p = new OtherFunctions();
                    if (choice == 1) {
                        clear();
                        System.out.println("\t\t\t\t\tVIEW MODULES");
                        System.out.println("\t\t\t\tLIST OF ALL COMPLETED MODULES\n");
                        if (p.is_empty("completed_modules.csv")) {
                            CSVReader cr = new CSVReader(new FileReader("completed_modules.csv"));
                            ArrayList<String[]> first_list2 = new ArrayList<>();
                            String second_list2[];
                            while ((second_list2 = cr.readNext()) != null) {
                                first_list2.add(second_list2);
                            }
                            p.iteration(first_list2);
                            cr.close();
                            p.try_again("View module again?", "Invalid input.", 2);
                        } else {
                            System.out.println("\t\t\t\t   No completed module yet");
                            p.try_again("View module again?", "Invalid input.", 2);
                        }
                    } else if (choice == 2){
                        clear();
                        System.out.println("\t\t\t\t\tVIEW MODULES");
                        System.out.println("\t\t\t\t\tALL MODULES\n");
                        if (p.is_empty("all_modules.csv")) {
                            CSVReader cr = new CSVReader(new FileReader("all_modules.csv"));
                            ArrayList<String[]> first_list2 = new ArrayList<>();
                            String second_list2[];
                            while ((second_list2 = cr.readNext()) != null) {
                                first_list2.add(second_list2);
                            }
                            p.iteration(first_list2);
                            cr.close();
                            p.try_again("View module again?", "Invalid input.", 2);
                        } else {
                            System.out.println("\t\t\t\t\tNo module yet");
                            p.try_again("View module again?", "Invalid input.", 2);
                        }
                    } else if (choice == 3){
                        main2();
                        p.restart();
                    }
                }
            }
            catch (InputMismatchException e) {
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
                System.out.println("Please enter a number: ");
                int choice = Integer.parseInt(br.readLine());
                //error
                if ((choice  <1) && (choice > 2)) {
                    System.out.println("Invalid input");
                } else {
                    if (choice == 1) {
                        clear();
                        System.out.println("\t\t\t\t\tSCHEDULE MODULES");
                        System.out.println("\t\t\t\t   CREATE SCHEDULE OF MODULES\n");
                        if (p.is_empty("queue.csv")) { // may error

                            CSVReader cr = new CSVReader(new FileReader("queue.csv"));
                            ArrayList<String[]> first_list = new ArrayList<>();
                            List<Tasks> sort_list = new ArrayList<>();
                            List<String> indiv_list;
                            List<List<String>> collect_list = new ArrayList<>();
                            List<String[]> queue_list = new ArrayList<>();
                            String second_list[];
                            while ((second_list = cr.readNext()) != null) {
                                first_list.add(second_list);
                            }
                            cr.close();

                            String[] split1,split2,split3,split4;

                            for (int i = 0; i < first_list.size(); i++) {
                                split1 = first_list.get(i)[0].split("\\t", 0);
                                split2 = first_list.get(i)[1].split("\\t", 0);
                                split3 = first_list.get(i)[2].split("\\t", 0);
                                split4 = first_list.get(i)[3].split("\\t", 0);
                                indiv_list = Arrays.asList(new String[]{split1[0],split2[0],split3[0],split4[0]});
                                collect_list.add(indiv_list);
                            }

                            for (int i = 1; i < collect_list.size(); i++) {
                                sort_list.add(new Tasks(collect_list.get(i).get(0),
                                        collect_list.get(i).get(1),
                                        Integer.parseInt(collect_list.get(i).get(2)),
                                        Integer.parseInt(collect_list.get(i).get(3))));
                            }

                            Comparator<Tasks> comp = Comparator.comparing(Tasks::getNo_task);
                            Collections.sort(sort_list, comp);
                            Comparator<Tasks> comp2 = Comparator.comparing(Tasks::getPriority);
                            Collections.sort(sort_list, comp2);

                            CSVWriter cw = new CSVWriter(new FileWriter("queue.csv"));
                            queue_list.add(new String[]{"Modules","Type","No. of Tasks", "Priority"});
                            for (int i = 0; i < sort_list.size(); i++) {
                                queue_list.add(new String[]{sort_list.get(i).module_name, sort_list.get(i).type,
                                        String.valueOf(sort_list.get(i).no_task), String.valueOf(sort_list.get(i).priority)});
                            }
                            cw.writeAll(queue_list);
                            cw.close();

                            CSVReader cr2 = new CSVReader(new FileReader("queue.csv"));
                            ArrayList<String[]> first_list2 = new ArrayList<>();
                            String second_list2[];
                            while ((second_list2 = cr2.readNext()) != null) {
                                first_list2.add(second_list2);
                            }

                            p.iteration(first_list2);
                            cr2.close();
                            p.try_again("Go back to schedule module?", "Invalid input.", 3);
                        }
                    } else if (choice == 2){
                        main2();
                        p.restart();
                    }
                }
            }
            catch (InputMismatchException e) {
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
        int choice = 0;
        try {
            if (p.is_empty("queue.csv")) {
                CSVReader cr = new CSVReader(new FileReader("queue.csv"));
                ArrayList<String[]> first_list2 = new ArrayList<>();
                ArrayList<String[]> collect_list2 = new ArrayList<>();
                ArrayList<String[]> collect_list3 = new ArrayList<>();
                String second_list2[];
                while ((second_list2 = cr.readNext()) != null) {
                    first_list2.add(second_list2);
                }
                cr.close();
                p.iteration(first_list2);
                while (true){
                    System.out.println("Mark as complete the topmost module? YES(1) or NO(0): ");
                    choice = Integer.parseInt(br.readLine());
                    if (choice < 0 && choice > 1) {       //logic error
                        System.out.println("Invalid input");
                    } else {
                        if (choice == 1){  //writing to completed modules
                            CSVWriter cw3 = new CSVWriter(new FileWriter("completed_modules.csv"));
                            collect_list3.add(new String[]{"Modules","Type","No. of Tasks", "Priority"});
                            collect_list3.add(first_list2.get(1));
                            cw3.writeAll(collect_list3);
                            cw3.close();

                            first_list2.remove(0);
                            first_list2.remove(0);
                            CSVWriter cw4 = new CSVWriter(new FileWriter("queue.csv"));
                            collect_list2.add(new String[]{"Modules","Type","No. of Tasks", "Priority"});
                            collect_list2.addAll(first_list2);
                            cw4.writeAll(collect_list2);
                            cw4.close();

                        } else if (choice == 0) {
                            p.restart();
                        }
                        p.try_again("Get a module again?", "Invalid input.", 4);
                    }
                }
            }
            else {
                System.out.println("\t\tQueue is empty");
                p.restart();
            }

        } catch (IOException | NumberFormatException | InputMismatchException e) { //Depnde sayo if ano exception lalagay mu
            e.printStackTrace();
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


    public static void main(String args[]) throws IOException, InterruptedException {
        make_csv_files();
        main2();
    }
}











