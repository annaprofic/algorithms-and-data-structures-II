import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Graph {
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static Map<List<Integer>, Integer> paths = new HashMap<>();
    private static Stack<Integer> path = new Stack<>();
    private static boolean[] hasChild;
    private static int[][] adjacencyMatrix;
    private static int[] times;
    private static int size;
    private static int time = 0;

    private static void adjacencyMatrix() throws IOException{
        adjacencyMatrix = new int[size][size];
        hasChild = new boolean[size];

        StringBuilder childIndex = new StringBuilder();

        for(int i = 0; i < size; i++) {
            String newLine = input.readLine();
            String[] newLoadedLine = newLine.split(" ");

            hasChild[i] = Integer.parseInt(newLoadedLine[1]) > 0;

            for(int j = 2; j < newLoadedLine.length; j++){
                int index = 0;

                while(newLoadedLine[j].charAt(index) != '(') {
                    childIndex.append(newLoadedLine[j].charAt(index));
                    index++;
                }

                adjacencyMatrix[i][Integer.parseInt(childIndex.toString())] = 1;
                childIndex.setLength(0);

            }
        }
    }

    private static void printMatrix(){
        for(int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private static void setTimes() throws IOException{
        times = new int[size];
        String line = input.readLine();
        String[] loadedLine = line.split(" ");
        int skip = Integer.parseInt(loadedLine[1]) + 1;

        for(int i = 0; i < skip; i++) input.readLine();

        for(int i = 0; i < size; i++) {
            line = input.readLine();
            loadedLine = line.split(" ");

            times[i] = Integer.parseInt(loadedLine[0]);
            System.out.println("T" + i + " -> " + times[i]);
        }
    }


    private static void allPaths(int currIndex){
        path.push(currIndex);

        for(int i = 1; i < size; i++) {
            if (adjacencyMatrix[currIndex][i] == 1) {
                allPaths(i);
                path.pop();
            }
        }

        if(!hasChild[currIndex]) {
            path.forEach(i -> {
                time += times[i];
            });

            List<Integer> list = new ArrayList<>(path);

            System.out.println("path: " + list + " time: " + time);

            paths.put(list, time);
            time = 0;
        }
    }

    private static void longestPathSearch() {
        for(int i = 0; i < size; i++) {
            int maxTime = 0;
            int remove = 0;

            for (Map.Entry<List<Integer>, Integer> entry : paths.entrySet()) {
                if (maxTime < entry.getValue()) {
                    maxTime = entry.getValue();
                    remove = entry.getKey().get(0);
                }
            }
            System.out.print(remove + " ");

            for (Map.Entry<List<Integer>, Integer> entry : paths.entrySet()) {
                if (!entry.getKey().isEmpty() && entry.getKey().get(0) == remove) {
                    entry.setValue(entry.getValue() - times[remove]);
                    entry.getKey().remove(0);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String line = input.readLine();
        String[] loadedLine = line.split(" ");
        size = Integer.parseInt(loadedLine[1]);

        adjacencyMatrix();

        System.out.println("Adjacency matrix of input graph: ");
        printMatrix();

        System.out.println("Time info: ");
        setTimes();

        System.out.println();
        allPaths(0);

        System.out.println("\nOutput: ");
        longestPathSearch();

    }
}
