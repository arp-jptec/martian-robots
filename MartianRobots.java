import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MartianRobots {

    private int[][] grid;
    private List<String> outputList;

    public MartianRobots(int upperX, int upperY){
        grid = new int[++upperX][++upperY];
        outputList = new ArrayList<>();
    }

    void instruct(RobotPosition robotPosition, String instruction){
        for(char command : instruction.toCharArray()){
           if(!canMove(robotPosition,command))
               return;
        }
        outputList.add(robotPosition.toString());
    }

    private boolean canMove(RobotPosition robotPosition, char command){
        switch (command){
            case 'L':
                robotPosition.direction = Direction.getDirectionByLabel(robotPosition.direction.left);
                return true;
            case 'R':
                robotPosition.direction = Direction.getDirectionByLabel(robotPosition.direction.right);
                return true;
            case 'F': {
                int nextX = robotPosition.x + robotPosition.direction.xMove;
                int nextY = robotPosition.y + robotPosition.direction.yMove;
                try{
                    int nextSquare = grid[nextX][nextY];
                    robotPosition.x = nextX;
                    robotPosition.y = nextY;
                    return true;
                }catch (ArrayIndexOutOfBoundsException aie){
                    if(grid[robotPosition.x][robotPosition.y] == 0){
                        outputList.add(robotPosition.toString()+" LOST");
                        //Leave scent (-1) when robot lost
                        grid[robotPosition.x][robotPosition.y] = -1;
                        return false;
                    }
                    else
                        return true;

                }
            }
            default:
                throw new IllegalArgumentException("Invald instruction");

        }
    }

    void printOutput(){
        outputList.forEach(System.out::println);
    }

    public static void main(String[] args){
        int lineCount  = 1;
        RobotPosition robotPosition = null;
        Scanner scanner = new Scanner(System.in);
        String[] coordinates = scanner.nextLine().split(" ");
        MartianRobots martianRobots = new MartianRobots(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.trim().length() == 0) {
                martianRobots.printOutput();
                System.exit(0);
            }
            if(lineCount % 2 == 1) {
                String[] positionData = line.split(" ");
                robotPosition = new RobotPosition(Integer.parseInt(positionData[0]), Integer.parseInt(positionData[1]),Direction.getDirectionByLabel(positionData[2].charAt(0)));
            }
            else {
                martianRobots.instruct(robotPosition,line);
            }
            lineCount++;

        }
    }


    static class RobotPosition{
        int x;
        int y;
        Direction direction;

        RobotPosition(int x, int y, Direction direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public String toString(){
            return x+" "+y+" "+direction.label;
        }
    }

    enum Direction {
        NORTH('N','W','E',0,1),
        EAST('E','N','S',1,0),
        SOUTH('S','E','W',0,-1),
        WEST('W','S','N',-1,0);

        char label;
        char left;
        char right;
        int xMove;
        int yMove;

        private Direction(char label, char left, char right,int xMove, int yMove){
            this.label = label;
            this.left = left;
            this.right = right;
            this.xMove = xMove;
            this.yMove = yMove;
        }

        public static Direction getDirectionByLabel(char label){
            switch (label){
                case 'N':
                    return NORTH;
                case 'E':
                    return EAST;
                case 'S':
                    return SOUTH;
                case 'W':
                    return WEST;
                default:
                    throw new IllegalArgumentException("Invalid direction "+label);
            }
        }
    }



}