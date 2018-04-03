import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;


public class Main {
    private final static String INPUT_DATA_SECTION_HEAD="# Section:";
    private final static String FLIGHT_ARRIVAL ="ARRIVAL";
    private final static String DEST_BAGGAGE_CLAIM ="BaggageClaim";
    private final static String SINGLE_WHITE_SPACE=" ";
    public static void main(String [] args){
        Scanner scan=null;
        if(args.length >0) {
            File inputDataFile=new File(args[0].trim());
            if(inputDataFile.exists()){
                try {
                    scan = new Scanner(inputDataFile);
                }catch (FileNotFoundException fnfex){
                    scan=promptAndParse();
                }
            }else{
               scan=promptAndParse();
            }
        }else
            scan=promptAndParse();
        if(scan != null){
            List<DirectedEdge> edges= parseInputGraph(scan);
            userInterface userInterface1;
            Map<String,String> departuresMap=parseInputDepartures(scan);
            List<Bag> bagList = parseInputBags(scan);
            scan.close();
            for(Bag bag:bagList){
                String bagId=bag.getId();
                String entryGate=bag.getEntryGate();
                String flight = bag.getFlight();

                String destGate;
                if(flight.equals(FLIGHT_ARRIVAL)){
                    destGate=DEST_BAGGAGE_CLAIM;
                }else{
                    destGate=departuresMap.get(flight);
                }
                String pathLine=userInterface1.findShortestPath(entryGate,destGate,edges);

                System.out.println(bagId+SINGLE_WHITE_SPACE+pathLine);
            }
        }

    }


    private static Scanner promptAndParse(){
        System.out.println("Please input the data here:");
        Scanner scan = new Scanner(System.in);
        return scan;
    }

    private static List<DirectedEdge> parseInputGraph(Scanner scanner){
        String graphSection=scanner.nextLine();
        if(!graphSection.startsWith(INPUT_DATA_SECTION_HEAD)){
            throw new IllegalArgumentException("Illegal arguments or inputs. Please refer to readme for the input data format.");
        }
        String next=scanner.nextLine();
        List<DirectedEdge> edges=new ArrayList<>();
        while (!next.startsWith(INPUT_DATA_SECTION_HEAD)){
            String[] parts=next.trim().split("\\s+");
            if(parts.length>=3) {
                DirectedEdge directedEdge = new DirectedEdge(parts[0],parts[1],Integer.valueOf(parts[2]));
                edges.add(directedEdge);
                //Since it is bi-direction edge, will add another direction edge too.
                DirectedEdge rDirectedEdge = new DirectedEdge(parts[1],parts[0],Integer.valueOf(parts[2]));
                edges.add(rDirectedEdge);
            }else{
                throw new IllegalArgumentException("Illegal arguments or inputs. Please refer to readme for the input data format.");
            }
            next=scanner.nextLine();
        }
        return edges;
    }

    private static Map<String,String> parseInputDepartures(Scanner scanner){
        String next=scanner.nextLine();
        Map<String,String> departuresMap=new HashMap<>();
        while(!next.startsWith(INPUT_DATA_SECTION_HEAD)){
            String [] parts=next.trim().split("\\s+");
            if(parts.length >= 2){
                departuresMap.put(parts[0],parts[1]);
            }else{
                throw new IllegalArgumentException("Illegal arguments or inputs. Please refer to readme for the input data format.");
            }
            next=scanner.nextLine();
        }
        return departuresMap;
    }

    private static List<Bag> parseInputBags(Scanner scanner){
        String next ;
        List<Bag> bagList= new ArrayList<>();
        do{
            next = scanner.nextLine();
            String [] parts = next.trim().split("\\s+");
            if(parts.length >=3){
                Bag bag= new Bag(parts[0],parts[1],parts[2]);
                bagList.add(bag);
            }else{
                scanner.close();
                break;
            }
        }while(scanner.hasNextLine());
        return bagList;
    }
}
