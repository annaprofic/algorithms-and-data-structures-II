import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Graph {
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] adjacencyMatrix;
    private static int size;
    private static int[] numberOfParents;

    private static void adjacencyMatrix() throws IOException{
        adjacencyMatrix = new int[size][size];
        numberOfParents = new int[size];

        StringBuilder childIndex = new StringBuilder();

        for(int i = 0; i < size; i++) {
            String newLine = input.readLine();
            String[] newLoadedLine = newLine.split(" ");

            for(int j = 2; j < newLoadedLine.length; j++){
                int index = 0;

                while(newLoadedLine[j].charAt(index) != '(') {
                    childIndex.append(newLoadedLine[j].charAt(index));
                    index++;
                }

                int element = Integer.parseInt(childIndex.toString());
                adjacencyMatrix[i][element] = 1;
                numberOfParents[element]++;
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

    private static void topologicalSort(){

        List<Integer> addLater = new ArrayList<>();


        for(int index = 0; index < size; index++) {
            if (numberOfParents[index] == 0) {
                System.out.print(index + " ");
                for (int j = 0; j < size; j++) {
                    if (adjacencyMatrix[index][j] == 1) {
                        numberOfParents[j]--;
                    }
                }
            } else addLater.add(index);
        }

        for(int i : addLater) {
            System.out.print(i + " ");
        }

    }

    private static void printParents(){
        System.out.println("Number of parents for vertex: ");
        for(int i = 0; i < size; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
        for(int i = 0; i < size; i++) {
            System.out.print(numberOfParents[i] + " | ");
        }
    }

    public static void main(String[] args) throws IOException {
        String line = input.readLine();
        String[] loadedLine = line.split(" ");
        size = Integer.parseInt(loadedLine[1]);

        adjacencyMatrix();

        System.out.println("Adjacency matrix of input graph: ");
        printMatrix();

        printParents();
        System.out.println("\nTopological sort: ");
        topologicalSort();
    }
}
