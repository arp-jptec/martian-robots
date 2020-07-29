public class MartianRobots {

    private int[][] grid;

    public MartianRobots(int upperX, int upperY){
        grid = new int[++upperX][++upperY];
    }

    public void instruct(RobotPosition robotPosition, String instruction){
        for(char command : instruction.toCharArray()){
           if(!canMove(robotPosition,command))
               return;
        }
        System.out.println(robotPosition);
    }

    private boolean canMove(RobotPosition robotPosition, char command){
        switch (command){
            case 'L':
                robotPosition.direction = robotPosition.direction.left;
                return true;
            case 'R':
                robotPosition.direction = robotPosition.direction.right;
                return true;
            case 'F': {
                int nextX = robotPosition.x + robotPosition.direction.xMove;
                int nextY = robotPosition.y + robotPosition.direction.yMove;
                try{
                    grid[nextX][nextY];
                    robotPosition.x = nextX;
                    robotPosition.y = nextY;
                    return true;
                }catch (ArrayIndexOutOfBoundsException aie){
                    if(grid[robotPosition.x][robotPosition.y] == 0){
                        System.out.print(robotPosition);
                        System.out.println(" LOST");
                        //Leave scent (-1) when robot lost
                        grid[robotPosition.x][robotPosition.y] = -1;
                        return false;
                    }
                    else
                        return true;

                }
            }
            default:
                throw new IllegalArgumentException("Invald instruction")

        }
    }


    class RobotPosition{
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
        NORTH('N',WEST,EAST,0,1),
        EAST('E',NORTH,SOUTH,1,0),
        SOUTH('S',EAST,WEST,0,-1),
        WEST('W',SOUTH,NORTH,-1,0);

        char label;
        Direction left;
        Direction right;
        int xMove;
        int yMove;

        private Direction(char label, Direction left, Direction right,int xMove, int yMove){
            this.label = label;
            this.left = left;
            this.right = right;
            this.xMove = xMove;
            this.yMove = yMove;
        }

        public Direction getDirectionByLabel(char label){
            for(Direction d : values())
                if(d.label == label)
                    return d;
            throw new IllegalArgumentException("Invalid direction "+label);
        }
    }



}