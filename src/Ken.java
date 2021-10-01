import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Ken {
   /* public static class Tasks {
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
    }*/
    public static void main(String[] args){
        /*ArrayList<List<Tasks>> first_list = new ArrayList<>();
        List<Tasks> second_list = new ArrayList<>();

        second_list.add(new Tasks("Ken","Epic",2,1));
        second_list.add(new Tasks("Dan","Epic",2,3));
        second_list.add(new Tasks("Gayle","Epic",1,1));
        second_list.add(new Tasks("Marithe","Epic",1,4));

        //second_list.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        first_list.add(second_list);

        Comparator<Tasks> comp = Comparator.comparing(Tasks::getNo_task);
        Collections.sort(second_list, comp);
        Comparator<Tasks> comp2 = Comparator.comparing(Tasks::getPriority);
        Collections.sort(second_list, comp2);
        System.out.println(second_list.get(0));

        CSVWriter writer = new CSVWriter(new FileWriter("ken.csv"));
        *//*List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] { "Name", "Class", "Marks" });
        data.add(new String[] { "Ken", "10", "620" });
        data.add(new String[] { "Suraj", "10", "630" });
        writer.writeAll(data);*//*
        String[] data2 = { "pia", "10", "630" };
        String[] data3 = { "case", "10", "630" };
        writer.writeNext(data2);
        writer.writeNext(data3);

        // closing writer connection
        writer.close();

        FileReader filereader = new FileReader("C:\\Users\\Enzo\\IdeaProjects\\TechknowNotion\\ken.csv");

        try (CSVReader csvReader = new CSVReader(filereader)) {
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        }
*/
        String format = "|%1$-30s|%2$-10s|%3$-20s|\n";
        System.out.format(format, "Adklasjdklasjddsakldkasld", "AA", "AAA");
        System.out.format(format, "B", "", "BBBBB");
        System.out.format(format, "C", "CCCCC", "CCCCCCCC");
    }
}
