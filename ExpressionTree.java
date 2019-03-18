import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionTree {
    private ArrayList<String> children;
    ArrayList<String> expression = new ArrayList<String>();


    private void genTree() throws IOException  {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the number of parents of your tree: ");
        int treeSize = Integer.valueOf(in.readLine());
        System.out.println("Please enter adjacency list of your tree like: \n1 2 4\n2 3 5 6 \n" +
                "where 1 / 2 -> parent, a 2 4 / 3 5 6 -> his children");

        for (int i = 0; i < treeSize; i++) {
            int top = 0;

            String inputLine = in.readLine();
            children = new ArrayList<String>(Arrays.asList(inputLine.split(" ")));
            top = Integer.parseInt(children.get(0));
            children.remove(0);

            String topString = String.valueOf(top);

            if(expression.contains(topString)){
                int index = expression.indexOf(topString);
                index++;
                expression.add(index, "(");
                index ++;
                for(int k = 0; k < children.size(); k++, index++) {
                    expression.add(index, String.valueOf(children.get(k)));
                }
                expression.add(index, ")");
            }
            else {
                expression.add(topString);
                expression.add("(");
                for (String aChildren : children) {
                    expression.add(String.valueOf(aChildren));
                }
                expression.add(")");
            }
            children.clear();

        }

    }

    private void genExpression(){
        System.out.println("\nExpression Tree : ");
        for (String anExpression : expression) {
            System.out.print(anExpression);
        }
    }

    private void depthFirstSearch(){
        ArrayList<String> brackets = new ArrayList<String>(Arrays.asList(")", "("));
        expression.removeAll(brackets);
        System.out.println("\nDepth-first Search: ");
        for (String anExpression : expression) {
            System.out.print(anExpression + " ");
        }
    }


    public static void main(String[] args) throws IOException {
        ExpressionTree tree = new ExpressionTree();
        tree.genTree();
        tree.genExpression();
        tree.depthFirstSearch();
    }
}
